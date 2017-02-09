/**
 * 展示饼状图
 * @param ele  元素
 * @param title  标题
 * @param data  数据
 */
function showDataPieCharts(ele,title,data,plotOptions){
	  var chart = {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        };
		   var title = {
		      text: title,
		   };
		   var tooltip = {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		   };
//	       var plotOptions = {
//	    		 pie: {
//	                allowPointSelect: true,
//	                cursor: 'pointer',
//	                events: {
//	                    click
//	                },
//	                dataLabels: {
//	                    enabled: true,
//	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
//	                    style: {
//	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
//	                    }
//	                }
//	            }
//	        };
	       var  series = [{
	            name: '占比',
	            colorByPoint: true,
	            data: data
	        }]
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;   
		   json.tooltip = tooltip;
		   json.series = series;
	       json.plotOptions = plotOptions;
		   $('#'+ele).highcharts(json);
}
	 