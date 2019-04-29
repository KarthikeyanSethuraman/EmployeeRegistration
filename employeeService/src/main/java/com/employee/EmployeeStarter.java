package com.employee;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
@ComponentScan(basePackages={"com.*"})
public class EmployeeStarter extends SpringBootServletInitializer {

	private static final Logger logg= LoggerFactory.getLogger(EmployeeStarter.class);
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		logg.info("Employee Service Application is starting on External Tomcat Server ....");
		return applicationBuilder.sources(EmployeeStarter.class);
	}

	public static void main(String[] args) {
		logg.info("Employee Service Application is starting on Embedded Tomcat Server ....");
		SpringApplication.run(EmployeeStarter.class, args);
	}
	
    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
    	logg.info("Setting Cross orgin setup for allow spring boot servlet");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.addAllowedHeader("Access-Control-Allow-Headers");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
