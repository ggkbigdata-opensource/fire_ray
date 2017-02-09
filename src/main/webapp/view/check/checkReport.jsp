<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测报告管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<body>
<div id="tblQuery" style="padding:3px">
	<div class="inputDiv">
		<div class='inputDivEg'>
			<span class="inputLabel">行政区:</span>
			<input id="districtId" class="easyui-combobox inputDiv-input" data-options="width:100">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">街道:</span>
			<input id="streetId" class="easyui-combobox inputDiv-input" data-options="width:100">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">社区:</span>
			<input id="blockId" class="easyui-combobox inputDiv-input" data-options="width:100">
		</div>
		<%--<span class="inputLabel">报告类型:</span>
		<%--<input id="reportType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">--%>
		<div class='inputDivEg'>
			<span class="inputLabel">开始月份:</span>
			<input id="checkStartMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkStartDate',editable:false" style="width:100px">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">结束月份:</span>
			<input id="checkEndMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkEndDate[\'#checkStartMonth\']',editable:false" style="width:100px">
		</div>
		<div class="height10"></div>
		<div class='inputDivEg'>
			<span class="inputLabel">报告名称:</span>
			<input id="name" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
		</div>
		<a href="#" class="resetBtn" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-back" onclick="doExport()">导出</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-print" onclick="doImport()">导入</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-sum" onclick="doSumReport()">分析报告数据</a>
	</div>
</div>
<div class="tableClass">
	<table id="dg_check"  title="数据列表" class="easyui-datagrid"
        url='<%=request.getContextPath() %>/app/checkReport/queryPage'
        		toolbar="#toolbar"
       			rownumbers="true" 
        		fitColumns="true" 
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                pageSize="20"
                striped="true"
                checkOnSelect="true" 
                selectOnCheck="true"> 
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
            <th field="name" width="50">报告名称</th>
			<th field="code" width="50">编码</th>
			<%--<th field="reportType" width="50">检测类型</th>--%>
			<th field="riskIndex" width="50" formatter="formatString">风险指数</th>
			<th field="streetName" width="50">所属街道</th>
			<th field="blockName" width="50">社区名称</th>
			<th field="pubTime" width="50">报告日期</th>
        </tr>
    </thead>
          <input type="hidden" name="streetId" value="streetId" />
          <input type="hidden" name="blockId" value="blockId" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" iconCls="icon-add" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgCheckReport" class="easyui-dialog" style="width:1050px;height:440px;"
        closed="true" buttons="#dlg-buttons">
	<div id="checkReportTabs" class="easyui-tabs" data-options="fit:true">
		<div title="基础信息">
			<form id="fm_check" method="post" novalidate style="padding: 22px">
				<div class="fitem_building">
					<label>社区:</label>
					<input class="easyui-combotree" id="comCcBlock_checkReport" name = "blockId" style="width:173px;height:26px" required="true" >
				</div>
				<div class="fitem_building">
					<label>您选择的是:</label>
					<span id="showSelected_checkReport" style="color: #FF3030;width: 200px;"></span>
				</div>
				<div class="fitem_building">
					<label>建筑名称:</label>
					<input id="comCcBuilding_checkReport"  name = "buildingId"  class="easyui-combobox"
						data-options="required:true,editable:false" />
				</div>
				<div class="fitem_building">
					<label>报告名称:</label>
					<input name="name" class="easyui-textbox" required="true">
				</div>
				<div class="fitem_building">
					<label>场所名称:</label>
					<input name="placeName" class="easyui-textbox" required="true">
				</div>
<!-- 				 <div class="fitem_building">
					 <label>报告封面:</label>
					 <input name="reportImage" class="easyui-textbox" required="true">
				</div> -->
				<div class="fitem_building">
					 <label>报告类型:</label>
					 <input id="comCcReportType" name="reportType"  class="easyui-combobox"
							data-options="required:true,editable:false">
				</div>
				<div class="fitem_building">
					 <label>报告日期:</label>
					 <input class="easyui-datetimebox" name="pubTimeString"
							data-options="required:true,showSeconds:false" value="3/4/2010 2:3">
				</div>
			    <div class="fitem_building">
					 <label>报告人电话:</label>
					 <input name="reporterPhone" class="easyui-textbox" data-options="validType:'length[1,11]'">
			   </div>
