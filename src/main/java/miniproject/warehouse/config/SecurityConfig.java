package miniproject.warehouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}adminpw").roles("ADMIN")
                .and()
                .withUser("warehouse").password("{noop}warehousepw").roles("WAREHOUSEADMIN")
                .and()
                .withUser("store").password("{noop}storepw").roles("STOREADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/goods").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/goods/{goodsId}").hasRole("ADMIN")
                .regexMatchers(HttpMethod.GET, "/api/goods.*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/goods/{goodsId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/store").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/store/{storeId}").hasRole("ADMIN")
                .regexMatchers(HttpMethod.GET, "/api/store.*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/store/{storeId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/warehouse").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/warehouse/{warehouseId}").hasRole("ADMIN")
                .regexMatchers(HttpMethod.GET, "/api/warehouse.*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/warehouse/{warehouseId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transfer/stw").hasRole("WAREHOUSEADMIN")
                .antMatchers(HttpMethod.GET, "/api/transfer/stw").hasRole("WAREHOUSEADMIN")
                .antMatchers(HttpMethod.POST, "/api/transfer/wtw").hasRole("WAREHOUSEADMIN")
                .antMatchers(HttpMethod.GET, "/api/transfer/wtw").hasRole("WAREHOUSEADMIN")
                .antMatchers(HttpMethod.POST, "/api/transfer/wts").hasRole("WAREHOUSEADMIN")
                .antMatchers(HttpMethod.GET, "/api/transfer/wts").hasRole("WAREHOUSEADMIN")
                .regexMatchers(HttpMethod.GET, "/api/inventory/warehouse.*").hasRole("WAREHOUSEADMIN")
                .regexMatchers(HttpMethod.GET, "/api/inventory/store.*").hasRole("STOREADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
