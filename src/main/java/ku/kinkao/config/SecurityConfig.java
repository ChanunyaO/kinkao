package ku.kinkao.config;

import ku.kinkao.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.context.ApplicationContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private OidcUserService oidcUserService;

    @Autowired
    private ApplicationContext context;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/review")
                .hasAuthority("SCOPE_read:reviews")
                .mvcMatchers(HttpMethod.POST, "/api/review")
                .hasAuthority("SCOPE_create:reviews")
                .antMatchers("/home", "/signup",
                        "/css/**", "/js/**").permitAll()
                .antMatchers("/restaurant/add")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/restaurant", "/review", "/review/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/restaurant", true)
                .permitAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/restaurant", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .permitAll();

        ClientRegistrationRepository repository = context.getBean(ClientRegistrationRepository.class);

        if (repository != null) {
            http
                    .oauth2Login().clientRegistrationRepository(repository)
                    .userInfoEndpoint().oidcUserService(oidcUserService).and()
                    .loginPage("/login").permitAll();
        }

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }
}