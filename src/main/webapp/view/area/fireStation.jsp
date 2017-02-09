<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微型消防站管理</title>
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
		<span class="inputLabel">社区:</span>
		<input id="blockId" class="easyui-combobox inputDiv-input" data-options="width:150">
	</div>
	<div class='inputDivEg'>
		<span class="inputLabel">名称:</span>
		<input id="name" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
	</div>
	<div class='inputDivEg'>
		<span class="inputLabel">编码:</span>
		<input id="code" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
	</div>
	<div class="height10"></div>
	<a href="#" class="resetBtn" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
	<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
	<a href="#" class="queryBtn" plain="true"  iconCls="icon-back" onclick="doExport()">导出</a>
	<a href="#" class="queryBtn" plain="true"  iconCls="icon-print" onclick="doImport()">导入</a>
	</div>
</div>
<div class="tableClass">
<table id="dg_fire_station" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/area/fireStation/queryPage'
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
        	<th field="blockName" width="50">社区名称</th>
            <th field="name" width="50">消防站名称</th>
            <th field="code" width="50">编码</th>
            <th field="officeSum" width="50">人员总数</th>
            <th field="vehicleSum" width="50">车辆总数</th>
            <th field="equipmentSum" width="50">装备总数</th>
            <th field="remark" width="50">说明</th>
            <th field="area" width="50">面积</th>
            <th field="modTime" width="50">时间</th>
        </tr>
    </thead>
    	   <input type="hidden" name="districtId" value="districtId" />
          <input type="hidden" name="streetId" value="streetId" />
          <input type="hidden" name="blockId" value="streetId" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgFireStation" class="easyui-dialog" style="width:1050px;height:550px;"
        closed="true" closable="false" buttons="#dlgFireStation-buttons">
