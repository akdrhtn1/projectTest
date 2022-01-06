import React from "react";
import styled from "styled-components";
import { device, theme } from "./theme";

export default function CommonModal ({children, onClick, size}) {

    return (
        <ModalWrapper>
            <InnerWrapper>
                <div style={{justifySelf:"end", cursor:"pointer", alignSelf:"center"}} onClick={onClick}>X</div>
                <div style={{justifySelf:"center"}}>{children}</div>
            </InnerWrapper>
        </ModalWrapper>
    );
}

const ModalWrapper = styled.div`
    position: fixed;
    width: 100%;
    min-height: 600px;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: 0;
    z-index: 4;
    &::before {
        position: absolute;
        content: '';
        width: 100%;
        height: 100%;
        opacity: 0.5;
        background-color: white;
    };

    animation: modalAni both 1s ease;
    @keyframes modalAni {
        to {
            opacity: 1;
        }
    }
`;

const InnerWrapper = styled.div`
    display: grid;
    grid-template-rows: 10% 90%;
    grid-template-columns: 90%;
    z-index: 1;
    width: ${props => props.size === 'big' ? '70%' : '420px'};
    height: 50%;
    align-items: stretch;
    justify-content: center;
    background-color: ${theme.boxColor};
    border-radius: 15px;

    ${device.mobile} {
        transform: translate(0px, 300px);
        animation: modalMobile both 1s ease;
    };

    @keyframes modalMobile {
        to {
            transform: translate(0px, 0px);
        };
    };
`;
