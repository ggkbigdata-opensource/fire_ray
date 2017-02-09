<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测报告定义管理</title>
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
	<div>
		<div class='inputDivEg'>
			<span style="font-size:12px">检测项编码:</span>
			<input id="code" class="easyui-textbox" style="line-height:20px;"/>
		</div>
		<div class='inputDivEg'>
			<span style="font-size:12px">报告名称:</span>
			<input id="name" class="easyui-textbox" style="line-height:20px;"/>
		</div>
		<a href="#" class="resetBtn" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
	</div>
</div>
<table id="dg_check_def" title="数据列表" class="easyui-datagrid"
        url='<%=request.getContextPath() %>/app/checkItemDef/query'
        		toolbar="#toolbar_def"
       			rownumbers="true"
	   			fitColumns="true"
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                pageSize="20"
                striped="true"
	   			loadFilter="loadFilter"
                checkOnSelect="false"
                selectOnCheck="true">
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
            <th field="code" editor="text">检测项编码</th>
			<th field="name" editor="text" width="100">检测项名称</th>
			<th field="level" editor="text">检测项等级</th>
			<th field="standard" editor="text" width="500">检测项标准</th>
        </tr>
    </thead>
</table>

<div id="toolbar_def">
    <a href="#" class="resetBtn"  iconCls="icon-add" plain="true" onclick="doImport()">新增</a>
    <a href="#" class="queryBtn"  iconCls="icon-edit" plain="true" onclick="editStreet()">预览</a>
    <a href="#" class="queryBtn"  iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<div id="import_check_report" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入新增报告数据</div>
<form id="fm_file_check_report" method="post" enctype="multipart/form-data" target="ajaxUpload">
     <%--<div style="margin-top:10px;">--%>
		<%--<a href="<%=request.getContextPath() %>/template/checkReport_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>--%>
	<%--</div>--%>
	<div style="margin-top:10px;">
		<div style="float:left;">
			<input class="easyui-filebox" data-options="buttonText:'选择文件'" id="uploadFile" name="uploadFile" style="width:250px;margin:20px;">
		</div>
		<div style="float:right;">
			<a href="#" class="easyui-linkbutton" iconCls='icon-ok' onclick="uploadFile()">提交</a>
		</div>
	</div>
	<div style="padding-top: 40px">
		<span id="checkIng"></span>
	</div>
</form>

<iframe name="ajaxUpload" style="display:none"></iframe>

</div>
<script type="text/javascript">
var dictListCheckReport;
var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/checkReport/queryPage',
		insertData: '<%=request.getContextPath() %>/app/checkReport/insertData',
		updateData: '<%=request.getContextPath() %>/app/checkReport/updateData',
		deleteData: '<%=request.getContextPath() %>/app/checkReport/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/checkReport/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/checkReport/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/checkReportInfo/uploadReportFile',
        checkProgress:'<%=request.getContextPath() %>/app/checkReportInfo/progress',
	    importResult:'<%=request.getContextPath() %>/app/checkItemResult/importReportResult'
};

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
	$('#dg_check').datagrid('load',{
		name: $('#name').val(),
		code: $('#code').val()
	});
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
//		params.showDistrictId = undefined;
//		params.showStreetId = undefined;
//		params.showBlockId = undefined;
//	    $("#districtId").combobox('select', 0);
//        $('#reportType').combobox('select', 0);
	    doSearch();
}

//导入
function doImport(){
    $('#import_check_report').dialog('open').dialog('setTitle','导入数据');
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
	          	$.messager.alert("操作提示", result.msg, "info");
				$('#checkIng').html("处理中");
                checkProgress(result.data);
	          }else {
                if (result.msg) {
                    $.messager.alert("操作提示", result.msg, "error");
                } else {
                    $('#import_check_report').dialog('close');        // close the dialog
                    $('#dg_check').datagrid('reload');    // reload the user data
                }
            }
        }
    });
}

$('#dg_check_def').datagrid({
	loadFilter: function(data){
		console.info("data",data);
		return {
			rows:data.data,
			total:data.total
		}
	}
});
</script>
</body>
</html>