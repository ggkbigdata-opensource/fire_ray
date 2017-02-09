<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>火灾事故管理</title>
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
    <div class="inputDiv">
        <div class='inputDivEg'>
	        <span class="inputLabel">行政区:</span>
	        <input id="districtId" class="easyui-combobox inputDiv-input" data-options="width:100">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">街道:</span>
	        <input id="streetId" class="easyui-combobox inputDiv-input" data-options="width:100">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">社区:</span>
	        <input id="blockId" class="easyui-combobox inputDiv-input" data-options="width:120">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">开始月份:</span>
	        <input id="occurTimeStart" class="easyui-datebox easyui-validatebox" data-options="validType:'checkStartDate',editable:false" style="width:100px">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">结束月份:</span>
	        <input id="occurTimeEnd" class="easyui-datebox easyui-validatebox" data-options="validType:'checkEndDate[\'#occurTimeStart\']',editable:false" style="width:100px">
        </div>
        <div class="height10"></div>
	    <div class='inputDivEg'>
		    <span class="inputLabel">火灾类型:</span>
		    <input id="fireType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:120">
	    </div>
	    <div class='inputDivEg'>
		    <span class="inputLabel">起火原因:</span>
		    <input id="fireReasonType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">
	    </div>
	    <div class='inputDivEg'>
		    <span class="inputLabel">使用类型:</span>
		    <input id="placeUseType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">
	    </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">入库类型:</span>
	        <input id="fireEventState" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">
        </div>
        <%--<span class="inputLabel">场所名称:</span>--%>
        <%--<input id="placeName" class="easyui-textbox" style="line-height:20px;"/>--%>
        <div class='inputDivEg'>
	        <span class="inputLabel">事故名称:</span>
	        <input id="name" class="easyui-textbox" style="line-height:20px;"/>
        </div>
        <div class="height10"></div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
        <a href="#" class="queryBtn" plain="true" onclick="doExport()">导出源数据</a>
        <a href="#" class="queryBtn" plain="true" onclick="doImport()">导入</a>
        <a href="#" class="queryBtn" plain="true" onclick="doSumFire()">分析火情数据</a>
        <a href="#" class="queryBtn" plain="true" onclick="doExportReport()">导出报表</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_fire" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
       toolbar="#toolbar"
       rownumbers="true"
       fitColumns="true"
       singleSelect="false"
       autoRowHeight="false"
       pagination="true"
       pageSize="20"
                striped="true"
       checkOnSelect="true"
       selectOnCheck="true">
    <thead>
    <tr>
        <th field="ck" checkbox="true"></th>
        <th field="caseNumber" width="50">序号</th>
        <th field="name" width="50">事故名称</th>
        <th field="placeName" width="50">场所名称</th>
        <th field="ownerUnitName" width="50">建筑名称</th>
        <th field="fireType" width="50">火灾类型</th>
        <th field="fireReasonType" width="50">起火原因</th>
        <th field="placeUseType" width="50">使用性质</th>
        <th field="state" width="50">入库类型</th>
        <th field="description" width="50">案情</th>
        <th field="districtName" width="30">行政区</th>
        <th field="streetName" width="50">所属街道</th>
        <th field="blockName" width="50">社区名称</th>
        <th field="occurTime" width="50">火情发生时间</th>
        <th field="modDate" width="50">修改时间</th>
    </tr>
    </thead>
    <input type="hidden" name="streetId" value="streetId"/>
</table>
</div>

<div id="toolbar">
    <a href="#" class="resetBtn" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" plain="true" onclick="editStreet()">修改</a>
    <a href="#" class="queryBtn" plain="true" onclick="destroyStreets()">删除</a>
</div>

<!--To create or edit a user, we use the same dialog.  -->

