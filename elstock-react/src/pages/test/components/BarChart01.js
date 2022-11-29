import React from 'react';
import {Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend} from 'chart.js';
import { Bar } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

function Contents(props) {
	console.log('props.chartData');
	console.log(props.chartData);
	
	const math_data = props.chartData.map(one => one.math);
	//console.log(math_data)
	
	/* data section begin */
	const mydata = {
		labels: props.chartData.map(one => one.name),
		datasets: [
			{
				label: '국어',
				data: props.chartData.map(one => one.kor),
				backgroundColor: 'rgba(255, 99, 132, 0.7)',
			},
			{
				label: '영어',
				data: props.chartData.map(one => one.eng),
				backgroundColor: 'rgba(53, 162, 235, 0.7)',
			},	
			{
				label: '수학',
				data: math_data,
				backgroundColor: 'rgba(53, 120, 222, 0.7)',
			},				
    	],
	};	
	/* data section end */
	
	/* option section begin */
	const myoptions = {
		responsive: true,
		plugins: {
		legend: {
			position: 'top',
		},
		title: {
			display: true,
			text: '학생들 시험 점수',
		},
		},
	};
	/* option section end */
	
	return <Bar options={myoptions} data={mydata} />;
}

export default Contents;