<div id="fireStationTabs" class="easyui-tabs" data-options="fit:true">
    <div title="基础信息">
	    <form id="fm_fire_station" method="post" novalidate>
	    	<input type="hidden" name="id"/>
	    	
	    	<input type="hidden" name="office"/>
	    	<input type="hidden" name="vehicle"/>
	    	<input type="hidden" name="equipment"/>
	    	
		    <div style="margin-top: 10px;margin-left:10px;">
				<%--<span  style="color: red;font-size:16px;text-align:center;">温馨提示：请依此选择：行政区-->街道-->社区</span>--%>
			</div>
			<%--<br/>--%>
		    <%--<div class="fitem_building">--%>
		    <%--<label>行政区:</label>--%>
			    <%--<input id="comCcDistrict_fire_station"  name = "districtId"  class="easyui-combobox"--%>
			    <%--data-options="required:true,editable:false,width:173" />--%>
			<%--</div>--%>
	    	<%--<div class="fitem_building">--%>
			    <%--<label>街道:</label>--%>
			    <%--<input id="comCcStreet_fire_station"  name = "streetId"  class="easyui-combobox"--%>
			    <%--data-options="required:true,editable:false,width:173" /> --%>
			<%--</div>--%>
			<div class="fitem_building">
				<label>社区:</label>
				<input class="easyui-combotree" id="comCcBlock_fire_station" name = "blockId" style="width:173px;height:26px" required="true" >
			</div>
			<div class="fitem_building">
				<label>您选择的是:</label>
				<span id="showSelected_fireStation" style="color: #FF3030;width: 200px;"></span>
			</div>
	        <div class="fitem_building">
	            <label>名称:</label>
	            <input name="name" class="easyui-textbox" required="true" />
	        </div>
	        <div class="fitem_building">
		        <label>居委会主任:</label>
		        <input name="committeesDirector" class="easyui-textbox"/>
		    </div>
		    <div class="fitem_building">
		        <label>居委会电话:</label>
		        <input name="committeesPhone" class="easyui-textbox" data-options="validType:'length[1,11]'">
		     </div>
	        <div class="fitem_building">
		        <label>站长:</label>
		        <input name="stationMaster" class="easyui-textbox"/>
		    </div>
		    <div class="fitem_building">
		        <label>站长电话:</label>
		        <input name="stationPhone" class="easyui-textbox" data-options="validType:'length[1,11]'">
		     </div>
	        <div class="fitem_building">
		        <label>实用面积:</label>
		        <input name="area" class="easyui-numberbox" precision="2"prompt="请输入数字"/>
		     </div>
		      <div class="fitem_building">
				<label>支援服务救援站:</label>
				<input id="comCcEmergencyService"  name = "emergencyService"  class="easyui-combobox" 
					data-options="required:true,editable:false,width:173" /> 
			</div>
			  <div class="fitem_building">
		        <label>人员总数:</label>
		        <input name="officeSum" class="easyui-textbox" readonly="true" prompt="统计数据，不需要输入"/>
		    </div>
			  <div class="fitem_building">
		        <label>车辆总数:</label>
		        <input name="vehicleSum" class="easyui-textbox" readonly="true" prompt="统计数据，不需要输入"/>
		    </div>
			  <div class="fitem_building">
		        <label>装备总数:</label>
		        <input name="equipmentSum" class="easyui-textbox" readonly="true" prompt="统计数据，不需要输入"/>
		    </div>
		     <div class="fitem">
				  	 <label class="area">居委会地址:</label>
			       <input class="easyui-textbox" data-options="multiline:true" name="committeesAddress" style="width:500px;height:60px">
			</div>
		     <div class="fitem">
				  	 <label class="area">消防站地址:</label>
			       <input class="easyui-textbox" data-options="multiline:true" name="stationAddress" style="width:500px;height:60px">
			</div>
		    <div class="fitem">
				  	 <label class="area">说明:</label>
			       <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:70px">
			</div>
			<!-- <div class="fitem" id="officeDiv">
			 <label class="area"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendOfficeText()">添加人员</a></label>
				<ul style="list-style-type:none;display:inline-block;padding: 0;" id="officeUl">
				</ul>
		  	</div>
			<div class="fitem" id="vehicleDiv">
			 <label class="area"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendVehicleText()">添加车辆</a></label>
				<ul style="list-style-type:none;display:inline-block;padding: 0;" id="officeUl">
				</ul>
		 	</div>
			<div class="fitem" id="equipmentDiv">
			 <label class="area"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendEquipmentText()">添加设备</a></label>
				<ul style="list-style-type:none;display:inline-block;padding: 0;" id="officeUl">
				</ul>
			</div> -->
	    </form>
	    </div>

         <!-- 消防站人员信息 -->
		<div title="人员信息" style="padding: 22px;height: 440px">
			<form id="fm_fire_station_office" method="post" novalidate>
				<input type="hidden" name="id" />
			 	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendOfficeText()">添加人员</a>
				<div style="overflow: hidden; margin-top:5px;">
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px;">姓名</div>
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px; margin-left: 30px;">职务</div>
			    </div>
				<div class="fitem" id="officeDiv">
					<ul style="list-style-type:none;display:inline-block;padding: 0;" id="officeUl">
					</ul>
			 	</div>
			</form>
		</div>

		<!-- 配备车辆信息 -->
		<div title="配备车辆信息" style="padding: 22px;height: 440px">
			<form id="fm_fire_station_vehicle" method="post" novalidate>
				<input type="hidden"name="id" />
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendVehicleText()">添加车辆</a>
				<div style="overflow: hidden;margin-top:5px;">
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px;">车辆类型</div>
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px; margin-left: 30px;">泡沫装载量</div>
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px; margin-left: 30px;">载水量</div>
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px; margin-left: 30px;">数量</div>
			    </div>
				<div class="fitem" id="vehicleDiv">
						<ul style="list-style-type:none;display:inline-block;padding: 0;" id="vehicleUl">
						</ul>
				</div>
			</form>
		</div>

		<!-- 配备装备信息 -->
		<div title="配备装备信息" style="padding: 22px;height: 440px">
			<form id="fm_fire_station_equipment" method="post" novalidate>
				<input type="hidden" name="id" />
				 <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendEquipmentText()">添加装备</a>
				<div style="overflow: hidden; margin-top:5px;">
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px;">器材名称</div>
				    <div style="width: 177px; text-align: center; height: 20px; line-height:20px; float:left;color: red;font-size:14px; margin-left: 30px;">数量</div>
			    </div>
				<div class="fitem" id="equipmentDiv">
						<ul style="list-style-type:none;display:inline-block;padding: 0;" id="equipmentUl">
						</ul>
				</div>
			</form>
		</div>

    </div>
