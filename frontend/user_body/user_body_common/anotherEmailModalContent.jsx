import React from "react";

export default function AnotherEmailModalContent ({ onClick }) {

    return (
        <div className="anotherModalBtnContent" onClick={onClick}>
            <div>다른이메일이란?</div>
            아이디를 잃어버렸을 때 사용자의 계정을 찾아주기 위한 보안수단 이에요.
        </div>
    );
}