<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社区管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
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
			<span class="inputLabel">街道:</span>
			<input id="streetId" class="easyui-combobox inputDiv-input" data-options="width:150">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">类型:</span>
			<input id="blockType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:150">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">名称:</span>
			<input id="name" class="easyui-textbox" style="line-height:20px;"/>
		</div>
		<a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
		<a href="#" class="queryBtn" plain="true" onclick="doExport()">导出</a>
		<a href="#" class="queryBtn" plain="true" onclick="doImport()">导入</a>
	</div>
</div>
<div class="tableClass">
<table id="dg_block" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/area/block/queryPage'
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
        	<th field="districtName" width="50">行政区</th>
        	<th field="streetName" width="50">街道名称</th>
            <th field="name" width="50">社区名称</th>
            <th field="code" width="50">编码</th>
            <th field="typeName" width="50">类型</th>
            <th field="remark" width="50">说明</th>
            <th field="longitude" width="50">经度</th>
            <th field="latitude" width="50">纬度</th>
            <th field="coverArea" width="50">面积</th>
            <th field="population" width="50">人口</th>
            <th field="modTime" width="50">时间</th>
        </tr>
    </thead>
    	   <input type="hidden" name="districtId" value="districtId" />
          <input type="hidden" name="streetId" value="streetId" />
          <input type="hidden" name="blockType" value="blockType" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" iconCls="icon-add" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" iconCls="icon-remove" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgBlock" class="easyui-dialog" style="width:750px;height:450px;padding:10px 20px"
        closed="true" buttons="#dlgBlock-buttons">
    <div class="ftitle">社区 信息</div>
    <form id="fm_block" method="post" novalidate>
		<input name = "districtId" type="hidden">
	    <%--<div>--%>
			<%--<span  style="color: red;font-size:16px;text-align:center;">温馨提示：请依此选择：行政区-->街道</span>--%>
		<%--</div>--%>
		<%--<br/>--%>
	    <%--<div class="fitem_building">--%>
	    <%--<label>行政区:</label>--%>
		    <%--<input id="comCcDistrict_block"  name = "districtId"  class="easyui-combobox"--%>
		    <%--data-options="required:true,editable:false,width:173" />--%>
		<%--</div>--%>
    	<div class="fitem_building">
		    <label>街道:</label>
			<input class="easyui-combotree" id="comCcStreet_block" name = "streetId" style="width:173px;height:26px">
		</div>
		<div class="fitem_building">
			<label>您选择的是:</label>
			<span id="showSelected_block" style="color: #FF3030;width: 200px;"></span>
		</div>
        <div class="fitem_building">
            <label>名称:</label>
            <input name="name" class="easyui-textbox" required="true" />
        </div>
        <div class="fitem_building">
		    <label>类型:</label>
		    <input id="comCcBlockType"  name = "blockType"  class="easyui-combobox" 
		    data-options="required:true,editable:false,width:173" /> 
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
<div id="dlgBlock-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgBlock').dialog('close');$('#fm_block').form('clear');$('#showSelected_block').text('');" style="width:90px">取消</a>
</div>


<div id="import_block" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file_block" method="post" enctype="multipart/form-data" target="ajaxUpload">
    <div style="margin-top:10px;">
		<a href="<%=request.getContextPath() %>/template/block_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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
var blockTypeData,
blockTypeDataSearch;
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getDictList?typeCode=block_type",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
        	blockTypeData = data.data;
        	blockTypeDataSearch = data.data;
        }
    }
});
var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=2";
$(document).ready(function() {
	//新增位置   类型
	queryAreaTree(queryAreaTreeUrl,"#comCcStreet_block","#showSelected_block");
	$('#comCcBlockType').combobox({
		data : blockTypeData,
		valueField:'code',
		textField:'name'
	});

	blockTypeDataSearch.unshift({code: '0', name: '全部'});
	$('#blockType').combobox({
			data : blockTypeDataSearch,
			valueField:'code',
			textField:'name',
			value: '0'
	});
	//表单窗口打开前禁用联动查询
	$('#fm_block').form({
		onLoadSuccess : function(){
			console.info("load Success");
			disableLinkage(false);
		}
	});
});

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
var params = {
		districtUrl : districtUrl, 
		streetUrl:streetUrl,
		newDistrictEle:'comCcDistrict_block', 
		searchDistrictEle:'districtId',
		newStreetEle:'comCcStreet_block',
		searchStreetEle:'streetId'
};

