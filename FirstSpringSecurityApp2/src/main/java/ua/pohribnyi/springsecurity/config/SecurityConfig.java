package ua.pohribnyi.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ua.pohribnyi.springsecurity.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final PersonDetailsService personDetailsService;

	@Autowired
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// This is a sample configuration of spring security in our app
		http
	      .authorizeHttpRequests((authz) -> authz
	    		  	  .requestMatchers("/admin").hasRole("ADMIN")
					  .requestMatchers("/auth/login", "/auth/registration", "/error").permitAll()
					  .anyRequest().hasAnyRole("USER", "ADMIN"))
					  .formLogin((formLogin) -> formLogin
							  .loginPage("/auth/login")
							  .loginProcessingUrl("/process_login")
						      .defaultSuccessUrl("/hello", true)
						      .failureUrl("/auth/login?error"))
					  .logout((logout) -> logout
							  		.logoutUrl("/logout")
							  		.logoutSuccessUrl("/auth/login"));
	      	
		return http.build();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		// This is a sample configuration of authentication in our app
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.personDetailsService);
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
