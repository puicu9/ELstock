import {useState, useEffect} from 'react';

import {Bar, Doughnut, Line} from 'react-chartjs-2';
import axios from 'axios' ;

const Contents = () => {
        const [quarantinedData, setQuarantinedData] = useState({})

        // async와 await는 찾아 봐야 하는 데 아마도 비동기 통신과 관련된 듯함
        useEffect(() => {
                const fetchEvents = async () => {
                        const res = await axios.get("https://api.covid19api.com/total/dayone/country/kr")
                        console.log(res);
                        makeData(res.data) ;
                }

                const makeData = (items) => {
                        // items.forEach(item => console.log(item)) ;
                        // cur는 현재 데이터
                        const arr = items.reduce((acc, cur) => {
                                const currentDate = new Date(cur.Date) ;
                                const year = currentDate.getFullYear();
                                const month = currentDate.getMonth();
                                const date = currentDate.getDate();
                                // console.log(cur, year, month, date);

                                const confirmed = cur.Confirmed ;
                                const active = cur.Active ;
                                const death = cur.Deaths ;
                                const recovered = cur.Recovered ;

                                const findItem = acc.find(a => a.year === year && a.month === month) ;

                                if(!findItem){
                                        acc.push({year, month, date, confirmed, active, death, recovered})
                                }

                                if(findItem && findItem.date < date){
                                        findItem.active = active;
                                        findItem.death = death;
                                        findItem.date = date;
                                        findItem.year = year;
                                        findItem.month = month;
                                        findItem.recovered = recovered;
                                        findItem.confirmed = confirmed;
                                }
                                return acc ;
                        }, [])

                        console.log(arr);

                        // Line Chart 영역
                        const labels = arr.map(a => '${a.month+1}월');
                        setQuarantinedData({
                                labels:labels,
                                datasets:[{
                                        label:"월별 격리자 현황",
                                        borderColor:"salmon",
                                        fill:false,
                                        data:arr.map(a=> a.active)
                                }]
                        }) // setQuarantinedData
                } // const makeData

                fetchEvents();
        }, []) // useEffect(()

        return(
                <section>
                        <h2>국내 코로나 현황</h2>
                        <div className="contents">
                                <div>
                                        <Line data={quarantinedData}
                                                options={
                                                        {title:{display:true, text:"월별 격리자 현황", fontSize:16}},
                                                        {legend:{display:true, position:"botton"}}
                                        }/>
                                </div>
                        </div>
                </section>
        );
}

export default Contents;
