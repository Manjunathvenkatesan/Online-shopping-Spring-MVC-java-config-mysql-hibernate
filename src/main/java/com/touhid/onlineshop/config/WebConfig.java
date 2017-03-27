package com.touhid.onlineshop.config;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.bind.support.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.touhid.onlineshop.validator.ProductFormValidator;

@EnableAspectJAutoProxy
@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.touhid.onlineshop")
@Import({ApplicationContext.class,SecurityConfiguration.class,WebFlowConfig.class})
@PropertySource("classpath:mysql-connection.properties")
public class WebConfig extends WebMvcConfigurerAdapter{

	
	
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		argumentResolvers.add(resolver());
	}
	
	@Bean
	public AuthenticationPrincipalArgumentResolver resolver(){
		
		return new AuthenticationPrincipalArgumentResolver();
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		
		ResourceBundleMessageSource source=new ResourceBundleMessageSource();
		source.setBasename("i18n/messages");
		source.setDefaultEncoding("UTF-8");
		
		return source;
	}
	
	@Bean
	public ProductFormValidator productFormValidator(){
		return new ProductFormValidator();
	}
	
	
	
	@Bean
	public LocaleResolver localeResolver(){
		
		SessionLocaleResolver resolver=new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		
		return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}
}
