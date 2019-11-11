package ru.cdek.test.task.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.cdek.test.task.service.Auth;

/**
 * @author Aleksey Rud
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("/webjars/")
				.resourceChain(false);
	}
	@Bean
	@Scope("singleton")
	public Auth authService(){
		return new Auth();
	}
}
