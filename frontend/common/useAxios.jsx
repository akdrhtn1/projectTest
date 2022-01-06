import axios from "axios";

export default function UseAxios ({url, callback, type}) {
    axios({
        url: url,
        method: type,
        baseURL: "http://localhost:8080",
        withCredentials: true,
    })
    .then(res => {
        callback(res.data);
    })
    .catch(error => {
        alert("관리자에게 문의하세요. (에러코드 : " + error + ")");
    })
}