</div>
<div id="dlgFireStation-buttons">
<!--  	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-redo" onclick="nextTab()" style="width:90px">下一步</a> -->
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelAction()" style="width:90px">取消</a>
</div>


<div id="import_fire_station" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file_fire_station" method="post" enctype="multipart/form-data" target="ajaxUpload">
	<div style="margin-top:10px;">
		<a href="<%=request.getContextPath() %>/template/fire_station_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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

var officeTemplate = '<li  style="margin-bottom:5px;"><input class="easyui-textbox" required="true" name="officeNames" />&nbsp;：<input class="easyui-textbox" required="true"  name="officeBusinesses"/>&nbsp; <a href="#" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconCls="icon-remove" plain="true" onclick="delText(this)">删除</a></li>';
// 配备车辆：车辆类型 泡沫装载量  载水量 数量
var vehicleTemplate = '<li  style="margin-bottom:5px;"><input class="easyui-textbox" required="true" name="vehicleTypes"/>&nbsp;：<input class="easyui-textbox" required="true" name="foamLoadinges"/>&nbsp; ：<input class="easyui-textbox" required="true"name="waterAmounts"/>&nbsp; ：<input class="easyui-textbox" required="true"name="amounts"/>&nbsp; <a href="#" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconCls="icon-remove" plain="true" onclick="delText(this)">删除</a></li>';
// 配备装备  器材名称  数量
var equipmentTemplate = '<li  style="margin-bottom:5px;"><input class="easyui-textbox" required="true" name="equipmentNames"/>&nbsp;：<input class="easyui-textbox" required="true"name="equipmentNums"/></span>&nbsp; <a href="#" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconCls="icon-remove" plain="true" onclick="delText(this)">删除</a></li>';

// 添加节点
function appendOfficeText() {
	var targetObj =$("#officeDiv ul").append(officeTemplate);
	$.parser.parse(targetObj);
}
// 删除节点
function delText(params) {
	$(params).parent().remove();
}
//添加节点
function appendVehicleText() {
	var targetObj = $("#vehicleDiv ul").append(vehicleTemplate);
	$.parser.parse(targetObj);
}

//添加节点
function appendEquipmentText() {
	var targetObj = $("#equipmentDiv ul").append(equipmentTemplate);
	$.parser.parse(targetObj);
}

var emergencyServiceData;
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getDictList?typeCode=emergency_service",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
        	emergencyServiceData = data.data;
        }
    }
});
var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
$(document).ready(function() {
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_fire_station","#showSelected_fireStation");
	$('#comCcEmergencyService').combobox({
		data : emergencyServiceData,
		valueField:'code',
		textField:'name'
	});
	//表单窗口打开前禁用联动查询
	$('#fm_fire_station').form({
		onLoadSuccess : function(){
			console.info("load Success");
			disableLinkage(false);
		}
	});
});

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";

var params = {
        districtUrl : districtUrl, 
        streetUrl:streetUrl,
        blockUrl:blockUrl,
		newDistrictEle:'comCcDistrict_fire_station', 
		searchDistrictEle:'districtId',
		newStreetEle:'comCcStreet_fire_station',
		searchStreetEle:'streetId',
		newBlockEle:'comCcBlock_fire_station',
		searchBlockEle:'blockId'
};

initDistrict(params);

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/area/fireStation/queryPage',
		insertData: '<%=request.getContextPath() %>/app/area/fireStation/insertData',
		insertOfficeData: '<%=request.getContextPath() %>/app/area/fireStation/insertOfficeData',
		insertVehicleData: '<%=request.getContextPath() %>/app/area/fireStation/insertVehicleData',
		insertEquipmentData: '<%=request.getContextPath() %>/app/area/fireStation/insertEquipmentData',
		updateData: '<%=request.getContextPath() %>/app/area/fireStation/updateData',
		deleteData: '<%=request.getContextPath() %>/app/area/fireStation/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/area/fireStation/deleteDataByIds',
		exportLibStreet: '<%=request.getContextPath() %>/app/area/fireStation/exportLibStreet',
		importStreet: '<%=request.getContextPath() %>/app/area/fireStation/importStreet',
};

