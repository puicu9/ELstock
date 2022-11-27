import {useState, useEffect} from 'react';
import axios from 'axios';
import {Bar} from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Tooltip,
} from 'chart.js';

// for "category" is not a registered scale
import Chart from 'chart.js/auto';

const Contents = () => {
	const [receivedData, setReceivedData] = useState(null);
	const [confirmedData, setConfirmedData] = useState({});
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState(null);
	
	ChartJS.register(
		CategoryScale,
		LinearScale,
		BarElement,
		Tooltip,
	);

	useEffect(() => {
		const fetchData = async () => {
			try {
				/* 요청이 시작 할 때에는 error 와 receivedData 를 초기화하고 */
				setError(null);
				setReceivedData(null);

				/* loading 상태를 true 로 바꿉니다. */
				setLoading(true);

				const url = 'https://api.covid19api.com/total/dayone/country/kr';
				const response = await axios.get(url);

				/* 데이터는 response.data 안에 들어있습니다. */
				setReceivedData(response.data);
				//console.log(response.data);
				makeData(response.data) ;

			} catch (err) {
				setError(err);
				console.log(err);
			} /* end try...catch */

			setLoading(false); /* 로딩이 끝났음 */

		}; /* end fetchData */
		
		const makeData = (items) => {
		// items.forEach(item => console.log(item)) ;
			// cur는 현재 데이터
			const arr = items.reduce((acc, cur) => {
				const currentDate = new Date(cur.Date) ;
				const year = currentDate.getFullYear();
				const month = currentDate.getMonth();
				const date = currentDate.getDate();
				//console.log(cur);
				console.log(year, month, date);

				const confirmed = cur.Confirmed ;
				const active = cur.Active ;
				const death = cur.Deaths ;
				const recovered = cur.Recovered ;

				const findItem = acc.find(a => a.year === year && a.month === month) ;
				
				if(year==2022){
					if(!findItem){
						acc.push({year, month, date, confirmed, active, death, recovered})
					}					
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

			//console.log('arr');
			//console.log(arr);

			// Bar Chart 영역
			const labels = arr.map(a => `${a.month+1}월`);
			setConfirmedData({
				labels:labels,
				datasets:[{
					label:"국내 누적 확진자",
					backgroundColor:"salmon",
					fill:true,
					data:arr.map(a=> a.confirmed)
				}]
			}) /*  setConfirmedData */
		} /* const makeData */

		fetchData(); /* call function */
	}, []); /* end useEffect */

	if(loading) return <div>로딩중..</div>;
	if(error) return <div>에러가 발생했습니다</div>;
	if(!receivedData) return null;
	
	return(
		<section>               
			<h2>국내 코로나 현황</h2>
			<div className="contents">
				<div>
					<Bar data={confirmedData}
						options={
							{title:
								{display:true, text:"누적 확진자 추이", fontSize:16}
							},
							{legend:
								{display:true, position:"botton"}
							}
						}
					/>
				</div>
			</div>			
		</section>
	);
}

export default Contents;
