package br.com.imarket.istoque.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${security.cookie.name}")
	private String cookieName;
	
	@Autowired 
	private UserDetailsDAO userDetailsService;
	@Autowired 
	private RememberMeAuthenticationProvider rememberMeAuthenticationProvider;

	@Override 
	protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginPage("/")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/home")
	            .permitAll()
	            .and()
            .logout()
            	.logoutUrl("/logout")
            	.clearAuthentication(true)
                .deleteCookies(cookieName)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
            .rememberMe().rememberMeServices(rememberMeServices());
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		auth.authenticationProvider(rememberMeAuthenticationProvider);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean   
	TokenBasedRememberMeService rememberMeServices() {
		TokenBasedRememberMeService service = new TokenBasedRememberMeService(cookieName, userDetailsService);
		service.setAlwaysRemember(true);
		service.setCookieName(cookieName);
		return service;
	}

	@Bean
	RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		return new RememberMeAuthenticationProvider(cookieName);
	}
}