package hua.WebApp.SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationProvider authProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//           auth.userDetailsService(userDetailsService);
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "referer"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/requests").hasRole("STUDENT")
                .antMatchers("/requestsPage").hasRole("STUDENT")
                .antMatchers("/addRequest").hasRole("STUDENT")
                .antMatchers("/addRequestPage").hasRole("STUDENT")
                .antMatchers("/editRequest/{requestId}").hasRole("STUDENT")
                .antMatchers("deleteRequest/{requestId}").hasRole("STUDENT")
                .antMatchers("/pendingRequests").hasRole("PROFESSOR")
                .antMatchers("/pendingRequestsPage").hasRole("PROFESSOR")
                .antMatchers("/setRequestStatus/{requestId}").hasRole("PROFESSOR")
                .antMatchers("/acceptedRequests").hasRole("PROFESSOR")
                .antMatchers("/acceptedRequestsPage").hasRole("PROFESSOR")
                .antMatchers("/addLetter").hasRole("PROFESSOR")
                .antMatchers("/addLetterPage").hasRole("PROFESSOR")
                .antMatchers("/editLetter/{letterId}").hasRole("PROFESSOR")
                .antMatchers("/deleteLetter/{letterId}").hasRole("PROFESSOR")
                .antMatchers("/viewLetters").hasRole("PROFESSOR")
                .antMatchers("/viewLettersPage").hasRole("PROFESSOR")
                .antMatchers("/homepage").hasAnyRole("STUDENT","PROFESSOR", "SECRETARY")
                .antMatchers("/register").hasRole("SECRETARY")
                .antMatchers("/createUserPage").hasRole("SECRETARY")
                .antMatchers("/viewUsersPage").hasRole("SECRETARY")
                .antMatchers("/users").hasRole("SECRETARY")
                .antMatchers("/editUser/{userId}").hasRole("SECRETARY")
                .antMatchers("/deleteUser/{userId}").hasRole("SECRETARY")
                .and().formLogin()
                .and().csrf().disable();
        //   http
        // ...
        //   .cors().disable();
        // do not use any default headers unless explicitly listed

    }

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("OK");
                httpServletResponse.setStatus(200);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder;
    }
}