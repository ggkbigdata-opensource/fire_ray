function showDataLineCharts(ele,monthX,totalY){
	  var chart = {
		      type: 'line'
		   };
		   var title = {
		      text: '消防数据走势',
		      x:-20
		   };
		   var xAxis = {
		      categories: monthX,
		      title: {
		         text: null
		      }
		   };
		   var yAxis = {
		      title: {
		         text: '数量 (次)'
		      },
		      plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	          }]
		   };
		   var tooltip = {
		      valueSuffix: '次'
		   };
		   var legend = {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		    };
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;   
		   json.tooltip = tooltip;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis;  
		   json.series = totalY;
		   json.legend = legend;
		   $('#'+ele).highcharts(json);
}