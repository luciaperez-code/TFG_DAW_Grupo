package com.edix.apirest.cinema.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edix.apirest.cinema.service.MyUserDetailsService;
import com.edix.apirest.cinema.security.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// Para que funcionen los formularios
			.csrf().disable()
			.authorizeRequests()
			// Los directorios estáticos no requieren autenticacion
			.antMatchers("/rest/demo-bcrypt/**").permitAll()
			
			// Las vistas públicas no requieren autenticación
			.antMatchers("/", "/films/**", "/products/**", "/producttype/**","/projections/**", "/screens/**", "/users/**", "/login").permitAll()
			// Las autorizaciones sobre urls para ROLES
//			.antMatchers("/lista-productos/editar-producto/**").hasAnyAuthority("Admin")
//			.antMatchers("/lista-productos/borrar-producto/**").hasAnyAuthority("Admin")
//			.antMatchers("/lista-usuarios").hasAnyAuthority("Admin")
//			.antMatchers("/lista-roles").hasAnyAuthority("Admin")
//			.antMatchers("/gracias").hasAnyAuthority("Cliente", "Admin")
			
			// Todas las demás URLs de la Aplicación requieren autenticación
			.anyRequest().authenticated()
			
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
            .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
            .httpBasic().disable();
        	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
