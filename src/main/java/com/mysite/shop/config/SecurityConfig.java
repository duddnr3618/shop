package com.mysite.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 로그인 시 경우
		http.formLogin()
				 .loginPage("/member/login")
				 .defaultSuccessUrl("/")
				 .usernameParameter("email")
				 .failureUrl("/member/login/error")
				 .and()
				 .logout()
				 .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				 .logoutSuccessUrl("/");
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		
		//암호화
		return new BCryptPasswordEncoder();
	}
	
}
