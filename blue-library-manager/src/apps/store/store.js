import { configureStore, createStore } from "@reduxjs/toolkit";
import authReducer from "../slice/auth-slice";
import authStatusReducer from "../slice/auth-status";
import storage from "redux-persist/lib/storage"
import { persistReducer, persistStore } from "redux-persist";

const defaultReducer = {
    auth: authReducer,
    authStatus: authStatusReducer
};

// const persistConfig = {
//     key: 'root',
//     storage,
// }

// const persistedReducer = persistReducer(persistConfig, authReducer);

const store = configureStore({
    reducer: defaultReducer
});

// const newStore = createStore(persistedReducer);
// export const { persistor } = persistStore(newStore);
export default store;