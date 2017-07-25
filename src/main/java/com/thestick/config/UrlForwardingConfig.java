package com.thestick.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Handles forwarding the index.html to all valid URLs
 * 
 * @author Eddie
 */
@Configuration
public class UrlForwardingConfig extends WebMvcConfigurerAdapter {
	
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.addViewController( "/heroes" ).setViewName( "forward:/index.html" );
        registry.addViewController( "/overwatch" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
}
