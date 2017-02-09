<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>警情统计管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/exporting.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/export-csv.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/fireEventSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/punishEventSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/checkReportSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataLine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataPie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
<style type="text/css">
	.panel .combo-panel{
		border-top:0;
		line-height: 20px;
		padding:0 0 5px 0;
		color: #666;
	}
	.ConDiv text{
		font-weight: 500 !important;;
	}
</style>
</head>
<body class="FireES">
<div id="tblQuery" style="padding:3px">
	<div class="inputDiv">
		<div class='inputDivEg'>
			<span class="inputLabel">行政区:</span>
			<input id="districtId" class="easyui-combobox inputDiv-input" data-options="editable:false,width:120">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">街道:</span>
			<input id="streetId" class="easyui-combobox inputDiv-input" data-options="editable:false,width:120">
		</div>
		 <div class='inputDivEg'>
			 <span class="inputLabel">社区:</span>
			 <input id="blockId" class="easyui-combobox inputDiv-input" data-options="editable:false,width:120">
		 </div>
		<a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
	</div>

<div class="ConDiv">
	<div class="ConTitle positionR"><a href="#">消防数据走势</a>
	<form id="form1" action="" method="post">
		<div class="print">
			<input type="hidden" name="svg" id="svg" />
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back" onclick="exportHighcharts('word');">导出 word</a>
			<%--<input id="btn_word" type="button" value="导出word" onclick="exportHighcharts('word');"/>--%>
		</div>
	</form>
	</div>
	<div id="containerData" style="max-width: 1000px; height: 400px;"></div>
</div>
<div class="ConDiv clearB">
	<div class="ConTitle" id="fireEventData"><a class="ConTitleHref" href="#">火情数据</a></div>
	<div id="containerFireEvent" style="width: 40%; height: 400px;float:left;padding-left: 50px;"></div>
	<div id="fireEventDesc" class="punishEventDescClass">
		<!-- <span id="fireStartTime"></span>  至	<span id="fireEndTime"></span> -->
		<ul>
			<li>原始火灾警情今年 <span id="originalFireThis"></span> 起，去年 <span id="originalFireLast"></span> 起
				<span id="originalFireDescRatio">同比增长<span id="originalFireRatio"></span></span>
			</li>
			<li>	确认火灾警情今年 <span id="confirmFireThis"></span> 起，去年 <span id="confirmFireLast"></span> 起
			<span id="confirmFireDescRatio">同比增长<span id="confirmFireRatio"></span></span>
			</li>
			<li>冒烟火灾警情今年 <span id="smokeFireThis"></span> 起，去年 <span id="smokeFireLast"></span> 起
			<span id="smokeFireDescRatio">同比增长<span id="smokeFireRatio"></span></span>
			</li>
		</ul>
	</div>
	<div class="clearB"></div>
</div>
<!-- <div  style="margin: 0 auto;width:100%;height:500px;">
	<div style="font-size:16px;width:10%; height: 400px;float:left;" id="checkReportData"><a href="#"  style="text-decoration: none;">报告数据</a></div>
	<div id="containerCheckReport" style="width: 40%; height: 400px; float:left;padding-left: 50px;"></div>
	<div id="checkReportDesc" style="width: 40%; height: 400px;float:left;padding-left: 50px;padding-top: 100px;font-size: larger;">


		<ul>

			 <li style="margin-bottom:20px">初检数今年 <span id="checkReportNumThis"></span>，去年 <span id="checkReportNumLast"></span>
				<span id="checkReportDescRatio">同比增长<span id="checkReportRatio"></span></span>
			</li>

			<li style="margin-bottom:20px">初检通过数今年 <span id="checkPassReportNumThis"></span>，去年 <span id="checkPassReportNumLast"></span>
			<span id="checkPassReportDescRatio">同比增长<span id="checkPassReportRatio"></span></span>
			</li>

			<li style="margin-bottom:20px">复检数今年 <span id="reCheckReportNumThis"></span>，去年 <span id="reCheckReportNumLast"></span>
			<span id="reCheckReportDescRatio">同比增长<span id="reCheckReportRatio"></span></span>
			</li>

			<li style="margin-bottom:20px">复检通过数今年 <span id="reCheckPassReportNumThis"></span>，去年 <span id="reCheckPassReportNumLast"></span>
			<span id="reCheckPassReportDescRatio">同比增长<span id="reCheckPassReportRatio"></span></span>
			</li>

		</ul>

	</div>
