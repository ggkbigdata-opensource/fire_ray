<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>建筑物管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/fire_system.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
	<style type="text/css">
		.firecontrol-table{ width: 100%; border-top: 1px solid #ebecee; border-left: 1px solid #ebecee; font-size: 14px; color: #393a3e;}
		.firecontrol-table td{ height: 30px; line-height: 30px; white-space: nowrap; border-right: 1px solid #ebecee; border-bottom: 1px solid #ebecee; text-align: center; padding: 5px;}
		.firecontrol-td{ font-weight: bold; background-color: #f6f7fb;}
		.firecontrol-table input{ border: 1px solid #ebecee; width: 100%; height: 26px; line-height: 26px; text-indent: 5px;}
		.windownTab{
			position: relative
		}
		.windownTabBtnDiv{
			position: absolute;
			top: 6px;
			right:35px;
			z-index: 10;
		}
		.windownTab .panel-header {
			background: url("../../images/partition.png") 0 13px no-repeat;
			padding-left: 20px;
			border:none !important;
			border-bottom: 1px dotted #b9cbe4 !important;
			margin-bottom: 10px;
			padding-bottom: 10px;
			width: auto !important;
		}
		.windownTab .resetBtn{
			padding: 4px 14px;
			margin-top:2px;
		}
		.windownTab .queryBtn{
			padding: 4px 14px;
			margin-top:2px;
		}
	</style>
</head>
<body>
<div id="tblQuery" style="padding:3px">
	<div class="inputDiv">
		<div class="inputDivEg">
			<span class="inputLabel">行政区:</span>
			<input id="districtId" class="easyui-combobox inputDiv-input" data-options="width:150">
		</div>
		<div class="inputDivEg">
			<span style="font-size:12px">街道:</span>
			<input id="streetId" class="easyui-combobox inputDiv-input" data-options="width:150">
		</div>
		<div class="inputDivEg">
			<span class="inputLabel">社区:</span>
			<input id="blockId" class="easyui-combobox inputDiv-input" data-options="width:150">
		</div>
		<div class="inputDivEg">
			<span class="inputLabel">建筑类别:</span>
			<input id="baseBuildingClass" class="easyui-combobox inputDiv-input" data-options="editable:false,width:150">
		</div>
		<div class="inputDivEg">
			<span style="font-size:12px">产权单位名称:</span>
			<input id="ownerUnitName" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
		</div>
		<div class="height10"></div>
		<a href="#" class="resetBtn" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-print" onclick="doImport()">导入</a>
	</div>
</div>
<div class="tableClass">
<table id="dg_building_subject" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/buildingSubject/queryPage'
        		toolbar="#toolbar"
       			rownumbers="true" 
        		fitColumns="true" 
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                striped="true"
                pageSize="20"
	   			collapsible="true"
                checkOnSelect="true"
                selectOnCheck="true"> 
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
            <th field="ownerUnitName" width="50">产权单位名称</th>
            <th field="baseCode" width="50">建筑编码</th>
			<th field="fireManager" width="50">消防安全负责人</th>
			<th field="contactName" width="50">联系人姓名</th>
			<th field="contactPhone" width="50">联系人电话</th>
			<th field="supChargeUnitName" width="50">上级主管单位名称</th>
            <th field="baseBuildingClass" width="50">建筑类别</th>
            <th field="address" width="50">地点</th>
            <th field="districtName" width="50">行政区</th>
            <th field="streetName" width="50">所属街道</th>
            <th field="blockName" width="50">社区名称</th>
        </tr>
    </thead>
          <input type="hidden" name="streetId" value="streetId" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" plain="true" onclick="destroyStreets()">删除</a>
</div>
<div id="dlgBuildingSubject" class="easyui-dialog" style="width:1024px;height:650px;" closed="true"  closable="false" maximizable="true" buttons="#dlg-buttons">
	<div id="buildingSubjectTabs" class="easyui-tabs" data-options="fit:true">
    	<div title="建筑物概况表" style="padding: 22px;height: 440px">
    <form id="fm_building" method="post" novalidate>
		<input type="hidden"name="id"/>
		<div class="fitem_building">
			<label>社区:</label>
			<input class="easyui-combotree" id="comCcBlock_building" name = "blockId" style="width:173px;height:26px" required="true" >
		</div>
		<div class="fitem_building">
			<label>您选择的是:</label>
			<span id="showSelected_building" style="color: #FF3030;width: 200px;"></span>
		</div>
	    <div class="fitem_building">
		    <label>产权单位名称:</label>
		    <input name="ownerUnitName" class="easyui-textbox" required="true" />
		</div>
	     <div class="fitem_building">
	         <label>消防安全负责人:</label>
	         <input name="fireManager" class="easyui-textbox">
	      </div>
		 <div class="fitem_building">
			 <label>联系人姓名:</label>
			 <input name="contactName" class="easyui-textbox">
		  </div>
		   <div class="fitem_building">
	         <label>联系人电话:</label>
	         <input name="contactPhone" class="easyui-textbox" data-options="validType:'length[1,11]'" prompt="请输入11位数字">
	      </div>
			<div class="fitem_building">
				<label>上级主管单位:</label>
				<input name="supChargeUnitName" class="easyui-textbox">
			</div>
		<div class="fitem_building">
			<label>行业监管部门:</label>
			<input name="industrySupervisionDepart" class="easyui-textbox">
		</div>
		<div class="fitem_building">
			<label>经度:</label>
			<input name="longitude" class="easyui-textbox" prompt="请输入数字">
		</div>
		<div class="fitem_building">
			<label>纬度:</label>
			<input name="latitude" class="easyui-textbox" prompt="请输入数字">
		</div>
		<div class="fitem_building">
			<label>投入使用时间:</label>
			<input name="useTime" class="easyui-textbox" prompt="单位：年月">
		</div>
		<div class="fitem_building">
			<label>建筑类别:</label>
			<input id="comCcBaseBuildingClass"  name = "baseBuildingClass"  class="easyui-combobox"
				   data-options="required:true,editable:false,width:173" />
		</div>
		<div class="fitem_building">
			<label>占地面积(m2):</label>
			<input name="conCoverArea" class="easyui-numberbox" precision="2" prompt="请输入数字"/>
		</div>
		<div class="fitem_building">
			<label>建筑面积(m2):</label>
			<input name="conBuildArea"class="easyui-numberbox" precision="2" prompt="请输入数字"/>
		</div>
		<div class="fitem_building">
	         <label>建筑高度(m):</label>
	         <input name="conBuildHight" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
	      </div>
		   <div class="fitem_building">
	         <label>地表层数(层):</label>
	         <input name="conFloors" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
	      </div>
	      <div class="fitem_building">
	         <label>地下层数(层):</label>
	         <input name="conUnderFloors" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
	      </div>
			<div class="fitem_building">
				<label>地表使用功能:</label>
				<input id="comCcSurfaceFunction"  name = "surfaceFunction"  class="easyui-combobox"
					   data-options="required:true,editable:false,width:173" />
			</div>
			<div class="fitem_building">
				<label>地下使用功能:</label>
				<input id="comCcUndergroundFunction"  name = "undergroundFunction"  class="easyui-combobox"
					   data-options="required:true,editable:false,width:173" />
			</div>
			<div class="fitem_building">
				<label>土地使用性质:</label>
				<input id="comCcConClass"  name = "conClass"  class="easyui-combobox"
					   data-options="required:true,editable:false,width:173" />
			</div>
			<div class="fitem_building">
				<label>设计单位:</label>
				<input name="designUnit" class="easyui-textbox" prompt="设计单位"/>
			</div>
			<div class="fitem_building">
				<label>使用单位数量:</label>
				<input name="useUnitNum" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
			</div>
			<div class="fitem">
				<label class="area">维保单位:</label>
				<input class="easyui-textbox" data-options="multiline:true" name="maintenanceUnit" style="width:500px;height:70px"/>
			</div>
			<div class="fitem">
				<label class="area">三方消检:</label>
				<input class="easyui-textbox" data-options="multiline:true" name="thirdPartyFireDetection" style="width:500px;height:70px" />
			</div>
	        <div class="fitem">
	            <label class="area">地址:</label>
	            <input class="easyui-textbox" data-options="multiline:true" name="address" style="width:500px;height:70px">
			</div>
         <div class="fitem">
	         <label class="area">说明:</label>
	         <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:70px">
	     </div>
     <input type="hidden" name="thumbImg" id="building_thumb_img"/>
    </form>
    <form id="file_building_img" method="post" enctype="multipart/form-data">
	    <div class="fitem_building" >
		    <label>上传缩略图:</label>
		    <input class="easyui-filebox" data-options="buttonText:'选择'" id="thumb_img_input" name="thumb_img">
	    </div>
    </form>
         <div class="fitem" style="margin-top: 30px;margin-left: -380px;margin-bottom: 30px;">
	         <label class="area">缩略图展示:</label>
	         	<img onfocus=this.blur()  id="thumb_img_show" border=0 align="middle" src="" alt="没有图片！" width="100px" height="100px"> </img> 
	     </div>
	</div>

		<!-- 建筑物主要使用功能表 -->
		<div title="建筑物主要使用功能表" style="padding: 22px;height: 440px">
			<form id="fm_building_function" method="post" novalidate>
				<input type="hidden" name="id" />
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendFunctionText(parseInt(Math.random()*1000))">添加主要使用功能</a>
				<div class="fitem" id="buildingFunctionDiv">
					<ul style="list-style-type:none;display:inline-block;padding: 0;" id="buildingFunctionUl">
					</ul>
				</div>
			</form>
		</div>


		<div title="物业管理单位概况表" style="padding: 22px;height: 440px">
			<form id="fm_management" method="post" novalidate>
				<input type="hidden"name="buildingId" />
				<input type="hidden"name="id" />
				<div class="fitem_building">
					<label>单位名称:</label>
					<input name="managerUnitName" class="easyui-textbox" required="true" />
				</div>
				<div class="fitem_building">
					<label>单位负责人:</label>
					<input name="chargePerson" class="easyui-textbox">
				</div>
				<div class="fitem_building">
					<label>联系人姓名:</label>
					<input name="contactName" class="easyui-textbox">
				</div>
				<div class="fitem_building">
					<label>联系人电话:</label>
					<input name="contactPhone" class="easyui-textbox" data-options="validType:'length[1,11]'" prompt="请输入11位数字">
				</div>
				<div class="fitem_building">
					<label>上级主管单位:</label>
					<input name="supChargeUnitName" class="easyui-textbox">
				</div>
				<div class="fitem_building">
					<label>行业监管部门:</label>
					<input name="industrySupervisionDepart" class="easyui-textbox">
				</div>
				<div class="fitem_building">
					<label>成立时间:</label>
					<input class="easyui-datetimebox" name="publishTimeString"
						   data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:173px">
				</div>
				<div class="fitem_building">
					<label>注册资金（元）:</label>
					<input name="registeredMoney"class="easyui-numberbox" precision="2" prompt="请输入数字"/>
				</div>
				<div class="fitem_building">
					<label>从业人员数量:</label>
					<input name="employeesNum" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
				</div>
				<div class="fitem_building">
					<label>消防持证人员:</label>
					<input name="fireWitnessNum" class="easyui-numberbox" precision="0" prompt="请输入数字"/>
				</div>
				<div class="fitem">
					<label class="area">单位地址:</label>
					<input class="easyui-textbox" data-options="multiline:true" name="managerAddress" style="width:500px;height:70px">
				</div>
				<div class="fitem">
					<label class="area">说明:</label>
					<input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:70px">
				</div>
			</form>
		</div>
		<div title="消防重点部位概况表">
			<form id="fm_keypart" method="post" novalidate>
			<table id="tb_keypart_info"class="firecontrol-table" cellspacing="0" cellpadding="0">
				<tr name="title">
					<td rowspan="2" class="firecontrol-td">消防控制室</td>
					<td>设置位置</td>
					<td>面积（m2）</td>
					<td>所配消防设施</td>
					<td colspan="2">每班值班人数（人）</td>
					<td colspan="2">持证上岗人数（人）</td>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="消防控制室"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text" name="area"/></td>
					<td><input type="text" name="fireEquipment"/></td>
					<td colspan="2"><input type="text" name="dutyNum"/></td>
					<td colspan="2"><input type="text" name="diplomaNum"/></td>
					<input type="hidden" name="firePumpNum" value="0"/>
					<input type="hidden" name="sprayPumpNum" value="0"/>
					<input type="hidden" name="pressurePumpNum" value="0"/>
					<input type="hidden" name="airTankVolume" value="0"/>
					<input type="hidden" name="storageArea" value="0"/>
					<input type="hidden" name="oilVolume" value="0"/>
				</tr>
				<tr name="title">
					<td rowspan="2" class="firecontrol-td">消防水泵房</td>
					<td>设置位置</td>
					<td>面积（m2）</td>
					<td>所配消防设施</td>
					<td>消火栓泵数量（台）</td>
					<td>喷淋泵数量（台）</td>
					<td>稳压泵数量（台）</td>
					<td>气压罐体积（m2）</td>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="消防水泵房"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text"  name="area"/></td>
					<td><input type="text"  name="fireEquipment"/></td>
					<td><input type="text" name="firePumpNum"/></td>
					<td><input type="text" name="sprayPumpNum"/></td>
					<td><input type="text" name="pressurePumpNum"/></td>
					<td><input type="text" name="airTankVolume"/></td>
					<input type="hidden" name="dutyNum" value="0"/>
					<input type="hidden" name="diplomaNum" value="0"/>
					<input type="hidden" name="storageArea"value="0" />
					<input type="hidden" name="oilVolume" value="0"/>
				</tr>
				<tr name="title">
					<td rowspan="2" class="firecontrol-td">柴油发电机房</td>
					<td>设置位置</td>
					<td>面积（m2）</td>
					<td>所配消防设施</td>
					<td colspan="2">储油间面积（m2）</td>
					<td colspan="2">储油量（m2）</td>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="柴油发电机房"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text" name="area"/></td>
					<td><input type="text"  name="fireEquipment"/></td>
					<td colspan="2"><input type="text" name="storageArea"/></td>
					<td colspan="2"><input type="text" name="oilVolume"/></td>
					<input type="hidden" name="dutyNum" value="0"/>
					<input type="hidden" name="diplomaNum" value="0"/>
					<input type="hidden" name="firePumpNum" value="0"/>
					<input type="hidden" name="sprayPumpNum" value="0"/>
					<input type="hidden" name="pressurePumpNum" value="0"/>
					<input type="hidden" name="airTankVolume" value="0"/>
				</tr>
				<tr name="title">
					<td rowspan="2" class="firecontrol-td">变、配电室</td>
					<td>设置位置</td>
					<td>面积（m2）</td>
					<td colspan="6">所配消防设施</td>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="变、配电室"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text" name="area"/></td>
					<td colspan="6"><input type="text"  name="fireEquipment"/></td>
					<input type="hidden" name="dutyNum" value="0"/>
					<input type="hidden" name="diplomaNum" value="0"/>
					<input type="hidden" name="firePumpNum" value="0"/>
					<input type="hidden" name="sprayPumpNum" value="0"/>
					<input type="hidden" name="pressurePumpNum" value="0"/>
					<input type="hidden" name="airTankVolume" value="0"/>
					<input type="hidden" name="storageArea"value="0" />
					<input type="hidden" name="oilVolume" value="0"/>
				</tr>
				<tr name="title">
					<td rowspan="3" class="firecontrol-td">其它消防<br>重点部位</td>
					<td>设置位置</td>
					<td>面积（m2）</td>
					<td colspan="6">所配消防设施</td>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="其它消防重点部位一"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text" name="area"/></td>
					<td colspan="6"><input type="text"  name="fireEquipment"/></td>
					<input type="hidden" name="dutyNum" value="0"/>
					<input type="hidden" name="diplomaNum" value="0"/>
					<input type="hidden" name="firePumpNum" value="0"/>
					<input type="hidden" name="sprayPumpNum" value="0"/>
					<input type="hidden" name="pressurePumpNum" value="0"/>
					<input type="hidden" name="airTankVolume" value="0"/>
					<input type="hidden" name="storageArea"value="0" />
					<input type="hidden" name="oilVolume" value="0"/>
				</tr>
				<tr>
					<input type="hidden" name="id"/>
					<input type="hidden" name="buildingId"/>
					<input type="hidden" name="managementId"/>
					<input type="hidden" name="keypartName" value="其它消防重点部位二"/>
					<td><input type="text" name="position"/></td>
					<td><input type="text" name="area"/></td>
					<td colspan="6"><input type="text"  name="fireEquipment"/></td>
					<input type="hidden" name="dutyNum" value="0"/>
					<input type="hidden" name="diplomaNum" value="0"/>
					<input type="hidden" name="firePumpNum" value="0"/>
					<input type="hidden" name="sprayPumpNum" value="0"/>
					<input type="hidden" name="pressurePumpNum" value="0"/>
					<input type="hidden" name="airTankVolume" value="0"/>
					<input type="hidden" name="storageArea"value="0" />
					<input type="hidden" name="oilVolume" value="0"/>
				</tr>
			</table>
			</form>
		</div>
		<div title="主要消防系统概况表" style="padding: 22px;height: 440px">
			<%--注意:table id 不能修改--%>
			<div class="windownTab">
				<table id="hydrant001">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#hydrant001')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#hydrant001')">重置</a>
					<%--<a class="windownTabIcon"></a>--%>
				</div>
			</div>
			<div class="windownTab">
				<table id="automaticFire002">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#automaticFire002')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#automaticFire002')">重置</a>
				</div>
			</div>
			<div class="windownTab">
				<table id="waterSpraySystem003">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#waterSpraySystem003')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#waterSpraySystem003')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="gasFire004">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#gasFire004')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#gasFire004')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="automaticAlarm005">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#automaticAlarm005')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#automaticAlarm005')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="smokeSystem006">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#smokeSystem006')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#smokeSystem006')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="fireproofRolling007">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#fireproofRolling007')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#fireproofRolling007')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="powerSupply008">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#powerSupply008')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#powerSupply008')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="evacuationIndicating009">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#evacuationIndicating009')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#evacuationIndicating009')">重置</a>
				</div>
			</div>

			<div class="windownTab">
				<table id="buildingExtinguisher010">
				</table>
				<div class="windownTabBtnDiv">
					<a href="javascript:void(0);" class="resetBtn"  plain="true"  onclick="saveChanges('#buildingExtinguisher010')">保存</a>
					<a href="javascript:void(0);" class="queryBtn" plain="true" onclick="reject('#buildingExtinguisher010')">重置</a>
				</div>
			</div>

		</div>
	</div>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelAction()" style="width:90px">取消</a>
</div>


<div id="import_building_subject" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入数据</div>
<form id="fm_file_building" method="post" enctype="multipart/form-data" target="ajaxUpload">
		<div style="margin-top:10px;">
    		<a href="<%=request.getContextPath() %>/template/building_subject_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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
	//将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
	(function($){
		$.fn.serializeJson = function(){
			var jsonData1 = {};
			var serializeArray = this.serializeArray();
			// 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
			$(serializeArray).each(function () {
				if (jsonData1[this.name]) {
					if ($.isArray(jsonData1[this.name])) {
						jsonData1[this.name].push(this.value);
					} else {
						jsonData1[this.name] = [jsonData1[this.name], this.value];
					}
				} else {
					jsonData1[this.name] = this.value;
				}
			});
			// 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
			var vCount = 0;
			// 计算json内部的数组最大长度
			for(var item in jsonData1){
				var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
				vCount = (tmp > vCount) ? tmp : vCount;
			}

			if(vCount > 1) {
				var jsonData2 = new Array();
				for(var i = 0; i < vCount; i++){
					var jsonObj = {};
					for(var item in jsonData1) {
						jsonObj[item] = jsonData1[item][i];
					}
					jsonData2.push(jsonObj);
				}
				return JSON.stringify(jsonData2);
			}else{
				return "[" + JSON.stringify(jsonData1) + "]";
			}
		};
	})(jQuery);
var managementRowData,
		keypartRowListData;

var dictList;
var buildClassData =  new Array(),
buildClassDataSearch = new Array(),
surfaceFunctionData = new Array(),
undergroundFunctionData = new Array(),
buildingConClassData = new Array();
var saveChangesFireSystemUrl = '<%=request.getContextPath() %>/app/fireSystem/saveChanges',
		fireSystemQueryAllUrl ='<%=request.getContextPath() %>/app/fireSystem/queryAll';

// 获取数据字典数据
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getAllDictList",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
            dictList = data.data.rows;
        }
    }
});
$.each(dictList,function(n,value) {   
    var name = value.name,
    code = value.code;
    //建筑类别：building_class 
    if ("building_class" == value.typeCode) {
        buildClassData.push({name: name, code: code});
        // 搜索框
        buildClassDataSearch.push({name: name, code: code});
    } 
    //地表主要使用功能 ：surface_function
    if ("surface_function" == value.typeCode) {
		surfaceFunctionData.push({name: value.name, code: value.code});
    }
	if ("underground_function" == value.typeCode) {
		undergroundFunctionData.push({name: value.name, code: value.code});
	}
    if ("building_con_class" == value.typeCode) {
		buildingConClassData.push({name: value.name, code: value.code});
    }
});

