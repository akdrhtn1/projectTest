package com.todotogether.service;

import com.todotogether.domain.entity.Member;
import com.todotogether.domain.entity.Role;
import com.todotogether.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //유효성 검사
    public void validateMember(Member member){
        Member findEmail = memberRepository.findByEmail(member.getEmail());
        Member findNickname = memberRepository.findByNickname(member.getNickname());
        if(findEmail != null){
            throw new IllegalStateException("이미 등록된 이메일 입니다.");
        }else if(findNickname != null){
            throw new IllegalStateException("이미 등록된 닉네임 입니다.");
        }
    }

    public long signUp(Member member){
        String encodePassword = passwordEncoder.encode(member.getPassword()); //암호화
        member.setPassword(encodePassword);

        member.setEnabled(true);
        Role role = new Role();
        role.setRId(1l);
        member.getRoles().add(role);

        Member save = memberRepository.save(member);

        return save.getUId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRoles().get(1).toString())
                .build();
    }
}
