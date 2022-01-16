package com.todotogether.service;

import com.todotogether.auth.PrincipalDetails;
import com.todotogether.domain.entity.Member;
import com.todotogether.domain.entity.Role;
import com.todotogether.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//시큐리 설정에서  loginProcessUrl("/login")
// /login 요청이 오면 자동으로 UserDetails 타입으로 IOC되어 있 loadUserByUsername 함수가 실행

public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //유효성 검사
    public void validateMember(Member member){
        Boolean findEmail = memberRepository.existsByEmail(member.getEmail());
        Boolean findNickname = memberRepository.existsByNickname(member.getNickname());
        if(findEmail){
            throw new IllegalStateException("이미 등록된 이메일 입니다.");
        }else if(findNickname){
            throw new IllegalStateException("이미 등록된 닉네임 입니다.");
        }
    }

    public long signUp(Member member, PasswordEncoder passwordEncoder){
        String encodePassword = passwordEncoder.encode(member.getPassword()); //암호화
        member.setPassword(encodePassword);

        member.setEnabled(true);
        Role role = new Role();
        role.setRId(1l);
        member.getRoles().add(role);

        Member save = memberRepository.save(member);

        return save.getUId();
    }

    //함수 종료시 @AuthenticationPrinciapl 어노테이션이 만들어진다.
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        //시큐리티 session(내부 Authentication(내부 UserDetails))
        return new PrincipalDetails(member);
    }


    //추가 내용 -----
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // -----------

}
