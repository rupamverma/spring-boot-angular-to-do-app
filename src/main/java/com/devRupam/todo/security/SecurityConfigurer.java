package com.devRupam.todo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.devRupam.todo.service.PersonServices;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PersonServices personService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     
		auth.userDetailsService(personService);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/todo").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.formLogin();
		
		
		
	}
	
	
	
	
	
	
	
	

}
