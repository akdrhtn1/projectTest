package com.todotogether.config;

import com.todotogether.repository.MemberRepository;
import com.todotogether.service.EmailService;
import com.todotogether.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){return new MemberService(memberRepository);}

    @Bean
    public EmailService emailService(){return new EmailService();}

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}
}
