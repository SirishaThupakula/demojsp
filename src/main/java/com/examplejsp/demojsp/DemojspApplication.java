package com.examplejsp.demojsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemojspApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemojspApplication.class, args);
	}

	@Bean
	public ViewResolver viewResolver() {
		final InternalResourceViewResolver r = new InternalResourceViewResolver();
		r.setPrefix("/WEB-INF/jsp/");
		r.setSuffix(".jsp");
		return r;
	}
}
