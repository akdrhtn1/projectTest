
import React, { useState, useMemo, useEffect } from "react";
import { throttle } from "lodash";
import "./header.scss";
import styled from "styled-components";

import { BsFillBellFill } from "react-icons/bs";
import StyledLinkBtn from "../common/StyledLinkBtn";
import { Link } from "react-router-dom";
import LoginModal from "./loginModal";

function Header () {
    const [isLogin, setIsLogin] = useState();
    const [ showing, setShowing ] = useState(false);
    const [scrollEvent, setScrollEvent] = useState(false);

    // 헤더 스크롤이벤트
    const handlerScroll = useMemo(
        () =>
            throttle(() => {
                if (window.scrollY >= 15 && !scrollEvent) {
                    setScrollEvent(true);
                } else if (window.scrollY <= 15 && scrollEvent) {
                    setScrollEvent(false);
                }
            }, 300)
    );
    useEffect(() => {
        window.addEventListener("scroll", handlerScroll);
        return () => { window.removeEventListener("scroll", handlerScroll); };
    }, [scrollEvent]);

    // 모달 핸들러
    const handlerModal = () => { setShowing(!showing); };
    
    return (
        <>
        <HeaderTag scrollEvent={scrollEvent}>
            <div className="innerWrapper">
                <span>TodoTogether</span>
                <Link className="basicBtn" to="/notice">공지사항</Link>
            </div>
            <h1>{isLogin ? <BsFillBellFill/> : null}</h1>
            <div className="innerWrapper"> 
                <span className="basicBtn" onClick={ handlerModal }>로그인</span>
                <StyledLinkBtn to="/signup">회원가입</StyledLinkBtn>
            </div>
        </HeaderTag>
        {showing ? <LoginModal onClick={ handlerModal }/> : null}
        </>
    );
}
export default Header;

const HeaderTag = styled.div`
    position: ${props => props.scrollEvent ? "fixed" : "static"};
    background-color: ${props => props.scrollEvent ? "rgb(253, 246, 253)" : "inherit"};
    z-index: 1;   
    margin-top: -8px; 
    display: grid;
    grid-template-columns: 20% 60% 20%;
    width: 100%;
    height: 50px;
    align-items: center;
    justify-items: center;

    @media screen and (max-width: 960px) {
        grid-template-columns: 30% 45% 25%;
        font-size: 15px;
    }
    @media screen and (max-width: 920px) {
        grid-template-columns: 30% 45% 25%;
    }
    @media screen and (max-width: 700px) {
        grid-template-columns: 40% 30% 30%;
    }
    @media screen and (max-width: 500px) {
        grid-template-columns: 43% 23% 34%;
        font-size: 12px;
    }


    @keyframes colorOn {
        from {
            opacity: 0;
            background-color: white;
        };
        to {
            opacity: 1;
            background-color: rgb(253, 246, 253);
        };
    }
    @keyframes colorOff {
        from {
            opacity: 0;
            background-color: rgb(253, 246, 253);
        };
        to {
            opacity: 1;
            background-color: white;
        };
    }
    animation: ${props => props.scrollEvent ? "colorOn" : "colorOff"} 1s both linear;

`;
