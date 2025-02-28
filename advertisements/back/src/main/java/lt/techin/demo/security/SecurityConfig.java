//package lt.techin.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    http
//          .cors(Customizer.withDefaults())
//          .csrf(AbstractHttpConfigurer::disable)
//          .httpBasic(Customizer.withDefaults())
//          .authorizeHttpRequests((authorize) -> authorize
//                  .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
//                  .requestMatchers(HttpMethod.DELETE, "/api/movies/**").hasRole("ADMIN")
//                  .anyRequest().authenticated()
//          );
//
//    return http.build();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//}

package lt.techin.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests((authorize) -> authorize

                    .requestMatchers(HttpMethod.GET, "/api/ads/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/ads").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/about").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/contact").permitAll()


                    .requestMatchers(HttpMethod.DELETE, "/api/ads/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/ads").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT, "/api/ads/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/categories").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST, "/api/admin/add-ad/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/admin/add-ad/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/api/admin/add-ad/**").hasRole("USER")

                    .requestMatchers(HttpMethod.POST, "/api/admin/add-ad/**").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/api/admin/add-ad/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/admin/add-ad/**").hasRole("ADMIN")


                    .anyRequest().authenticated()
            );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}