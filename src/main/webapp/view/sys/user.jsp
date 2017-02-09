<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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
		    <input id="districtId" class="easyui-combobox inputDiv-input" data-options="editable:false,width:173">
	    </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">用户名:</span>
	        <input id="username" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">手机号码:</span>
	        <input id="mobile" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
        </div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_user" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/user/queryPage'
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
        	<th field="uid" width="50">UID</th>
        	<th field="disctrictName" width="50">行政区</th>
        	<th field="username" width="50">用户名</th>
            <th field="realName" width="50">姓名</th>
            <th field="mobile" width="50">电话</th>
            <th field="email" width="50">电子邮件</th>
            <th field="job" width="50">工作岗位</th>
            <th field="remark" width="50">备注</th>
            <th field="modDate" width="50">时间</th>
        </tr>
    </thead>
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" iconCls="icon-add" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgUser" class="easyui-dialog" style="width:750px;height:380px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">用户 信息</div>
    <form id="fm_user" method="post" novalidate>
	    <div class="fitem_building">
			    <label>行政区:</label>
			    <input id="comCc_user"  name = "districtId"  class="easyui-combobox" 
			    data-options="required:true,editable:false,width:150" /> 
		</div>
        <div class="fitem_building">
            <label>您选择的是:</label>
            <span id="showSelected_user" style="color: #FF3030;"></span>
        </div>
        <div class="fitem_building">
            <label>角色:</label>
            <input id="comCcUserRole"  name = "roleId"  class="easyui-combobox"
                   data-options="required:true,editable:false,width:173" />
        </div>
        <div class="fitem_building">
            <label>用户名:</label>
            <input name="username" class="easyui-textbox"  prompt="登录账号(英文小写字母)"  required="true">
        </div>
        <div class="fitem_building">
	        <label>姓名:</label>
	        <input name="realName" class="easyui-textbox" required="true">
	    </div>
        <div class="fitem_building">
	        <label>密码:</label>
	        <input class="easyui-passwordbox" name="password" prompt="密码" iconWidth="28" required="true">
	    </div>
        <div class="fitem_building">
            <label>电话:</label>
            <input name="mobile" class="easyui-textbox" data-options="validType:'length[1,11]'" prompt="请输入1-11位的数字">
        </div>
        <div class="fitem">
            <label class="area">备注:</label>
            <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:100px">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgUser').dialog('close');$('#showSelected_user').text('');" style="width:90px">取消</a>
</div>
<script type="text/javascript">

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/user/queryPage',
		insertData: '<%=request.getContextPath() %>/app/user/insertData',
		updateData: '<%=request.getContextPath() %>/app/user/updateData',
		deleteData: '<%=request.getContextPath() %>/app/user/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/user/deleteDataByIds',
};
var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=1";
$(document).ready(function() {
    queryAreaTree(queryAreaTreeUrl,"#comCc_user","#showSelected_user");
	//表单窗口打开前禁用联动查询
	$('#fm_user').form({
		onLoadSuccess : function(){
			console.info("load Success");
			disableLinkage(false);
		}
	});
});

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var params = {
		districtUrl : districtUrl, 
		newDistrictEle:'comCc_user', 
		searchDistrictEle:'districtId'
};
initDistrict(params);

var rolesUrl = "${pageContext.request.contextPath}/app/role/queryAll";
// 获取数据字典数据
var comCcUserRoleDatas;
$.ajax({
    url:rolesUrl,
    async: false,      //ajax同步
    type:"get",
    success: function(data){
        comCcUserRoleDatas = data.rows;
    }
});
$('#comCcUserRole').combobox({
    data : comCcUserRoleDatas,
    valueField:'roleId',
    textField:'roleName'
});

// 新增
function newStreet(){
    queryAreaTree(queryAreaTreeUrl,"#comCc_user","#showSelected_user");
    $('#dlgUser').dialog('open').dialog('setTitle','新建 用户');
    $('#fm_user').form('clear');
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
    var rowNum = $('#dg_user').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_user').datagrid('getSelected');
        if (row) {
            $('#dlgUser').dialog('open').dialog('setTitle', '编辑 用户');
            $('#fm_user').form('load', row);
            url = UrlConfig.updateData + "?id=" + row.id;
        }
    }
}

// 保存
function saveUser(){
	console.log("user save");
    $('#fm_user').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){

            var result = eval('('+result+')');
            if(result.successful){
          	  	$.messager.alert("操作提示", result.data, "info");
	            $('#dlgUser').dialog('close');        // close the dialog
	            $('#dg_user').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#dlgUser').dialog('close');        // close the dialog
	                    $('#dg_user').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
	//返回选中多行
    var selRow = $('#dg_user').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else{    
        var deleteIds="";
        for (i = 0; i < selRow.length;i++) {
            if (deleteIds =="") {
            	deleteIds = selRow[i].uid;
            } else {
            	deleteIds = selRow[i].uid + "," + deleteIds;
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
                         	  	$('#dg_user').datagrid('clearSelections');
                                $('#dg_user').datagrid('reload');    // reload the user data
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
	$('#dg_user').datagrid('load',{
		username: $('#username').val(),
		mobile: $('#mobile').val()
	});
}

function doReset(){
	  $("#tblQuery").find("input").val("");
	  $("#districtId").combobox('select', 0);
	  doSearch();
}
</script>
</body>
</html>