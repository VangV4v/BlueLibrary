import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authEmployeeAPI from "../../api/auth/auth-employee";

export const authenticateEmployee = createAsyncThunk(
    "auth/authEmployee", async (param) => {

        const response = await authEmployeeAPI.authenticate(param);
        return response.data;
    }
)

const authSlice = createSlice({

    name: 'auth',
    initialState: {
        authResponse: JSON.parse(sessionStorage.getItem("authResponse"))
            || {
            authenticated: false,
            expiration: new Date().getTime(),
            jwt: '',
            role: ''
        }
    },
    reducers: {
        logoutEmployee: (state) => {
            state.authResponse = {
                authenticated: false,
                expiration: new Date().getTime(),
                jwt: '',
                role: ''
            }
        }
    },
    extraReducers: (builder) => {
        builder.addCase(authenticateEmployee.fulfilled, (state, action) => {
            state.authResponse = action.payload;
        })
    }
});

const { reducer, actions } = authSlice;
export const { logoutEmployee } = actions;
export default reducer;