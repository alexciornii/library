package md.esempla.library.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalAuthentication
public class LibraryConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/", "/**").authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/resources/**")
                .antMatchers("/static/**")
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/webjars/**")
                .antMatchers("/dist/**")
                .antMatchers("/bootstrap/**")
                .antMatchers("/plugins/**")
                .antMatchers("/api-docs/**")
                .antMatchers("/images/**");
    }
}


