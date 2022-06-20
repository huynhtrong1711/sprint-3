package com.example.bookstoremanagement.config;
import com.example.bookstoremanagement.security.jwt.JwtEntryPoint;
import com.example.bookstoremanagement.security.jwt.JwtTokenFilter;
import com.example.bookstoremanagement.security.userprinciple.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //tiem interface cua he thong,them @service vao lop trien khai nos
    @Autowired
    AccountDetailService accountDetailService;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cross origin
        http.cors().and().csrf().disable()
                //request gui len request nao se dk truy cap
                .authorizeRequests().antMatchers("/api/login/*","/api/book/*").permitAll()
                //tat ca cac request con lai phai dang nhap
                .anyRequest().authenticated()
                //neu request gui len k co request goi den entryPoint
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                // viec xu ly ngoai le xu ly theo phien nen can den sessionManager
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //xu ly truoc khi  tim thay token
                http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
