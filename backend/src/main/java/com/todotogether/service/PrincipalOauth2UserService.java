package com.todotogether.service;

import com.todotogether.auth.PrincipalDetails;
import com.todotogether.domain.entity.Member;
import com.todotogether.domain.entity.Role;
import com.todotogether.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리 되는 함수
    //함수 종료시 @AuthenticationPrinciapl 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("어떤 OAuth로 로그인 했는지 확인" + userRequest.getClientRegistration());


        OAuth2User oAuth2User = super.loadUser(userRequest);

        //구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client라이브러리) -> AccessToken요청
        //userRequest정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아준다.
        log.info("사용자 정보 출력:"+ oAuth2User.getAttributes());

        //회원가입을 강제로 진행
        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider+"_"+providerId; //중복x
        String name = oAuth2User.getAttribute("name");
        String password = passwordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email");

        Member memberEntity = memberRepository.findByEmail(username);

        if(memberEntity == null){
            memberEntity = Member.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            //가입은 MemberService에서 해당 정보는 view에 리턴만 해주면 됨
        }
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
    }
}
