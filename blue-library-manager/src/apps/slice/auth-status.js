import { createSlice } from "@reduxjs/toolkit";
import sessionStorage from "redux-persist/es/storage/session";

const authStatusSlice = createSlice({
    name: 'authStatus',
    initialState: {
        status: (sessionStorage.getItem("authStatus") && sessionStorage.getItem("authStatus")) || false
    },
    reducers: {
        changeAuthStatus: (state, action) => {
            state.status = action.payload;
        }
    }
});

const { reducer, actions } = authStatusSlice;
export const { changeAuthStatus } = actions;
export default reducer;