package com.users.messages.management.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan
public class ViewResolverConfiguration implements WebMvcConfigurer {
	@Override
	  public void configureViewResolvers(ViewResolverRegistry registry) {
	      registry.jsp("/WEB-INF/views/", ".jsp");
	  }

	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	      registry.addViewController("/").setViewName("login");	      
	      registry.addViewController("/login").setViewName("login");
	      registry.addViewController("/secured/dashboard").setViewName("/secured/dashboard");
	  }
}
