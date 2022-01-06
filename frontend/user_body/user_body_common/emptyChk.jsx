import React from "react";
import SweetAlert from "../../common/sweetAlert";

export default function EmptyChk (e) {
    if (e.target.email.value.trim() === "") {
        SweetAlert("이메일을 입력해주세요.","필수항목입니다.");
    } else if (e.target.pw.value.trim() === "") {
        SweetAlert("비밀번호를 입력해주세요.","최대?자 ~ ?자 사이 / 특수문자,대문자 최소 1개 이상");
    } else if (e.target.name.value.trim() === "") {
        SweetAlert("이름을 입력해주세요.","특수문자 사용 불가능");
    } else if (e.target.nickname.value.trim() === "") {
        SweetAlert("닉네임을 입력해주세요.","특수문자 사용 불가능");
    } else if (e.target.phone.value.trim() === "") {
        SweetAlert("전화번호를 입력해주세요.","-입력하지 않음. ex) 01011112222");
    } else if (e.target.anotherEmail.value.trim() === "") {
        SweetAlert("다른이메일을 입력해주세요.","추후 아이디를 찾는데 도움이 됩니다.(필수항목)");
    }
}