package com.owra.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.owra.web"})
@MapperScan(value={"com.owra.web.example.mapper"})
public class SimpleWebSampleApplication extends SpringBootServletInitializer{
	
	private static Class<SimpleWebSampleApplication> applicationClass = SimpleWebSampleApplication.class;
	public static void main(String[] args) {
		SpringApplication.run(SimpleWebSampleApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