// 新增
function newStreet(){
//	flag = true;
    $('#dlgFireStation').dialog('open').dialog('setTitle','新建 消防站');
    $('#fm_fire_station').form('clear');
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_fire_station","#showSelected_fireStation");
    // 清除节点
    $('#officeUl').children().remove();
    $('#vehicleUl').children().remove();
    $('#equipmentUl').children().remove();
    $('#fireStationTabs').tabs('select','基础信息');
    url =UrlConfig.insertData;
}

// 编辑修改
function editStreet(){
	$('#dlgFireStation').dialog('open').dialog('setTitle','新建 消防站');
    var tab = $('#fireStationTabs').tabs('getSelected');
    // 0：消防站基础信息  1：人员信息  2：配备车辆信息  3：配置设备信息
    var index = $('#fireStationTabs').tabs('getTabIndex',tab);
    var rowNum = $('#dg_fire_station').datagrid("getSelections").length;
    if(rowNum > 1 || rowNum == 0){
        $.messager.alert("提示","请选择一行修改！","error");
    }else {
        var row = $('#dg_fire_station').datagrid('getSelected');
        if (row) {
        	//表单加载数据前关掉联动查询
        	disableLinkage(true);
    	    if(index != 0){
    	    	$('#fireStationTabs').tabs('select','基础信息');
    	    }
            $('#dlgFireStation').dialog('open').dialog('setTitle', '编辑 消防站');
            console.log(row);
			$('#fm_fire_station').form('load', row);
			  url = UrlConfig.updateData + "?id=" + row.id;
			// 人员信息
		     var office = $("input[name='office']").val();
		     var vehicle = $("input[name='vehicle']").val();
		     var equipment = $("input[name='equipment']").val();
		     console.log(office);
		     // 转json
		     if(office){
		    	 var jsonOffice =JSON.parse(office);
			     $(jsonOffice).each(function(index){ 
				    	 var officeName = $(jsonOffice)[index].officeName;
				    	 var business = $(jsonOffice)[index].business;
				    	// 添加节点
				    	 appendOfficeText();
				    	 //赋值
				    	 $('ul#officeUl li').eq(index).find("input[name='officeNames']").val(officeName);
				    	 $('ul#officeUl li').eq(index).find("input[name='officeBusinesses']").val(business);
				    });  
		     }
		     console.log(vehicle);
		     if(vehicle){
		    	 var jsonVehicle =JSON.parse(vehicle);
			     $(jsonVehicle).each(function(index){ 
				    	 var vehicleType = $(jsonVehicle)[index].vehicleType;
				    	 var foamLoadinge = $(jsonVehicle)[index].foamLoadinge;
				    	 var waterAmount = $(jsonVehicle)[index].waterAmount;
				    	 var amount = $(jsonVehicle)[index].amount;
				    	 appendVehicleText();
				    	 $('ul#vehicleUl li').eq(index).find("input[name='vehicleTypes']").val(vehicleType);
				    	 $('ul#vehicleUl li').eq(index).find("input[name='foamLoadinges']").val(foamLoadinge);
				    	 $('ul#vehicleUl li').eq(index).find("input[name='waterAmounts']").val(waterAmount);
				    	 $('ul#vehicleUl li').eq(index).find("input[name='amounts']").val(amount);
				    });  
		     }
		     console.log(equipment);
		     if(equipment){
		    	 var jsonEquipment =JSON.parse(equipment);
			     $(jsonEquipment).each(function(index){ 
				    	 var equipmentName = $(jsonEquipment)[index].equipmentName;
				    	 var equipmentNum = $(jsonEquipment)[index].equipmentNum;
				    	 appendEquipmentText();
				    	 $('ul#equipmentUl li').eq(index).find("input[name='equipmentNames']").val(equipmentName);
				    	 $('ul#equipmentUl li').eq(index).find("input[name='equipmentNums']").val(equipmentNum);
				    });  
		     }
		     
        } else {
            $.messager.alert("提示", "请选择要查看的行！", "info");
        }
    }
}
// 下一步
//function nextTab(){
//	$('#fireStationTabs').tabs('select','人员信息');
//}

