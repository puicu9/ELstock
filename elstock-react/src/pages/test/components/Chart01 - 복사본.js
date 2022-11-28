import {useState, useEffect} from 'react';

import {Bar, Doughnut, Line} from 'react-chartjs-2';
import axios from 'axios' ;

const Contents = () => {


        // async와 await는 찾아 봐야 하는 데 아마도 비동기 통신과 관련된 듯함
        useEffect(() => {
                const fetchEvents = async () => {
                        const res = await axios.get("https://api.covid19api.com/total/dayone/country/kr")
                        console.log(res);
                        makeData(res.data) ;
                }


                fetchEvents();
        }, []) // useEffect(()

        return(
                <section>
                        <h2>국내 코로나 현황</h2>
                        <div className="contents">
                                <div>
                                        <Bar data={confirmedData}
                                                options={{title:{display:true, text:"누적 확진자 추이", fontSize:16}},
                                                        {legend:{display:true, position:"botton"}}
                                                }/>
                                </div>
                        </div>
                </section>
        );
}

export default Contents;
