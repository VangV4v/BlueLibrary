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
        authResponse: {
            authenticated: false,
            expiration: '',
            jwt: '',
            role: ''
        }
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(authenticateEmployee.fulfilled, (state, action) => {
            state.authResponse = action.payload;
        })
    }
});

const { reducer } = authSlice;
export default reducer;