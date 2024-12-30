import { configureStore } from '@reduxjs/toolkit';
import SaleSlice from './slices/SaleSlice';
import MemberSlice from './slices/MemberSlice'
import BestProductsSlice from './slices/BestProductSlice';


const store = configureStore({
    reducer: {
        SaleSlice,
        MemberSlice,
        BestProductsSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    })
});

export default store;