import {combineReducers, createStore} from "redux";
import {certificationReducer} from "./certificationReducer";


const RootReducer = combineReducers({
    certificationReducer,
});

export const store = createStore(RootReducer);