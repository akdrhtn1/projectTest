package com.todotogether.controller;

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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public MemberDto Test2553(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);

        return memberDto;
    }
    /*
    public ResponseEntity userValid(@RequestBody @Valid MemberDto memberDto, Errors errors){

        Map<String, String> validatorResult = null;
        //회원 유효성 검사
        if(errors.hasErrors()) {
            //--------------추가수정
            validatorResult = memberService.validateHandling(errors);
        }
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validatorResult); //유효성 검사 실패시
        if(!memberDto.getPassword().equals(memberDto.getPassword2())){
             validatorResult.put("valid_pwCheck","비밀번호가 일치하지 않습니다.");
        }
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

     */


    @PostMapping("/test2")
    public MemberDto Test23(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);

        return memberDto;
    }

    @GetMapping(value = "test11")
    public String tes443332(@RequestParam String email){

        System.out.println(email);
        return email;
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


}
