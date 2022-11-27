// npm install --save-dev faker@5.5.3
import React from 'react';
import {Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement} from 'chart.js';
import { Line } from 'react-chartjs-2';
import faker from 'faker';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

function Contents(props) {
	const labels = props.chartData.map(one => one.name);
	
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

	const mydata = {
		labels,
		datasets: [{
			label: '국어',
			data: props.chartData.map(one => one.kor),
			borderColor: 'rgb(255, 99, 132)',
			backgroundColor: 'rgba(255, 99, 132, 0.5)',
		},
		{
			label: '영어',
			data: props.chartData.map(one => one.eng),
			borderColor: 'rgb(53, 162, 235)',
			backgroundColor: 'rgba(53, 162, 235, 0.5)',
		},],
	};

  return <Line options={myoptions} data={mydata} />;
}



export default Contents;