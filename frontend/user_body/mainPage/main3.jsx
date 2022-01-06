import React from "react";
import img2 from "../../common/imgs/img2.png";
import { Link } from "react-router-dom";
import { BsReplyAllFill } from 'react-icons/bs';


export default function Main2 () {
    return (
        <div className="userBodyInnerWrapper">
            <div className="userBodyInnerDiv">
                <h1>지금까지 달려온 성취도를 직관적으로 파악할 수 있어요.</h1>
                <h5>나의 성실함은 어느정도 될까요? 한눈에 확인할 수 있어요. 다른 투게더들과 비교하여 분발할 점과 우수한 점을 파악할 수 있어요.</h5>
            </div>
            <div className="userBodyInnerDiv">
                <img src={img2} className="img1 main2imgSetting" />
            </div>
            <Link style={{color: "black", textDecoration: "none"}} to="/">이동하기<BsReplyAllFill/></Link>
        </div>
    );
}