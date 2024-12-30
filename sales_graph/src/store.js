import { configureStore } from '@reduxjs/toolkit';
import SaleSlice from './slices/SaleSlice';
import MemberSlice from './slices/MemberSlice'


const store = configureStore({
    reducer: {
        SaleSlice,
        MemberSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    })
});

export default store;