import React from "react";
import img1 from "../../common/imgs/img1.png";
import { Link } from "react-router-dom";
import { BsReplyAllFill } from 'react-icons/bs';

export default function Main2 () {
    return (
        <div className="userBodyInnerWrapper">
            <div className="userBodyInnerDiv main2Img">
                <img src={img1} className="img1 main2imgSetting" style={{left: "5%"}}/>
            </div>
            <div className="userBodyInnerDiv main2Div">
                <h1>다양한 목표들을 한눈에 볼 수 있어요.</h1>
                <h4>아직 뚜렷한 목표를 세우지 못 했다면 다른 투게더들이 세운 목표를 먼저 확인하고 결정할 수 있어요.</h4>
            </div>
            <Link style={{color: "black", textDecoration: "none"}} to="/" className="main2Link">이동하기<BsReplyAllFill/></Link>
        </div>
    );
}