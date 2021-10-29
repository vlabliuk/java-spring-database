package ua.labliuk.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("Unix").password("12345").roles("manager"))
//                .withUser(userBuilder.username("Alena").password("1234").roles("administrator"))
//                .withUser(userBuilder.username("krapiva").password("12345").roles("HR"))
//                .withUser(userBuilder.username("nata").password("12345").roles("employee"))
//                .withUser(userBuilder.username("Lord").password("123456").roles("employee"))
//                .withUser(userBuilder.username("loudovik").password("321654").roles("employee"));
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/people/greetingpage")
//                .hasAnyRole("manager","administrator","HR","employee").and().formLogin().permitAll();
//        http.authorizeRequests().antMatchers("/people")
//                .hasAnyRole("manager","HR","administrator");
//
//    }



    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .and()
//                .csrf();
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/people/greetingpage")
//                .hasAnyRole("ROLE_ADMIN", "ROLE_USER").and().formLogin().permitAll();
//        http.authorizeRequests().antMatchers("/people")
//                .hasRole("ROLE_ADMIN");
//    }

//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/people/greetingpage").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
//                .and()
//                .formLogin().permitAll();
//        http.authorizeRequests().antMatchers("/people").access("hasRole('ROLE_ADMIN')");
//    }


    // THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
//                .usersByUsernameQuery(
//                        "select username,password, enabled from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_roles where username=?");
    }

            @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE","MANAGER","HR")
                .antMatchers("/people/greetingpage").hasAnyRole("EMPLOYEE","MANAGER","HR")
                .antMatchers("/people").hasRole("MANAGER")
                .and().formLogin().permitAll().and().logout().logoutSuccessUrl("/people/firstpage");
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/people/firstpage");
    }
}
