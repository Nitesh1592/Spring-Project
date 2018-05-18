package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com"})
public class WebMvcConfig implements WebMvcConfigurer{
	
	/**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
 
    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
    
    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
     
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }
    
//  @Bean
//  public InternalResourceViewResolver resolver() {
//     InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//     resolver.setViewClass(JstlView.class);
//     resolver.setPrefix("/WEB-INF/views/");
//     resolver.setSuffix(".jsp");
//     return resolver;
//  }
//
//  @Bean
//  public MessageSource messageSource() {
//     ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//     source.setBasename("messages");
//     return source;
//  }
//
//  @Override
//  public Validator getValidator() {
//     LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//     validator.setValidationMessageSource(messageSource());
//     return validator;
//  }
}
