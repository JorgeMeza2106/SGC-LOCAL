package pe.com.fisi.cenpro.sigeco.mgc.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.antMatchers("/alumno/**").access("hasRole('ALUMNO')")
	  	.antMatchers("/admin_ag/**").access("hasRole('ADMIN_AG')")
	  	.antMatchers("/admin_cc/**").access("hasRole('ADMIN_CC')")
	  	.antMatchers("/admin_oa/**").access("hasRole('ADMIN_OA')")
	  	.antMatchers("/admin_ad/**").access("hasRole('ADMIN_AD')")
	  	.antMatchers("/admin_od/**").access("hasRole('ADMIN_OD')")
	  	.antMatchers("/admin_cl/**").access("hasRole('ADMIN_CL')")
	  	.antMatchers("/admin_ca/**").access("hasRole('ADMIN_CA')")
	  	.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	  	.usernameParameter("login").passwordParameter("clave")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Acceso_Denegado");
	  
	  CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      http.addFilterBefore(filter,CsrfFilter.class);
      //rest of your code   
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**");
	}
}