initDistrict(params);

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/area/block/queryPage',
		insertData: '<%=request.getContextPath() %>/app/area/block/insertData',
		updateData: '<%=request.getContextPath() %>/app/area/block/updateData',
		deleteData: '<%=request.getContextPath() %>/app/area/block/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/area/block/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/area/block/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/area/block/importStreet',
};
// 新增
function newStreet(){
//	flag = true;
    $('#dlgBlock').dialog('open').dialog('setTitle','新建 社区');
    $('#fm_block').form('clear');
	queryAreaTree(queryAreaTreeUrl,"#comCcStreet_block","#showSelected_block");
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
    var rowNum = $('#dg_block').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_block').datagrid('getSelected');
        if (row) {
        	//表单加载数据前关掉联动查询
        	disableLinkage(true);
            $('#dlgBlock').dialog('open').dialog('setTitle', '编辑 社区');
			$('#fm_block').form('load', row);
            url = UrlConfig.updateData + "?id=" + row.id;
        } else {
            $.messager.alert("提示", "请选择要查看的行！", "info");
        }
    }
}

// 保存
function saveUser(){
    $('#fm_block').form('submit',{
        url: url,
        onSubmit: function(){
			var t = $('#comCcStreet_block').combotree('tree'); // 得到树对象
			var n = t.tree('getSelected'); // 得到选择的节点
			//这里经过实践测试应该使用t.tree('getChecked');
			if(queryAreaTreeUrl.indexOf('flag=2')!=-1 && n.text == "天河区") {
				$.messager.alert("提示", "请下拉选择【"+n.text+"】下的街道数据！", "info");
				return false;
			}
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
//			return validate;
        	return (flag && validate);
        },
        success: function(result){
            var result = eval('('+result+')');
			if (result.successful) {
				$.messager.alert("操作提示", result.data, "info");
				$('#dlgBlock').dialog('close');        // close the dialog
				$('#dg_block').datagrid('reload');    // reload the user data
			} else {
				if (result.msg) {
					$.messager.alert("操作提示", result.msg, "error");
				} else {
					$('#dlgBlock').dialog('close');        // close the dialog
					$('#dg_block').datagrid('reload');    // reload the user data
				}
			}
			//提交后需要清除表单数据
			$('#fm_block').form('clear');
        }
    });
}

//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_block').datagrid('getSelections')
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
	                		$('#dg_block').datagrid('clearSelections');
	                        $('#dg_block').datagrid('reload');    // reload the user data
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
	$('#dg_block').datagrid('load',{
		name: $('#name').val(),
		districtId:$('#districtId').combobox('getValue'),
		streetId:$('#streetId').combobox('getValue'),   // 下拉框获取数据ID
		blockType:$('#blockType').combobox('getValue')
	});
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
	  $("#districtId").combobox('select', 0);
	  $("#streetId").combobox('select', 0);
	  $("#blockType").combobox('select', 0);
	  $("#name").val("");
      doSearch();
}
// 导出
function doExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
          	var exportName = $('#name').val();
        	var openUrl = UrlConfig.exportLibStreet+"?name="+encodeURI(exportName)+"&streetId="+$('#streetId').combobox('getValue')+
        								"&districtId="+$('#districtId').combobox('getValue')+"&blockType="+$('#blockType').combobox('getValue');
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import_block').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file_block').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
	          	  $.messager.alert("操作提示", result.data, "info");
	          	  $('#import_block').dialog('close');        // close the dialog
	                $('#dg_block').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#import_block').dialog('close');        // close the dialog
	                    $('#dg_block').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

</script>
</body>
</html>