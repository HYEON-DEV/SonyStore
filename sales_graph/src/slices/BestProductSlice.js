import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "/api/today_best_products";

export const fetchBestProducts = createAsyncThunk("BestProductsSlice/fetchBestProducts", async () => {
  const response = await axios.get(API_URL);
 
  return response.data;
});

const BestProductsSlice = createSlice({
  name: "BestProductsSlice",
  initialState: {
    weekly: [],
    monthly: [],
    status: 'idle',
    error: null
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchBestProducts.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchBestProducts.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.weekly = action.payload.weekly;
        state.monthly = action.payload.monthly; 
      })
      .addCase(fetchBestProducts.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  }
});

export default BestProductsSlice.reducer;