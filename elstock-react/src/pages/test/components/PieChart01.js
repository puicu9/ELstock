import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

export default function Contents(props) {
	const total_jumsu = props.chartData.map(one => one.kor + one.eng + one.math);
	console.log(total_jumsu);
	
	const mydata = {	
		labels: props.chartData.map(one => one.name),
		datasets: [
			{
				label: '# of Votes',
				data: total_jumsu,
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
				],
				borderWidth: 1,
			},
		],
	};
	
	const myoptions = {
		responsive: false,		
	};
	
	return <Pie data={mydata} options={myoptions} />;
}
