package egg.Radishy;

import egg.Radishy.Servicios.User_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 *
 * @author Fabri
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    
    @Autowired
    private User_servicio userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().sameOrigin()
                
                .and().authorizeRequests()
                .antMatchers("/css/*", "/img/*", "/js/*").permitAll()
                
                .antMatchers("/").permitAll()
                
                .anyRequest().authenticated()
  
                .and().formLogin()
                           
                .loginPage("/login")
                            
                .usernameParameter("nombre")
                .passwordParameter("password")
                     
                .defaultSuccessUrl("/")
                         
                .loginProcessingUrl("/logincheck")
                .failureUrl("/").permitAll()
                .and().logout()
                            
                .logoutUrl("/logout")
                   
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
    }
    
    
        /////////////////////////
    
    
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
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().disable();
//    }
}
