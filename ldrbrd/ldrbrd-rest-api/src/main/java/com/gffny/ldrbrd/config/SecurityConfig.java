package com.gffny.ldrbrd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author John D. Gaffney | gffny.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 
	 */
	public SecurityConfig() {
		System.out.println("loading sec config");
	}

	/**
	 * 
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		// in-memory authentication
		auth.inMemoryAuthentication().withUser("gffny").password("gffny").roles("USER", "LEGEND");

		// using custom UserDetailsService DAO
		// auth.userDetailsService(new AppUserDetailsServiceDAO());

	}

	/**
	 * 
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		// Spring Security should completely ignore URLs ending with .html
				.antMatchers("/*.html");
	}

}
