<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
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
	        <span class="inputLabel">角色名称:</span>
	        <input id="roleName" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
        </div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_role" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/role/queryPage'
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
        	<th field="roleId" width="50">ID</th>
        	<th field="roleName" width="50">角色名称</th>
            <th field="remark" width="50">备注</th>
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
<div id="dlgRole" class="easyui-dialog" style="width:750px;height:550px"
     closed="true" closable="false" buttons="#dlgRole-buttons">
    <div id="roleMenuTabs" class="easyui-tabs" data-options="fit:true">
        <div title="角色信息">
            <form id="fm_role" method="post" novalidate style="padding:20px 5px">
                <input type="hidden" name="roleId"/>
                <div class="fitem_building">
                    <label>角色名称:</label>
                    <input name="roleName" class="easyui-textbox" required="true">
                </div>
                <div class="fitem_building">
                    <label>备注:</label>
                    <input name="remark" class="easyui-textbox" >
                </div>
            </form>
        </div>
        <div title="配置菜单权限">
            <div class="tableClass">
                <table id="roleMenuTreeGrid"></table>
            </div>
        </div>
    </div>
</div>
<div id="dlgRole-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgRole').dialog('close');$('#roleMenuTreeGrid').treegrid('clearChecked') " style="width:90px">取消</a>
</div>
<script type="text/javascript">

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/role/queryPage',
		insertData: '<%=request.getContextPath() %>/app/role/insertData',
		updateData: '<%=request.getContextPath() %>/app/role/updateData',
		deleteData: '<%=request.getContextPath() %>/app/role/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/role/deleteDataByIds',
};

$(document).ready(function() {
	//表单窗口打开前禁用联动查询
	$('#fm_role').form({
		onLoadSuccess : function(){
			console.info("load Success");
			disableLinkage(false);
		}
	});
});
// 新增
function newStreet(){
    $('#dlgRole').dialog('open').dialog('setTitle','新建角色');
    $('#fm_role').form('clear');
    initMenu("");
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
    var rowNum = $('#dg_role').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_role').datagrid('getSelected');
        if (row) {
            $('#dlgRole').dialog('open').dialog('setTitle', '编辑角色');
            $('#fm_role').form('load', row);
            url = UrlConfig.updateData + "?roleId=" + row.roleId;
            initMenu(row.roleId);
        }
    }
}
var roleId_tab1;
// 保存
function saveUser(){
    // 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
    var tab = $('#roleMenuTabs').tabs('getSelected');
    // 0：角色基本信息  1：配置角色菜单权限
    var index = $('#roleMenuTabs').tabs('getTabIndex',tab);
    var dialogTitle = $('#dlgRole').dialog('header')[0].textContent;
    if(index == 0){
        saveRoleInfo();
    }else if(index == 1){
        if(dialogTitle == "编辑角色"){
            roleId_tab1 =$("#fm_role").children("input[name='roleId']").val();
        }
        // 获取选择的菜单
        var node = $('#roleMenuTreeGrid').treegrid('getCheckedNodes');
        var menuIds = [];
        for(var i = 0;i<node.length;i++){
            console.log("menuid="+node[i].menuid+",parentId="+node[i].parentId);
            var parentId = node[i].parentId;
            // 由于有些父节点是半选中状态，所以均是后台查，后续优化
            if(parentId != null){
                menuIds.push(node[i].menuid);
            }
        }
        // 调接口保存数据
       $.ajax({
            url:"${pageContext.request.contextPath}/app/roleMenu/insertRoleMenus?roleId="+roleId_tab1,
            async: false,      //ajax同步
            type:"POST",
            contentType:"application/json;charset=UTF-8",
            data : JSON.stringify(menuIds),
            success: function(data){
                if(data.successful){
                    $.messager.alert("操作提示", data.data, "info");
                }
            }
        });
    }

}

function saveRoleInfo() {
    $('#fm_role').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){

            var result = eval('('+result+')');
            if(result.successful){
//                $.messager.alert("操作提示", result.data, "info");
//                $('#dlgRole').dialog('close');        // close the dialog
                $.messager.confirm('操作提示',  result.data+'！是否继续配置角色的菜单权限?', function(r){
                    // 是的话跳转tab2
                    if (r){
                        $('#roleMenuTabs').tabs('select','配置菜单权限');
                    }
                });
                $('#dg_role').datagrid('reload');    // reload the user data
                roleId_tab1 = result.total;
            }else{
                if (result.msg){
                    $.messager.alert("操作提示", result.msg, "error");
                } else {
                    $('#dlgRole').dialog('close');        // close the dialog
                    $('#dg_role').datagrid('reload');    // reload the user data
                }
            }
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
	//返回选中多行
    var selRow = $('#dg_role').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else{    
        var deleteIds="";
        for (i = 0; i < selRow.length;i++) {
            if (deleteIds =="") {
            	deleteIds = selRow[i].roleId;
            } else {
            	deleteIds = selRow[i].roleId + "," + deleteIds;
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
                         	  	$('#dg_role').datagrid('clearSelections');
                                $('#dg_role').datagrid('reload');    // reload the user data
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
	$('#dg_role').datagrid('load',{
		roleName: $('#roleName').val()
	});
}

function doReset(){
	  $("#tblQuery").find("input").val("");
	  doSearch();
}

function initMenu(roleId) {
    var menuData;
    $.ajax({
        url:"${pageContext.request.contextPath}/app/menu/queryAllByRoleId?roleId="+roleId,
        async: false,      //ajax同步
        type:"get",
        success: function(data){
            if(data.successful){
                menuData = data.data;
                console.log(menuData);
            }
        }
    });
    $('#roleMenuTreeGrid').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
        checkbox: true,
        idField:'menuid',
        treeField:'menuname',
        method: 'get',
        data:menuData,
        columns:[[
            {title:'菜单名称',field:'menuname',width:180},
            {title:'链接URL',field:'url',width:180}
        ]]
    });
}
</script>
</body>
</html>