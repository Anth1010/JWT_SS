package vn.iotstar.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import vn.iotstar.repository.UserInfoRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserInfoRepository repository;

    // Bean cho UserDetailsService sử dụng UserInfoService
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(repository);
    }

    // Bean cho PasswordEncoder (Sử dụng BCryptPasswordEncoder để mã hóa mật khẩu)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean cho AuthenticationProvider (Sử dụng DaoAuthenticationProvider với UserDetailsService và PasswordEncoder)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // Bean cho SecurityFilterChain để cấu hình bảo mật ứng dụng
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())  // Vô hiệu hóa CSRF (tùy chọn)
                   .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/user/new").permitAll()  // Cho phép truy cập vào URL "/user/new"
                       .requestMatchers("/").permitAll()  // Cho phép truy cập vào trang chủ
                       .requestMatchers("/customer/**").authenticated()  // Cần đăng nhập để truy cập "/customer/**"
                       //.anyRequest().authenticated()  // Uncomment nếu muốn yêu cầu xác thực cho tất cả các URL còn lại
                   )
                   .formLogin(Customizer.withDefaults())  // Cấu hình đăng nhập với form mặc định
                   .build();
    }
}
