function MyTable(props) {
	console.log('props.sendData');
	console.log(props.sendData);
	
	return (
		<table border="1">
			<thead>
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>국어</td>
					<td>영어</td>
					<td>수학</td>
					<td>총점</td>					
				</tr>
			</thead>
			<tbody>
			{
				props.sendData.map((item) => (					
					<tr key={item.id}>
						<td>{item.id}</td>
						<td>{item.name}</td>
						<td>{item.kor}</td>
						<td>{item.eng}</td>
						<td>{item.math}</td>
						<td>{item.kor+item.eng+item.math}</td>
					</tr>					
				))				
			}
			</tbody>
		</table>	
	);
}

export default MyTable;