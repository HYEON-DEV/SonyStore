import {createAsyncThunk} from '@reduxjs/toolkit';
import axiosHelper from '../helpers/AxiosHelper';
import reduxHelper from '../helpers/ReduxHelper';


export const getList = createAsyncThunk('MemberSlice/getList', async ( {url}, {rejectWithValue}) =>{
    let result = null;

    try {
        result = await axiosHelper.get(url);
    } catch(err) {
        result = rejectWithValue(err);
    }

    return result;
});

const MemberSlice = reduxHelper.getDefaultSlice('MemberSlice', [getList]);

export default MemberSlice.reducer;