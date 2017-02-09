<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>检测排名</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
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
        <span style="font-size:12px">风险级别:</span>
        <input id="riskLevel" class="easyui-combobox"
               data-options="editable:false,width:100">
        <span style="font-size:12px">开始月份:</span>
        <input id="reportAnalysisStartMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkStartDate',editable:false"
               style="width:100px">
        <span style="font-size:12px">结束月份:</span>
        <input id="reportAnalysisEndMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkEndDate[\'#reportAnalysisStartMonth\']',editable:false"
               style="width:100px">
    </div>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="doReset()">重置</a>
    </div>
</div>
<table id="dg_analysis" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
       rownumbers="true"
       fitColumns="true"
       singleSelect="false"
       autoRowHeight="false"
       pagination="true"
       pageSize="20"
       checkOnSelect="true"
       selectOnCheck="true">
    <thead>
    <tr>
    	<th field="checkReportName" width="80">报告名称</th>
	    <th field="districtName" width="50">行政区</th>
		<th field="streetName" width="50">街道名称</th>
	    <th field="blockName" width="50">社区名称</th>
        <th field="totalUnqualified" width="50" formatter="formatRatio">总不合格率</th>
        <th field="riskLevelName" width="50">级别</th>
        <th field="facilitiesUnqualified" width="50">设施不合格数</th>
        <th field="facilitiesUnqualifiedRatio" width="50" formatter="formatRatio">设施不合格率</th>
        <th field="evacuateUnqualified" width="50">疏散不合格数</th>
        <th field="evacuateUnqualifiedRatio" width="50" formatter="formatRatio">疏散不合格率</th>
        <th field="manageUnqualified" width="50">管理不合格数</th>
        <th field="manageUnqualifiedRatio" width="50" formatter="formatRatio">管理不合格率</th>
        <th field="dangerUnqualified" width="50">危险源不合格数</th>
        <th field="dangerUnqualifiedRatio" width="50" formatter="formatRatio">危险源不合格率</th>
        <th field="importantUnqualified" width="50">重点部位不合格数</th>
        <th field="importantUnqualifiedRatio" width="50" formatter="formatRatio">重点部位不合格率</th>
        <th field="checkUnqualified" width="50">设施检测不合格数</th>
        <th field="checkUnqualifiedRatio" width="50" formatter="formatRatio">设施检测不合格率</th>
        <th field="pubTime" width="50">报告日期</th>
    </tr>
    </thead>
    <input type="hidden" name="streetId" value="streetId"/>
</table>

<script type="text/javascript">
//初始化月份条件选择
initDateMonthBox('reportAnalysisStartMonth');
initDateMonthBox('reportAnalysisEndMonth');
var UrlConfig = {
   	listPage: '<%=request.getContextPath() %>/app/reportAnalysis/queryPage',
};

//区域信息
var showDistrictId = '<%=request.getParameter("districtId")%>',
showStreetId = '<%=request.getParameter("streetId")%>',
showBlockId = '<%=request.getParameter("blockId")%>',
monthStart = '<%=request.getParameter("monthStart")%>',
monthEnd = '<%=request.getParameter("monthEnd")%>',
riskLevel = '<%=request.getParameter("riskLevel")%>';
if(showDistrictId && showDistrictId == 'null'){
	showDistrictId = "";
}
if(showStreetId && (showStreetId == 'null' || showStreetId == 'undefined') ){
	showStreetId = "";
}
if(showBlockId && (showBlockId == 'null' || showBlockId == 'undefined') ){
	showBlockId = "";
}


var dictListReportAnalysis;
riskLevelDataSearch = new Array();
// 获取数据字典数据
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getAllDictList",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
        	dictListReportAnalysis = data.data.rows;
        }
    }
});
$.each(dictListReportAnalysis,function(n,value) {   
    var name = value.name,
    code = value.code;
    //风险级别：risk_level
    if ("risk_level" == value.typeCode) {
        // 搜索框
        riskLevelDataSearch.push({name: name, code: code});
    } 
});  
$(document).ready(function() { 
	riskLevelDataSearch.unshift({code: '0', name: '全部'});
    $('#riskLevel').combobox({
        data: riskLevelDataSearch,
        valueField: 'code',
        textField: 'name',
        value: '0',
        onLoadSuccess: function () { //数据加载完毕事件
	      		if(riskLevel && riskLevel.length > 0 && riskLevel != 'null'){
	     				var selected = false;
	     				$.each(riskLevelDataSearch,function(i,v){
	     					if(v.code == riskLevel){
	     						selected = true;
	     						$('#riskLevel').combobox('select',v.code);
	     						return ;
	     					}
	     				});
	     			}else{
	     				 $('#riskLevel').combobox('select', 0);
	     			}
        }
    });
    
    if(monthStart && monthStart.length == 7){
        $('#reportAnalysisStartMonth').datebox('setValue',monthStart);
    }
    if(monthEnd && monthEnd.length == 7){
        $('#reportAnalysisEndMonth').datebox('setValue',monthEnd);
    }
    $('#dg_analysis').datagrid({
        url:UrlConfig.listPage,
        queryParams:{
            riskLevel:riskLevel,
            districtId: showDistrictId,
            streetId: showStreetId,
            blockId: showBlockId,
            reportAnalysisStartMonth: monthStart=='null'?'':monthStart,
            reportAnalysisEndMonth: monthEnd=='null'?'':monthEnd
        }
    });
});

//行政区
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
        showDistrictId:showDistrictId,
        showStreetId:showStreetId,
        showBlockId:showBlockId
};

initDistrict(params);
    function doSearch(value, name) {
        var reportAnalysisStartMonth = $('#reportAnalysisStartMonth').datebox('getValue');
        var reportAnalysisEndMonth = $('#reportAnalysisEndMonth').datebox('getValue');
        $('#dg_analysis').datagrid('load', {
            streetId: $('#streetId').combobox('getValue'),
            districtId: $('#districtId').combobox('getValue'),
            blockId: $('#blockId').combobox('getValue'),
            riskLevel:$('#riskLevel').combobox('getValue'),
            reportAnalysisStartMonth: reportAnalysisStartMonth=='null'?'':reportAnalysisStartMonth,
            reportAnalysisEndMonth: reportAnalysisEndMonth=='null'?'':reportAnalysisEndMonth
        });
    }

    function doReset() {
  //      $("#tblQuery").find("input").val("");
       	params.showDistrictId = undefined;
		params.showStreetId = undefined;
		params.showBlockId = undefined;
	    $("#districtId").combobox('select', 0);
	  	$("#streetId").combobox('select', 0);
	  	$("#blockId").combobox('select', 0);
		$("#riskLevel").combobox('select', 0);
        $('#reportAnalysisStartMonth').datebox('setValue',undefined);
        $('#reportAnalysisEndMonth').datebox('setValue',undefined);
        doSearch();
    }
    
    function formatRatio(val,row,index){
      if(val==null){  
         return ""; 
       }
	   var number = val*100;
	   return number.toFixed(1) +"%"
	}
</script>
</body>
</html>