<!-- 				<div class="fitem_building">
					<label>风险指数:</label>
					<input id="comCcRiskIndex"  name = "riskIndex"  class="easyui-combobox"
						data-options="required:true,editable:false" />
				</div>
			    <div class="fitem_building">
				 	<label>是否通过:</label>
					<input id="comCcIsPass" name="isPass"   class="easyui-combobox"
						data-options="required:true,editable:false" />
			    </div>
			  	<div class="fitem_building">
					<label>不通过项:</label>
					<input name="unpassUum" type="text" class="easyui-numberbox"
						 size="8"   style="text-align:right;width:171px;"/>
				</div> -->
			    <div class="fitem_building">
				  	<label class="area">说明:</label>
			        <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:100px">
			 	</div>
			</form>
		</div>
		<div title="检测项" style="padding: 22px;height: 440px">
			<div>
				<form id="resultQuery">
					<span style="font-size:12px">二级指标:</span>
					<input id="sortCodeInput" class="easyui-combobox" data-options="editable:false,width:150">
					<span style="font-size:12px">三级指标:</span>
					<input id="typeCodeInput" class="easyui-combobox" data-options="editable:false,width:300">
					<span style="font-size:12px">项目:</span>
					<input id="resultName" class="input" >
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearchResult()">查询</a>
					<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-undo" onclick="doSearchReset()">重置</a>
				</form>
				<table id="dg_check_result">
					<div id="result_toolbar">
						<a href="#" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="doImportResult()">导入</a>
						<a href="#" class="easyui-linkbutton" iconCls="icon-sum" plain="true" onclick="runAnalysis()">运行分析</a>
					</div>
				</table>
			</div>
			<div id="importResult" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
				 closed="true">
				<div class="ftitle">导入数据</div>
				<form id="report_file" method="post" enctype="multipart/form-data" target="resultAjaxUpload">
					<div style="margin-top:10px;">
						<a href="<%=request.getContextPath() %>/template/import_check_result.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
					</div>
					<div style="margin-top:10px;">
						 	<input class="easyui-filebox" data-options="buttonText:'选择文件'"  name="file" style="width:250px;margin:20px;">
					</div>
					<div style="margin-top:20px;float:right;">
						<a href="#"   class="easyui-linkbutton" iconCls='icon-ok' id="iru"  onclick="uploadResultFile()">提交</a>
					</div>
				</form>
				<iframe name="resultAjaxUpload" style="display:none"></iframe>
				<input id="reportId" type="hidden">
			</div>
		</div>
	</div>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgCheckReport').dialog('close');$('#showSelected_checkReport').text('');" style="width:90px">取消</a>
</div>


<div id="import_check_report" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入报告数据</div>
<form id="fm_file_check_report" method="post" enctype="multipart/form-data" target="ajaxUpload">
     <div style="margin-top:10px;">
		<a href="<%=request.getContextPath() %>/template/checkReport_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
	</div>
	<div style="margin-top:10px;">
		 	<input class="easyui-filebox" data-options="buttonText:'选择文件'" id="file" name="file" style="width:250px;margin:20px;">
	</div>
	<div style="margin-top:20px;float:right;">
		<a href="#"   class="easyui-linkbutton" iconCls='icon-ok' onclick="uploadFile()">提交</a>
	</div>
</form>

<iframe name="ajaxUpload" style="display:none"></iframe>

