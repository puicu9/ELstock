// npm install --save-dev faker@5.5.3
// import React from 'react';
// import {Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend} from 'chart.js';
// import { Bar } from 'react-chartjs-2';
// import faker from 'faker';

// ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);


// export default function Contents() {
  // return <Bar options={myoptions} data={mydata} />;
// }


// import { Chart as ChartJS } from 'chart.js/auto'    //미사용하지만 안적어주면 오류남
import { Bar } from 'react-chartjs-2';
import ChartDataLabels from 'chartjs-plugin-datalabels';

function Contents() {
	
	const data = {
        plugins: [ChartDataLabels],	//플러그인 사용을 위해 연결
        labels: labels, //그래프상 날짜 데이터
        datasets: [
            {
                label: eventTypeList[0],	//라벨명 ex)쓰러짐
                data: falldownData,			// 날짜 데이터 순서의 value list
                datalabels: dataFont,		//폰트 사이즈 및 색상 
                backgroundColor:objColor[0],//bar차트의 색상
                borderColor: objColor[0],	//bar의 테두리 색상
                order: 1,					//순서 non-important
            },
           //.........생략... 누적 바 차트의 데이터...
            {
                label: eventTypeList[6],
                data: crowdData,
                datalabels: dataFont,
                backgroundColor: objColor[6],
                borderColor: objColor[6],
                order: 1,
            },		//누적 bar 차트의 데이터 끝
            {
                label: "전체",		//라인차트 데이터
                data: total,		 //날짜 label데이터 순서 출력할 라인 차트의 데이터 리스트
                datalabels:{		// 라인차트의 CSS
                    // color: 'white'
                    color: lineColor,
                    backgroundColor : 'white',
                    font:{size:13,weight: 'bold'},
                },
                lineTension: 0.1, // 각 꼭지점 근처 라인의 border-radius(뾰족하게 할지, 둥글게 할지)  0-1 사이의 숫자
                backgroundColor: lineColor,
                borderColor: lineColor,
                borderDash: [5, 5],
                borderWidth : 1,
                fill: false,
                pointHoverRadius: 0,
                pointHoverBorderWidth: 0,
                type: "line",
                order: 0,
                pointRadius : 0,  //포인트 스타일 - 포인트 모양의 반지름 0일시, 그려지지않음
            },
        ],
      };
	  
	  const options = {
        interaction: {
            mode: 'index',  	//툴팁 전체 출력
            intersect: false,
        },
        maxBarThickness: 15,    // bar 타입 막대의 최대 굵기
        layout: {
            padding: {
                top : 30
            }
        },
        plugins: {
            legend: {
                position: 'bottom',		//레전드 위치 
            },
            title: {
                display: false,		//타이틀 
                text: "Total",
                fontSize: 25,
            },
            datalabels: {
                anchor: 'end',  //start , end 
                align: 'top',   //top bottom middle 데이터 라벨 표시 위치
                formatter: function(value, context) { 
                     //데이터 값이 0 이면 출력 안함
                    if(context.dataset.label !== '전체'){
                        if(value == 0){
                            return null;
                        }else{
                            return value;
                        }
                    }else{
                        if(value == 0){
                            return null;
                        }else{
                            let result = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
                            return result;
                        }
                    }
                },
            },
            tooltip: {	
                // backgroundColor: 'rgba(124, 35, 35, 0.4)',
                padding: 10,
                // bodySpacing: 5,     //툴팁 내부의 항목 간격
            }  
        },
        maintainAspectRatio: false, //false :  상위 div에 구속
        responsive: true, //false : 정적 true: 동적
        scales: {
            x: {
                stacked: true,
            },
            y: {
                stacked: true,
                // beginAtZero: true
            },
        },
        onClick: function(evt, element) {
            // onClickNot working element null
            console.log(evt, element);	//클릭시 이벤트 추가 가능
        }
    };
	  
	return (
		<div className="chart">
			<Bar
				data={data}
				options={options}
				plugins = {data.plugins}
			/>
		</div>
    );
}

export default Contents;