</div> -->
	<div class="clearB"></div>
<div class="ConDiv">
	<div class="ConTitle" id="punishEventData"><a class="ConTitleHref" href="#">执法数据</a></div>
	<div id="containerPunishEvent" style="width: 40%; height: 400px; float:left;padding-left: 50px;"></div>
	<div id="punishEventDesc" class="punishEventDescClass">
		<ul>
			<li>行政拘留 <span id="detainedNumThis"></span>起，去年 <span id="detainedNumLast"></span>起
					<span id="detainedDescRatio">同比增长<span id="detainedRatio"></span></span>
			</li>
			<li>行政罚款 <span id="fineNumThis"></span>起，去年 <span id="fineNumLast"></span>起
					<span id="fineDescRatio">同比增长<span id="fineRatio"></span></span>
			</li>
			<li>三停 <span id="stopNumThis"></span>起，去年 <span id="stopNumLast"></span>起
					<span id="stopDescRatio">同比增长<span id="stopRatio"></span></span>
			</li>
			<li>临时查封 <span id="closeNumThis"></span>起，去年 <span id="closeNumLast"></span>起
					<span id="closeDescRatio">同比增长<span id="closeRatio"></span></span>
			</li>
	
		</ul>
	</div>
	<div class="clearB"></div>
</div>

<script type="text/javascript">
var fireResult;
$('#fireEventData').click(function () {
	   window.parent.addTab('消防火情','app/page/fireEvent');
});

$('#checkReportData').click(function () {
	   window.parent.addTab('检测报告','app/page/checkReport');
});

$('#punishEventData').click(function () {
	   window.parent.addTab('消防执法','app/page/punishEvent');
});
var showDistrict = '<%=request.getParameter("showDistrict")%>';
var showStreet = '<%=request.getParameter("showStreet")%>';
console.info("request.getQueryString();",showDistrict,showStreet);
$(document).ready(function() {  
	 var districtIdByMap;
	 var streetIdByMap;
	 if(showDistrict != "null"){
			$.ajax({
				url:"${pageContext.request.contextPath}/app/area/district/getId?name="+showDistrict,
				async: false,      //ajax同步  
				type:"get",
				success: function(data){
					districtIdByMap = data;
				}
		});
	 }else{
		 districtIdByMap = 1;
	 }
	 if(showStreet != "null"){
			$.ajax({
				url:"${pageContext.request.contextPath}/app/area/street/getId?name="+showStreet,
				async: false,      //ajax同步  
				type:"get",
				success: function(data){
					streetIdByMap = data;
				}
		});
			 if( !streetIdByMap || streetIdByMap.length == 0){
				 $.messager.alert("提示", "该街道暂无数据，请选择其他街道查看！", "error");
			 }else{
				 showLineCharts(districtIdByMap,streetIdByMap,null);
			 }
	 }else{
		 // 非点击地图进入
		 showLineCharts(districtIdByMap,streetIdByMap,null);
	 }
	});
var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/fireEventSum/queryPage',
		exportLibStreet: '<%=request.getContextPath() %>/app/fireEvent/exportLibStreet',
};

// 行政区
var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
// 街道
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
// 社区
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";

var params = {
        districtUrl : districtUrl, 
        streetUrl:streetUrl,
        blockUrl:blockUrl,
        searchDistrictEle:'districtId',
        searchStreetEle:'streetId',
        searchBlockEle:'blockId',
        showDistrict:showDistrict,
        showStreet:showStreet
};

initDistrict(params);

function doSearch(value,name){
	var	districtId = $('#districtId').combobox('getValue'),
	streetId = $('#streetId').combobox('getValue'),
	blockId = $('#blockId').combobox('getValue');
		// 搜索
	 	showLineCharts(districtId,streetId,blockId);
	}

function doReset(){
//	  $("#tblQuery").find("input").val("");
	 	params.showDistrict = undefined;
		params.showStreet = undefined;
	    $("#districtId").combobox('select', 0);
	  	$("#streetId").combobox('select', 0);
	  	$("#blockId").combobox('select', 0);
	    doSearch();
}

