package com.ysp.smdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: shipeng.yu
 * @time: 2016年12月14日 下午1:11
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@SpringBootApplication
@ServletComponentScan
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