var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
$(document).ready(function() {
	$("#key_part_fire_control_num").hide();
	$("#key_part_water_pump").hide();
	$("#key_part_oil_info").hide();
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_building","#showSelected_building");
    // 建筑类别 
    $('#comCcBaseBuildingClass').combobox({
            data : buildClassData,
            valueField:'code',
            textField:'name'
    });
    buildClassDataSearch.unshift({code: '0', name: '全部'});
    $('#baseBuildingClass').combobox({
        data : buildClassDataSearch,
        valueField:'code',
        textField:'name',
        value:0
    });
    // 地表主要使用功能
    $('#comCcSurfaceFunction').combobox({
        data : surfaceFunctionData,
        valueField:'code',
        textField:'name'
    });

	$('#comCcUndergroundFunction').combobox({
		data : undergroundFunctionData,
		valueField:'code',
		textField:'name'
	});
    //土地使用性质
    $('#comCcConClass').combobox({
            data : buildingConClassData,
            valueField:'code',
            textField:'name'
    });

    //表单窗口打开前禁用联动查询
    $('#fm_building').form({
        onLoadSuccess : function(){
            disableLinkage(false);
        }
    });
	$('#buildingSubjectTabs').tabs({
		border:false,
		onSelect:function(title){
			var dialogTitle = $('#dlgBuildingSubject').dialog('header')[0].textContent;
			if(dialogTitle == "编辑建筑主体"){
				if(title == "物业管理单位概况表"){
					// tab2
					$.ajax({
						url:"${pageContext.request.contextPath}/app/management/getByBuildingId?buildingId="+edit_row_id,
						async: true,      //async : false,表示同步
						type:"get",
						success: function(data){
							if(data){
								managementRowData = data;
								$('#fm_management').form('load', managementRowData);
							}
						}
					});

					// 编辑的是哪个记录，填数据到对应的表格
					if(managementRowData){
						if(edit_row_id == managementRowData.buildingId){
							$('#fm_management').form('load', managementRowData);
							url = UrlConfig.updateManagementData + "?id=" + managementRowData.id;
						}else{
							$('#fm_management').form('clear');
						}
					}else{
						$('#fm_management').form('clear');
						url = UrlConfig.insertManagementData;
					}
				}else if(title == "建筑物主要使用功能表"){
					$.ajax({
						url:"${pageContext.request.contextPath}/app/buildingFunction/queryAll?buildingId="+edit_row_id,
						async: true,      //async : false,表示同步
						type:"get",
						success: function(data){
							if(data){
								if(data.rows){
									// 移除之前的节点
									$('#buildingFunctionUl').children().remove();
									$.each(data.rows, function(index, value){
										var buildingFloor = value.buildingFloor;
										var functionValue = value.function;
										var isSpecificLocation = value.isSpecificLocation;
										var funBusinessName = value.funBusinessName;
										var funBuildArea = value.funBuildArea;
										// 添加节点
										appendFunctionText(value.id);
										//赋值
										$("#"+value.id+"_buildingFloor").textbox('setValue',buildingFloor);
										$("#"+value.id+"_function").textbox('setValue',functionValue);
										$("#"+value.id+"_isSpecificLocation").textbox('setValue',isSpecificLocation);
										$("#"+value.id+"_funBusinessName").textbox('setValue',funBusinessName);
										$("#"+value.id+"_funBuildArea").textbox('setValue',funBuildArea);
									});
								}else{
									$.messager.alert("温馨提示", "暂无建筑物主要使用功能数据，请添加！", "info");
								}
							}
						}
					});
				}else if(title == "消防重点部位概况表"){
					$.ajax({
						url:"${pageContext.request.contextPath}/app/keypart/queryAll?buildingId="+edit_row_id,
						async: true,      //async : false,表示同步
						type:"get",
						success: function(data){
							if(data){
								keypartRowListData = data.rows;
								setFormForKeypartInfo();
							}
						}
					});

					if(keypartRowListData != null && keypartRowListData.length > 0){
						if(edit_row_id == keypartRowListData[0].buildingId){
							setFormForKeypartInfo();
						}else{
							$('#fm_keypart').form('clear');
							url = UrlConfig.insertKeypartData;
						}
					}else{
						$('#fm_keypart').form('clear');
						url = UrlConfig.insertKeypartData;
					}
				}else if(title == "主要消防系统概况表"){
					$.ajax({
						url:"${pageContext.request.contextPath}/app/fireSystem/queryAll?buildingId="+edit_row_id,
						async: true,      //async : false,表示同步
						type:"get",
						success: function(data){
							console.log(data);
							if(data){
								if(data.rows && data.rows.length > 0 ){
									reloadAllFireSystemDatagrid(edit_row_id);
								}else{
									$.ajax({
										url:"${pageContext.request.contextPath}/app/fireSystem/insertData?id="+edit_row_id,
										async: true,      //async : false,表示同步
										type:"get",
										success: function(data){
											if(data.successful){
												reloadAllFireSystemDatagrid(edit_row_id);
											}else{
												$.messager.alert("操作提示", data.msg, "error");
											}
										}
									});
								}
							}
						}
					});
				}
			}else{
				if(building_subject_id_tab1){
					if(title == "建筑物主要使用功能表"){
						url = UrlConfig.insertFuntionData;
					}else if(title == "消防重点部位概况表"){
						url = UrlConfig.insertKeypartData;
					}else if(title == "物业管理单位概况表"){
						url = UrlConfig.insertManagementData;
					}else if(title == "主要消防系统概况表"){
						// 初始化十大系统并绑定建筑id
						$.ajax({
							url:"${pageContext.request.contextPath}/app/fireSystem/insertData?id="+building_subject_id_tab1,
							async: true,      //async : false,表示同步
							type:"get",
							success: function(data){
								if(data.successful){
									reloadAllFireSystemDatagrid(building_subject_id_tab1);
								}else{
									$.messager.alert("操作提示", data.msg, "error");
								}
							}
						});
					}
				}else{
					if(title != "建筑物概况表"){
						$.messager.alert("操作提示", "请先填写基础信息！！", "error");
					}
				}
			}
		}
	});

});
/*	var buildingFunctionTemplate = '<li  style="margin-bottom:5px;"><input class="easyui-textbox" id="id_buildingFloor" required="true" name="buildingFloor"  prompt="输入建筑物楼层"/>' +
			'<input class="easyui-textbox" required="true"  name="function" prompt="输入使用功能"/>' +
			'<input class="easyui-textbox" required="true" name="isSpecificLocation"  prompt="是否特定场所"/>' +
			'<input class="easyui-textbox"  name="funBusinessName" prompt="商家名称"/>' +
			'<input class="easyui-numberbox" precision="2" name="funBuildArea" prompt="建筑面积（m2）"/>' +
			'<input type="hidden" name="buildingId"/>'+
			'<a href="#" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconCls="icon-remove" plain="true" onclick="delText(this)">删除</a></li>';



	// 添加节点
	function  appendFunctionText() {
		var targetObj =$("#buildingFunctionDiv ul").append(buildingFunctionTemplate);
		$.parser.parse(targetObj);
	}*/
	var buildingFunctionTemplate = '<li  style="margin-bottom:5px;">' +
			'<input class="easyui-textbox" id="_id__buildingFloor" required="true" name="buildingFloor"  prompt="输入建筑物楼层"/>' +
			'<input class="easyui-textbox" id="_id__function" required="true"  name="function" prompt="输入使用功能(分好分隔)"/>' +
			'<input class="easyui-textbox" id="_id__isSpecificLocation" required="true" name="isSpecificLocation"  prompt="是否特定场所(1/0)"/>' +
			'<input class="easyui-textbox" id="_id__funBusinessName" name="funBusinessName" prompt="输入商家名称"/>' +
			'<input class="easyui-numberbox" id="_id__funBuildArea" precision="2" name="funBuildArea" prompt="输入建筑面积（m2）"/>' +
			'<input type="hidden" name="buildingId" value="_id_"/>'+
			'<a href="#" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconCls="icon-remove" plain="true" onclick="delText(this)">删除</a></li>';

	// 添加节点
	function  appendFunctionText(id) {
		var targetObj =$("#buildingFunctionDiv ul").append(buildingFunctionTemplate.replace(/_id_/g,id));
		$.parser.parse(targetObj);
	}

	// 删除节点
	function delText(params) {
		$(params).parent().remove();
	}

	function onloading(){
		$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo(".panel-body");
		$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo(".panel-body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
	}
	function removeload(){
		$(".datagrid-mask").remove();
		$(".datagrid-mask-msg").remove();
	}
	function reloadAllFireSystemDatagrid(building_subject_id) {
		bindData('（一）消火栓系统','#hydrant001',fireSystemQueryAllUrl+'?systemName=hydrant001&buildingId='+building_subject_id,saveChangesFireSystemUrl);
		bindData('（二）自动喷水灭火系统','#automaticFire002',fireSystemQueryAllUrl+'?systemName=automaticFire002&buildingId='+building_subject_id,saveChangesFireSystemUrl);

		bindData('（三）雨淋、水幕及水喷雾灭火系统','#waterSpraySystem003',fireSystemQueryAllUrl+'?systemName=waterSpraySystem003&buildingId='+building_subject_id,saveChangesFireSystemUrl);
		bindData('（四）气体灭火系统','#gasFire004',fireSystemQueryAllUrl+'?systemName=gasFire004&buildingId='+building_subject_id,saveChangesFireSystemUrl);

		bindData('（五）火灾自动报警系统','#automaticAlarm005',fireSystemQueryAllUrl+'?systemName=automaticAlarm005&buildingId='+building_subject_id,saveChangesFireSystemUrl);
		bindData('（六）防烟和排烟系统','#smokeSystem006',fireSystemQueryAllUrl+'?systemName=smokeSystem006&buildingId='+building_subject_id,saveChangesFireSystemUrl);

		bindData('（七）防火门、窗和防火卷帘','#fireproofRolling007',fireSystemQueryAllUrl+'?systemName=fireproofRolling007&buildingId='+building_subject_id,saveChangesFireSystemUrl);
		bindData('（八）消防电源及其配电','#powerSupply008',fireSystemQueryAllUrl+'?systemName=powerSupply008&buildingId='+building_subject_id,saveChangesFireSystemUrl);

		bindData('（九）消防应急照明和疏散指示系统','#evacuationIndicating009',fireSystemQueryAllUrl+'?systemName=evacuationIndicating009&buildingId='+building_subject_id,saveChangesFireSystemUrl);
		bindData('（十）建筑灭火器','#buildingExtinguisher010',fireSystemQueryAllUrl+'?systemName=buildingExtinguisher010&buildingId='+building_subject_id,saveChangesFireSystemUrl);
	}
	
	function setFormForKeypartInfo() {
		var index = 0;
		$('#tb_keypart_info tr').each(function(a,item){
			if($(item).attr("name") != "title"){
				$(item).children('input').each(function (b,hiddenItem) {
					var hiddenEle = $(hiddenItem);
					var key = hiddenEle.attr('name');
					hiddenEle.val(keypartRowListData[index][key]);
				});
				$(item).children('td').each(function (index2,item2) {
					var inputEle = $(item2).children('input');
					var key = inputEle.attr('name');
					inputEle.val(keypartRowListData[index][key]);
				});
				index ++;
			}
		});
		url = UrlConfig.updateKeypartData;
	}
//上传缩略图
$('#thumb_img_input').filebox({
	'accept':['image/png','image/jpeg'],
	'onChange':function() {
		uploadImgFile();
	}
});
var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/buildingSubject/queryPage',
		insertData: '<%=request.getContextPath() %>/app/buildingSubject/insertData',
		updateData: '<%=request.getContextPath() %>/app/buildingSubject/updateData',
		deleteData: '<%=request.getContextPath() %>/app/buildingSubject/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/buildingSubject/deleteDataByIds',
		uploadImgUrl: '<%=request.getContextPath() %>/app/buildingSubject/upload',
		insertManagementData: '<%=request.getContextPath() %>/app/management/insertData',
		updateManagementData: '<%=request.getContextPath() %>/app/management/updateData',
		insertKeypartData: '<%=request.getContextPath() %>/app/keypart/insertData',
		updateKeypartData: '<%=request.getContextPath() %>/app/keypart/updateData',
		insertFuntionData:'<%=request.getContextPath() %>/app/buildingFunction/insertData',
		importStreet: '<%=request.getContextPath() %>/app/buildingSubject/importStreet',
};
var thumbImg = "";
// 上传缩略图
function uploadImgFile(){
    $('#file_building_img').form('submit',{
        url: UrlConfig.uploadImgUrl,
        success: function(result){
        	var rt =$.parseJSON(result);
        	if(rt.successful){
        		  $.messager.alert("提示", "图片上传成功！继续填写其他信息", "info");
        		  $("#building_thumb_img").val( rt.data);//填充内容
        	}
        }
    });
}

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";

