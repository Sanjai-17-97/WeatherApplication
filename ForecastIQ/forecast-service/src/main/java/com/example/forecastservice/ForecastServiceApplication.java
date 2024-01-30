package com.example.forecastservice;

import com.example.forecastservice.filter.ForecastFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForecastServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ForecastFilter());
		bean.addUrlPatterns("/api/forecast/*");
		return bean;
	}
}
