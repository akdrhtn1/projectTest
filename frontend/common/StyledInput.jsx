import React from "react";
import styled from "styled-components";
import { theme } from "./theme";

export default function StyledInput ({ id, name, type, onChange, Ref, value, labeling, onBlur, error, placeholder }) {
    return (
        <InputWrapper error={error} labeling={labeling} id={id}>
            <label htmlFor={id} style={{color:theme.fontColor}}>{labeling}</label>
                <InputTag id={id}
                        name={name}
                        type={type}
                        onChange={onChange}
                        ref={Ref}
                        value={value}
                        error={error}
                        onBlur={onBlur}
                        placeholder={placeholder}
                />
        </InputWrapper>
    );
}

const InputTag = styled.input`
    color: ${theme.fontColor};
    border: none;
    border-bottom: 2px solid ${theme.fontColor};
    width: 100%;
    background-color: inherit;

    ::placeholder {
        color: ${theme.fontColor};
        font-size: 8px;
        transform: translate(45px, 0px);
    }
`;
const InputWrapper = styled.div`
    &::after {
        content: '${props => props.error !== props.id ? '' : "올바른 " + props.labeling + " 양식을 입력해주세요."}';
        font-size: 10px;
        color: red;
        display: block;
    }
`;
