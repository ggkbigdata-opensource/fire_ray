<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>行政区管理</title>
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
    <div class="inputDiv">
        <div class='inputDivEg'>
	        <span class="inputLabel">名称:</span>
	        <input id="name" class="easyui-textbox inputDiv-input"/>
        </div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
        <a href="#" class="queryBtn" plain="true" onclick="doExport()">导出</a>
        <a href="#" class="queryBtn" plain="true" onclick="doImport()">导入</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_district" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/area/district/queryPage'
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
            <th field="name" width="50">名称</th>
            <th field="code" width="50">编码</th>
            <th field="coverArea" width="50">面积</th>
            <th field="population" width="50">人口</th>
            <th field="remark" width="50">说明</th>
            <th field="modDate" width="50">时间</th>
            <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>  
        </tr>
    </thead>
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" plain="true" onclick="destroyUsers()">批量删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgDistrict" class="easyui-dialog" style="width:750px;height:350px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">行政区 信息</div>
    <form id="fm_district" method="post" novalidate>
        <div class="fitem_building">
            <label>名称:</label>
            <input name="name" class="easyui-textbox" required="true" />
        </div>
        <div class="fitem_building">
	        <label>面积:</label>
	        <input name="coverArea" class="easyui-textbox">
	    </div>
	    <div class="fitem_building">
	        <label>人口:</label>
	        <input name="population" class="easyui-textbox" >
	    </div>
	    <div class="fitem">
		  	 <label class="area">说明:</label>
	        <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:100px">
	 </div>
    </form>
</div>

<div id="dlgCheckDistrict" class="easyui-dialog" style="width:750px;height:280px;padding:10px 20px"
    closed="true">
<div class="ftitle">行政区 信息</div>
<form id="fm_check_district">
    <div class="fitem_building">
        <label>名称:</label>
        <input name="name" class="easyui-textbox">
    </div>
    <div class="fitem_building">
        <label>说明:</label>
        <input name="remark" class="easyui-textbox">
    </div>
    <div class="fitem_building">
        <label>面积:</label>
        <input name="coverArea" class="easyui-textbox">
    </div>
    <div class="fitem_building">
        <label>人口:</label>
        <input name="population" class="easyui-textbox" >
    </div>
</form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgDistrict').dialog('close')" style="width:90px">取消</a>
</div>


<div id="import_district" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file_district" method="post" enctype="multipart/form-data" target="ajaxUpload">
    <div style="margin-top:10px;">
		<a href="<%=request.getContextPath() %>/template/district_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/area/district/queryPage',
		insertData: '<%=request.getContextPath() %>/app/area/district/insertData',
		updateData: '<%=request.getContextPath() %>/app/area/district/updateData',
		deleteData: '<%=request.getContextPath() %>/app/area/district/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/area/district/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/area/district/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/area/district/importStreet'
};
function formatOper(val,row,index){  
    return  '<a href="#" class="tableBtn" onclick="checkBean('+index+')">查看</a>'+
    '<a href="#" class="tableBtn" onclick="editBean('+index+')">更新</a>'+
    '<a href="#" class="tableBtn" onclick="deleteBean('+index+')">删除</a>';
}  

function editBean(index){  
    $('#dg_district').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg_district').datagrid('getSelected');  
    if (row){  
        $('#dlgDistrict').dialog('open').dialog('setTitle','更新行政区信息');  
        $('#fm_district').form('load',row);  
        url = UrlConfig.updateData + "?id=" + row.id;
    }  
}  
function checkBean(index){  
    $('#dg_district').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg_district').datagrid('getSelected');  
    if (row){  
        $('#dlgCheckDistrict').dialog('open').dialog('setTitle','查看行政区信息');  
        $('#fm_check_district').form('load',row);  
//        url = UrlConfig.updateData + "?id=" + row.id;
    }  
}  


//单个删除
function deleteBean(index){
	 $('#dg_district').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg_district').datagrid('getSelected');
    if (row){
        $.messager.confirm('确认','你确定要删除名称为【'+row.name+'】数据吗？',function(r){
            if (r){
                $.post(UrlConfig.deleteData,{id:row.id},function(result){
                    if (result.successful){
                        $('#dg_district').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.data
                        });
                    }
                },'json');
            }
        });
    }
}


// 新增
function newStreet(){
    $('#dlgDistrict').dialog('open').dialog('setTitle','新建 行政区');
    $('#fm_district').form('clear');
    url =UrlConfig.insertData;
}

// 编辑修改
//function editStreet(){
//    var rowNum = $('#dg_district').datagrid("getSelections").length;
//    if(rowNum > 1 || rowNum == 0){
//        $.messager.alert("提示","请选择一行修改！","error");
//    }else {
//        var row = $('#dg_district').datagrid('getSelected');
//        if (row) {
//            $('#dlgDistrict').dialog('open').dialog('setTitle', '编辑 行政区');
//            $('#fm_district').form('load', row);
//            url = UrlConfig.updateData + "?id=" + row.id;
//        }
//    }
//}

// 保存
function saveUser(){
    $('#fm_district').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
          	  	$.messager.alert("操作提示", result.data, "info");
                $('#dlgDistrict').dialog('close');        // close the dialog
                $('#dg_district').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                  $('#dlgDistrict').dialog('close');        // close the dialog
	                  $('#dg_district').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

//批量删除
function destroyUsers(){//返回选中多行
    var selRow = $('#dg_district').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else{    
        var deleteIds=[];
        for (i = 0; i < selRow.length;i++) {
            deleteIds.push(selRow[i].id);
        }
        console.log("destroy：ids="+JSON.stringify(deleteIds));
        $.messager.confirm('提示', '是否删除选中数据?', function (r) {
            if (!r) {
                return;
            }else{
                $.ajax({
                    url:UrlConfig.deleteDataByIds,
                    async: false,      //ajax同步
                    type:"POST",
                    contentType:"application/json;charset=UTF-8",
                    data : JSON.stringify(deleteIds),
                    success: function(result){
                        if(result.successful){
                   	  	$.messager.alert("操作提示", result.data, "info");
                		$('#dg_district').datagrid('clearSelections');
                        $('#dg_district').datagrid('reload');    // reload the user data
         	          }else{
         	          		if (result.msg){
         	              	  $.messager.alert("操作提示", result.msg, "error");
         	                }
         	          }
                    }
                });
            }
        });
    }
 }

function doSearch(value,name){
	$('#dg_district').datagrid('load',{
		name: $('#name').val()
	});
}

function doReset(){
	  $("#tblQuery").find("input").val("");
}
//导出
function doExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
        	var exportName = $('#name').val();
        	var openUrl = UrlConfig.exportLibStreet+"?name="+encodeURI(exportName);
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import_district').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file_district').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
            	  $.messager.alert("操作提示", result.data, "info");
            	  $('#import_district').dialog('close');        // close the dialog
                  $('#dg_district').datagrid('reload');    // reload the user data
            }else{
            	if (result.msg){
                	  $.messager.alert("操作提示", result.msg, "error");
                  } else {
                      $('#import_district').dialog('close');        // close the dialog
                      $('#dg_district').datagrid('reload');    // reload the user data
                  }
            }
        }
    });
}
</script>
</body>
</html>