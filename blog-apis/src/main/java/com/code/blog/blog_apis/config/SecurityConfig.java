package com.code.blog.blog_apis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.code.blog.blog_apis.security.CustomerUserDetailService;
import com.code.blog.blog_apis.security.JWTAuthenticationFilter;
import com.code.blog.blog_apis.security.JwtAuthenticationEntryPoint;

@Configuration
public class SecurityConfig 
{
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JWTAuthenticationFilter filter;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
//	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
//		http.csrf().disable().authorizeHttpRequests().anyRequest()
//			.authenticated().and().httpBasic();
//		
//		http.authenticationProvider(daoAuthenticationProvider());
//		
//		DefaultSecurityFilterChain filterChain= http.build();
//		return filterChain;
		
		 http.csrf(csrf -> csrf.disable())
         .authorizeHttpRequests()
         .requestMatchers("/api/v1/auth/login").permitAll()
         .anyRequest()
         .authenticated()
         .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 
		 http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		 return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		return new CustomerUserDetailService();
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(customerUserDetailService);
		dao.setPasswordEncoder(passwordEncoder());
		
		return dao;
	}
}
