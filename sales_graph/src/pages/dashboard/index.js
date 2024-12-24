import React, {memo, useEffect, useMemo} from 'react';
import styled from 'styled-components';
import Spinner from '../../components/Spinner';
import { useSelector, useDispatch } from 'react-redux';
import { getList } from '../../slices/SaleSlice';

import GraphBoard from './GraphBoard';

const PagesContainer = styled.div`

`;

const Pages = memo(() => {

    const {loading, status, message, item} = useSelector( state => state.SaleSlice);
    
    const dispatch = useDispatch();

    useEffect( () => {
        dispatch(getList());
    }, []);

    return (
        <PagesContainer>
            <Spinner loading={loading} />

            { status !== 200 && (
                <div className='error-info'>
                    <h1> {status} Error </h1>
                    <p> {message} </p>
                </div>
            ) }

            <GraphBoard />

            {/* {item && ( <p> {JSON.stringify(item)} </p> )} */}

        </PagesContainer>
    );
});

export default Pages;