</div>
<script type="text/javascript">
var dictListCheckReport;
var reportTypeData =  new Array(),
reportTypeDataSearch = new Array(),
riskIndexData = new Array();
// 获取数据字典数据
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getAllDictList",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
            dictListCheckReport = data.data.rows;
        }
    }
});
$.each(dictListCheckReport,function(n,value) {   
    var name = value.name,
    code = value.code;
    // 检测类型：初检/复检：report_type
    if ("report_type" == value.typeCode) {
        reportTypeData.push({name: name, code: code});
        // 搜索框
        reportTypeDataSearch.push({name: name, code: code});
    } 
    //风险指数 report_risk_index
    // if ("report_risk_index" == value.typeCode) {
    //     riskIndexData.push({name: value.name, code: value.code});
    // }
    
});
var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
$(document).ready(function() {
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_checkReport","#showSelected_checkReport");
    // 检测类型 
  	$('#comCcReportType').combobox({
		data: reportTypeData,
		valueField:'code',
		textField:'name'
	});
    reportTypeDataSearch.unshift({code: '0', name: '全部'});
    $('#reportType').combobox({
        data : reportTypeDataSearch,
        valueField:'code',
        textField:'name',
        value:0
    });
    // 风险指数
 //    $('#comCcRiskIndex').combobox({
	// 	data : riskIndexData,
	// 	valueField:'code',
	// 	textField:'name'
	// });
    
	//表单窗口打开前禁用联动查询
	$('#fm_check').form({
		onLoadSuccess : function(){
			console.info("load sunness");
			disableLinkage(false);
		}
	});
	
});

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/checkReport/queryPage',
		insertData: '<%=request.getContextPath() %>/app/checkReport/insertData',
		updateData: '<%=request.getContextPath() %>/app/checkReport/updateData',
		deleteData: '<%=request.getContextPath() %>/app/checkReport/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/checkReport/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/checkReport/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/checkReport/importStreet',
	    importResult:'<%=request.getContextPath() %>/app/checkItemResult/importReportResult'
};

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";
//建筑
var buildingUrl = "${pageContext.request.contextPath}/app/building/queryPage";
var params = {
		districtUrl : districtUrl, 
		streetUrl:streetUrl,
		blockUrl:blockUrl,
		buildingUrl:buildingUrl,
		newDistrictEle:'comCcDistrict_checkReport', 
		searchDistrictEle:'districtId',
		newStreetEle:'comCcStreet_checkReport',
		searchStreetEle:'streetId',
		newBlockEle:'comCcBlock_checkReport', 
		searchBlockEle:'blockId',
		newBuildingEle:'comCcBuilding_checkReport'
};

initDistrict(params);
initBuilding(params,null);

//初始化月份选择
initDateMonthBox('checkStartMonth');
initDateMonthBox('checkEndMonth');

// 是否通过
// var isPssData = [{ "id":1, "name":"通过" },{ "id":0, "name":"不通过"}];
// $('#comCcIsPass').combobox({
// 	data: isPssData,
// 	valueField: 'id',
// 	textField: 'name',
// });
var sortCode = [] , itemType = [] ;
//初始化检测项查询条件
function initResultSearchCode(reportId){
	var showSort = [] , showType = [];
	var codeUrl = '<%=request.getContextPath() %>/app/checkItemResult/querySearchCode?reportId=' + reportId;
	$.getJSON(codeUrl,function(data){
		if(!data.successful){
			showSort = [{name:"无数据",code:"0"}];
			showType = [{name:"无数据",code:"0"}];
		}
		if(data.data){
			showSort = data.data;
			$.each(showSort,function(i,v){
				itemType[v.code] = v.typeBean;
				sortCode.push(v.code);
//				showType = showType.concat(v.typeBean);
			});
			showSort.unshift({code: 0, name: '全部'});
//			showType.unshift({code: 0, name: '全部'});
			$('#sortCodeInput').combobox({
	            data: showSort,
	            valueField: 'code',
	            textField: 'name',
	            value:0,
	            onSelect:function(value){
					showType = [];
	            	if(value.code==0){
	            		$.each(sortCode,function(i,v){
	        				showType = showType.concat(itemType[v]);
	        			});
	            	}else{
	            		showType = showType.concat(itemType[value.code]);
	            	}
					showType.unshift({code: 0, name: '全部'});
	            	$('#typeCodeInput').combobox('loadData',showType);
					$('#typeCodeInput').combobox('select',0);
	            }
			});
			$('#typeCodeInput').combobox({
	            data: showType,
	            valueField: 'code',
	            textField: 'name',
	            value:0
			});
		}
	});
}

//初始化检测结果页面
function initResultTab(reportId) {
	var searchStr = $('#resultQuery').serialize(); 
	$('#dg_check_result').datagrid({
		url:'<%=request.getContextPath() %>/app/checkItemResult/queryPage?reportId=' + reportId + '&' + searchStr,
		title:'数据列表',
		toolbar:"#result_toolbar",
		rownumbers:"true",
		method:'get',
//		fitColumns:true,
		singleSelect:true,
		pagination:true,
		pageSize:10,
		columns:[[
			{field:'itemTypeCode',title:'编码',width:'60'},
			{field:'itemSort',title:'所属大类',width:'130'},
			{field:'itemType',title:'所属小类',width:'130'},
			{field:'name',title:'检测项',width:'350' , style:function(value){
				console.info("value",value); return 'white-space: pre-wrap;word-wrap: break-word;'}
			},
			{field:'level',title:'重点',width:'60',formatter:function(value){
				if(value==1){
					return '是';
				}else if(value==0){
					return '';
				}else{
					return value;
				}
			}},
			{field:'isPass',title:'检测结果',width:'70',formatter:function(value){
				//不符合：-1，无此项：0，缺陷：1，符合：2
				if(value==-1){
					return '不符合';
				}else if(value==0){
					return '无此项';
				}else if(value==1){
					return '缺陷项';
				}else if(value==2){
					return '符合';
				}
			}},
			{field:'result',title:'备注',width:'170'}
		]]
	});
}
//绑定dialog的关闭事件，关闭后tab页调为显示基础信息，reportId隐藏框去掉值