function showLineCharts(districtId,streetId,blockId){
	// 获取当前年份
	var date = new Date()
	var yearNow = date.getFullYear();
	var _dateTime = getFireTime(yearNow);
	
	var totalY=  new Array();
	var monthX = new Array();
	var areaType;
	var areaId;
	if(blockId && blockId != 0){
		areaType = 2;
		areaId = blockId;
	}else if(streetId && streetId != 0){
		areaType = 1;
		areaId = streetId;
	}else if(districtId && districtId != 0 ){
		areaType = 0;
		areaId = districtId;
	}else{
		areaType = 0;
		areaId = 1;
	}

	// 数据走势图  初始化的时候
		$.ajax({
			url:"${pageContext.request.contextPath}/app/areaInfo/detail?areaType="+areaType+"&areaId="+areaId,
			async: false,      //ajax同步  
			type:"get",
			success: function(data){
					var obj=eval(data);
					$(obj).each(function (index){
						if(obj.data){
							var trend = obj.data.area.areaTrend;
							// 折线图：检测报告
//							var val=trend.checkReportDatas;
//							if(val.length>1){
//								var total = new Array();
//								for(i=0;i<val.length;i++){
//									 if(val[i] != null){
//										 total[i]  = val[i].total;
//										 monthX[i]  = val[i].month;
//									 }else{
//										 $.messager.alert("提示", "检测报告暂无折线数据", "info");
//									 }
//								}
//								totalY.push({name: '检查报告', data: total});
//							}
							
							// 折线图：执法数据
							var punishEventDatas=trend.punishEventDatas;
							if(punishEventDatas.length>1){
								 var total = new Array();
								for(i=0;i<punishEventDatas.length;i++){
									 if(punishEventDatas[i] != null){
										 total[i]  = punishEventDatas[i].total;
										 monthX[i]  = punishEventDatas[i].month;
									 }else{
										 $.messager.alert("提示", "执法暂无折线数据", "info");
									 }
								}
								totalY.push({name: '执法数据', data: total});
							}
							
							// 折线图：警情数据
							var fireEventDatas=trend.fireEventDatas;
							if(fireEventDatas.length>1){
								 var total = new Array();
								for(i=0;i<fireEventDatas.length;i++){
									 if(fireEventDatas[i] != null){
										 total[i]  = fireEventDatas[i].total;
										 monthX[i]  = fireEventDatas[i].month;
									 }else{
										 $.messager.alert("提示", "警情暂无折线数据", "info");
									 }
								}
								totalY.push({name: '警情数据', data: total});
							}
							//同比增长率=（本年的指标值-去年同期的值）÷去年同期的值*100%
							//图表更新：火情
							var series=  new Array();
							fireResult = obj.data.fireResult.list;
							var fireResultTitle = obj.data.fireResult.title;
							var arrEle = {
								    '0': ['originalFireLast', 'confirmFireLast', 'smokeFireLast'],  // 去年的数量标签
								    '1': ['originalFireThis', 'confirmFireThis', 'smokeFireThis']  // 今年的数量标签
							};
							var arrValue = {
								    '0': ['lastYearOriginalFireNum', 'lastYearConfirmFireNum', 'lastYearSmokeFireNum'],  // 去年的数量
								    '1': ['thisYearOriginalFireNum','thisYearConfirmFireNum','thisYearSmokeFireNum']  // 今年的数量
							};
							var arrDesc =['originalFireDescRatio', 'confirmFireDescRatio', 'smokeFireDescRatio']; // 描述
							var arrRatio = ['originalFireRatio', 'confirmFireRatio', 'smokeFireRatio']; //增长率
							
							if(fireResult.length>1){
								for(i=0;i<fireResult.length;i++){
									 var arr = new Array();
									 if(fireResult[i] != null){
											arr[0] = fireResult[i].data1;
											arr[1] = fireResult[i].data2;
											arr[2] = fireResult[i].data3;
											if(fireResult[i].year != yearNow){
											    for(var j= 0 ;  j< 3;j++) {
											        $('#'+arrEle[0][j]).html(arr[j]);
											        arrValue[0][j] = arr[j];
											    }
												series.push({name: fireResult[i].year, data: arr,color:'#FF9655'});
											}else{
											    for(var j = 0; j < 3; j++) {
											        $('#'+arrEle[1][j]).html(arr[j]);
											        arrValue[1][j] = arr[j];
											    }
												series.push({name: fireResult[i].year, data: arr});
											}
											showFireEventCharts(_dateTime.start+'至'+_dateTime.end+fireResultTitle,series,districtId,streetId,blockId);
									 }else{
										 $.messager.alert("提示", "火情柱状图暂无对比数据", "info");
									 }
								}
								
								for(var k=0;k<3;k++){
									console.log('lastYear='+arrValue[0][k]+'，thisYear='+arrValue[1][k]);
									if(arrValue[0][k] == 0 || arrValue[1][k] == 0){
											  $('#'+arrDesc[k]).html("");
									}else{
										var ratio = (arrValue[1][k] - arrValue[0][k] )/arrValue[0][k];
											  $('#'+arrRatio[k]).html(ratio.toFixed(2)+'%');
									}
								}
							}
							// 执法柱状图
							var seriesPunish=  new Array();
							var punishResult = obj.data.punishResult.list;
							var punishResultTitle = obj.data.punishResult.title;
							
							var arrPunishEle = {
								    '0': ['detainedNumLast', 'fineNumLast', 'stopNumLast','closeNumLast'],  // 去年的数量标签
								    '1': ['detainedNumThis', 'fineNumThis', 'stopNumThis','closeNumThis']  // 今年的数量标签
							};				
							var arrPunishValue = {
								    '0': ['lastYearCheckNum', 'lastYearCheckPassNum', 'lastYearReCheckNum', 'lastYearReCheckPassNum'],  // 去年的数量
								    '1': ['thisYearCheckNum','thisYearCheckPassNum','thisYearReCheckNum','thisYearReCheckPassNum']  // 今年的数量
							};
							var arrPunishDesc =['detainedDescRatio', 'fineDescRatio', 'stopDescRatio','closeDescRatio']; // 描述
							var arrPunishRatio = ['detainedRatio', 'fineRatio', 'stopRatio','closeRatio']; //增长率
							
							
							if(punishResult.length>1){
								for(i=0;i<punishResult.length;i++){
									 var arr = new Array();
									 if(punishResult[i] != null){
											arr[0] = punishResult[i].data1;
											arr[1] = punishResult[i].data2;
											arr[2] = punishResult[i].data3;
											arr[3] = punishResult[i].data4;
											if(punishResult[i].year != yearNow){
												   for(var j= 0 ;  j< 4;j++) {
												        $('#'+arrPunishEle[0][j]).html(arr[j]);
												        arrPunishValue[0][j] = arr[j];
												    }
												seriesPunish.push({name: punishResult[i].year, data: arr,color:'#FF9655'});
											}else{
												 for(var j= 0 ;  j< 4;j++) {
												        $('#'+arrPunishEle[1][j]).html(arr[j]);
												        arrPunishValue[1][j] = arr[j];
												 }
												seriesPunish.push({name: punishResult[i].year, data: arr});
											}
											showPunishEventCharts(_dateTime.start+'至'+_dateTime.end+punishResultTitle,seriesPunish,districtId,streetId,blockId);
									 }else{
										 $.messager.alert("提示", "执法柱状图暂无对比数据", "info");
									 }
								}
								for(var k=0;k<4;k++){
									console.log('Punish：lastYear='+arrPunishValue[0][k]+'，thisYear='+arrPunishValue[1][k]);
									if(arrPunishValue[0][k] == 0 || arrPunishValue[1][k] == 0){
											  $('#'+arrPunishDesc[k]).html("");
									}else{
												var ratio = (arrPunishValue[1][k] - arrPunishValue[0][k] )/arrPunishValue[0][k];
											    $('#'+arrPunishRatio[k]).html(ratio.toFixed(2)+'%');
									}
								}
							}
							// 报告柱状图
//							var seriesReport=  new Array();
//							var reportResult = obj.data.reportResult.list;
//							var reportResultTitle = obj.data.reportResult.title;
//
//
//							var arrReportEle = {
//								    '0': ['checkReportNumLast', 'checkPassReportNumLast', 'reCheckReportNumLast','reCheckPassReportNumLast'],  // 去年的数量标签
//								    '1': ['checkReportNumThis', 'checkPassReportNumThis', 'reCheckReportNumThis','reCheckPassReportNumThis']  // 今年的数量标签
//							};
//							var arrReportValue = {
//								    '0': ['lastYearCheckNum', 'lastYearCheckPassNum', 'lastYearReCheckNum', 'lastYearReCheckPassNum'],  // 去年的数量
//								    '1': ['thisYearCheckNum','thisYearCheckPassNum','thisYearReCheckNum','thisYearReCheckPassNum']  // 今年的数量
//							};
//							var arrReportDesc =['checkReportDescRatio', 'checkPassReportDescRatio', 'reCheckReportDescRatio','reCheckPassReportDescRatio']; // 描述
//							var arrReportRatio = ['checkReportRatio', 'checkPassReportRatio', 'reCheckReportRatio','reCheckPassReportRatio']; //增长率
//							if(reportResult.length>1){
//								for(i=0;i<reportResult.length;i++){
//									 var arr = new Array();
//									 if(reportResult[i] != null){
//											arr[0] = reportResult[i].data1;
//											arr[1] = reportResult[i].data2;
//											arr[2] = reportResult[i].data3;
//											arr[3] = reportResult[i].data4;
//											if(reportResult[i].year != yearNow){
//												   for(var j= 0 ;  j< 4;j++) {
//												        $('#'+arrReportEle[0][j]).html(arr[j]);
//												        arrReportValue[0][j] = arr[j];
//												    }
//												seriesReport.push({name: reportResult[i].year, data: arr,color:'#FF9655'});
//											}else{
//												  	for(var j= 0 ;  j< 4;j++) {
//												        $('#'+arrReportEle[1][j]).html(arr[j]);
//												        arrReportValue[1][j] = arr[j];
//												    }
//												seriesReport.push({name: reportResult[i].year, data: arr});
//											}
//
//											showCheckReportCharts(_dateTime.start+'至'+_dateTime.end+reportResultTitle,seriesReport,districtId,streetId,blockId);
//									 }else{
//										 $.messager.alert("提示", "报告柱状图暂无对比数据", "info");
//									 }
//								}
//								for(var k=0;k<4;k++){
//									console.log('Report：lastYear='+arrReportValue[0][k]+'，thisYear='+arrReportValue[1][k]);
//									if(arrReportValue[0][k] == 0 || arrReportValue[1][k] == 0){
//											  $('#'+arrReportDesc[k]).html("");
//									}else{
//												var ratio = (arrReportValue[1][k] - arrReportValue[0][k] )/arrReportValue[0][k];
//											    $('#'+arrReportRatio[k]).html(ratio.toFixed(2)+'%');
//									}
//								}
//							}
						}else{
							 $.messager.alert("提示", "暂无数据", "info");
						}
				});
		}
});
// 展示图标
showDataLineCharts("containerData",monthX,totalY);
}
function exportHighcharts(type){
	var containerData = $("#containerData").highcharts();
	var containerFireEvent = $("#containerFireEvent").highcharts();
	var containerCheckReport = $("#containerCheckReport").highcharts();
	var containerPunishEvent = $("#containerPunishEvent").highcharts();

	var svg_containerData = containerData.getSVG();
	var svg_containerFireEvent = containerFireEvent.getSVG();
	// var svg_containerCheckReport = containerCheckReport.getSVG();
	var svg_containerPunishEvent = containerPunishEvent.getSVG();
	// var svg = svg_containerData+"_"+svg_containerFireEvent+"_"+svg_containerCheckReport+"_"+svg_containerPunishEvent;
	var svg = svg_containerData+"_"+svg_containerFireEvent+"_"+svg_containerPunishEvent;
	$("#svg").val(svg);
	$('#form1').form('submit',{
		url:"${pageContext.request.contextPath}/app/fireEvent/exportword?type="+type,
		onSubmit: function(){
			return true;
		},
		success: function(result){
//			var result = eval('('+result+')');
//			console.log(result);
//			if (result.successful) {
//				$.messager.alert("操作提示", result.data, "info");
//			}
		}
	});
}

</script>
</body>
</html>