var params = {
        districtUrl : districtUrl, 
        streetUrl:streetUrl,
        blockUrl:blockUrl,
        newDistrictEle:'comCcDistrict_building', 
        searchDistrictEle:'districtId',
        newStreetEle:'comCcStreet_building',
        searchStreetEle:'streetId',
        newBlockEle:'comCcBlock_building', 
        searchBlockEle:'blockId',
};

initDistrict(params);

//第三个tab
$(function(){
	$(".firecontrol-table").each(function(){
		$(this).find("tr:even").css("background-color","#f1f2f6");  //改变偶数行背景色
		/* 把背景色保存到属性中 */
		$(this).find("tr:eq(10)").css("background-color","#fff");
	});
})

// 新增
function newStreet(){
    $('#dlgBuildingSubject').dialog('open').dialog('setTitle','新建建筑主体');
	$('#buildingSubjectTabs').tabs('select','建筑物概况表');
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_building","#showSelected_building");
	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
	var tab = $('#buildingSubjectTabs').tabs('getSelected');
	// 0：建筑物基础信息  1：物业信息  2：重点部位  3：主要消防系统
	var index = $('#buildingSubjectTabs').tabs('getTabIndex',tab);
	$('#fm_building').form('clear');
	$('#fm_management').form('clear');
	$('#buildingFunctionUl').children().remove();
	if(index == 0){
		url =UrlConfig.insertData;
	}
}
// 编辑修改
var edit_row_id;
function editStreet(){
	var thumbImgUrl= "http://"+window.location.host+$('#dg_building_subject').datagrid("getSelections")[0].thumbImg;
	$("#thumb_img_show").attr("src", thumbImgUrl);
	$('#buildingSubjectTabs').tabs('select','建筑物概况表');
	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
	var tab = $('#buildingSubjectTabs').tabs('getSelected');
	// 0：建筑物基础信息  1：物业信息  2：重点部位  3：主要消防系统
	var index = $('#buildingSubjectTabs').tabs('getTabIndex',tab);


	var rowNum = $('#dg_building_subject').datagrid("getSelections").length;
	if(rowNum > 1 || rowNum == 0){
		$.messager.alert("提示","请选择一行修改！","error");
	}else {
		// 当前选中的是哪一行  建筑物
		var row = $('#dg_building_subject').datagrid('getSelected');
		if (row) {
			//表单加载数据前关掉联动查询
			disableLinkage(true);
			$('#dlgBuildingSubject').dialog('open').dialog('setTitle', '编辑建筑主体');
			$('#fm_building').form('load', row)
			url = UrlConfig.updateData + "?id=" + row.id;
			if(index != 0){
				$('#buildingSubjectTabs').tabs('select','建筑物概况表');
			}
			edit_row_id = row.id;
			// tab2
			$.ajax({
				url:"${pageContext.request.contextPath}/app/management/getByBuildingId?buildingId="+row.id,
				async: true,      //ajax同步
				type:"get",
				success: function(data){
					if(data){
						managementRowData = data;
					}
				}
			});
			// tab3
			$.ajax({
				url:"${pageContext.request.contextPath}/app/keypart/queryAll?buildingId="+row.id,
				async: true,      //ajax同步
				type:"get",
				success: function(data){
					if(data){
						keypartRowListData = data.rows;
					}
				}
			});
		}
	}
}

