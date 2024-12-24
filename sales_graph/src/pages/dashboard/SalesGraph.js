import React, {memo, useMemo} from "react";
import styled from 'styled-components';

import { useSelector } from "react-redux";

import { Chart, CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement } from 'chart.js';
import {Bar} from 'react-chartjs-2';
Chart.register(CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement);

const SalesGraphContainer = styled.div`
    background-color: rgba(0,0,0,0.02);
    flex: 1 0 50%;

    h2 {
        text-align: center;
    }

    .container {
        /* background-color:rgb(219, 232, 218); */
        margin: 10px;
        height: 300px;        
    }
    
`;

const SalesGraph = memo( () => {
    const {item} = useSelector( state => state.SaleSlice );

    const { keys, values } = useMemo( () => {

        if(!item) {
            return {keys:null, values:null};
        }

        const keys = item.map( v => v.date );
        // console.log(keys);

        const values = item.map( v => v.total );
        // console.log(values);

        // const result = { keys, values };
        // console.log(result);
        return { keys, values };

    }, [item] );


    return(
        <SalesGraphContainer>
            <h2> 총 매출 </h2>

            <div className="container">
                {/* {item && JSON.stringify(item).substring(0,50)} */}
                {/* {keys && JSON.stringify(keys)} */}
                {/* {values && JSON.stringify(values)} */}
                {keys && values && (
                    <Bar
                        data={{
                            labels: keys,
                            datasets: [
                                {
                                    label: '원',
                                    data: values,
                                    backgroundColor: 'rgba(219, 232, 218, 0.5)',
                                    borderColor: 'rgba(219, 232, 218, 1)',
                                    borderWidth: 1
                                }
                            ]
                        }}
                        options={{
                            responsive: true,
                            maintainAspecRatio: false,
                            plugins: {
                                legend: {
                                    position: 'left'
                                },
                                title: {
                                    display: true,
                                    text: "주간 일별 매출 집계",
                                    font: {
                                        size: 18,
                                        color: "#000",
                                    }
                                }
                            }
                        }}
                    />
                )}
                
            </div>
        </SalesGraphContainer>
    );
} );

export default SalesGraph;