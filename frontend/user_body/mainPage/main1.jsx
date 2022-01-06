import React from "react";
import { Link } from "react-router-dom";

import styled from "styled-components";

import img1 from "../../common/imgs/winter1.png";
import img2 from "../../common/imgs/winter2.png";
import { BsReplyAllFill } from 'react-icons/bs';

export default function Main1 () {
    return (
        <div className="userBodyInnerWrapper">

            <div className="userBodyInnerDiv">
                <h1>실현 가능한, 생생한 목표를 실천해보세요.</h1>
                <h4>To do together를 통해 구체적인 목표를 세우고 실천할 수 있습니다. 공통된 목표를 가진 투게더들을 보며 노하우도 전달받아 보세요.</h4>
            </div>
            <div className="userBodyInnerDiv">
                <img src={img1} className="img1"/>
                <img src={img2} className="img2"/>
            </div>
            <Link style={{color: "black", textDecoration: "none"}} to="/">이동하기<BsReplyAllFill/></Link>
        </div>
    );
}

const UserBodyInnerDiv = styled.div`
    justify-self: center;
    width: 90%;
    font-size: 2.2vw;

    @media screen and (max-width: 720px) {
        font-size: 15px;
    }
`;
