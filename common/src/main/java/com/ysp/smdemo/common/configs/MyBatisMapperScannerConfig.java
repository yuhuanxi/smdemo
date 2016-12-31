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

import com.ysp.smdemo.common.utils.DevRepository;
import com.ysp.smdemo.common.utils.ProdRepository;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: shipeng.yu
 * @time: 2016年12月21日 下午9:19
 * @version: 1.0
 * @since: 1.0
 * @description: Mybatis 扫描配置
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    /**
     * 开发环境数据源
     *
     * @return 返回开发环境 mybatis 扫描配置信息
     */
    @Bean
    public MapperScannerConfigurer devMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("devSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(DevRepository.class);
        mapperScannerConfigurer.setBasePackage("com.ysp.smdemo.dao.mappers"); // 多个package用,隔开
        return mapperScannerConfigurer;
    }

    /**
     * 生产环境数据源
     *
     * @return 返回生产环境 mybatis 扫描配置信息
     */
    @Bean
    public MapperScannerConfigurer prodMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("prodSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(ProdRepository.class);
        mapperScannerConfigurer.setBasePackage("com.ysp.smdemo.dao.mappers"); // 多个package用,隔开
        return mapperScannerConfigurer;
    }

    /**
     * 默认不配置注解则为开发环境数据源
     *
     * @return 返回默认的开发环境 mybatis 扫描配置信息
     */
    @Bean
    public MapperScannerConfigurer defaultMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("devSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.ysp.smdemo.dao.mappers"); // 多个package用,隔开
        return mapperScannerConfigurer;
    }
}
