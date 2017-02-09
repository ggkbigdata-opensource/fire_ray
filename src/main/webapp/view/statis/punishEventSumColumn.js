function showPunishEventCharts(title,series,districtId,streetId,blockId){
	  var chart = {
		      type: 'column'
		   };
		   var title = {
		      text: title   
		   };
		   //用这个标准术语：行政拘留、行政罚款、三停、临时查封统称为“消防执法”
		   var xAxis = {
		      categories: ['行政拘留', '行政罚款', '三停','临时查封'],
		      title: {
		         text: null
		      }
		   };
		   var yAxis = {
		      min: 0,
		      title: {
		         text: '数量 (次)',
		         align: 'high'
		      },
		      labels: {
		         overflow: 'justify'
		      }
		   };
		   var tooltip = {
		      valueSuffix: '次'
		   };
		   var plotOptions = {
			series: {
				        cursor: 'pointer',
				        events: {
				        		click: function(e) {
				        			var punishTypeName = e.point.category;
				        			var year = this.name;
									var dateTime = getPunishTime(year);
				        			window.parent.addTab('执法数据列表','app/page/punishEvent?punishTypeName='+punishTypeName+"&monthStart="+dateTime.start+
										'&monthEnd='+dateTime.end+'&districtId='+districtId+"&streetId="+streetId+"&blockId="+blockId);
				        		}
				        },
			  },
			  column:{
                  dataLabels:{
                      enabled:true // dataLabels设为true
                  }
              },
		      bar: {
		         dataLabels: {
		            enabled: true
		         }
		      }
		   };
		   var legend = {
		      layout: 'vertical',
		      align: 'left',
		      verticalAlign: 'top',
		      x: 65,
		      y: -10,
		      floating: true,
		      borderWidth: 1,
		      backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
		      shadow: true
		   };
		   var credits = {
		      enabled: false
		   };
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;   
		   json.tooltip = tooltip;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   json.legend = legend;
		   json.credits = credits;
		   $('#containerPunishEvent').highcharts(json);
}

function getPunishTime(year){
	var date = new Date();
	var month = date.getMonth();
	var dateTime = {
		start : year + "-01"
	};
	if(month == 0){
		dateTime.end = dateTime.start
	}else{
		dateTime.end = year + (month>9?"-"+month:"-0"+month);
	}
	console.info("dateTime",dateTime);
	return dateTime;
}
	 