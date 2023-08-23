package com.mysite.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		// ###################Spring Boot 3.X. #####################################
		http
		// http://localhost:8005/ 요청은 인증 없이 접근을 허용 
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
				//shop 추가 
				.requestMatchers(new AntPathRequestMatcher("/css/**\", \"/js/**\", \"/img/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("\"/member/**\", \"/item/**\", \"/images/**\"")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				)
		
		// http://localhost:8005/h2-console 요청의 접근 을 허용   , h2 DB는 csrf 적용이안되어서 설정 	
		//.csrf().disable()   // 모든 csrf를 사용하지 않도록 설정 
		.csrf((csrf)-> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
				)
		// H2 콘솔에서 프레임을 작동 시키도록 설정 
		.headers((headers) -> headers
		.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
						))
				)
		
		//로그인을 처리하도록 설정 : controller에서 처리하지 않고 Spring Security 에서 처리 하도록 설정
		// "/user/login"  post 요청을 Security 에서 처리하겠다. 
		//  인증이 성공시 "/"    http://localhost:9696/
		
		//로그인 처리 ( Post 요청 : /user/login ) 
		.formLogin((formLogin) -> formLogin
		.loginPage("/member/login")
				
				// Spring Security 의 기본 설정 : ID 의 name = "usernanme"
				//                             password 필드의 name = "password" 
				.usernameParameter("email")
				//.passwordParameter("password")
				
				// MemberService에서 인증 처리를 하고 있음. 
				
				
				.failureUrl("/member/login/error")

				.defaultSuccessUrl("/")
				)
		

		.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true));
		
		//.csrf().disable();
    	// ###################Spring Boot 3.X. #####################################
 
		http.exceptionHandling()
				.authenticationEntryPoint(new CustomEntryPoint());
		 
		
//		//http.csrf().disable();
//				http.formLogin()		
//					.loginPage("/member/login") 
//					.defaultSuccessUrl("/")
//					.usernameParameter("email")			
//					.failureUrl("/member/login/error")
//					.and()
//					.logout()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
//					.logoutSuccessUrl("/");
				
				
				return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		
		//암호화
		return new BCryptPasswordEncoder();
	}
	
}
