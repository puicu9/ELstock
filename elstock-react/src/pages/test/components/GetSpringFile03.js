import {useState, useEffect} from 'react';
import axios from 'axios';

import Contents from './BarChart01';
import MyTable from './MakeTable01';
 
 
function GetSpringComponent03() {
	const [receivedData, setReceivedData] = useState(null);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState(null);
 
	useEffect(() => {
		const fetchUsers = async () => {
			try {
				/* 요청이 시작 할 때에는 error 와 receivedData 를 초기화하고 */
				setError(null);
				setReceivedData(null);
				
				/* loading 상태를 true 로 바꿉니다. */
				setLoading(true);
				
				const url = 'http://localhost:8989/thymeleaf/rest/ex03';
				const response = await axios.get(url);
				
				/* 데이터는 response.data 안에 들어있습니다. */
				setReceivedData(response.data); 
				//console.log(response.data);

			} catch (err) {
				setError(err);
			} /* end try...catch */
			
			setLoading(false); /* 로딩이 끝났음 */
			
		}; /* end fetchUsers */
 
		fetchUsers(); /* call function */ 
	}, []); /* end useEffect */
 
	if(loading) return <div>로딩중..</div>;
	if(error) return <div>에러가 발생했습니다</div>;
	if(!receivedData) return null; 

	return (	
		<div>
			<Contents chartData={receivedData} />		
			<MyTable sendData={receivedData} />				
		</div>
	);
}
 
export default GetSpringComponent03;

