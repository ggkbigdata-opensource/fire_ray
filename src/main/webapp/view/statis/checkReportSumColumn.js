function showCheckReportCharts(title,series,districtId,streetId,blockId){
	  var chart = {
		      type: 'column'
		   };
		   var title = {
		      text: title   
		   };
		   var xAxis = {
		      categories: ['初检数', '初检通过数', '复检数','复检通过数'],
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
				        			// 跳转页面：报告统计  点击任意的柱状图
				        			var year = this.name;
									var dateTime = getCheckTime(year);
				        			window.parent.addTab('报告统计','app/page/reportAnalysisPie?&districtId='+districtId+"&streetId="+streetId+"&blockId="+blockId+
										'&monthStart='+dateTime.start+'&monthEnd='+dateTime.end);
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
		   $('#containerCheckReport').highcharts(json);
}
function getCheckTime(year){
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