<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>树网格案例</title>
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
	<div style="margin:5px 0;">
		<div class="inputDiv">
		<a href="javascript:void(0)" class="resetBtn" plain="true"  onclick="newSuperMenu()">新增一级菜单</a>
		<a href="javascript:void(0)" class="queryBtn" plain="true" onclick="reload()">刷新</a>
		</div>
	</div>
	<div class="tableClass">
	<table id="menuTreeGrid" class="easyui-treegrid" title="菜单结构列表" style="width:auto;height:auto;"
			data-options="
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				 <%--checkbox: true,--%>
				url: '<%=request.getContextPath() %>/app/menu/queryAll',
				method: 'get',
				idField: 'menuId',
				treeField: 'menuName',
				onContextMenu: onContextMenu
			">
		<thead>
			<tr>
				<th data-options="field:'menuName',width:180">菜单名称</th>
				<th data-options="field:'menuUrl',width:150">菜单Url</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="mm_hasParent" class="easyui-menu" style="width:120px;">
		<div onclick="editMenu()" data-options="iconCls:'icon-add'">编辑菜单</div>
		<div onclick="appendMenuChild()" data-options="iconCls:'icon-add'">添加子菜单</div>
		<div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="editMenu()" data-options="iconCls:'icon-add'">编辑菜单</div>
		<div onclick="appendMenuChild()" data-options="iconCls:'icon-add'">添加子菜单</div>
		<div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapse()">折叠</div>
		<div onclick="expand()">展开</div>
	</div>
	
<div id="dlgMenuSuper" class="easyui-dialog" style="width:350px;height:300px;padding:10px 20px"
    closed="true" buttons="#dlgMenuSuper-buttons">
<div class="ftitle">菜单信息</div>
<form id="fm_menu_super">
<!-- 	<div class="fitem">
        <label>角色(权限):</label>
        <input id="comCcMenuRole"  name = "roleId"  class="easyui-combobox"
               data-options="required:true,editable:false,width:173" />
    </div> -->
    <div class="fitem">
        <label>名称:</label>
        <input name="menuName" class="easyui-textbox"  required="true" >
    </div>
	<div class="fitem">
		<label>菜单Url:</label>
		<input name="menuUrl" class="easyui-textbox" >
	</div>
	<%--<div class="fitem">--%>
		<%--<label>角色:</label>--%>
		<%--<input class="easyui-combobox" --%>
			<%--id="rolesForMenuAuth"--%>
			<%--name="menuUrl" style="width:245px;"--%>
			<%--data-options="--%>
					<%--url:' ${pageContext.request.contextPath}/app/role/queryAllForMenu',--%>
					<%--method:'get',--%>
					<%--valueField:'roleId',--%>
					<%--textField:'roleName',--%>
					<%--multiple:true,--%>
					<%--panelHeight:'auto'--%>
		<%--">--%>
	<%--</div>--%>
	<input type="hidden" name="parentMenuId">
	<input type="hidden" name="menuId">
</form>
</div>
<div id="dlgMenuSuper-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveMenuSuper()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgMenuSuper').dialog('close')" style="width:90px">取消</a>
</div>

<script type="text/javascript">
var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/menu/queryPage',
		insertData: '<%=request.getContextPath() %>/app/menu/insertData',
		updateData: '<%=request.getContextPath() %>/app/menu/updateData',
		deleteData: '<%=request.getContextPath() %>/app/menu/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/menu/deleteDataByIds',
};

// var rolesUrl = "${pageContext.request.contextPath}/app/role/queryAll";
// //获取数据字典数据
// var comCcMenuRoleDatas;
// $.ajax({
// 	 url:rolesUrl,
// 	 async: false,      //ajax同步
// 	 type:"get",
// 	 success: function(data){
// 	     comCcMenuRoleDatas = data.rows;
// 	 }
// });
// $('#comCcMenuRole').combobox({
// 	 data : comCcMenuRoleDatas,
// 	 valueField:'roleId',
// 	 textField:'roleName'
// });

function onContextMenu(e,row){
	e.preventDefault();
	$(this).treegrid('select', row.menuId);
	if(row.children != null && row.children.length > 0){
		$('#mm').menu('show',{
			left: e.pageX,
			top: e.pageY
		});
	}else{
		$('#mm_hasParent').menu('show',{
			left: e.pageX,
			top: e.pageY
		});
	}
}
//var idIndex = 100;
function appendMenuChild(){
//	idIndex++;
	var node = $('#menuTreeGrid').treegrid('getSelected');
	console.log(node.menuId+",parentId="+node._parentId);
	$('#dlgMenuSuper').dialog('open').dialog('setTitle','新建菜单');
	$('#fm_menu_super').form('clear');
	$("input[name='parentMenuId']").val(node.menuId);
	url =UrlConfig.insertData;
	 // $.messager.alert("提示", "待开发，请耐心等候！", "info");
	/*$('#menuTreeGrid').treegrid('append',{
		parent: node.menuId,
		data: [{
			menuId: idIndex,
			_parentId:node.menuId,
			menuName: '新增菜单'+idIndex
		}]
	})*/
}


function editMenu() {
	var node = $('#menuTreeGrid').treegrid('getSelected');
	if (node) {
		//表单加载数据前关掉联动查询
		disableLinkage(true);
		$('#dlgMenuSuper').dialog('open').dialog('setTitle', '编辑菜单');
		$('#fm_menu_super').form('load', node);
		url = UrlConfig.updateData + "?id=" + node.menuId;
	} else {
		$.messager.alert("提示", "请选择要查看的行！", "info");
	}
}
function removeIt(){
	var node = $('#menuTreeGrid').treegrid('getSelected');
//	if (node){
//		$('#menuTreeGrid').treegrid('remove', node.menuId);
//	}
	var node = $('#menuTreeGrid').treegrid('getSelected');
	var delMsg = "";
	if(node.children != null && node.children.length > 0){
		delMsg = '菜单【'+node.menuName+'】有子菜单，是否删除全部数据？';
	}else{
		delMsg = '是否删除【'+node.menuName+'】数据？';
	}
	$.messager.confirm('提示', delMsg, function (r) {
		if (!r) {
			return;
		}else{
			$.post(UrlConfig.deleteData, {
						id : node.menuId
					},
					function(result) {
						if(result.successful){
							$.messager.alert("操作提示", result.data, "info");
							$('#menuTreeGrid').treegrid('reload');
						}else{
							if (result.msg){
								$.messager.alert("操作提示", result.msg, "error");
							}
						}
					});
		}
	});
}
function collapse(){
	var node = $('#menuTreeGrid').treegrid('getSelected');
	if (node){
		$('#menuTreeGrid').treegrid('collapse', node.menuId);
	}
}
function expand(){
	var node = $('#menuTreeGrid').treegrid('getSelected');
	if (node){
		$('#menuTreeGrid').treegrid('expand', node.menuId);
	}
}

function newSuperMenu(){
    $('#dlgMenuSuper').dialog('open').dialog('setTitle','新建菜单');
    $('#fm_menu_super').form('clear');
    url =UrlConfig.insertData;
}

function saveMenuSuper(){
    $('#fm_menu_super').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
          	  	$.messager.alert("操作提示", result.data, "info");
                $('#dlgMenuSuper').dialog('close');        // close the dialog
                $('#menuTreeGrid').treegrid('reload');
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                  $('#dlgMenuSuper').dialog('close');        // close the dialog
	                  $('#menuTreeGrid').treegrid('reload');
	                }
	          }
        }
    });
}
function  reload() {
	$('#menuTreeGrid').treegrid('reload');
}
</script>
</body>
</html>