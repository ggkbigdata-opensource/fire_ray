<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执法统计</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/fireEventSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/punishEventSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/checkReportSumColumn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataLine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataPie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/main.js"></script>
</head>
<body>
<div id="tblQuery" style="padding:3px">
	<span style="font-size:12px">行政区:</span>
	<input id="districtId" class="easyui-combobox" 
	       data-options="editable:false,width:173"> 
	<span style="font-size:12px">街道:</span>
	 <input id="streetId" class="easyui-combobox" 
            data-options="editable:false,width:173"> 
	 <span style="font-size:12px">社区:</span>
	 <input id="blockId" class="easyui-combobox" 
            data-options="editable:false,width:173"> 
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
</div>
<table id="dg_punishEventSum" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/punishEventSum/queryPage'
       			rownumbers="true" 
        		fitColumns="true" 
        		singleSelect="true"
                pagination="true"
                pageSize="20"
                checkOnSelect="false"
                collapsible="true"
                selectOnCheck="false">
    <thead>
        <tr>
        	<th field="year" width="50">年份</th>
        	<th field="month" width="50" formatter="getMonth">月份</th>
	       	 <th field="districtName" width="50">行政区</th>
	    	 <th field="streetName" width="50">街道名称</th>
            <th field="blockName" width="50">社区名称</th>
            <th field="detainedNum" width="50">行政拘留数量</th>
            <th field="fineNum" width="50">行政罚款数量</th>
            <th field="fineTotal" width="50">行政罚款总额</th>
            <th field="threeStopNum" width="50">三停数量</th>
            <th field="createDate" width="50">汇总时间</th>
        </tr>
    </thead>
</table>
<!-- 详细信息 -->
<div id="punish_detail" class="easyui-dialog" style="width:1000px;height:530px;padding:10px 20px"
	 closed="true" >
	<div id="detail_tblQuery" style="padding:3px">
		<div>
            <div class='inputDivEg'>
                <span style="font-size:12px">执法类型:</span>
                <input id="punishType" class="easyui-combobox"
                       data-options="editable:false,width:100">
            </div>
			<div class='inputDivEg'>
				<span style="font-size:12px">执法名称:</span>
				<input id="punishName" style="line-height:20px;" class="input">
			</div>
			<div class='inputDivEg'>
				<span style="font-size:12px">场所名称:</span>
				<input id="placeName" style="line-height:20px;" class="input">
			</div>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doDetailSearch()">查询</a>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="doDetailReset()">重置</a>
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back" onclick="doDetailExport()">导出</a>
		</div>
	</div>
	<table id="dg_punish_detail" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
		   url='<%=request.getContextPath() %>/app/punishEvent/queryPage'
		   rownumbers="true"
		   fitColumns="true"
		   singleSelect="true"
		   autoRowHeight="false"
		   pagination="true"
		   pageSize="20"
		   checkOnSelect="false"
		   selectOnCheck="false">
		<thead>
		<tr>
			<th field="name" width="50">执法名称</th>
			<th field="punishNumber" width="50">执法编码</th>
			<th field="placeName" width="50">场所名称</th>
			<th field="buildingBaseName" width="50">建筑名称</th>
			<th field="punishTypeName" width="50">执法类型</th>
			<th field="remark" width="50">案情</th>
			<th field="blockName" width="50">社区名称</th>
			<th field="streetName" width="50">所属街道</th>
			<th field="punishTime" width="50">发生时间</th>
		</tr>
		</thead>
		<input type="hidden" name="streetId" value="streetId"/>
	</table>
</div>

<script type="text/javascript">
// 行政区
var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
$.getJSON(districtUrl, function(json) {
	json.rows.unshift({id: 0, name: '全部'});
	$('#districtId').combobox({
		data : json.rows,
		valueField:'id',
		textField:'name'
});
});
// 街道
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
$.getJSON(streetUrl, function(json) {
	json.rows.unshift({id: 0, name: '全部'});
	$('#streetId').combobox({
			data : json.rows,
			valueField:'id',
			textField:'name'
	});
});

// 社区
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";
$.getJSON(blockUrl, function(json) {
	json.rows.unshift({id: 0, name: '全部'});
	$('#blockId').combobox({
		data : json.rows,
		valueField:'id',
		textField:'name'
});
});

function doSearch(value,name){
	var	districtId = $('#districtId').combobox('getValue'),
	streetId = $('#streetId').combobox('getValue'),
	blockId = $('#blockId').combobox('getValue');
	// 列表更新
	$('#dg_punishEventSum').datagrid('load',{
		districtId:districtId,
		streetId:streetId,   // 下拉框获取数据ID
		blockId:blockId
	});
	}

function doReset(){
	  $("#tblQuery").find("input").val("");
	    $("#districtId").combobox('select', 0);
	  	$("#streetId").combobox('select', 0);
	  	$("#blockId").combobox('select', 0);
	    doSearch();
}

//-----------------------punishEvent start---------------------
//执法类型
var urlPunishType = "${pageContext.request.contextPath}/app/config/getDictList?typeCode=punish_type";
$.getJSON(urlPunishType, function (json) {
	json.data.unshift({code: '0', name: '全部'});
	$('#punishType').combobox({
		data: json.data,
		valueField: 'code',
		textField: 'name',
		value: '0'
	});
});
var detailRow;
//单击事件
//$('#dg_punishEventSum').datagrid({
//	onClickRow: function(index,row){
//		detailRow = row;
//		//清空详细窗口查询条件
//		$("#detail_tblQuery").find("input").val("");
//        $('#punishType').combobox('select' ,0);
//		$('#punish_detail').dialog('open').dialog('setTitle','警情详情');
//		doDetailSearch();
//	}
//});
//详细查询
function doDetailSearch() {
	$('#dg_punish_detail').datagrid('load',{
		name: $('#punishName').val(),
		placeName: $('#placeName').val(),
		punishType: $('#punishType').combobox('getValue'),
		districtId:detailRow.districtId,
		streetId:detailRow.streetId,
		blockId:detailRow.blockId,
		punishMonthStart:detailRow.month,
		punishMonthEnd:detailRow.month
	});
}
//详细导出
function doDetailExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
		if (!r) {
			return;
		} else {
			var exportName = $('#punishName').val();
			var exportPlaceName = $('#placeName').val();
			var openUrl = '<%=request.getContextPath() %>/app/punishEvent/exportLibStreet' +
					"?name=" + encodeURI(exportName) +
					"&placeName=" + encodeURI(exportPlaceName) +
					"&streetId=" + $('#streetId').combobox('getValue') +
					"&districtId=" + $('#districtId').combobox('getValue') +
					"&blockId=" + $('#blockId').combobox('getValue')
			window.open(openUrl);
		}
	});
}
//重置查询条件
function doDetailReset(){
    $("#detail_tblQuery").find("input").val("");
    $('#punishType').combobox('select' ,0);
    doDetailSearch();
}
//-----------------------punishEvent end-----------------------
</script>
</body>
</html>