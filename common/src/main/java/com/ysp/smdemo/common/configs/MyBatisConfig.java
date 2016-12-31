/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ysp.smdemo.common.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author: shipeng.yu
 * @time: 2016年12月21日 下午9:19
 * @version: 1.0
 * @since: 1.0
 * @description: Mybatis 基础配置
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    private static final Logger LOG = LogManager.getLogger(MyBatisConfig.class);

    @Autowired
    @Qualifier("devDataSource")
    DataSource devDataSource;   //结合 Qualifier 注解,让其按名称来匹配

    @Autowired
    @Qualifier("prodDataSource")
    DataSource prodDataSource;

    // 配置生产数据源
    @Bean(name = "devDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dev.druid")
    public DataSource devDataSource() {
        return new DruidDataSource();
    }

    // 配置生产数据源
    @Bean(name = "prodDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.prod.druid")
    public DataSource prodDataSource() {
        return new DruidDataSource();
    }

    // 配置开发环境 sqlSessionFactory
    @Bean(name = "devSqlSessionFactory")
    @Primary
    public SqlSessionFactory devSqlSessionFactoryBean(@Qualifier("devDataSource") DataSource dataSource) throws SQLException, IOException {
        return getSqlSessionFactory(dataSource);
    }

    // 配置生产环境 sqlSessionFactory
    @Bean(name = "prodSqlSessionFactory")
    public SqlSessionFactory prodSqlSessionFactoryBean(@Qualifier("prodDataSource") DataSource dataSource) throws SQLException, IOException {
        return getSqlSessionFactory(dataSource);
    }

    private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:com.ysp.smdemo.dao.xmls/*.xml")); // 之前版本使用 / 代替 .
        bean.setTypeAliasesPackage("com.ysp.smdemo.model");// 多个 model 用 , 隔开
        bean.setVfs(SpringBootVFS.class); // 不加这个的话,打成 jar 会加载不了类
        try {
            return bean.getObject();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Transaction 相关配置
     * 因为有两个数据源，所有使用ChainedTransactionManager把两个DataSourceTransactionManager包括在一起。
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dev = new DataSourceTransactionManager(devDataSource);
        DataSourceTransactionManager prod = new DataSourceTransactionManager(prodDataSource);
        return new ChainedTransactionManager(dev, prod);
    }
}
