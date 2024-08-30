package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import  org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@Configuration
//@EnableWebSecurity
//public class MyConfig extends WebSecurityConfigurerAdapter{
//
//	@Bean
//	public UserDetailsService getUserDetailsService()
//	{
//		return new UserDetailsServiceImpl();
//	}
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider()
//	{
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		
//		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
//	
////	configure method
//	
//	@Override
//	protected void congifure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.authenticationProvider(authenticationProvider());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http)throws Exception{
//	http.authorizeRequests.antMatchers("/admin/**").hasRole("ADMIN")
//	.antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//	}
//	
//	
//}

@Configuration
@EnableWebSecurity

public class MyConfig
{	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService getDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(getDetailsService());
		daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoauthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/**").permitAll()
		.and().formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/index")
		;
		return http.build();
		
	
	}
}