<div id="dlgFireEvent" class="easyui-dialog" style="width:750px;height:530px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">警情 信息</div>
    <form id="fm_fire" method="post" novalidate>

        <div class="fitem_building">
            <label><span style="color: #FF0000;font-weight: bold;">*</span>社区:</label>
            <input class="easyui-combotree" id="comCcBlock_fire" name = "blockId" style="width:173px;height:26px" required="true" >
        </div>
        <div class="fitem_building">
            <label>您选择的是:</label>
            <span id="showSelected_fireEvent" style="color: #FF3030;width: 200px;"></span>
        </div>
        <div class="fitem_building">
            <label>建筑:</label>
            <input id="comCcBuilding_fire" name="buildingId" class="easyui-combobox"
                   data-options="required:false,editable:false,width:173"/>
        </div>
        <div class="fitem_building">
            <label><span style="color: #FF0000;font-weight: bold;">*</span>城市区域:</label>
            <input name="cityArea" class="easyui-textbox" required="true" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>企业性质:</label>
            <input name="enterpriseNature" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label><span style="color: #FF0000;font-weight: bold;">*</span>入库类型:</label>
            <input id="comCcFireEventState" name="state" class="easyui-combobox"
                   data-options="required:true,editable:false,width:173"/>
        </div>
        <div class="fitem_building">
            <label>发生时间:</label>
            <input class="easyui-datetimebox" name="occurTimeString"
                   data-options="required:false,showSeconds:false" value="3/4/2010 2:3" style="width:173px">
        </div>
        <div class="fitem">
            <label class="area">案情:</label>
            <input class="easyui-textbox" data-options="multiline:true" name="description" style="width:500px;height:70px">
        </div>
        <div class="fitem">
            <label class="area">现场案情:</label>
            <input class="easyui-textbox" data-options="multiline:true" name="sceneDesc" style="width:500px;height:70px">
        </div>
        <div class="fitem_building">
	        <label><span style="color: #FF0000;font-weight: bold;">*</span>序号:</label>
	        <input name="caseNumber" class="easyui-textbox" required="true" style="text-align:right;width:173px">
	    </div>
        <div id="firstInsert">
	    <div class="fitem_building">
	        <label><span style="color: #FF0000;font-weight: bold;">*</span>火灾类型:</label>
	        <input id="comCcFireType" name="fireType" class="easyui-combobox"
	               data-options="required:true,editable:false,width:173"/>
	    </div>
	    <div class="fitem_building">
           <label>消防手续:</label>
           <input id="comCcPlaceFireType" name="placeFireType" class="easyui-combobox"
                  data-options="required:false,editable:false,width:173"/>
       </div>
       <div class="fitem_building">
	       <label>地理位置:</label>
	       <input id="comCcPlacePositionType" name="placePositionType" class="easyui-combobox"
	              data-options="required:false,editable:false,width:173"/>
	   </div>
        <div class="fitem_building">
	       <label><span style="color: #FF0000;font-weight: bold;">*</span>工程性质:</label>
	       <input id="comCcPlaceBuildType" name="placeBuildType" class="easyui-combobox"
	              data-options="required:true,editable:false,width:173"/>
	    </div>
        <div class="fitem_building">
            <label><span style="color: #FF0000;font-weight: bold;">*</span>使用性质:</label>
            <input id="comCcPlaceUseType" name="placeUseType" class="easyui-combobox"
                   data-options="required:true,editable:false,width:173"/>
        </div>
        <div class="fitem_building">
	        <label>事故名称:</label>
	        <input name="name" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>场所名称:</label>
            <input name="placeName" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
	    <div class="fitem_building">
	        <label><span style="color: #FF0000">*</span>起火原因分类:</label>
	        <input id="comCcFireReasonType" name="fireReasonType" class="easyui-combobox"
	               data-options="required:true,editable:false,width:173"/>
	    </div>
        <div class="fitem_building">
            <label>起火位置:</label>
            <input name="firePosition" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>起火物:</label>
            <input name="fireObject" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>经济损失:</label>
            <input name="loss" type="text" class="easyui-numberbox"
                   precision="2" size="10" maxlength="18" style="text-align:right;width:173px"/>
        </div>
        <div class="fitem_building">
            <label>死亡人数:</label>
            <input name="deadNum" type="text" class="easyui-numberbox"
                   size="8" maxlength="18" style="text-align:right;width:173px"/>
        </div>

        <div class="fitem_building">
            <label>受伤人数:</label>
            <input name="hurtNum" type="text" class="easyui-numberbox"
                   size="8" maxlength="18" style="text-align:right;width:173px;"/>
        </div>
        <div class="fitem_building">
            <label>值班组:</label>
            <input name="dutyPart" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>消防执法案号:</label>
            <input name="punishCaseNumber" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>移交部门:</label>
            <input name="handleDepart" class="easyui-textbox" style="text-align:right;width:173px">
        </div>
        <div class="fitem_building">
            <label>消防站参与:</label>
            <input class="easyui-combobox" name="fireStation"data-options="
                valueField: 'value',
                textField: 'label',
                data: [{
                    label: '是',
                    value: '1'
                },{
                    label: '否',
                    value: '0'
                }],width:173" />
        </div>
        <div class="fitem_building">
            <label>是否自救:</label>
            <input class="easyui-combobox" name="selfSave" data-options="
                valueField: 'value',
                textField: 'label',
                data: [{
                   label: '是',
                        value: '1'
                    },{
                        label: '否',
                        value: '0'
                }],width:173" />
        </div>
        <div class="fitem">
            <label class="area">起火原因:</label>
	         <input class="easyui-textbox" data-options="multiline:true" name="fireReason" style="width:500px;height:70px">
        </div>
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlgFireEvent').dialog('close');$('#showSelected_fireEvent').text('');" style="width:90px">取消</a>
</div>


