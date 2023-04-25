package cinema.config;

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
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls","/movies").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/movies").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/cinema-halls", "/movies").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movie-sessions/available)")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/movie-sessions").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasAnyRole("USER")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/users/by-email").hasAnyRole("ADMIN")
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
