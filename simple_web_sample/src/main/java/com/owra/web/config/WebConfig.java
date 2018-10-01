package com.owra.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.owra.web.common.Interceptor.AuthInterceptor;

@Configuration
@ComponentScan("com.owra.web")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/main" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
	
	@Autowired
	AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/checkLoginUser")
        .excludePathPatterns("/common/authFalse")
        .excludePathPatterns("/error")
        .excludePathPatterns("/common/*")
        .excludePathPatterns("/login")
        .excludePathPatterns("/logout")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/img/**")
        .excludePathPatterns("*.js")
        .excludePathPatterns("*.css")
        .excludePathPatterns("*.ico")
        .excludePathPatterns("*.jpg");
        
    }
	
}