// 新增
function newStreet(){
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_checkReport","#showSelected_checkReport");
    $('#dlgCheckReport').dialog('open').dialog('setTitle','新建 检测报告');
    $('#fm_check').form('clear');
	$('#comCcIsPass').combobox('select',1);
    $('#reportId').val(undefined);
    $('#checkReportTabs').tabs('select','基础信息');
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
	 $('input[name="pubTimeString"]').attr('name','pubTime');   
	 console.log( $('input[name="pubTimeString"]'));
	var rowNum = $('#dg_check').datagrid("getSelections").length;
	if(rowNum > 1 || rowNum == 0){
		$.messager.alert("提示","请选择一行修改！","error");
	}else {
		var row = $('#dg_check').datagrid('getSelected');
		if (row) {
		 	//表单加载数据前关掉联动查询
        	disableLinkage(true);
			$('#dlgCheckReport').dialog('open').dialog('setTitle', '编辑 检测报告');
			$('#fm_check').form('load', row);
			$('#reportId').val(row.id);
            $('#checkReportTabs').tabs('select','基础信息');
            initResultSearchCode(row.id);
			initResultTab(row.id);
			url = UrlConfig.updateData + "?id=" + row.id;
		}
	}
}

// 保存
function saveUser(){
    $('#fm_check').form('submit',{
        url: url,
        onSubmit: function(){
			var t = $('#comCcBlock_checkReport').combotree('tree'); // 得到树对象
			var n = t.tree('getSelected'); // 得到选择的节点
			//这里经过实践测试应该使用t.tree('getChecked');
			if(queryAreaTreeUrl.indexOf('flag=3')!=-1 && n.text == "天河区") {
				$.messager.alert("提示", "请下拉选择【"+n.text+"】下的社区数据！", "info");
				return false;
			}
        	
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
          	  	$.messager.alert("操作提示", result.data, "info");
                $('#dlgCheckReport').dialog('close');        // close the dialog
                $('#dg_check').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                  $('#dlgCheckReport').dialog('close');        // close the dialog
	                  $('#dg_check').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_check').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else{    
        var deleteIds="";
        for (i = 0; i < selRow.length;i++) {
            if (deleteIds =="") {
            	deleteIds = selRow[i].id;
            } else {
            	deleteIds = selRow[i].id + "," + deleteIds;
            }               
        }
        console.log("destroy：ids="+deleteIds);
        $.messager.confirm('提示', '是否删除选中数据?', function (r) {
            if (!r) {
                return;
            }else{
                $.post(UrlConfig.deleteDataByIds, {  
                	deleteIds : deleteIds  
                }, 
                function(result) {  
	                	if(result.successful){
	                		$('#dg_check').datagrid('clearSelections');
	                   	  	$.messager.alert("操作提示", result.data, "info");
	                        $('#dg_check').datagrid('reload');    // reload the user data
	         	          }else{
	         	          		if (result.msg){
	         	              	  $.messager.alert("操作提示", result.msg, "error");
	         	                }
	         	          }
                });
            }
        });
    }
 }

function doSearch(value,name){
	var checkStartMonth = $('#checkStartMonth').combobox('getValue');
	var checkEndMonth = $('#checkEndMonth').combobox('getValue');
	$('#dg_check').datagrid('load',{
		name: $('#name').val(),
		streetId:$('#streetId').combobox('getValue'),   // 下拉框获取数据ID
		blockId:$('#blockId').combobox('getValue'),
		districtId: $('#districtId').combobox('getValue'),
		reportType: $('#reportType').combobox('getValue'),
		checkStartMonth:checkStartMonth,
		checkEndMonth:checkEndMonth
	});
}

//检测结果列表刷新
function doSearchResult(){
	$('#dg_check_result').datagrid('load',{
		reportId:$('#reportId').val(),
		sortCode:$('#sortCodeInput').combobox('getValue'),
		typeCode:$('#typeCodeInput').combobox('getValue'),
		name:$('#resultName').val()
	});
}

function doSearchReset() {
	$('#sortCodeInput').combobox('select',0);
	$('#resultName').val("");
	doSearchResult();
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
		params.showDistrictId = undefined; 
		params.showStreetId = undefined;
		params.showBlockId = undefined;
	    $("#districtId").combobox('select', 0);
        $('#reportType').combobox('select', 0);
	    doSearch();
}
// 导出
function doExport(value,name){
	var checkStartMonth = $('#checkStartMonth').combobox('getValue');
	var checkEndMonth = $('#checkEndMonth').combobox('getValue');
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
        	var exportName = $('#name').val();
        	var openUrl = UrlConfig.exportLibStreet+
        	"?name="+encodeURI(exportName)+
        	"&streetId="+$('#streetId').combobox('getValue')+
        	"&blockId="+$('#blockId').combobox('getValue')+
           	"&districtId="+$('#districtId').combobox('getValue')+
           	"&reportType="+$('#reportType').combobox('getValue')+
           	"&checkStartMonth="+checkStartMonth+
           	"&checkEndMonth="+checkEndMonth
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import_check_report').dialog('open').dialog('setTitle','导入数据');
}

function doImportResult(){
	$('#importResult').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file_check_report').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
	          	  $.messager.alert("操作提示", result.data, "info");
	          	  $('#import_check_report').dialog('close');        // close the dialog
	                $('#dg_check').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#import_check_report').dialog('close');        // close the dialog
	                    $('#dg_check').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

//上传结果文件
function uploadResultFile(){
	console.log('upload resultFile');
    //禁用提交按钮，防止重复提交
    $('#iru').attr('disabled',true);
	var reportId = $('#reportId').val();
	if(!reportId || reportId == 0){
		$.messager.alert('错误', '请先保存基础信息再导入统计结果' , 'error');
        $('#iru').attr('disabled',false);
		return ;
	}
	$('#report_file').form('submit',{
		url: UrlConfig.importResult + '?reportId=' + reportId,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (!result.successful){
				var msg = '<h4>'+result.msg+'</h4>';
				if(result.data && result.data.length > 0){
					msg = msg + '<ul>'
					$.each(result.data,function(i,v){
						msg = msg + '<li>' + v + '</li>';
					})
					msg = msg + '</ul>';
				}
				$.messager.alert('错误', msg , 'error');
                $('#iru').attr('disabled',false);
			} else {
				$.messager.alert('成功', '导入成功' , 'success');
				$('#importResult').dialog('close');
				$('#dg_check_result').datagrid('reload');
                $('#iru').attr('disabled',false);
			}
		}
	});
}

//调用结果分析
function runAnalysis(){
    var reportId = $('#reportId').val();
    if(reportId && reportId>0) {
        var url = '<%=request.getContextPath() %>/app/data/sumReportAnalysis?reportId=' + reportId;
        $.messager.alert("提示", "已经开始后台分析结果，请等待！", "info");
        $.get(url, function (data) {
            if (data && data.successful) {
            	$.messager.alert("提示", "分析成功！风险为：" + data.data, "ok");
            } else {
                if (data.msg && data.msg.length > 0) {
                    $.messager.alert("错误", data.msg, "error");
                } else {
                    $.messager.alert("错误", '请求失败！', "error");
                }
            }
        });
    }else{
        $.messager.alert('错误', '请先保存基础信息再导入统计结果后运行分析' , 'error');
    }
}

function doSumReport() {
	var begin = $('#checkStartMonth').combobox('getValue');
	var end = $('#checkEndMonth').combobox('getValue');
	if( !begin || !end || end < begin ){
		$.messager.alert('错误', '请先选择正确的时间段！' , 'error');
		return ;
	}
	var url = '<%=request.getContextPath() %>/app/data/sumCheckReport?begin='+begin+'&end='+end;
	$.get(url,function(data){
		if(data && data.successful){
			$.messager.alert('提示', '分析成功！' , 'ok');
		}else{
			$.messager.alert('错误', data.msg , 'error');
		}
	});
}
 
function formatString(value){
	if(!value){
		return '待分析';
	}
	return value;
}
</script>
</body>
</html>