package com.builds.sprojects.sms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
		.antMatchers("/students/showForm*").hasAnyRole("PROFESSOR","HOD")
		.antMatchers("/students/save*").hasAnyRole("PROFESSOR","HOD")
		.antMatchers("/students/delete").hasRole("HOD")
		.antMatchers("/students/**").hasRole("LECTURER")
		.antMatchers("/resources/**").permitAll()
		.and()
		.formLogin()
		    .loginPage("/showMyLoginPage")
		    .loginProcessingUrl("/authenticateTheUser")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
