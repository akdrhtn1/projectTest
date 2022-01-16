package com.todotogether.controller;

import com.todotogether.auth.PrincipalDetails;
import com.todotogether.domain.dto.MemberDto;
import com.todotogether.domain.dto.UploadFileDto;
import com.todotogether.domain.entity.Member;
import com.todotogether.service.EmailService;
import com.todotogether.service.ImageManagerService;
import com.todotogether.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private MemberService memberService;
    private ModelMapper modelMapper;
    private EmailService emailService;
    private ImageManagerService imageManagerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(MemberService memberService, ModelMapper modelMapper, EmailService emailService, ImageManagerService imageManagerService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.imageManagerService = imageManagerService;
        this.passwordEncoder = passwordEncoder;
    }
    /*
            회원 유효성 검사
     */
    @PostMapping("/valid")
    public ResponseEntity userValid(@RequestBody(required = false) @Valid MemberDto memberDto, Errors errors){

        Map<String, String> validatorResult = null;
        //회원 유효성 검사
        if(errors.hasErrors()) {
            //--------------추가수정
            validatorResult = memberService.validateHandling(errors);
        }
        //
        /*
        if(memberDto.getPassword() != null && memberDto.getPassword2() !=null){
            if(!memberDto.getPassword().equals(memberDto.getPassword2())){
                validatorResult.put("valid_pwCheck","비밀번호가 일치하지 않습니다.");
            }
        }
        */
        //아이디 중복 검증 유효성 검사
        try{
            Member member = modelMapper.map(memberDto, Member.class);
            memberService.validateMember(member);
        }catch (IllegalStateException e){
            validatorResult.put("valid_idCheck",e.getMessage());
            //return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(validatorResult);
    }

    //회원가입(s3 가능해야함)
    @PostMapping(value = "/")
    public ResponseEntity<Boolean> signUp(@RequestBody MemberDto memberDto){

        System.out.println(memberDto);
        Member member = modelMapper.map(memberDto, Member.class);
        long result = memberService.signUp(member, passwordEncoder);

        System.out.println(result + "asd"+ member);
        if(result >0){
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    // 파일전송 테스트 코드 실제론 dto받고 경로 전송
    @PostMapping(value ="/file")
    public ResponseEntity upload(@RequestParam(required = false) MultipartFile profile){
        String folderName = "image";
        UploadFileDto uploadFileDto = null;
        String foldDiv = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String filePath = folderName + File.separator + foldDiv;

        try {
             uploadFileDto = imageManagerService.createAndUploadFile(profile, filePath);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
            return ResponseEntity.status(HttpStatus.OK).body(uploadFileDto);
    }

    //email인증 클릭시 인증번호 발송
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody String email)throws Exception{

        String confirm = emailService.sendSimpleMessage(email);

        return ResponseEntity.status(HttpStatus.OK).body(confirm);
    }

    //로그인 후 계정 세션 정보를 확인
    @GetMapping("/login")
    public String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails PrincipalDetails){
        //1번째 방법 getUser정보
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info("authentication : " + principalDetails.getMember());

        //2번째 방법 생성한 principalDetails 상속받은 userDetails을 활용하는 방법
        log.info("userDetails : " + PrincipalDetails.getMember());
        return "세션 정보 확인";
    }

    //구글 로그인 후 세션 정보 확인하기
    @GetMapping("/login")
    public String testOAuthLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){
        //1번째 방법 getUser정보
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("authentication : " + oAuth2User.getAttributes());

        log.info("oauth2User : " + oauth.getAttributes());


        //2번째 방법 생성한 principalDetails 상속받은 userDetails을 활용하는 방법
        return "OAuth 세션 정보 확인";
    }

    //OAuth 로그인을 해도 PrincipalDetails
    //일반 로그인을 해도 PrincipaDetails
    @GetMapping("/user")
    public Member user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return principalDetails.getMember();
    }

}
