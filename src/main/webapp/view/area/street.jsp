<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>街道管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
</head>
<body>
<div id="tblQuery" style="padding:3px">
	<div class="inputDiv">
		<div class='inputDivEg'>
			<span class="inputLabel">行政区:</span>
			<input id="districtId" class="easyui-combobox inputDiv-input" data-options="width:150">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">名称:</span>
			<input id="name" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
		</div>
		<a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
		<a href="#" class="queryBtn" plain="true" onclick="doExport()">导出</a>
		<a href="#" class="queryBtn" plain="true" onclick="doImport()">导入</a>
	</div>
</div>
<div class="tableClass">
<table id="dg_street" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/area/street/queryPage'
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
        	<th field="disctrictName" width="50">行政区名称</th>
            <th field="name" width="50">街道名称</th>
            <th field="code" width="50">编码</th>
            <th field="remark" width="50">说明</th>
            <th field="longitude" width="50">经度</th>
            <th field="latitude" width="50">纬度</th>
            <th field="coverArea" width="50">面积</th>
            <th field="population" width="50">人口</th>
            <th field="modDate" width="50">时间</th>
        </tr>
    </thead>
          <input type="hidden" name="districtId" value="districtId" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" iconCls="icon-add" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgStreet" class="easyui-dialog" style="width:750px;height:450px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">街道 信息</div>
    <form id="fm_street" method="post" novalidate>
    	<div class="fitem_building">
		    <label>行政区:</label>
		    <%--<input id="comCc_street"  name = "districtId"  class="easyui-combobox" --%>
		    <%--data-options="required:true,editable:false,width:173" />--%>
			<input class="easyui-combotree" id="comCc_street" name = "districtId" style="width:173px;height:26px">
		</div>
		<div class="fitem_building">
			<label>您选择的是:</label>
			<span id="showSelected_Street" style="color: #FF3030;"></span>
		</div>
        <div class="fitem_building">
            <label>名称:</label>
            <input name="name" class="easyui-textbox" required="true" />
        </div>
        <div class="fitem_building">
	        <label>经度:</label>
	        <input name="longitude" class="easyui-textbox">
	    </div>
	    <div class="fitem_building">
	        <label>纬度:</label>
	        <input name="latitude" class="easyui-textbox" >
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
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgStreet').dialog('close');$('#showSelected_Street').text('');" style="width:90px">取消</a>
</div>


<div id="import_street" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file_street" method="post" enctype="multipart/form-data" target="ajaxUpload">
    <div style="margin-top:10px;">
		<a href="<%=request.getContextPath() %>/template/street_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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
		listPage: '<%=request.getContextPath() %>/app/area/street/queryPage',
		insertData: '<%=request.getContextPath() %>/app/area/street/insertData',
		updateData: '<%=request.getContextPath() %>/app/area/street/updateData',
		deleteData: '<%=request.getContextPath() %>/app/area/street/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/area/street/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/area/street/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/area/street/importStreet',
};

var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=1";
$(document).ready(function() {
	queryAreaTree(queryAreaTreeUrl,"#comCc_street","#showSelected_Street");
	//表单窗口打开前禁用联动查询
	$('#fm_street').form({
		onLoadSuccess : function(){
			console.info("load Success");
			disableLinkage(false);
		}
	});
});

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var params = {
		districtUrl : districtUrl, 
		newDistrictEle:'comCc_street', 
		searchDistrictEle:'districtId'
};
initDistrict(params);

// 新增
function newStreet(){
    $('#dlgStreet').dialog('open').dialog('setTitle','新建 街道');
    $('#fm_street').form('clear');
	queryAreaTree(queryAreaTreeUrl,"#comCc_street","#showSelected_Street");
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
    var rowNum = $('#dg_street').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_street').datagrid('getSelected');
        if (row) {
        	//表单加载数据前关掉联动查询
        	disableLinkage(true);
            $('#dlgStreet').dialog('open').dialog('setTitle', '编辑街道');
            $('#fm_street').form('load', row);
            url = UrlConfig.updateData + "?id=" + row.id;
        } else {
            $.messager.alert("提示", "请选择要查看的行！", "info");
        }
    }
}

// 保存
function saveUser(){
    $('#fm_street').form('submit',{
        url: url,
        onSubmit: function(){
        	var flag = false;
        	// 经度纬度 验证
        	var longitude=$("input[name='longitude']").val();
        	var latitude=$("input[name='latitude']").val();
        	//用来验证数字，包括小数的正则
        	var reg = /^[0-9]+\.?[0-9]*$/;
        	if(longitude){
            	if(!reg.test(longitude)){
            		   $.messager.alert("提示", "请输入正确的数字格式！", "info");
            		   return false;
            	}else{
            		flag = true;
            	}
        	}else{
        		flag = true;
        	}
        	if(latitude){
            	if(!reg.test(latitude)){
            		   $.messager.alert("提示", "请输入正确的数字格式！", "info");
            		   return false;
            	}else{
            		flag = true;
            	}
        	}else{
        		flag = true;
        	}
        	var validate = $(this).form('validate');
        	return (flag && validate);
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
          	  	$.messager.alert("操作提示", result.data, "info");
	            $('#dlgStreet').dialog('close');        // close the dialog
	            $('#dg_street').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#dlgStreet').dialog('close');        // close the dialog
	                    $('#dg_street').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_street').datagrid('getSelections')
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
                         	  	$.messager.alert("操作提示", result.data, "info");
                         	  	$('#dg_street').datagrid('clearSelections');
                                $('#dg_street').datagrid('reload');    // reload the user data
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
	$('#dg_street').datagrid('load',{
		name: $('#name').val(),
		districtId:$('#districtId').combobox('getValue')   // 下拉框获取数据ID
	});
}

function doReset(){
	  $("#tblQuery").find("input").val("");
	  $("#districtId").combobox('select', 0);
      doSearch();
}
// 导出
function doExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
        	var exportName = $('#name').val();
        	var openUrl = UrlConfig.exportLibStreet+"?name="+encodeURI(exportName)+"&districtId="+$('#districtId').combobox('getValue');
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import_street').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file_street').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){

            var result = eval('('+result+')');
            if(result.successful){
            	  $.messager.alert("操作提示", result.data, "info");
            	  $('#import_street').dialog('close');        // close the dialog
                  $('#dg_street').datagrid('reload');    // reload the user data
            }else{
            	if (result.msg){
                	  $.messager.alert("操作提示", result.msg, "error");
                  } else {
                      $('#import_street').dialog('close');        // close the dialog
                      $('#dg_street').datagrid('reload');    // reload the user data
                  }
            }
        }
    });
}

</script>
</body>
</html>