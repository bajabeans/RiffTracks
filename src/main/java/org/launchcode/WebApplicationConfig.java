package org.launchcode;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.MultipartConfigElement;

import org.launchcode.AuthenticationInterceptor;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    // Create managed bean to allow autowiring
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationInterceptor() );
    }
    
	@SuppressWarnings("deprecation")
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("12800KB");
		factory.setMaxRequestSize("12800KB");
		return factory.createMultipartConfig();
	}
	
	@Bean 
	public UrlBasedViewResolver setupViewResolver(){
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
		
	}
    


}
