import React, {memo, useMemo, useState, useEffect} from "react";
import styled from 'styled-components';

import { useSelector, useDispatch } from "react-redux";
import {getList} from '../../slices/SaleSlice';

import { Chart, CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement } from 'chart.js';
import {Bar} from 'react-chartjs-2';
Chart.register(CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement);


const SalesGraphContainer = styled.div`
    background-color: rgba(0,0,0,0.02);
    flex: 1 0 50%;

    /* h2 {
        text-align: center;
    } */

    .container_title {
        display: flex;
        align-items: center;

        * {
            margin-right: 30px;
        }

        .dropdown {
            height: 25px;
            width: 80px;
            text-align: center;
            margin-top: 5px;
        }
    }

    .container {
        /* background-color:rgb(219, 232, 218); */
        margin: 30px;
        height: 300px;        
    }
    
`;

const SalesGraph = memo( () => {
    const dispatch = useDispatch();
    const {item} = useSelector( state => state.SaleSlice );
    const [period, setPeriod] = useState('weekly');
    const [data, setData] = useState({ keys: [], values: [] });

    // const { keys, values } = useMemo( () => {

    //     if(!item) {
    //         return {keys:null, values:null};
    //     }

    //     const keys = item.map( v => v.date );
    //     // console.log(keys);

    //     const values = item.map( v => v.total );
    //     // console.log(values);

    //     // const result = { keys, values };
    //     // console.log(result);
    //     return { keys, values };

    // }, [item] );

    useEffect(() => {
        const url = period === 'weekly' ? '/api/today_sales/day' : '/api/today_sales/day?day=28';
        dispatch(getList({ url }));
    }, [dispatch, period]);

    useEffect( () => {
        
        if (!item) return;

        let keys = [];
        let values = [];

        if (period === 'weekly') {
            keys = item.map(v => v.date);
            values = item.map(v => v.total);
        } else {
            const weeks = [];
            for (let i=0; i<item.length; i+=7) {
                weeks.push( item.slice( i, i+7 ) );
            }
            // console.log(weeks);

            const weeklyData = weeks.map( week => week.reduce( (acc, cur) => acc + cur.total, 0 ) );
            // console.log(weeklyData);

            // keys = weeklyData.map( (v, i) => `-${weeklyData.length-i}주` );
            keys = weeks.map( (v,i) => `${v[0].date} ~ ${v[v.length-1].date}`);
            values = weeklyData;
        }

        setData({ keys, values });

    }, [item, period]);


    return(
        <SalesGraphContainer>
            <div className="container_title">
                <h2> 총 매출 </h2>

                <select className="dropdown" value={period} onChange={(e) => setPeriod(e.target.value)}>
                    <option value="weekly">주간</option>
                    <option value="monthly">월간</option>
                </select>
            </div>

            <div className="container">
                {/* {item && JSON.stringify(item).substring(0,50)} */}
                {/* {keys && JSON.stringify(keys)} */}
                {/* {values && JSON.stringify(values)} */}
                {data.keys && data.values && (
                    <Bar
                        data={{
                            labels: data.keys,
                            datasets: [
                                {
                                    label: '원',
                                    data: data.values,
                                    backgroundColor: 'rgba(219, 232, 218, 0.5)',
                                    borderColor: 'rgba(219, 232, 218, 1)',
                                    borderWidth: 1
                                }
                            ]
                        }}
                        options={{
                            responsive: true,
                            maintainAspectRatio: false,
                            plugins: {
                                legend: {
                                    position: 'bottom'
                                },
                                title: {
                                    display: true,
                                    text: period==='weekly' ? "주간 일별 매출 집계" : "월간 주별 매출 집계",
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