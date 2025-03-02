package ssafy.sorhy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import ssafy.sorhy.config.auth.PrincipalDetailsService;
import ssafy.sorhy.config.jwt.JwtAuthenticationFilter;
import ssafy.sorhy.config.jwt.JwtAuthorizationFilter;
import ssafy.sorhy.repository.user.UserRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((authorize) ->
                authorize
                        .requestMatchers("/user/profile").authenticated()
                        .requestMatchers("/health-check").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/article/**").permitAll()
                        .requestMatchers("/articles/**").permitAll()
                        .requestMatchers("/game").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rank/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin.disable() // 폼 태그를 이용한 로그인을 하지 않겠다.
                )
                .httpBasic((httpBasic) ->
                        httpBasic.disable() // 헤더에 ID, PW 를 담아서 보내는 방식(httpBasic)을 사용하지 않겠다.
                );

        new MyCustomDsl().configure(http);
        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {

            AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
            sharedObject.userDetailsService(principalDetailsService);
            AuthenticationManager authenticationManager = sharedObject.build();

            // 만들어준 authenticationManager  시큐리티에 등록
            http.authenticationManager(authenticationManager);
            http
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }
}
