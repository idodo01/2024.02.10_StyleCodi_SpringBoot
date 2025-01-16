package ido.style.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(config -> {
            config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
        });


        http.authorizeHttpRequests(configure -> {
            configure.requestMatchers("/static/**").permitAll();
            configure.requestMatchers("/").permitAll();
            configure.requestMatchers("/image/*","/tel/auth", "/email/auth").permitAll();
            configure.requestMatchers("/user/join").permitAll();
            configure.requestMatchers("/product/*","/product/**").permitAll();
            configure.requestMatchers("/style").permitAll();
            configure.anyRequest().permitAll();
        });



        http.formLogin(configure -> {
            configure.loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/") // 로그인이 완료되었을 시 홈페이지로 이동
                    .permitAll();
        });

        http.logout(configure -> {
            configure.logoutUrl("/user/logout").permitAll();
            configure.invalidateHttpSession(true);
            configure.deleteCookies("JSESSIONID");
            configure.clearAuthentication(true);
            configure.logoutSuccessUrl("/");
        });

        http.oauth2Login(configure -> {
            configure
                    .loginPage("/user/login") // 기본 경로인 /login 이 아니라 우리의 로그인 페이지 사용
                    .failureUrl("/user/join") // 소셜 로그인이 실패했다면 회원가입부터 해야하는 유저다
                    .defaultSuccessUrl("/")
                    .permitAll(); // 로그인이 완료되었을 시 홈페이지로 이동
        });

        http.rememberMe(configure -> {
            configure.userDetailsService(userDetailsService);
            configure.tokenRepository(persistentTokenRepository());
            configure.tokenValiditySeconds(60 * 60 * 24 * 7);
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
