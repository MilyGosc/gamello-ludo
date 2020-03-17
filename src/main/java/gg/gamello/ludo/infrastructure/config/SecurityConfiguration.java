package gg.gamello.ludo.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.gamello.dev.authentication.factory.PublicKeyFactory;
import gg.gamello.dev.authentication.properties.AuthProperties;
import gg.gamello.dev.authentication.security.JwtAuthenticationFilter;
import gg.gamello.dev.authentication.security.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.cert.CertificateException;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private ObjectMapper objectMapper;

	private AuthProperties authProperties;

	public SecurityConfiguration(ObjectMapper objectMapper, AuthProperties authProperties) {
		this.objectMapper = objectMapper;
		this.authProperties = authProperties;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated();

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/client", "/websocket/**"); // todo: !!! IMPORTANT !!! remove /client path in prod version
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider());
	}

	@Bean
	public JwtAuthenticationProvider jwtAuthenticationProvider() throws IOException, CertificateException {
		var provider = new JwtAuthenticationProvider();
		var publicKey = PublicKeyFactory.generate(authProperties.getCertificate());
		provider.setObjectMapper(objectMapper);
		provider.setProperties(authProperties);
		provider.setPublicKey(publicKey);
		return provider;
	}

	private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		var filter = new JwtAuthenticationFilter("/**");
		filter.setAuthenticationManager(super.authenticationManagerBean());
		return filter;
	}
}
