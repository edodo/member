package member.config;


import member.service.MemberAccessDeniedHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.inject.Qualifier;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/h2-console/**", "/webjars/**", "/signup", "/home", "/")
        .permitAll().anyRequest().authenticated().and()
        .headers().frameOptions().disable().and()
        .csrf().ignoringAntMatchers("/h2-console/**").and()
        .formLogin().loginPage("/login").loginProcessingUrl("/sign-in").defaultSuccessUrl("/welcome").failureUrl("/login?error").usernameParameter("email")
        .passwordParameter("passwd").permitAll().and()
        .exceptionHandling().accessDeniedHandler(MemberAccessDeniedHandler()).and()
        .rememberMe().key("jpub").rememberMeParameter("remember-me").rememberMeCookieName("jpubcookie")
        .tokenValiditySeconds(86400).tokenRepository(rememberMeTokenService()).userDetailsService(myUserService()).and()
        .logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(@Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserService myUserService() throws Exception {
        return new MyUserService();
    }

    @Bean
    public PersistentTokenRepository rememberMeTokenService() throws Exception {
        return new RememberMeTokenService();
    }

    @Bean
    public AccessDeniedHandler MemberAccessDeniedHandler() throws Exception {
        return new MemberAccessDeniedHandler();
    }
}
