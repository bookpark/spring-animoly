package toy.animoly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // REST API를 사용하며 쿠키를 사용하는 방식으로 인증을 하지 않기 때문에 CSRF disable 처리함
        http.csrf().disable();
        // 개발 환경에서 모든 URL에 대한 Security 설정을 permit함
        http.authorizeRequests().antMatchers("/**").permitAll();
        return http.build();
    }
}
