package com.spring.api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableWebSecurity
//@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// **********************User and password configured in class and its bean
	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	  auth.inMemoryAuthentication()
//	    .withUser("user")
//	    .password(passwordEncoder().encode("user"))
//	    .roles("ADMIN");
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	   return new BCryptPasswordEncoder();
//	}

	// ***********************Ignore CSS, JS, /images files
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}

	// ***********************
	
//	@Override
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	protected void configureMemory(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
//				.authorizeRequests().antMatchers("/api/userlog").permitAll().antMatchers("/api/users").permitAll()
//				.antMatchers("/api/**").authenticated()
//				// .antMatchers("/api/**").hasRole("USER")
//				.anyRequest().authenticated().and().httpBasic().realmName("CRM_REALM");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().hasAnyRole("admin","user")
		.and()
		.authorizeRequests().antMatchers("/api/login").permitAll()
		.and()
		.formLogin().loginPage("/api/login").loginProcessingUrl("/api/loginAction").permitAll()
		.and()
		.logout().logoutUrl("/api/logout").logoutSuccessUrl("/api/login").deleteCookies("auth_code", "JSESSIONID")
		.invalidateHttpSession(true).permitAll()
		//.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.csrf().disable();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
// 
//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
}
