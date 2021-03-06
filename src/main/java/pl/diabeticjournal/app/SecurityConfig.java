package pl.diabeticjournal.app;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.diabeticjournal.services.UserDetailsServiceImpl;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private UserDetailsServiceImpl userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests()
        .antMatchers("/hello")
        .authenticated()
        .antMatchers("/admin")
        .hasRole("ADMIN")
        .and()
        .formLogin()
        .defaultSuccessUrl("/hello");
    http.authorizeRequests().antMatchers("/resources/**").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }



  /* http.authorizeRequests().antMatchers("/resources/**").permitAll();*/
  /*



    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web
              .ignoring()
              .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

  */
}