<div id="importFireEvent" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
     closed="true">
    <div class="ftitle">导入数据</div>
    <form id="fm_file_fire_event" method="post" enctype="multipart/form-data" target="ajaxUpload">
        <div style="margin-top:10px;">
			<a href="<%=request.getContextPath() %>/template/fireEvent_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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
    //初始化月份选择
    initDateMonthBox('occurTimeStart');
    initDateMonthBox('occurTimeEnd');
	var UrlConfig = {
        listPage: '<%=request.getContextPath() %>/app/fireEvent/queryPage',
        insertData: '<%=request.getContextPath() %>/app/fireEvent/insertData',
        updateData: '<%=request.getContextPath() %>/app/fireEvent/updateData',
        deleteData: '<%=request.getContextPath() %>/app/fireEvent/deleteData',
        deleteDataByIds: '<%=request.getContextPath() %>/app/fireEvent/deleteDataByIds',
        exportLibStreet: '<%=request.getContextPath() %>/app/fireEvent/exportLibStreet',
        importStreet: '<%=request.getContextPath() %>/app/fireEvent/importStreet',
        exportReport:'<%=request.getContextPath() %>/app/fireEvent/exportReport',
    }
	// 从饼状图点击过来获取参数
	var fireType = '<%=request.getParameter("fireType")%>',
	fireReasonType = '<%=request.getParameter("fireReasonType")%>',
	<%--year = '<%=request.getParameter("year")%>',--%>
    monthStart = '<%=request.getParameter("monthStart")%>',
    monthEnd = '<%=request.getParameter("monthEnd")%>',
	placeUseType = '<%=request.getParameter("placeUseType")%>',
	// 区域信息
	showDistrictId = '<%=request.getParameter("districtId")%>',
	showStreetId = '<%=request.getParameter("streetId")%>',
	showBlockId = '<%=request.getParameter("blockId")%>';
	if(showDistrictId && showDistrictId == 'null'){
		showDistrictId = "";
	}
	if(showStreetId && (showStreetId == 'null' || showStreetId == 'undefined') ){
		showStreetId = "";
    }
	if(showBlockId && (showBlockId == 'null' || showBlockId == 'undefined') ){
		showBlockId = "";
	}

    var dictListFireEvent;
    var placeUseTypeData =  new Array(),
    placeUseTypeDataSearch =  new Array()
    placePositionTypeData = new Array(),
    placeSpaceTypeData = new Array(),
    placeBuildTypeData = new Array(),
    placeFireTypeData = new Array(),
    fireTypeData = new Array(),
    fireTypeDataSearch = new Array(),
    fireReasonTypeData = new Array(),
    fireReasonTypeDataSearch = new Array(),
    fireEventStateData = new Array(),
    fireEventStateDataSearch = new Array();
    // 获取数据字典数据
    $.ajax({
        url:"${pageContext.request.contextPath}/app/config/getAllDictList",
        async: false,      //ajax同步  
        type:"get",
        success: function(data){
            if(data.successful){
                dictListFireEvent = data.data.rows;
            }
        }
    });
    $.each(dictListFireEvent,function(n,value) {   
        var name = value.name,
        code = value.code;
        //场所使用类型：place_use_type 
        if ("place_use_type" == value.typeCode) {
            placeUseTypeData.push({name: name, code: code});
            placeUseTypeDataSearch.push({name: name, code: code});
        } 
        //地理位置 ：place_position_type
        if ("place_position_type" == value.typeCode) {
            placePositionTypeData.push({name: value.name, code: value.code});
        }
         //工程性质  place_build_type
        if ("place_build_type" == value.typeCode) {
            placeBuildTypeData.push({name: value.name, code: value.code});
        }
        //消防手续 place_fire_type
        if ("place_fire_type" == value.typeCode) {
            placeFireTypeData.push({name: value.name, code: value.code});
        }
        //种类 fire_type
        if ("fire_type" == value.typeCode) {
            fireTypeData.push({name: value.name, code: value.code});
            fireTypeDataSearch.push({name: value.name, code: value.code});
        }
        // 起火原因分类 fire_reason_type
        if ("fire_reason_type" == value.typeCode) {
            fireReasonTypeData.push({name: value.name, code: value.code});
            fireReasonTypeDataSearch.push({name: value.name, code: value.code});
        }
        if ("fire_event_state" == value.typeCode) {
            fireEventStateData.push({name: value.name, code: value.code});
            fireEventStateDataSearch.push({name: value.name, code: value.code});
        }
    });
    var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3&hasBuilding=1",
            buildingQueryAllUrl = "${pageContext.request.contextPath}/app/buildingSubject/queryAll?blockId=";
    $(document).ready(function() {
        queryAreaTree(queryAreaTreeUrl,"#comCcBlock_fire","#showSelected_fireEvent",buildingQueryAllUrl,"#comCcBuilding_fire");
        // 场所使用类型
       $('#comCcPlaceUseType').combobox({
            data: placeUseTypeData,
            valueField: 'code',
            textField: 'name'
        });
       
       // 搜索栏
       placeUseTypeDataSearch.unshift({code: '0', name: '全部'});
       $('#placeUseType').combobox({
           data: placeUseTypeDataSearch,
           valueField: 'code',
           textField: 'name',
           value: '0',
           onLoadSuccess: function () { //数据加载完毕事件
		      		if(placeUseType && placeUseType.length > 0 && placeUseType != 'null'){
		     				var selected = false;
		     				$.each(placeUseTypeDataSearch,function(i,v){
		     					if(v.code == placeUseType){
		     						selected = true;
		     						$('#placeUseType').combobox('select',v.code);
		     						return ;
		     					}
		     				});
		     			}else{
		     				 $('#placeUseType').combobox('select', 0);
		     			}
	        }
       });
        fireEventStateDataSearch.unshift({code: '0', name: '全部'});
        $('#fireEventState').combobox({
            data: fireEventStateDataSearch,
            valueField: 'code',
            textField: 'name',
            value: '0',
            onLoadSuccess: function () { //数据加载完毕事件
                $('#fireEventState').combobox('select', 0);
            }
        });
        //地理位置 
        $('#comCcPlacePositionType').combobox({
            data : placePositionTypeData,
            valueField:'code',
            textField:'name'
        });
        //工程性质
        $('#comCcPlaceBuildType').combobox({
            data : placeBuildTypeData,
            valueField:'code',
            textField:'name'
        });

        //消防手续
        $('#comCcPlaceFireType').combobox({
            data: placeFireTypeData,
            valueField: 'code',
            textField: 'name'
        });
        //种类
        $('#comCcFireType').combobox({
            data: fireTypeData,
            valueField: 'code',
            textField: 'name'
        });

        fireTypeDataSearch.unshift({code: '0', name: '全部'});
        $('#fireType').combobox({
            data: fireTypeDataSearch,
            valueField: 'code',
            textField: 'name',
            value: '0',
            onLoadSuccess: function () { //数据加载完毕事件
                if(fireType && fireType.length > 0 && fireType != 'null'){
                    var selected = false;
                    $.each(fireTypeDataSearch,function(i,v){
                        if(v.code == fireType){
                            selected = true;
                            $('#fireType').combobox('select',v.code);
                            return ;
                        }
                    });
                }else{
                     $('#fireType').combobox('select', 0);
                }
	        }
        });
        
        
        // 起火原因分类
        $('#comCcFireReasonType').combobox({
            data: fireReasonTypeData,
            valueField: 'code',
            textField: 'name'
        });
        
        fireReasonTypeDataSearch.unshift({code: '0', name: '全部'});
        $('#fireReasonType').combobox({
            data: fireReasonTypeDataSearch,
            valueField: 'code',
            textField: 'name',
            value: '0',
            onLoadSuccess: function () { //数据加载完毕事件
	      		if(fireReasonType && fireReasonType.length > 0 && fireReasonType != 'null'){
     				var selected = false;
     				$.each(fireReasonTypeDataSearch,function(i,v){
     					if(v.code == fireReasonType){
     						selected = true;
     						$('#fireReasonType').combobox('select',v.code);
     						return ;
     					}
     				});
     			}else{
     				 $('#fireReasonType').combobox('select', 0);
     			}
	        }
        });
        
    	//表单窗口打开前禁用联动查询
    	$('#fm_fire').form({
    		onLoadSuccess : function(){
    			console.info("load sunness");
    			disableLinkage(false);
    		}
    	});

        if(monthStart && monthStart.length==7){
            $('#occurTimeStart').datebox('setValue',monthStart);
        }
        if(monthEnd && monthEnd.length==7){
            $('#occurTimeEnd').datebox('setValue',monthEnd);
        }
        $('#dg_fire').datagrid({
            url:UrlConfig.listPage,
            queryParams:{
                fireType:fireType=="null"?"0":fireType,
                fireReasonType:fireReasonType=="null"?"0":fireReasonType,
                placeUseType:placeUseType=="null"?"0":placeUseType,
                districtId:showDistrictId=="null"?"0":showDistrictId,
                streetId:showStreetId=="null"?"0":showStreetId,
                blockId:showBlockId=="null"?"0":showBlockId,
                occurStartMonth:monthStart=='null'?'':monthStart,
                occurEndMonth:monthEnd=='null'?'':monthEnd
            }
        });
    });


    // 行政区
    var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
    // 街道
    var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
    // 社区
    var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";
    // 建筑
    var buildingUrl = "${pageContext.request.contextPath}/app/building/queryAll";
	var params = {
			districtUrl : districtUrl, 
			streetUrl:streetUrl,
			blockUrl:blockUrl,
			buildingUrl:buildingUrl,
			newDistrictEle:'comCcDistrict_fire', 
			searchDistrictEle:'districtId',
			newStreetEle:'comCcStreet_fire',
			searchStreetEle:'streetId',
			newBlockEle:'comCcBlock_fire', 
			searchBlockEle:'blockId',
			newBuildingEle:'comCcBuilding_fire',
		    showDistrictId:showDistrictId,
            showStreetId:showStreetId,
            showBlockId:showBlockId
	};
	
	initDistrict(params);
