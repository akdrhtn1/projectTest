import React, { useState, useRef, useEffect } from "react";
import StyledInput from "../../common/StyledInput";
import AnotherEmailModal from "../user_body_common/anotherEmailModal";
import ValidityChk from "../user_body_common/validityChk";

export default function LeftSignup () {
    const [values, setValues] = useState([]);
    const [error, setError] = useState([]);

    // 벨류핸들러
    const handlerValues = (type, e) => {
        setValues({ ...values, [type]: e.target.value });
    }
   
    // 유효성검사
    const handlervalidityChk = (e) => { ValidityChk(e, callback); }
    const callback = (type,e) => { setError({...error, [type]:e}); }



    // 타겟
    let inputEmailRef = useRef();
    let inputPwRef = useRef();
    let inputNameRef = useRef();
    let inputNicknameRef = useRef();
    let inputPhoneRef = useRef();
    let inputAnotherEmailRef = useRef();

    return (
        <div className="userInfoDeepLeftInnerWrapper">
            <StyledInput id="email"
                         name="email"
                         type="email"
                         labeling="이메일"
                         value={values.email||""}
                         onChange={e => handlerValues("email", e)}
                         Ref={e => inputEmailRef = e}
                         error={error.email||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="특수문자 사용 불가능"
                         />
            <StyledInput id="pw"
                         name="pw"
                         type="password"
                         labeling="패스워드"
                         value={values.pw||""}
                         onChange={e => handlerValues("pw", e)}
                         Ref={e => inputPwRef = e}
                         error={error.pw||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="특수문자 및 숫자 포함 8자 이상"
                         />
            <StyledInput id="name"
                         name="name"
                         type="text"
                         labeling="이름"
                         value={values.name||""}
                         onChange={e => handlerValues("name", e)}
                         Ref={e => inputNameRef = e}
                         error={error.name||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="특수문자 및 숫자 불가능"
                         />
            <StyledInput id="nickname"
                         name="nickname"
                         type="text"
                         labeling="닉네임"
                         value={values.nickname||""}
                         onChange={e => handlerValues("nickname", e)}
                         Ref={e => inputNicknameRef = e}
                         error={error.nickname||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="특수문자 불가능"
                         />
            <StyledInput id="phone"
                         name="phone"
                         type="phone"
                         labeling="전화번호"
                         value={values.phone||""}
                         onChange={e => handlerValues("phone", e)}
                         Ref={e => inputPhoneRef = e}
                         error={error.phone||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="ex) (-입력없이) 0109999999"
                         />
            <div>
            <StyledInput id="anotherEmail"
                         name="anotherEmail"
                         type="text"
                         labeling="다른이메일"
                         value={values.anotherEmail||""}
                         onChange={e => handlerValues("anotherEmail", e)}
                         Ref={e => inputAnotherEmailRef = e}
                         error={error.anotherEmail||""}
                         onBlur={e => handlervalidityChk(e)}
                         placeholder="특수문자 사용 불가능"
                         />
            <AnotherEmailModal />
            </div>
        </div>
    );
}