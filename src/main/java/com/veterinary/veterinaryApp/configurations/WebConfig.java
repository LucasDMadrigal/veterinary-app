package com.veterinary.veterinaryApp.configurations;

import com.veterinary.veterinaryApp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebConfig {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private CorsConfigurationSource corsConfigurationSource;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
						.cors(cors -> cors.configurationSource(corsConfigurationSource))
						.csrf(AbstractHttpConfigurer::disable)
						.httpBasic(AbstractHttpConfigurer::disable)
						.formLogin(AbstractHttpConfigurer::disable)
						
						.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
										HeadersConfigurer.FrameOptionsConfig::disable))
						
						.authorizeHttpRequests(authorize ->
										authorize
														.requestMatchers("/api-veterinary/login", "/api-veterinary/register", "/h2-console/**").permitAll()
														
														.requestMatchers( "/api-veterinary/current", "/api-veterinary/appointments/new", "/api-veterinary/pets/new", "/api-veterinary/offerings/", "/api-veterinary/offerings/{id}", "/api-veterinary/invoices/current", "/api-veterinary/pets/current", "/api-veterinary/appointments/current", "/api-veterinary/veterinarians/" ).hasAnyAuthority("CLIENT", "ROLE_ADMIN")

//														.requestMatchers(HttpMethod.GET,"/api-veterinary/**", "/api-veterinary/offerings/{id}").hasRole("ROLE_ADMIN")
														.requestMatchers(HttpMethod.GET,"/api-veterinary/**", "/api-veterinary/offerings/{id}").hasAnyAuthority("ROLE_ADMIN")

														// revisado
														.requestMatchers(HttpMethod.PUT, "/api-veterinary/**", "/api-veterinary/offerings/update-price").hasAnyAuthority("ROLE_ADMIN")
														
														// revisado
														.requestMatchers(HttpMethod.POST, "/api-veterinary/**","/api-veterinary/offerings/create", "/api-veterinary/veterinarian/new").hasAnyAuthority("ROLE_ADMIN")
														
														.requestMatchers(HttpMethod.DELETE,"/api-veterinary/**").hasAnyAuthority("ROLE_ADMIN")
														.anyRequest().authenticated()
						)
						
						.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
						.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						);
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
