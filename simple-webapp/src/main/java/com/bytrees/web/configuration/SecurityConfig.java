package com.bytrees.web.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bytrees.web.security.BytreesUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//配置用户权限验证规则
    	//http.authorizeRequests().antMatchers("/user", "/user/").authenticated();
    	for (String url: getAuthenticatedRequests()) {
    		http.authorizeRequests().mvcMatchers(url).authenticated();
    	}

        http.formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/user")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }

    @Bean
	public PasswordEncoder passwordEncoder(){
    	return new BCryptPasswordEncoder();
	}

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	//固定的账号密码是不安全的，spring不建议生产环境这样使用
        //UserDetails user =
        //     User.withDefaultPasswordEncoder()
        //       .username("user")
        //        .password("password")
        //        .roles("USER")
        //        .build();
        //return new InMemoryUserDetailsManager(user);
    	return new BytreesUserDetailService();
    }

    /**
     * 配置：需要验证权限的url
     * 使用mvcMatchers风格
     * @return
     */
    private List<String> getAuthenticatedRequests() {
    	List<String> requestList = new ArrayList<String>();
    	requestList.add("/user");
    	return requestList;
    }
}
