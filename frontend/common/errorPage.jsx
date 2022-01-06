import React, {useState, useEffect} from "react";
import styled from "styled-components";
import errorImg from "./imgs/errorpage.png";
import {device, theme} from "./theme";
import StyledLinkBtn from "./StyledLinkBtn";

export default function ErrorPage ({error}) {
    const [errorInfo, setErrorInfo] = useState(null);

    useEffect((prev, now) => {
        if (prev !== now) {
            setErrorInfo(error);
        }
    }, [errorInfo]);

    return (
        <ErrorTagsWrapper>
            <ErrorTagsInnerWrapper>
                <img src={errorImg} alt="사진" style={{width:"98%",heigth:"98%"}}></img>
                <div>
                    <h1>{error ? error : "페이지를 찾을 수 없습니다."}</h1>
                    <h3>관리자에게 문의하세요. 02-000-0000</h3>
                    <StyledLinkBtn to="/">메인페이지로</StyledLinkBtn>
                </div>
            </ErrorTagsInnerWrapper>
        </ErrorTagsWrapper>
    );
}

const ErrorTagsWrapper = styled.div`
    width: 100%;
    min-height: 600px;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;

`;

const ErrorTagsInnerWrapper = styled.div`
    display: grid;
    grid-template-columns: 50% 50%;
    width: 70%;
    align-items: center;
    justify-content: center;

    h1 {
        color: ${theme.adminFontColor2};
    };
    h3 {
        color: ${theme.adminFontColor};
    };
    ${device.tablet} {
        font-size: ${theme.fontSizeMobileSmall};
    };

    @media screen and (max-width: 1092px) {
        font-size: 14px;
    }
    @media screen and (max-width: 861px) {
        font-size: 11px;
    }

    @media screen and (max-width: 690px) {
        grid-template-columns: 80%;
        grid-template-rows: 50% 50%;
        animation: slideLeft both 1s ease-out;

        @keyframes slideLeft {
            from { transform: translate(200px, 0px); };
            to { transform: translate(0px, 0px); };
        }
      };
`;