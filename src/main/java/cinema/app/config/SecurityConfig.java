package cinema.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls", "/movies",
                        "/movie-sessions/available", "/movie-sessions/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/orders", "/shopping-carts/by-user")
                .hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/users/by-email").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies", "/movie-sessions").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
