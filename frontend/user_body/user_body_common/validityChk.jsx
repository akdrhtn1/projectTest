import React from "react";

export default function ValidityChk (e, callback) {
    const ele = e.target;
    if (ele.value.trim() !== "") {
        const id = ele.id;
        const value = ele.value;
    // 아이디 체크
        if (id === "email" || id === "anotherEmail") {
            if (!/^(?=.*[a-z||A-Z||0-9]).{5,25}$/.test(value)) {
                return callback(id, id);
            } else if (/^(?=.*[가-하||ㄱ-ㅎ||ㅏ-ㅓ]).{1,25}$/.test(value)) {
                return callback(id, id);
            } else if (/^(?=.*[!#$%^&*=+-?]).{1,25}$/.test(value) && 
                        !/^(?=.*[.]).{1,25}$/.test(value) || 
                        !/^(?=.*[@]).{1,25}$/.test(value)) {
                return callback(id, id);
            } else {
                return callback(id, "");
            }
        }    
    // 패스워드 체크
        if (id === "pw") {
            if (!/^(?=.*[a-z||A-Z]).{8,25}$/.test(value) ||
                !/^(?=.*[0-9]).{1,25}$/.test(value) ||
                !/^(?=.*[!@#$%^&*=+-?])/) {
                return callback(id, id);
            } else {
                return callback(id, "");
            }
        }
    // 이름 체크
        if (id === "name") {
            if (/^(?=.*[!@#$%^&*=+-?||0-9]).{1,25}$/.test(value)) {
                return callback(id, id);
            } else {
                return callback(id, "");
            }
        }
    // 닉네임 체크
        if (id === "nickname") {
            if (/^(?=.*[!@#$%^&*=+-?]).{1,25}$/.test(value)) {
                return callback(id, id);
            } else {
                return callback(id, "");
            }
        }
    // 전화번호 체크
        if (id === "phone") {
            if (/^(?=.*[!@#$%^&*=+-?]).{1,25}$/.test(value)) {
                return callback(id, id);
            } else {
                return callback(id, "");
            }
        }
    }
}