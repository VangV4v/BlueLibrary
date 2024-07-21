import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import AuthenticateResponseModel from "../../models/response/AuthenticateResponseModel";
import AuthenticateRequestModel from "../../models/request/AuthenticateRequestModel";
import authenticateApi from "../../api/authapi/authapi";

export const authenticateEmployee = createAsyncThunk(
    "auth/authEmployee", async (param: AuthenticateRequestModel) => {

        const response = await authenticateApi.authenticate(param);
        return response.data;
    }
)

interface AuthInfo {
    authResponse: AuthenticateResponseModel
}

const initialState: AuthInfo = {
    authResponse: {
        authenticated: false,
        expiration: '',
        jwt: '',
        role: ''
    }
};

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(authenticateEmployee.fulfilled, (state, action) => {
            state.authResponse = action.payload;
        })
    }
});

const { reducer } = authSlice;
export { authenticateEmployee };
export default reducer;