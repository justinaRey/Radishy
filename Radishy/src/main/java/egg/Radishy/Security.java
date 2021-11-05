package egg.Radishy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Fabri
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

//    UserDetailService
//    @Autowired
//    private UsuarioServicio usuarioServicio;
//    Metodo de autenticacion.
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
//    }
//    configuracion de peticiones http    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.headers().frameOptions().sameOrigin()
//        .and().authorizeRequests()
//                .antMatchers("/css/*","/img/*","/js/*").permitAll()
//        .and().formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("")
//                .loginProcessingUrl("/logincheck")
//                .failureUrl("").permitAll()
//        .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//        .and().csrf().disable();
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
    }
}
