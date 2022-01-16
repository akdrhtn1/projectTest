package com.todotogether.config;


//구글 로그인이 완료된 뒤의 후처리가 필요. 1.코드받기, 2.엑세스토큰(권한), 3.사용자프로필 정보를 가져와서
//4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함 4-2(이메일,전화번호,이름,아이디)쇼핑몰 -> (집주소), 백화점몰 ->(vip등급, 일반등급)

import com.todotogether.service.PrincipalOauth2UserService;
import com.todotogether.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity //스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;
    private PrincipalOauth2UserService principalDetailsService;

    @Autowired
    public SecurityConfig(MemberService memberService, PrincipalOauth2UserService principalDetailsService) {
        this.memberService = memberService;
        this.principalDetailsService = principalDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.formLogin()
                .loginPage("/loginPage")// ->로그인은 이경로에 수행이됨 view단의 위치
                .loginProcessingUrl("/login") // -> form Action과 일치하여야 시큐리티가 controller login 대신 진행해줌
                .defaultSuccessUrl("/") //성공시 이동되는 페이지
                .usernameParameter("email") //username대신 email을 사용!
                .failureUrl("/loginPage")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                //구글 로그인 설정
                .oauth2Login()
                .loginPage("/loginForm")  //구글 로그인이 완료된 뒤의 후처리 (tip.코트x(엑세스토큰 + 사용자프로필정보 O)
                .userInfoEndpoint()
                .userService(principalDetailsService)
        ;

        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .mvcMatchers(POST,"/api/**").permitAll()
                .mvcMatchers(POST,"/api/user/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")

                //.anyRequest().authenticated()  .permitAll()하면 전부다 권한 해제 //그 외에는 로그인 후 접근하도록 처리
                //.authenticated() 인증되면 들어갈 수 있는 주소
        ;
        /*
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
        */

        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource());


    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**").antMatchers(POST,"/api/**")
                .antMatchers(POST,"/api/user/**").antMatchers(GET,"/api/user/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