//	initBuilding(params,null);

    // 新增
    function newStreet() {
        queryAreaTree(queryAreaTreeUrl,"#comCcBlock_fire","#showSelected_fireEvent",buildingQueryAllUrl,"#comCcBuilding_fire");
    	disableLinkage(false);
        $('#dlgFireEvent').dialog('open').dialog('setTitle', '新建警情');
        $('#fm_fire').form('clear');
        url = UrlConfig.insertData;

        // 入库类型
        $('#comCcFireEventState').combobox({
            data: fireEventStateData,
            valueField: 'code',
            textField: 'name',
            onLoadSuccess: function () { //加载完成后,设置选中第一项
                var val = $(this).combobox("getData");
                for (var item in val[0]) {
                    if (item == "code") {
                        $(this).combobox("select", val[0][item]);
                    }
                }
            },
            onSelect : function (record) {
                console.log(record);
                if(record.code == "first_insert"){
                    $("#firstInsert").hide();
                }else{
                    $("#firstInsert").show();
                }
            }
        });
    }

    // 编辑修改
    function editStreet() {
        // 入库类型
        $('#comCcFireEventState').combobox({
            data: fireEventStateData,
            valueField: 'code',
            textField: 'name',
            onSelect : function (record) {
                if(record.code == "first_insert"){
                    $("#firstInsert").hide();
                }else{
                    $("#firstInsert").show();
                }
            }
        });
        var rowNum = $('#dg_fire').datagrid("getSelections").length;
        if (rowNum > 1 || rowNum == 0) {
            $.messager.alert("提示", "请选择一行修改！", "error");
        } else {
            var row = $('#dg_fire').datagrid('getSelected');
            if (row) {
            	//表单加载数据前关掉联动查询
            	disableLinkage(true);
            	$('#dlgFireEvent').dialog('open').dialog('setTitle', '编辑警情');
                $('#fm_fire').form('load', row);
                if(row.state == "初录"){
                    $("#firstInsert").hide();
                }else{
                    $("#firstInsert").show();
                }
                url = UrlConfig.updateData + "?id=" + row.id;
            }else {
                $.messager.alert("提示", "请选择要查看的行！", "info");
            }
        }
    }

    // 保存
    function saveUser() {
        $('#fm_fire').form('submit', {
            url: url,
            onSubmit: function () {
                var t = $('#comCcBlock_fire').combotree('tree'); // 得到树对象
                var n = t.tree('getSelected'); // 得到选择的节点
                //这里经过实践测试应该使用t.tree('getChecked');
                if(queryAreaTreeUrl.indexOf('flag=3')!=-1 && n.text == "天河区") {
                    $.messager.alert("提示", "请下拉选择【"+n.text+"】下的社区数据！", "info");
                    return false;
                }
            	// 后期需求，建筑信息选填
//              	var buildingId = $('#comCcBuilding_fire').combobox('getValue');
//            	if(!buildingId){
//            		 $.messager.alert("提示", "建筑数据必填，请选择正确的社区！", "info");
//            		 return false;
//            	}
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('('+result+')');
                if(result.successful){
              	  	$.messager.alert("操作提示", result.data, "info");
                    $('#dlgFireEvent').dialog('close');        // close the dialog
                    $('#dg_fire').datagrid('reload');    // reload the user data
    	          }else{
    	          	if (result.msg){
    	              	  $.messager.alert("操作提示", result.msg, "error");
    	                } else {
    	                  $('#dlgFireEvent').dialog('close');        // close the dialog
    	                  $('#dg_fire').datagrid('reload');    // reload the user data
    	                }
    	          }
            	//提交后需要清除表单数据
    			$('#fm_block').form('clear');
            }
        });
    }

    //批量删除
    function destroyStreets() {//返回选中多行
        var selRow = $('#dg_fire').datagrid('getSelections')
        //判断是否选中行
        if (selRow.length == 0) {
            $.messager.alert("提示", "请选择要删除的行！", "info");
            return;
        } else {
            var deleteIds = "";
            for (i = 0; i < selRow.length; i++) {
                if (deleteIds == "") {
                    deleteIds = selRow[i].id;
                } else {
                    deleteIds = selRow[i].id + "," + deleteIds;
                }
            }
            console.log("destroy：ids=" + deleteIds);
            $.messager.confirm('提示', '是否删除选中数据?', function (r) {
                if (!r) {
                    return;
                } else {
                    $.post(UrlConfig.deleteDataByIds, {
                                deleteIds: deleteIds
                            },
                            function (result) {
                              	if(result.successful){
        	                		$('#dg_fire').datagrid('clearSelections');
        	                   	  	$.messager.alert("操作提示", result.data, "info");
        	                        $('#dg_fire').datagrid('reload');    // reload the user data
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

    function doSearch(value, name) {
        var occurStartMonth = $('#occurTimeStart').datebox('getValue');
        var occurEndMonth = $('#occurTimeEnd').datebox('getValue');
        $('#dg_fire').datagrid('load', {
            name: $('#name').val(),
            placeName: $('#placeName').val(),
            streetId: $('#streetId').combobox('getValue'),   // 下拉框获取数据ID
            blockId: $('#blockId').combobox('getValue'),
            districtId:$('#districtId').combobox('getValue'),
            fireType:$('#fireType').combobox('getValue'),
            fireReasonType:$('#fireReasonType').combobox('getValue'),
            placeUseType:$('#placeUseType').combobox('getValue'),
            fireEventState:$('#fireEventState').combobox('getValue'),
            occurStartMonth:occurStartMonth=='null'?undefined:occurStartMonth,
            occurEndMonth:occurEndMonth=='null'?undefined:occurEndMonth
        });
    }

    function doReset() {
        $("#placeName").val("");
        $("#name").val("");
		params.showDistrictId = undefined;
		params.showStreetId = undefined;
		params.showBlockId = undefined;
        $("#districtId").combobox('select', 0);
        // 有点小问题
        $("#streetId").combobox('select', 0);
        $("#blockId").combobox('select', 0);
        $('#fireType').combobox('select', 0);
        $('#fireReasonType').combobox('select', 0);
        $('#placeUseType').combobox('select', 0);
        $('#fireEventState').combobox('select', 0);
        $('#occurTimeStart').datebox('setValue',undefined);
        $('#occurTimeEnd').datebox('setValue',undefined);
        doSearch();
    }
    // 导出
    function doExport(value, name) {
        $.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
            if (!r) {
                return;
            } else {
                var exportName = $('#name').val();
                var exportPlaceName = $('#placeName').val();
                var openUrl = UrlConfig.exportLibStreet +
                        "?name=" + encodeURI(exportName) +
                        "&placeName=" + encodeURI(exportPlaceName) +
                        "&streetId=" + $('#streetId').combobox('getValue') +
                        "&districtId=" + $('#districtId').combobox('getValue') +
                        "&blockId=" + $('#blockId').combobox('getValue')+
                        "&fireType=" + $('#fireType').combobox('getValue') +
                        "&placeUseType=" + $('#placeUseType').combobox('getValue') +
                        "&fireReasonType=" + $('#fireReasonType').combobox('getValue')
                window.open(openUrl);
            }
        });
    }

    // 导出报表
    function doExportReport(value, name) {
        var districtId = $('#districtId').combobox('getValue');
        var occurStartMonth = $('#occurTimeStart').datebox('getValue');
        var occurEndMonth = $('#occurTimeEnd').datebox('getValue');
        if(districtId && occurStartMonth && occurEndMonth){
            if(districtId == "0"){
                $.messager.alert("操作提示", "请选择行政区，非全部！", "info");
            }else{
                $.messager.confirm('提示', '是否导出报表数据?', function (r) {
                    if (!r) {
                        return;
                    } else {
                        var openUrl = UrlConfig.exportReport +
                                "?districtId=" + districtId +
                                "&monthBegin=" + occurStartMonth +
                                "&monthEnd=" + occurEndMonth
                        window.open(openUrl);
                    }
                });
            }
        }else{
            $.messager.alert("操作提示", "请选择行政区、报表开始月份及结束月份", "info");
        }
    }

    //导入
    function doImport() {
        $('#importFireEvent').dialog('open').dialog('setTitle', '导入数据');
    }

    function uploadFile() {
        $('#fm_file_fire_event').form('submit', {
            url: UrlConfig.importStreet,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('('+result+')');
                if(result.successful){
    	          	  $.messager.alert("操作提示", result.data, "info");
    	          	  $('#importFireEvent').dialog('close');        // close the dialog
    	                $('#dg_fire').datagrid('reload');    // reload the user data
    	          }else{
    	          	if (result.msg){
    	              	  $.messager.alert("操作提示", result.msg, "error");
    	                } else {
    	                    $('#importFireEvent').dialog('close');        // close the dialog
    	                    $('#dg_fire').datagrid('reload');    // reload the user data
    	                }
    	          }
            }
        });
    }

    //手动分析数据
    function doSumFire() {
        var begin = $('#occurTimeStart').combobox('getValue');
        var end = $('#occurTimeEnd').combobox('getValue');
        if( !begin || !end || end < begin ){
            $.messager.alert('错误', '请先选择正确的时间段！' , 'error');
            return ;
        }
        var url = '<%=request.getContextPath() %>/app/data/sumFireEvent?begin='+begin+'&end='+end;
        $.get(url,function(data){
            if(data && data.successful){
                $.messager.alert('提示', '分析成功！' , 'ok');
            }else{
                $.messager.alert('错误', data.msg , 'error');
            }
        });
    }

</script>
</body>
</html>