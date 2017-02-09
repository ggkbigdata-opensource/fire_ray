function showFireEventCharts(title,series,districtId,streetId,blockId){
	  var chart = {
		      type: 'column'
		   };
		   var title = {
		      text: title   
		   };
		   var xAxis = {
		      categories: ['原始火灾警情', '确认火灾警情', '冒烟火灾警情'],
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
			        			// 点击了柱状图是哪个年份及那种类型火情
			        			var fireTypeName = e.point.category;
			        			var year = this.name;
								var _dateTime = getFireTime(year);
			        			console.log("TO："+'app/page/fireEventDetailPie?fireTypeName='+fireTypeName+"&monthStart="+_dateTime.start+"&monthEnd="+_dateTime.end+"&districtId="+districtId+"&streetId="+streetId+"&blockId="+blockId);
			        			window.parent.addTab('警情详情','app/page/fireEventDetailPie?fireTypeName='+fireTypeName+"&monthStart="+_dateTime.start+"&monthEnd="+_dateTime.end+"&districtId="+districtId+"&streetId="+(streetId?streetId:'null')+"&blockId="+blockId);
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
		   $('#containerFireEvent').highcharts(json);
}

function getFireTime(year){
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
	return dateTime;
}