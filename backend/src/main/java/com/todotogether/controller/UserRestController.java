package com.todotogether.controller;

import com.todotogether.domain.dto.MemberDto;
import com.todotogether.domain.entity.Member;
import com.todotogether.service.EmailService;
import com.todotogether.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private MemberService memberService;
    private ModelMapper modelMapper;
    private EmailService emailService;

    @Autowired
    public UserRestController(MemberService memberService, ModelMapper modelMapper, EmailService emailService) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    /*
            회원 유효성 검사
     */
    @PostMapping("/valid")
    public ResponseEntity userValid(@RequestBody @Valid MemberDto memberDto, Errors errors){

        //회원 유효성 검사
        if(errors.hasErrors()){
            //--------------추가수정
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validatorResult); //유효성 검사 실패시
            //-------------

        }
        //아이디 중복 검증 유효성 검사
        try{
            Member member = modelMapper.map(memberDto, Member.class);
            memberService.validateMember(member);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @GetMapping("test")
    public String Test(){
        return "Testsss";
    }


    //회원가입(s3 가능해야함)
    @PostMapping(value = "")
    public ResponseEntity<Boolean> signUp(@RequestBody MemberDto memberDto){

        System.out.println(memberDto);
        Member member = modelMapper.map(memberDto, Member.class);
        long result = memberService.signUp(member);

        System.out.println(result + "asd"+ member);
        if(result >0){
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    //email인증 클릭시 인증번호 발송
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody String email)throws Exception{

        String confirm = emailService.sendSimpleMessage(email);

        return ResponseEntity.status(HttpStatus.OK).body(confirm);
    }


}
