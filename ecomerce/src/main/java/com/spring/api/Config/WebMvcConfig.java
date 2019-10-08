package com.spring.api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.api.Dao.DaoAllListImpl;
import com.spring.api.Dao.DaoByIdImpl;
import com.spring.api.Dao.DaoGenericImpl;
import com.spring.api.Service.ServiceAllListImpl;
import com.spring.api.Service.ServiceById;
import com.spring.api.Service.ServiceGenerImpl;
 
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.api.*")
public class WebMvcConfig implements WebMvcConfigurer {
 
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver createMultipartResolver() {
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        return resolver;
//    }
    
    @Bean(name = "serviceImpl")
    public ServiceAllListImpl serviceImpl() {
    	return new ServiceAllListImpl();
    }
    
    @Bean(name = "byIdImpl")
    public ServiceById byIdImpl() {
    	return new ServiceById();
    }
    
    @Bean(name = "genericImpl")
    public ServiceGenerImpl genericImpl() {
    	return new ServiceGenerImpl();
    }
    
    @Bean(name = "listDao")
    public DaoAllListImpl listDao() {
    	return new DaoAllListImpl();
    }
    
    @Bean(name = "byIdDao")
    public DaoByIdImpl byIdDao() {
    	return new DaoByIdImpl();
    }
    
    @Bean(name = "daoGen")
    public DaoGenericImpl daoGen() {
    	return new DaoGenericImpl();
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    
//    @Bean
//    public UsersController usercontrol() {
//    	return new UsersController();
//    }
 
}
