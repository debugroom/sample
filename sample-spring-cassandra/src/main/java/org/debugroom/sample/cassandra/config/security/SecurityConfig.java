package org.debugroom.sample.cassandra.config.security;

import org.debugroom.sample.cassandra.pattern2.common.security.SampleFailureHandler;
import org.debugroom.sample.cassandra.pattern2.common.security.SampleSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/api/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.csrf().disable()
			.formLogin()
			.loginProcessingUrl("/authenticate")
			.loginPage("/login")
			.failureUrl("/login")
			.successHandler(new SampleSuccessHandler())
			.failureHandler(new SampleFailureHandler())
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}


	
}
