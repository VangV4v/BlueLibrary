import { configureStore } from "@reduxjs/toolkit";
import authenticateReducer from "../slice/authenticate";

const defaultReducer = {
    auth: authenticateReducer,
};

const store = configureStore({
    reducer: defaultReducer,

});

export default store;