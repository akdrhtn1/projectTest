import React from "react";
import styled from "styled-components";
import {Link} from "react-router-dom";
import {theme} from "./theme";
export default function StyledLinkBtn ({ children, to }) {
    return <LinkTag to={to}>{children}</LinkTag>;
}

const LinkTag = styled(Link)`
background-color: ${theme.buttonColor};
border: 2px solid ${theme.fontColor};
border-radius: 15px;
padding: 10px;
color: white;
font-size: 14px;
text-decoration: none;
position: relative;

&:hover {
    animation: borderHover both 2s ease;
}
@keyframes borderHover {
    to {
        border: 2px solid ${theme.titleColor};
    }
}

&::after {
    position: absolute;
    content: '';
    width: 0px;
    height: 2px;
    background-color: ${theme.titleColor};
    left: 12px;
    bottom: -2px;
}
&::before {
    position: absolute;
    content: '';
    width: 0px;
    height: 2px;
    background-color: ${theme.titleColor};
    right: 12px;
    top: -2px;
}
&:hover::after, &:hover::before {
    animation: hovering both 1s ease;
}

@keyframes hovering {
    to { 
        width: 66%; 
    };
}

@media screen and (max-width: 471px) {
    font-size: 12px;
}

`;