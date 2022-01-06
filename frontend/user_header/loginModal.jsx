import React, { useRef, useState } from "react";
import CommonModal from "../common/commonModal";
import StyledInput from "../common/StyledInput";
import styled from "styled-components";
import { Link } from "react-router-dom";

import SweetAlert from "../common/sweetAlert";

function LoginModal ({onClick}) {
    const [values, setValues] = useState([]);

    // values핸들러
    const handlerValues = (type,e) => {
        setValues({...values, [type]:e.target.value});
    }

    // ref
    let inputRefId = useRef();
    let inputRefPw = useRef();

    // 서브밋핸들러
    const handlerSubmit = (e) => {
        e.preventDefault();
        if (inputRefId.current.value.trim() === "") {
            SweetAlert("아이디를 입력해주세요.", "최대 ?자 이상 ?자 미만(특수문자, 한글사용 불가능)");
        } else if (inputRefPw.current.value.trim() === "") {
            SweetAlert("비밀번호를 입력해주세요.", "대소문자+숫자+특수문자를 포함한 최소 6자 이상");
        } else {
            // 엑시오스
        }
    }
    return (
        <CommonModal onClick={onClick}>
            <form onSubmit={e => handlerSubmit(e)}>
            <h1>TodoTogether</h1>
            <StyledInput id="id"
                         name="id"
                         type="text"
                         labeling={"아이디"}
                         value={values.id || ""}
                         onChange={e => handlerValues("id",e)}
                         Ref={e => inputRefId.current = e}
                         />
            <StyledInput id="pw"
                         name="pw"
                         type="password"
                         labeling={"비밀번호"}
                         value={values.pw || ""}
                         onChange={e => handlerValues("pw",e)}
                         Ref={e => inputRefPw.current = e}
                         />
            <button className="loginBtn">로그인</button><br></br>
            <Modal_signupBtn className="basicBtn" to="/signup" onClick={onClick}>회원가입</Modal_signupBtn>
            </form>
        </CommonModal>
    );
}

export default LoginModal;

const Modal_signupBtn = styled(Link)`
    position: relative;
    top: 65px;
    left: 73px;
`;