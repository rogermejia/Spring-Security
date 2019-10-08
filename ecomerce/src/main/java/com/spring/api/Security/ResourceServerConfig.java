package com.spring.api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.spring.api.Entities.Role;
import com.spring.api.Service.IAllListService;

@Configuration
@EnableResourceServer

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	@Qualifier(value = "serviceImpl")
	public IAllListService listS;

	private static final String RESOURCE_ID = "my_rest_api";

	@Override

	public void configure(ResourceServerSecurityConfigurer resources) {

		resources.resourceId(RESOURCE_ID).stateless(false);

	}

	@Override

	public void configure(HttpSecurity http) throws Exception {
		String init = "hasRole('";
		String roleList ="";
		String closing = "')";
		String or = " or ";
		int z = listS.allRole().size();
		int s = 1;
		for(Role r: listS.allRole()) {
			roleList = roleList + init+r.getRolename();
			s++;
			if(s <= z) {
				roleList = roleList+closing+or;
			}
			
		}
		roleList = roleList+closing;
		System.out.println(roleList);
		
		http.requestMatchers().antMatchers("/api/**")
		.and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/products/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/userlog").permitAll()
		.antMatchers(HttpMethod.GET, "/api/role/**").access(roleList)
//		.antMatchers(HttpMethod.GET, "/api/role/**").access("hasRole('admin') or hasRole('User')")
//		.antMatchers(HttpMethod.GET, "/api/role/**").access("hasRole('admin') or hasRole('User')")
        .anyRequest().authenticated().and().exceptionHandling()
		.accessDeniedHandler(new OAuth2AccessDeniedHandler());

	}

//	@Override
//    public void configure(ResourceServerSecurityConfigurer config) {
//        config.tokenServices(tokenServices());
//    }
// 
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
// 
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
// 
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        return defaultTokenServices;
//    }
}
