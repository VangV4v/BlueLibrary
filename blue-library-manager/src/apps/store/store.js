import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../slice/auth-slice";

const defaultReducer = {
    auth: authReducer
};

const store = configureStore({
    reducer: defaultReducer
});

export default store;