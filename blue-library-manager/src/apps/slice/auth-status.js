import { createSlice } from "@reduxjs/toolkit";

const authStatusSlice = createSlice({
    name: 'authStatus',
    initialState: {
        status: false
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