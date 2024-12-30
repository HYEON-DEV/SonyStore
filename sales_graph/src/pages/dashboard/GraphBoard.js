import React, {memo} from "react";
import styled from 'styled-components';

import SalesGraph from "./SalesGraph";
import MemberGraph from "./MemberGraph";
import BestProduct from "./BestProduct";

const GraphBoardContainer = styled.div`
    * {
        margin-top: 20px;
    }
`;

const GraphBoard = memo( () => {
    return(
        <GraphBoardContainer>
            <SalesGraph />
            <MemberGraph />
            <BestProduct />
        </GraphBoardContainer>
    );
} );

export default GraphBoard;