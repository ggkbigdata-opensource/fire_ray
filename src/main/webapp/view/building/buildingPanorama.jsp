<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>建筑全景图管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<body>
<div id="tblQuery" style="padding:3px">
	<div class='inputDivEg'>
		<span style="font-size:12px">全景名称:</span>
		<input id="name" style="line-height:20px;" class="input">
	</div>
    <div class='inputDivEg'>
        <span style="font-size:12px">建筑:</span>
         <input id="buildingId" class="easyui-combobox"
                data-options="editable:false,width:150">
    </div>
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-back" onclick="doExport()">导出</a>
	<a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-print" onclick="doImport()">导入</a>
</div>
<table id="dg_building_panorama" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/buildingPanorama/queryPage'
        		toolbar="#toolbar"
       			rownumbers="true" 
        		fitColumns="true" 
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                pageSize="20"
                checkOnSelect="true" 
                selectOnCheck="true"> 
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
            <th field="name" width="50">全景名称</th>
            <th field="buildingName" width="50">建筑名称</th>
            <th field="modDate" width="50">时间</th>
        </tr>
    </thead>
          <input type="hidden" name="buildingId" value="buildingId" />
</table>

<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Street Information</div>
    <form id="fm_building_panorama" method="post" novalidate>
        <div class="fitem">
            <label>全景名称:</label>
            <input name="name" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>全景图地址:</label>
            <input name="url" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>建筑名称:</label>
            <input id="comCc"  name = "buildingId" value="0" class="easyui-combobox" 
            data-options="required:true,editable:false,width:150" /> 
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>


<div id="import" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file" method="post" enctype="multipart/form-data" target="ajaxUpload">
    <div class="fitem">
    	<a href="template/street_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
    	 <br/>
        <span style="font-size:12px">选择文件:</span>
        <input type="file" name="file" id="file" style="width:200px">   
        <input type="submit" value="提交"  onclick="uploadFile()" /> 
    </div>
</form>

<iframe name="ajaxUpload" style="display:none"></iframe>

</div>
<script type="text/javascript">

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/buildingPanorama/queryPage',
		insertData: '<%=request.getContextPath() %>/app/buildingPanorama/insertData',
		updateData: '<%=request.getContextPath() %>/app/buildingPanorama/updateData',
		deleteData: '<%=request.getContextPath() %>/app/buildingPanorama/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/buildingPanorama/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/buildingPanorama/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/buildingPanorama/importStreet',
};

var url = "${pageContext.request.contextPath}/app/building/queryPage";
$.getJSON(url, function(json) {
			$('#comCc').combobox({
			data : json.rows,
			valueField:'id',
			textField:'baseName'
	});
});

$.getJSON(url, function(json) {
			$('#buildingId').combobox({
			data : json.rows,
			valueField:'id',
			textField:'baseName'
	});
});
// 新增
function newStreet(){
    $('#dlg').dialog('open').dialog('setTitle','New buildingPanorama');
    $('#fm_building_panorama').form('clear');
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
    var rowNum = $('#dg_building_panorama').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_building_panorama').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', 'Edit buildingPanorama');
            $('#fm_building_panorama').form('load', row);
            url = UrlConfig.updateData + "?id=" + row.id;
        }
    }
}

// 保存
function saveUser(){
    $('#fm_building_panorama').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#dlg').dialog('close');        // close the dialog
                $('#dg_building_panorama').datagrid('reload');    // reload the user data
            }
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_building_panorama').datagrid('getSelections')
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
                          $('#dg_building_panorama').datagrid('clearSelections');
                          $.messager.alert("提示", "恭喜您，信息删除成功！", "info");
                          $('#dg_building_panorama').datagrid('reload');
                });
            }
        });
    }
 }

function doSearch(value,name){
	$('#dg_building_panorama').datagrid('load',{
		name: $('#name').val(),
		buildingId:$('#buildingId').combobox('getValue')   // 下拉框获取数据ID
	});
}

function doReset(){
	  $("#tblQuery").find("input").val("");
}
// 导出
function doExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
        	var openUrl = UrlConfig.exportLibStreet+"?name="+$('#name').val()+"&buildingId="+$('#buildingId').combobox('getValue');
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#import').dialog('close');        // close the dialog
                $('#dg_building_panorama').datagrid('reload');    // reload the user data
            }
        }
    });
}

</script>
</body>
</html>