function cancelAction(){
	$('#showSelected_fireStation').text('');
	$('#dlgFireStation').dialog('close');
	$('#fm_fire_station').form('clear');
	$('#dg_fire_station').datagrid('reload');
	$('#officeUl').children().remove();
	$('#vehicleUl').children().remove();
	$('#equipmentUl').children().remove();
	$('#fireStationTabs').tabs('select','基础信息');
}

var fire_station_id_tab1;
// 保存
function saveUser(){
    // 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
    var tab = $('#fireStationTabs').tabs('getSelected');
    // 0：消防站基础信息  1：人员信息  2：配备车辆信息  3：配置设备信息
    var index = $('#fireStationTabs').tabs('getTabIndex',tab);
    if(index == 0){
	    $('#fm_fire_station').form('submit',{
	        url: url,
	        onSubmit: function(){
//	        	// 街道id的验证，由于禁用下拉框，验证不到必填项
//	        	var streetId = $('#comCcStreet_fire_station').combobox('getValue');
//	        	if(!streetId){
//	        		   $.messager.alert("提示", "街道数据必填，请选择正确的行政区！", "info");
//	        		   return false;
//	        	}
//	        	var blockId = $('#comCcBlock_fire_station').combobox('getValue');
//	        	if(!blockId){
//	        		 $.messager.alert("提示", "社区数据必填，请选择正确的街道！", "info");
//	        		 return false;
//	        	}

				var t = $('#comCcBlock_fire_station').combotree('tree'); // 得到树对象
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
	            console.log(result);
				if (result.successful) {
//					$.messager.alert("操作提示", result.data, "info");
//					$('#dlgFireStation').dialog('close');        // close the dialog
					$.messager.confirm('操作提示',  result.data+'！是否继续填写人员信息?', function(r){
						// 是的话跳转tab到人员信息
						if (r){
							$('#fireStationTabs').tabs('select','人员信息');
						}
					});
					$('#dg_fire_station').datagrid('reload');    // reload the user data
					// 后台返回新建记录ID
					fire_station_id_tab1 = result.total;
				} else {
					if (result.msg) {
						$.messager.alert("操作提示", result.msg, "error");
					} else {
						$('#dlgFireStation').dialog('close');        // close the dialog
						$('#dg_fire_station').datagrid('reload');    // reload the user data
					}
				}
				//提交后需要清除表单数据
//				$('#fm_fire_station').form('clear');
	        }
	    });
   }else if( index == 1){
	   // 获取基础信息的id
//	   var fire_station_id = $("input[name='id']").val();
	  console.log("index="+index+",fire_station_id="+fire_station_id_tab1);
	   $("input[name='id']").val(fire_station_id_tab1);
	   if(fire_station_id_tab1){
		    $('#fm_fire_station_office').form('submit',{
		        url: UrlConfig.insertOfficeData,
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		            var result = eval('('+result+')');
					if (result.successful) {
						// $.messager.alert("操作提示", result.data, "info");
						// $('#dlgFireStation').dialog('close');        // close the dialog
						$.messager.confirm('操作提示',  result.data+'！是否继续填写配备车辆信息?', function(r){
							// 是的话跳转tab
							if (r){
								$('#fireStationTabs').tabs('select','配备车辆信息');
							}
						});
						$('#dg_fire_station').datagrid('reload');    // reload the user data
					} else {
						if (result.msg) {
							$.messager.alert("操作提示", result.msg, "error");
						} else {
							$('#dlgFireStation').dialog('close');        // close the dialog
							$('#dg_fire_station').datagrid('reload');    // reload the user data
						}
					}
					//提交后需要清除表单数据
//					$('#fm_fire_station_office').form('clear');
		        }
		    });
		   
	   }else{
		   $.messager.alert("操作提示", "请先填写基础信息！", "error");
	   }
   }else if( index == 2){
	   // 获取基础信息的id
	  console.log("index="+index+",fire_station_id="+fire_station_id_tab1);
	  $("input[name='id']").val(fire_station_id_tab1);
	   if(fire_station_id_tab1){
		    $('#fm_fire_station_vehicle').form('submit',{
		        url: UrlConfig.insertVehicleData,
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		            var result = eval('('+result+')');
					if (result.successful) {
						// $.messager.alert("操作提示", result.data, "info");
						// $('#dlgFireStation').dialog('close');        // close the dialog
						$.messager.confirm('操作提示',  result.data+'！是否继续填写配备装备信息?', function(r){
							// 是的话跳转tab
							if (r){
								$('#fireStationTabs').tabs('select','配备装备信息');
							}
						});
						$('#dg_fire_station').datagrid('reload');    // reload the user data
					} else {
						if (result.msg) {
							$.messager.alert("操作提示", result.msg, "error");
						} else {
							$('#dlgFireStation').dialog('close');        // close the dialog
							$('#dg_fire_station').datagrid('reload');    // reload the user data
						}
					}
					//提交后需要清除表单数据
//					$('#fm_fire_station_vehicle').form('clear');
		        }
		    });
		   
	   }else{
		   $.messager.alert("操作提示", "请先填写基础信息！", "error");
	   }
   }else if( index == 3){
	   // 获取基础信息的id
	  console.log("index="+index+",fire_station_id="+fire_station_id_tab1);
	  $("input[name='id']").val(fire_station_id_tab1);
	   if(fire_station_id_tab1){
		    $('#fm_fire_station_equipment').form('submit',{
		        url: UrlConfig.insertEquipmentData,
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		            var result = eval('('+result+')');
					if (result.successful) {
						$.messager.alert("操作提示", result.data, "info");
						$('#dlgFireStation').dialog('close');        // close the dialog
						$('#dg_fire_station').datagrid('reload');    // reload the user data
						  // 清除节点
					    $('#officeUl').children().remove();
					    $('#vehicleUl').children().remove();
					    $('#equipmentUl').children().remove();
					} else {
						if (result.msg) {
							$.messager.alert("操作提示", result.msg, "error");
						} else {
							$('#dlgFireStation').dialog('close');        // close the dialog
							$('#dg_fire_station').datagrid('reload');    // reload the user data
						}
					}
					//提交后需要清除表单数据
//					$('#fm_fire_station_equipment').form('clear');
		        }
		    });
		   
	   }else{
		   $.messager.alert("操作提示", "请先填写基础信息！", "error");
	   }
   }else{
   	 $.messager.alert("操作提示", "不存在该tab，请联系系统管理员！", "error");
   }
}

