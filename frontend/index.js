import React from "react";
import ReactDOM from "react-dom";
import App from "./app";
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import { store } from "./common/reduxFolder/configureReducer";

ReactDOM.render(
    <React.StrictMode>
    <BrowserRouter>
    <Provider store={store}>
      <App />
    </Provider>
    </BrowserRouter>
  </React.StrictMode>,
    document.getElementById("root")
);