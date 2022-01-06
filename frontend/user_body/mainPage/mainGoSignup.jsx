import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { theme } from "../../common/theme";
import { BsFillArrowRightCircleFill } from "react-icons/bs";

function MainGoSignup () {
    return (
        <MainSignupBar>
            <span>바로시작하기<BsFillArrowRightCircleFill/></span>
        </MainSignupBar>
    );
}

const MainSignupBar = styled(Link)`
    background-color: ${theme.buttonColor};
    width: 100%;
    height: 100px;
    display: flex;
    justify-content: center;
    font-size: 2.2vw;
    align-items: center;
    text-decoration: none;
    color: ${theme.titleColor};
    
    span {
        width: 20%;
        display: flex;
        justify-content: space-evenly;
    };
    @media screen and (max-width: 720px) {
        height: 70px;
        font-size: 15px;
        span {
            width: 25%;
            display: flex;
            justify-content: space-evenly;
        }
    };


`;

export default MainGoSignup;