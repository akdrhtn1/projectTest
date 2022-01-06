import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { action_on } from "../../../common/reduxFolder/certificationReducer";

export default function Certification ({children, Style, url, callback, type}) {
    const dispatch = useDispatch();
    const state = useSelector(state => state.certificationReducer);

    const handlerAxios = () => {
        if (!state) {
            dispatch(action_on());
        }
        // UseAxios(url, callback, type); -> 미들웨어를 켤 자리.
    }
    return (
        <>
            <span onClick={handlerAxios} 
                className="signupSendBtn" 
                style={Style}>{children}</span>
        </>
    );
}