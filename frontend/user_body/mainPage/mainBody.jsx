import React from "react";
import styled from "styled-components";
import "../userBody.scss";
import Main1 from "./main1";

import Main2 from "./main2";
import Main3 from "./main3";
import MainGoSignup from "./mainGoSignup";


export default function MainBody () {
    return (
        <MainWrapper>
            <Main1 />
            <Main2 />
            <Main3 />
            <MainGoSignup />

        </MainWrapper>
    );
}

const MainWrapper = styled.div`
    display: grid;
    grid-template-rows: 500px 400px 400px 100px;
    width: 100%;

    row-gap: 100px;
    align-items: center;

    @media screen and (max-width: 720px) {
        row-gap: 40px;
    }

    gap-bottom: 20px;
    align-items: center;

`;

