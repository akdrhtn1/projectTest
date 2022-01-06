import React from "react";
import styled from "styled-components";
import { theme } from "../../../common/theme";
import Certification from "../certification/certification";

export default function CertificationInput () {
    return (
        <CertificationWrapper>
            <input type="text" name="certificationEmail" id="styledCertificationInputTag" />
            <span className="certifiOkSpan">확인</span>
            <Certification>재전송</Certification>
            {/* 유알엘, 타입, 콜백함수 넣기 */}
        </CertificationWrapper>
    );
}

const CertificationWrapper = styled.div`
    position: relative;
    left: 6%;
    font-size: 1.6vw;
    width: 90%;
    color: ${theme.titleColor};
    margin-right: 3px;
    display: inline-flex;
    justify-content: space-evenly;
    align-items: center;
    cursor: pointer;
    #styledCertificationInputTag {
        width: 40%;
        background-color: rgb(224, 224, 224);
    };
    span { height: 10px; padding-top: 5px;}
    .certifiOkSpan { transform: translate(0px,-6px); }
    
`;