package com.edix.apirest.cinema.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
		    .cors().and()
			.csrf().disable()
			.authorizeRequests()
			// Los directorios estáticos no requieren autenticacion
			.antMatchers("/rest/demo-bcrypt/**").permitAll()
			
            .antMatchers("/films/add-film", "/films/delete-film", "/films/edit-film").hasRole("ADMIN")
            .antMatchers("/products/add-product", "/products/delete-product", "/products/edit-product").hasRole("ADMIN")
            .antMatchers("/producttype/add-productType", "/producttype/delete-productType", "/producttype/edit-productType").hasRole("ADMIN")
            .antMatchers("/projections/add-projection", "/projections/delete-projection", "/projections/edit-projection").hasRole("ADMIN")
            .antMatchers("/screens/add-screen", "/screens/delete-screen", "/screens/edit-screen").hasRole("ADMIN")
            
            //Vistas públicas
			.antMatchers("/", "/films/**", "/products/**", "/producttype/**","/projections/**", "/screens/**", "/users/register", "/login").permitAll()
			.antMatchers("/users/**").hasRole("ADMIN")
            .antMatchers("/orders/allOrders", "/orders/user/*").hasRole("ADMIN")
//			.antMatchers("/lista-productos/editar-producto/**").hasAnyAuthority("Admin")
//			.antMatchers("/lista-usuarios").hasAnyAuthority("Admin")
//			.antMatchers("/gracias").hasAnyAuthority("Cliente", "Admin")
			
			// Todas las demás URLs de la Aplicación requieren autenticación
		    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		    .antMatchers(HttpMethod.POST, "/**").authenticated()
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
