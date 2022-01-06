import React, { useRef, useState } from "react";
import alternative from "../../common/imgs/alternative.png";
import Certification from "../user_body_common/certification/certification";
import styled from "styled-components";

export default function RightSignup () {
    const [ imgValue, setImgValue ] = useState();
    // 파일 미리보기 및 벨류업데이트
    const handlerValue = (e) => {    
        if (e.target.files.length !== 0) {
            let fileData = e.target.files[0];
            const fileReader = new FileReader();

            fileReader.readAsDataURL(fileData, "utf-8");

            fileReader.onload = (res) => {
                imgRef.current.src = res.target.result;
            };
            setImgValue(fileData.name);
        };
    }

    //타겟
    let imgRef = useRef();

    return (
        <>  
            <img ref={e => imgRef.current = e} 
                 src={alternative}
                 onError={e => e.src = alternative} 
                 className="rightSignupUserImg"/>

            <div className="inputFile">
                <input type="file"
                    name="profile"
                    id="profile"
                    onChange={e => handlerValue(e)}/>
            <Certification Style={{top: "205px", padding: "5px 10px 5px 10px"}}>인증하기</Certification>
            {/* 유알엘, 타입, 콜백함수 넣기 */}
            </div>
        </>
    );
}
