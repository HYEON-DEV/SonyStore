import React, { useMemo, useEffect, useState } from 'react';
import styled from 'styled-components';
import { useSelector, useDispatch } from 'react-redux';
import { fetchBestProducts } from '../../slices/BestProductSlice';
import { Chart, CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement } from 'chart.js';
import { Bar } from 'react-chartjs-2';

Chart.register(CategoryScale, LinearScale, Title, Tooltip, Legend, BarElement);

const BestProductContainer = styled.div`
    background-color: rgba(0,0,0,0.02);
    flex: 1 0 50%;

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
        margin: 30px;
        height: 300px;   
    }
`;

const BestProduct = () => {
    const dispatch = useDispatch();
    const { weekly, monthly, status, error } = useSelector(state => state.BestProductsSlice);
    const [period, setPeriod] = useState('weekly');
    const [data, setData] = useState({ keys: [], values: [] });

    useEffect(() => {
        dispatch(fetchBestProducts());
    }, [dispatch]);

    useEffect(() => {
        if (period === 'weekly') {
            const keys = weekly.map(product => product.title);
            const values = weekly.map(product => product.cnt);
            setData({ keys, values });
        } else {
            const keys = monthly.map(product => product.title);
            const values = monthly.map(product => product.cnt);
            setData({ keys, values });
        }
    }, [weekly, monthly, period]);

    if (status === 'loading') {
        return <div>Loading...</div>;
    }

    if (status === 'failed') {
        return <div>Error: {error}</div>;
    }

    return (
        <BestProductContainer>
            <div className="container_title">
                <h2>인기 제품 TOP5</h2>
                <select className="dropdown" value={period} onChange={(e) => setPeriod(e.target.value)}>
                    <option value="weekly">주간</option>
                    <option value="monthly">월간</option>
                </select>
            </div>

            <div className="container">
                {data.keys.length > 0 && data.values.length > 0 && (
                    <Bar
                        data={{
                            labels: data.keys,
                            datasets: [
                                {
                                    label: '판매량',
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
                                    text: period === 'weekly' ? '주간 베스트 제품' : '월간 베스트 제품',
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
        </BestProductContainer>
    );
};

export default BestProduct;