var building_subject_id_tab1;
// 保存
function saveUser() {
	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
	var tab = $('#buildingSubjectTabs').tabs('getSelected');
	// 0：建筑物基础信息  1：物业信息  2：重点部位  3：主要消防系统
	var index = $('#buildingSubjectTabs').tabs('getTabIndex', tab);
	var dialogTitle = $('#dlgBuildingSubject').dialog('header')[0].textContent;
	if (index == 0) {
		saveBuildingSubjectTab1();
		// 物业管理单位
	}  else if (index == 1) {
		if(dialogTitle == "编辑建筑主体"){
			building_subject_id_tab1 = $("#fm_building").children("input[name='id']").val();
		}
		console.log("index=" + index + ",building_subject_id_tab1=" + building_subject_id_tab1 );
		$("#fm_building_function").find("input[name='buildingId']").val(building_subject_id_tab1);
		if (building_subject_id_tab1) {
			var jsonStr = $("#fm_building_function").serializeJson();
			// 建筑物主要使用功能表
			saveBuildingFunctionTab2(jsonStr);
		} else {
			$.messager.alert("操作提示", "请先填写基础信息！", "error");
		}
	}else if (index == 2) {
		if(dialogTitle == "编辑建筑主体"){
			building_subject_id_tab1 =$("#fm_building").children("input[name='id']").val();
		}
		console.log("index=" + index + ",building_subject_id_tab1=" + building_subject_id_tab1 + ",url=" + url);
		$("#fm_management").children("input[name='buildingId']").val(building_subject_id_tab1);
		if (building_subject_id_tab1) {
			$('#fm_management').form('submit', {
				url: url,
				onSubmit: function () {
					return $(this).form('validate');
				},
				success: function (result) {
					var result = eval('(' + result + ')');
					if (result.successful) {
						$.messager.confirm('操作提示', result.data + '！是否继续填写消防重点部位概况表?', function (r) {
							// 是的话跳转tab
							if (r) {
								$('#buildingSubjectTabs').tabs('select', '消防重点部位概况表');
							}
						});
						$('#dg_building_subject').datagrid('reload');    // reload the user data
						management_id_tab2 = result.total;
					} else {
						if (result.msg) {
							$.messager.alert("操作提示", result.msg, "error");
						} else {
							$('#dlgBuildingSubject').dialog('close');        // close the dialog
							$('#dg_building_subject').datagrid('reload');    // reload the user data
						}
					}
				}
			});

		} else {
			$.messager.alert("操作提示", "请先填写基础信息！！", "error");
		}
	} else if (index == 3) {
		if(dialogTitle == "编辑建筑主体"){
			building_subject_id_tab1 = $("#fm_building").children("input[name='id']").val();
		}
		console.log("index=" + index + ",building_subject_id_tab1=" + building_subject_id_tab1 );
		$("#fm_keypart").find("input[name='buildingId']").val(building_subject_id_tab1);
		if (building_subject_id_tab1) {
			var jsonStr = $("#fm_keypart").serializeJson();
			$.ajax({
				url: url,
				type: "POST",
				contentType: 'application/json;charset=utf-8', //设置请求头信息
				dataType: "json",
				data: jsonStr,
				success: function (result) {
					if (result.successful) {
						$.messager.confirm('操作提示', result.data + '！是否继续填写主要消防系统概况表?', function (r) {
							// 是的话跳转tab
							if (r) {
								$('#buildingSubjectTabs').tabs('select', '主要消防系统概况表');
							}
						});
						$('#dg_building_subject').datagrid('reload');    // reload the user data
					} else {
						if (result.msg) {
							$.messager.alert("操作提示", result.msg, "error");
						} else {
							$('#dlgBuildingSubject').dialog('close');        // close the dialog
							$('#dg_building_subject').datagrid('reload');    // reload the user data
						}
					}
				}
			});
		} else {
			$.messager.alert("操作提示", "请先填写基础信息！", "error");
		}
	}
}

