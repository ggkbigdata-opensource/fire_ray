<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>报告统计</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/highcharts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/exporting.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/export-csv.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/fireEventSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/punishEventSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/checkReportSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataLine.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataPie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
     <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
</head>
<body>
<div id="tblQuery" style="padding:3px">
    <div>
        <span style="font-size:12px">行政区:</span>
        <input id="districtId" class="easyui-combobox"
               data-options="editable:false,width:100">
        <span style="font-size:12px">街道:</span>
        <input id="streetId" class="easyui-combobox"
               data-options="editable:false,width:100">
        <span style="font-size:12px">社区:</span>
        <input id="blockId" class="easyui-combobox"
               data-options="editable:false,width:120">
       <span style="font-size:12px">开始月份:</span>
        <input id="reportAnalysisPieStartMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkStartDate',editable:false"
               style="width:100px">
        <span style="font-size:12px">结束月份:</span>
        <input id="reportAnalysisPieEndMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkEndDate[\'#reportAnalysisPieStartMonth\']',editable:false"
               style="width:100px">
    </div>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="doReset()">重置</a>
    </div>
</div>
<div id="containerReportLevelPercentPie" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script type="text/javascript">
//行政区
var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
// 街道
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
// 社区
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";

// 区域信息
var showDistrictId = '<%=request.getParameter("districtId")%>',
showStreetId = '<%=request.getParameter("streetId")%>',
showBlockId = '<%=request.getParameter("blockId")%>',
monthStart = '<%=request.getParameter("monthStart")%>',
monthEnd = '<%=request.getParameter("monthEnd")%>';

if(showDistrictId && showDistrictId == 'null'){
	showDistrictId = "";
}
if(showStreetId && (showStreetId == 'null' || showStreetId == 'undefined') ){
	showStreetId = "";
}
if(showBlockId && (showBlockId == 'null' || showBlockId == 'undefined') ){
	showBlockId = "";
}
var params = {
        districtUrl : districtUrl, 
        streetUrl:streetUrl,
        blockUrl:blockUrl,
        searchDistrictEle:'districtId',
        searchStreetEle:'streetId',
        searchBlockEle:'blockId',
        showDistrictId:showDistrictId,
        showStreetId:showStreetId,
        showBlockId:showBlockId
};

initDistrict(params);

$(document).ready(function() { 
    if(monthStart && monthStart.length == 7){
        $('#reportAnalysisPieStartMonth').datebox('setValue',monthStart);
    }
    if(monthEnd && monthEnd.length == 7){
        $('#reportAnalysisPieEndMonth').datebox('setValue',monthEnd);
    }
    showReportPieCharts(showDistrictId,showStreetId,showBlockId,monthStart,monthEnd);
});

    //初始化月份条件选择
    initDateMonthBox('reportAnalysisPieStartMonth');
    initDateMonthBox('reportAnalysisPieEndMonth');

    function doSearch(value, name) {
	    var reportAnalysisPieStartMonth = $('#reportAnalysisPieStartMonth').datebox('getValue'),
        reportAnalysisPieEndMonth = $('#reportAnalysisPieEndMonth').datebox('getValue'),
    	districtId = $('#districtId').combobox('getValue'),
    	streetId = $('#streetId').combobox('getValue'),
    	blockId = $('#blockId').combobox('getValue');
    	// 搜索
        showReportPieCharts(districtId,streetId,blockId,reportAnalysisPieStartMonth,reportAnalysisPieEndMonth);
    }

function doReset() {
//    $("#tblQuery").find("input").val("");
	params.showDistrictId = undefined;
	params.showStreetId = undefined;
	params.showBlockId = undefined;
    $("#districtId").combobox('select', 0);
  	$("#streetId").combobox('select', 0);
  	$("#blockId").combobox('select', 0);
    $('#reportAnalysisPieStartMonth').datebox('setValue',undefined);
    $('#reportAnalysisPieEndMonth').datebox('setValue',undefined);
    doSearch();
}

function showReportPieCharts(_districtId,_streetId,_blockId,monthBegin,_monthEnd){
	var areaType;
	var areaId;
	if(_blockId != "null" && _blockId != 0 && _blockId != "undefined"){
		areaType = 2;
		areaId = _blockId;
	}else if(_streetId != "null" && _streetId != 0 && _streetId != "undefined"){
		areaType = 1;
		areaId = _streetId;
	}else if(_districtId != "null" && _districtId != 0 && _districtId != "undefined"){
		areaType = 0;
		areaId = _districtId;
	}else{
		areaType = 0;
		areaId = 1;
	}
	var reportLevelPercentPieData;
	if(monthBegin.length == 0 && _monthEnd.length == 0){
		getReportLevelPercentUrl = "${pageContext.request.contextPath}/app/statis/getReportLevelPercent?areaType="+areaType+"&areaId="+areaId;
	}else{
		getReportLevelPercentUrl = "${pageContext.request.contextPath}/app/statis/getReportLevelPercent?areaType="+areaType+"&areaId="+areaId+"&monthBegin="+
		monthBegin+"&monthEnd="+_monthEnd;
	}
	$.ajax({
		url:getReportLevelPercentUrl,
		async: false,      //ajax同步  
		type:"get",
		success: function(data){
				var obj=eval(data);
				if(obj.successful){
					reportLevelPercentPieData = obj.data;
				}
		}
	});
	
    var plotOptions = {
   		 pie: {
               allowPointSelect: true,
               cursor: 'pointer',
               events: {
            	   click: function(e) { 
            			// 跳转页面：检测排名
//	        			alert("跳转页面：检测排名app/page/reportAnalysis?riskLevel="+e.point.code);
            		   console.log('app/page/reportAnalysis?riskLevel='+e.point.code+"&districtId="+_districtId+"&streetId="+_streetId+"&blockId="+_blockId+
                               '&monthStart=' + monthStart + '&monthEnd=' + monthEnd);
	        			window.parent.addTab('检测排名统计','app/page/reportAnalysis?riskLevel='+e.point.code+"&districtId="+_districtId+"&streetId="+_streetId+"&blockId="+_blockId+
                                '&monthStart=' + monthStart + '&monthEnd=' + monthEnd );
            	   }
            	},
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
         }
    };

	console.log(reportLevelPercentPieData);
	if(reportLevelPercentPieData.length != 0){
		showDataPieCharts("containerReportLevelPercentPie","高中低检测风险分布情况图",reportLevelPercentPieData,plotOptions);
	}else{
		 $.messager.alert("提示", "暂无数据", "info");
	}
}
</script>
</body>
</html>