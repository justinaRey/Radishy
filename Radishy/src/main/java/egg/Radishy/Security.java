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
public class Security extends WebSecurityConfigurerAdapter{
    
//    UserDetailService
//    @Autowired
//    private UsuarioServicio usuarioServicio;
    
//    Metodo de autenticacion.
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throwa Exception{
//        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
//    }
    
//  configuracion de peticiones http    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/css/*","/img/*","/js/*").permitAll()
//                .and().formLogin().loginPage("")
//                .usernameParameter("")
//                .passwordParameter("")
//                .defaultSuccessUrl("")
//                .loginProcessingUrl("/logincheck")
//                .failureUrl("").permitAll()
//                .and().logout()
//                .logoutUrl("")
//                .logoutSuccessUrl("")
//                .and().csrf().disable();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.formLogin().disable();
    }
}
