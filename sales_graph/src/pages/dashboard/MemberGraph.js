import React, {memo, useMemo, useEffect, useState} from 'react';
import styled from 'styled-components';

import { useSelector, useDispatch } from "react-redux";
import {getList} from '../../slices/MemberSlice'

import { Chart, CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement } from 'chart.js';
import { Bar } from 'react-chartjs-2';
Chart.register(CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement);

const MemberGraphContainer = styled.div`
    background-color: rgba(0,0,0,0.02);
    flex: 1 0 50%;

    /* h2 {
        text-align: center;
    } */

    .container_title {
        display: flex;
        align-items: center;
        padding-left: 30px;

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
        margin: 30px;
        height: 300px;
    }
    
`;

const MemberGraph = memo(() => {
    const dispatch = useDispatch();
    const {item} = useSelector( state => state.MemberSlice );
    const [period, setPeriod] = useState('weekly');
    const [data, setData] = useState({ keys: [], values: [] });

    const { keys, values } = useMemo( () => {

        if(!item) {
            return {keys:null, values:null};
        }

        const keys = item.map( v => v.date );
        // console.log("keys : " + keys);

        const values = item.map( v => v.count );
        // console.log("values : " + values);

        const result = { keys, values };
        // console.log("result : " + result);
        return { keys, values };

    }, [item] );

    useEffect(() => {
        const url = period === 'weekly' ? '/api/week_member' : '/api/month_member';
        dispatch(getList({ url }));
    }, [dispatch, period]);

    useEffect( () => {
        
        if (!item) return;

        let keys = [];
        let values = [];

        if (period === 'weekly') {
            keys = item.map(v => v.date);
            values = item.map(v => v.count);
        } else {
            keys = item.map(v => v.date);
            values = item.map(v => v.count);
        }

        setData({ keys, values });

    }, [item, period]);

    // console.log(item);

    return (
        <MemberGraphContainer>
            <div className="container_title">
                <h2> 가입자 수 </h2>

                <select className="dropdown" value={period} onChange={(e) => setPeriod(e.target.value)}>
                    <option value="weekly">주간</option>
                    <option value="monthly">월간</option>
                </select>
            </div>

            <div className="container">
            {/* {keys && JSON.stringify(keys)} */}
            {/* <br /> */}
            {/* {values && JSON.stringify(values)} */}
            {keys && values && (
                <Bar data={{
                    labels: keys, // x축
                    datasets: [{
                        label: "가입자 수",
                        data: values,
                        backgroundColor: 'rgba(255, 139, 165, 0.7)',
                        borderColor: 'rgba(255, 139, 165, 1)',
                        borderWidth: 1
                    }]
                }}
                options={{
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: 'bottom',
                        },
                        title: {
                            display: true,
                            text: period ==='weekly' ? "주간 일별 가입자 수 집계" : "월간 주별 가입자 수 집계",
                            font: {
                                size: 18,
                                color: "#000"
                            }
                        }
                    },
                }}/>
            )}
            </div>
        </MemberGraphContainer>
    );
});

export default MemberGraph;