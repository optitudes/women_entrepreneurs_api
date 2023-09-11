package co.edu.uniquindio.women_entrepeneurs_api;

import co.edu.uniquindio.women_entrepeneurs_api.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class NegocioApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        SpringApplication.run(PersistenciaApplication.class,args);

    }
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()

                    .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/product/search/{pattern}").permitAll()


                    .anyRequest().authenticated();
        }
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(NegocioApplication.class);
        }
    }
}
