import React from "react";
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

const Swl = withReactContent(Swal)

export default function SweetAlert(title, footer) {
    Swl.fire({
        title: <p>{title}</p>,
        footer: footer,
        // didOpen: () => {
        //     // `MySwal` is a subclass of `Swal`
        //     //   with all the same instance & static methods
        //     Swl.clickConfirm()
        // }
        // }).then(() => {
        // return Swl.fire(<p>Shorthand works too</p>)
    })
}