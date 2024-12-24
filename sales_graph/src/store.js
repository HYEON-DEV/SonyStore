import { configureStore } from '@reduxjs/toolkit';
import SaleSlice from './slices/SaleSlice';


const store = configureStore({
    reducer: {
        SaleSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    })
});

export default store;