//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_fire_station').datagrid('getSelections')
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
	                		$('#dg_fire_station').datagrid('clearSelections');
	                        $('#dg_fire_station').datagrid('reload');    // reload the user data
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
	$('#dg_fire_station').datagrid('load',{
		name: $('#name').val(),
		code: $('#code').val(),
		districtId:$('#districtId').combobox('getValue'),
		streetId:$('#streetId').combobox('getValue'),   // 下拉框获取数据ID
		blockId:$('#blockId').combobox('getValue')
	});
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
	  $("#districtId").combobox('select', 0);
	  $("#streetId").combobox('select', 0);
	  $("#blockId").combobox('select', 0);
      $("#name").val("");
      $("#code").val("");
      doSearch();
}
// 导出
function doExport(value,name){
	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
        if (!r) {
            return;
        }else{
          	var exportName = $('#name').val();
          	var code = $('#code').val();
        	var openUrl = UrlConfig.exportLibStreet+"?name="+encodeURI(exportName)+"&code="+code+"&streetId="+$('#streetId').combobox('getValue')+
        								"&districtId="+$('#districtId').combobox('getValue')+"&blockId="+$('#blockId').combobox('getValue');
        	window.open(openUrl);
        }
    });
}

//导入
function doImport(){
    $('#import_fire_station').dialog('open').dialog('setTitle','导入数据');
}

function uploadFile(){
    $('#fm_file_fire_station').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
	          	  $.messager.alert("操作提示", result.data, "info");
	          	  $('#import_fire_station').dialog('close');        // close the dialog
	                $('#dg_fire_station').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#import_fire_station').dialog('close');        // close the dialog
	                    $('#dg_fire_station').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}

</script>
</body>
</html>