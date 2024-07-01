import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authenticateApi from "../../../api/authenticate/AuthenticateApi";

export const authenticate = createAsyncThunk(
    'auth/login',
    async (param) => {
        const response = await authenticateApi.login(param);
        const loginInfo = {
            status: response.status,
            statusText: response.statusText,
            data: response.data,
            config: response.config,
        };
        sessionStorage.setItem('loginInfo', JSON.stringify(loginInfo));
        return loginInfo;
    }
);

const authenticateSlice = createSlice({

    name: 'auth',
    initialState: JSON.parse(sessionStorage.getItem('loginInfo')) || {},
    reducers: {
    },
    extraReducers: (builder) => {
        builder.addMatcher(authenticate.fulfilled, (state, action) => {

            state.loginInfo = action.payload;
        })
    }
});

const { reducer } = authenticateSlice;
export default reducer;