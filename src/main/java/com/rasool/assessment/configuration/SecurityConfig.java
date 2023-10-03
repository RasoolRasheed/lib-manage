package com.rasool.assessment.configuration;


import com.rasool.assessment.auth.JwtAuthFilter;
import com.rasool.assessment.auth.JwtAuthenticationEntryPoint;
import com.rasool.assessment.auth.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEndPoint;

	@Override
	public void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		// we don't need CSRF because our token is invulnerable
		.csrf().disable()
		.cors().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEndPoint).and()

		// don't create session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

		.authorizeRequests()
		.antMatchers(
				"/user/*",
				"/*",
				"/*/*",
				"/*/*/*",
				"/*/*/*/*",
				"/*/*/*/*/*",
				"/*/*/*/*/*/*"

				).permitAll()
		.anyRequest()
		.authenticated();
		httpSecurity
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity
		.headers()
		.frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
		.cacheControl();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web
		.ignoring()
		.antMatchers(
				"/user",
				"/login",
				"/login/*",
				"/login/*/*",
				"/swagger-ui/**"
				)

		// allow anonymous resource requests
		.and()
		.ignoring()
		.antMatchers(
				"/*",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.css",
				"/*.css",
				"/**/*.js"
				);
	}
}