function saveBuildingFunctionTab2(jsonStr) {
	$.ajax({
		url: '<%=request.getContextPath() %>/app/buildingFunction/insertData',
		type: "POST",
		contentType: 'application/json;charset=utf-8', //设置请求头信息
		dataType: "json",
		data: jsonStr,
		success: function (result) {
			if (result.successful) {
				$.messager.confirm('操作提示', result.data + '！是否继续填写物业管理单位概况表?', function (r) {
					// 是的话跳转tab
					if (r) {
						$('#buildingSubjectTabs').tabs('select', '物业管理单位概况表');
					}
				});
				$('#dg_building_subject').datagrid('reload');    // reload the user data
			} else {
				if (result.msg) {
					$.messager.alert("操作提示", result.msg, "error");
				} else {
					$('#dlgBuildingSubject').dialog('close');        // close the dialog
					$('#dg_building_subject').datagrid('reload');    // reload the user data
				}
			}
		}
	});
}

function saveBuildingSubjectTab1() {
	console.log("saveUrl="+url);
	$('#fm_building').form('submit', {
		url: url,
		onSubmit: function () {
			var t = $('#comCcBlock_building').combotree('tree'); // 得到树对象
			var n = t.tree('getSelected'); // 得到选择的节点
			//这里经过实践测试应该使用t.tree('getChecked');
			if (queryAreaTreeUrl.indexOf('flag=3') != -1 && n.text == "天河区") {
				$.messager.alert("提示", "请下拉选择【" + n.text + "】下的社区数据！", "info");
				return false;
			}
			var flag = false;
			// 经度纬度 验证
			var longitude = $("input[name='longitude']").val();
			var latitude = $("input[name='latitude']").val();
			//用来验证数字，包括小数的正则
			var reg = /^[0-9]+\.?[0-9]*$/;
			if (longitude) {
				if (!reg.test(longitude)) {
					$.messager.alert("提示", "请输入正确的数字格式！", "info");
					return false;
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
			if (latitude) {
				if (!reg.test(latitude)) {
					$.messager.alert("提示", "请输入正确的数字格式！", "info");
					return false;
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
			var validate = $(this).form('validate');
			return (flag && validate);
		},
		success: function (result) {
			var result = eval('(' + result + ')');
			if (result.successful) {
				$.messager.confirm('操作提示', result.data + '！是否继续填写建筑物主要使用功能表?', function (r) {
					// 是的话跳转tab到人员信息
					if (r) {
						$('#buildingSubjectTabs').tabs('select', '建筑物主要使用功能表');
					}
				});
				$('#dg_building_subject').datagrid('reload');    // reload the user data
				building_subject_id_tab1 = result.total;
			} else {
				if (result.msg) {
					$.messager.alert("操作提示", result.msg, "error");
				} else {
					$('#dlgBuildingSubject').dialog('close');        // close the dialog
					$('#dg_building_subject').datagrid('reload');    // reload the user data
				}
			}
		}
	});
}


//批量删除
function destroyStreets(){//返回选中多行
    var selRow = $('#dg_building_subject').datagrid('getSelections')
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
        $.messager.confirm('提示', '后面四个tab数据一并删除，是否删除选中数据?', function (r) {
            if (!r) {
                return;
            }else{
                $.post(UrlConfig.deleteDataByIds, {  
                	deleteIds : deleteIds  
                }, 
                function(result) {  
		                	if(result.successful){
		                   	  	$.messager.alert("操作提示", result.data, "info");
		                		$('#dg_building_subject').datagrid('clearSelections');
		                        $('#dg_building_subject').datagrid('reload');    // reload the user data
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
	$('#dg_building_subject').datagrid('load',{
		ownerUnitName: $('#ownerUnitName').val(),
		streetId:$('#streetId').combobox('getValue'),   // 下拉框获取数据ID
		blockId:$('#blockId').combobox('getValue'),
		districtId:$('#districtId').combobox('getValue'),
		baseBuildingClass:$('#baseBuildingClass').combobox('getValue'),
	});
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
      $("#districtId").combobox('select', 0);
      $("#streetId").combobox('select', 0);
      $("#blockId").combobox('select', 0);
      $("#baseBuildingClass").combobox('select', 0);
      $("#ownerUnitName").val("");
      doSearch();
}

function uploadFile(){
	console.log("upload building");
    $('#fm_file_building').form('submit',{
        url: UrlConfig.importStreet,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
	          	  $.messager.alert("操作提示", result.data, "info");
	          	  $('#import_building_subject').dialog('close');        // close the dialog
	                $('#dg_building_subject').datagrid('reload');    // reload the user data
	          }else{
	          	if (result.msg){
	              	  $.messager.alert("操作提示", result.msg, "error");
	                } else {
	                    $('#import_building_subject').dialog('close');        // close the dialog
	                    $('#dg_building_subject').datagrid('reload');    // reload the user data
	                }
	          }
        }
    });
}


//导入
function doImport(){
	$('#import_building_subject').dialog('open').dialog('setTitle','导入数据');
}

function cancelAction(){
	$('#showSelected_building').text('');
	$('#dlgBuildingSubject').dialog('close');
	$('#dg_building_subject').datagrid('reload');
	$('#buildingFunctionUl').children().remove();
}
</script>
</body>
</html>