import {createAsyncThunk} from '@reduxjs/toolkit';
import axiosHelper from '../helpers/AxiosHelper';
import reduxHelper from '../helpers/ReduxHelper';

export const getList = createAsyncThunk('SaleSlice/getList', async ( {url}, {rejectWithValue}) =>{
    let result = null;
    let args = {_sort: 'id', _order: 'desc'};

    try {
        result = await axiosHelper.get(url, args);
    } catch(err) {
        result = rejectWithValue(err);
    }

    return result;
});

const SaleSlice = reduxHelper.getDefaultSlice('SaleSlice', [getList]);

export default SaleSlice.reducer;

