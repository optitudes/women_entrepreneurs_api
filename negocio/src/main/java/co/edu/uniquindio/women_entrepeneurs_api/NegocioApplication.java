package co.edu.uniquindio.women_entrepeneurs_api;

import co.edu.uniquindio.women_entrepeneurs_api.exceptions.CustomAccessDeniedHandlerException;
import co.edu.uniquindio.women_entrepeneurs_api.exceptions.CustomAuthenticationEntryPointException;
import co.edu.uniquindio.women_entrepeneurs_api.security.CorsConfig;
import co.edu.uniquindio.women_entrepeneurs_api.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@Import(CorsConfig.class)
public class NegocioApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        SpringApplication.run(PersistenciaApplication.class, args);

    }

    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()

                    .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                    //rutas relacionadas a la informacion de la app
                    .antMatchers("/api/appinfo/general/**").authenticated()
                    .antMatchers( "/api/appinfo/root/**").hasAuthority("ROLE_ROOT")
                    .antMatchers( "/api/appinfo/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_ROOT")
                    .antMatchers( "/api/user/public/**").permitAll()
                    //rutas relacionadas a usuarios logeados
                    .antMatchers( "/api/user/general/**").authenticated()
                    .antMatchers( "/api/user/root/**").hasAuthority("ROLE_ROOT")
                    .antMatchers( "/api/user/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_ROOT")
                    .antMatchers( "/api/user/entrepreneurs/**").hasAnyAuthority("ROLE_EMPRENDEDOR","ROLE_ROOT")

                    // rutas relacionadas a micrositios
                    .antMatchers( "/api/microsite/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_ROOT")
                    .antMatchers(HttpMethod.GET, "/api/product/search/{pattern}").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandlerException())
                    .authenticationEntryPoint(new CustomAuthenticationEntryPointException());;
        }
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(NegocioApplication.class);
        }
    }
}
