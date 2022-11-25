import {useState, useEffect} from 'react';
import axios from 'axios';
 
function GetSpringComponent01() {
	const [receivedData, setReceivedData] = useState(null);
	const [loading, setLoading] = useState(false); /* 로딩 됐는지, 체크 */
	const [error, setError] = useState(null); /* 에러 유무 */
 
	useEffect(() => {
		const fetchUsers = async () => { /* 비동기적으로 수행 */
			try {
				/* 요청이 시작 할 때에는 error 와 receivedData 를 초기화하고 */
				setError(null);
				setReceivedData(null);
				
				/* loading 상태를 true 로 바꿉니다. */
				setLoading(true);
				
				const url = 'http://localhost:8787/test99';
				const response = await axios.get(url);
				
				/* 데이터는 response.data 안에 들어있습니다. */
				setReceivedData(response.data); 
				console.log(response.data);

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
			<table border='1'>
			<tbody>
				<tr>
					<td>이름</td>
					
				</tr>
				<tr>
					<td>가격</td>
					
				</tr>
				<tr>
					<td>설명</td>
					
				</tr>
			</tbody>
			</table>
			<ul>
				
			</ul>
		</div>
	);
}
 
export default GetSpringComponent01;

