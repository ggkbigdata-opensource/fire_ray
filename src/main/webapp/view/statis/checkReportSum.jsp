<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<table id="dg_checkReportSum" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/checkReportSum/queryPage'
       			rownumbers="true" 
        		fitColumns="true" 
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                pageSize="20"
                checkOnSelect="true" 
                collapsible="true"
                selectOnCheck="true"> 
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
        	<th field="year" width="50">年份</th>
        	<th field="month" width="50" formatter="getMonth">月份</th>
	       	 <th field="districtName" width="50">行政区</th>
	    	 <th field="streetName" width="50">街道名称</th>
            <th field="blockName" width="50">社区名称</th>
            <th field="checkNum" width="50">初检数量</th>
            <th field="recheckNum" width="50">复检数量</th>
            <th field="checkPassNum" width="50">初检通过数</th>
            <th field="recheckPassNum" width="50">复检通过数</th>
            <th field="createDate" width="50">汇总时间</th>
        </tr>
    </thead>
</table>
<script type="text/javascript">
$(document).ready(function() {  
	   var series=  new Array();
	   var districtId;
	   // 获取当前登录用户所在行政区，默认展示该社区的对比图
		$.ajax({
				url:"${pageContext.request.contextPath}/app/user/getCurrentUser",
				async: false,      //ajax同步  
				type:"get",
				success: function(data){
					if(data.successful){
						var result = data.data;
						districtId = result.districtId;
					}else{
						 $.messager.alert("提示", result.msg, "info");
					}
				}
		});
		$.ajax({
				url:"${pageContext.request.contextPath}/app/areaInfo/compareCheckReport?areaType=0&areaId="+districtId,
				async: false,      //ajax同步  
				type:"get",
				success: function(data){
						var obj=eval(data);
						$(obj).each(function (index){
						var val=obj.data.result;
						if(val.length>1){
							for(i=0;i<val.length;i++){
								 var arr = new Array();
								 if(val[i] != null){
										arr[0] = val[i].data1;
										arr[1] = val[i].data2;
										arr[2] = val[i].data3;
										arr[3] = val[i].data4;
										series.push({name: val[i].year, data: arr});
								 }else{
									 $.messager.alert("提示", "选中位置暂无对比数据", "info");
								 }
							}
						}else{
							$.messager.alert("提示", "暂无对比数据", "info");
						}
				});
				}
		});
		if(series.length>0){
			showCheckReportCharts(series);
		}
	});
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
	$('#dg_checkReportSum').datagrid('load',{
		districtId:districtId,
		streetId:streetId,   // 下拉框获取数据ID
		blockId:blockId
	});
	//图表更新
	var districtIdUser;
	// 获取当前登录用户所在行政区，默认展示该社区的对比图
	$.ajax({
			url:"${pageContext.request.contextPath}/app/user/getCurrentUser",
			async: false,      //ajax同步  
			type:"get",
			success: function(data){
				if(data.successful){
					var result = data.data;
					districtIdUser = result.districtId;
				}else{
					 $.messager.alert("提示", result.msg, "info");
				}
			}
	});
	var series=  new Array();
	var areaType;
	var areaId;
	if(blockId){
		areaType = 2;
		areaId = blockId;
	}else if(streetId){
		areaType = 1;
		areaId = streetId;
	}else if(districtId){
		areaType = 0;
		areaId = districtId;
	}else{
		areaType = 0;
		areaId = districtIdUser;
	}
	$.ajax({
			url:"${pageContext.request.contextPath}/app/areaInfo/compareCheckReport?areaType="+areaType+"&areaId="+areaId,
			async: false,      //ajax同步  
			type:"get",
			success: function(data){
					var obj=eval(data);
					$(obj).each(function (index){
					var val=obj.data.result;
					if(val.length>1){
						for(i=0;i<val.length;i++){
							 var arr = new Array();
							 if(val[i] != null){
									arr[0] = val[i].data1;
									arr[1] = val[i].data2;
									arr[2] = val[i].data3;
									arr[3] = val[i].data4;
									series.push({name: val[i].year, data: arr});
							 }else{
								 $.messager.alert("提示", "选中位置暂无对比数据", "info");
							 }
						}
					}else{
						$.messager.alert("提示", "暂无对比数据", "info");
					}
			});
			}
	});
		if(series.length>0){
			showCheckReportCharts(series);
		}
	}

function doReset(){
	  $("#tblQuery").find("input").val("");
	    $("#districtId").combobox('select', 0);
	  	$("#streetId").combobox('select', 0);
	  	$("#blockId").combobox('select', 0);
	    doSearch();
}
</script>
</body>
</html>