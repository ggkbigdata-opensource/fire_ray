<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>执法管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
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
	        <input id="punishStartMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkStartDate',editable:false" style="width:100px">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">结束月份:</span>
	        <input id="punishEndMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkEndDate[\'#punishStartMonth\']',editable:false" style="width:100px">
        </div>
        <div class="height10"></div>
        <div class='inputDivEg'>
	        <span class="inputLabel">执法类型:</span>
	        <input id="punishType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">执法名称:</span>
	        <input id="name" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
        </div>
        <div class='inputDivEg'>
	        <span class="inputLabel">场所名称:</span>
	        <input id="placeName" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
        </div>
        <div class="height10"></div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
        <a href="#" class="queryBtn" plain="true" onclick="doExport()">导出源数据</a>
        <a href="#" class="queryBtn" plain="true" onclick="doImport()">导入</a>
        <a href="#" class="queryBtn" plain="true" onclick="doSumPunish()">分析执法数据</a>
        <a href="#" class="queryBtn" plain="true" onclick="doExportReport()">导出报表</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_punish" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
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
        <th field="name" width="50">执法名称</th>
        <th field="punishNumber" width="50">执法编码</th>
        <th field="placeName" width="50">场所名称</th>
        <th field="ownerUnitName" width="50">建筑名称</th>
        <th field="punishType" width="50">执法类型</th>
        <th field="districtName" width="50">行政区</th>
        <th field="streetName" width="50">所属街道</th>
        <th field="blockName" width="50">社区名称</th>
        <th field="punishTime" width="50">发生时间</th>
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

<div id="dlgPunishEvent" class="easyui-dialog" style="width:750px;height:560px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">执法信息管理</div>
    <form id="fm_punish" method="post" novalidate>
        <div class="fitem_building">
            <label>社区:</label>
            <input class="easyui-combotree" id="comCcBlock_punish" name = "blockId" style="width:173px;height:26px" required="true" >
        </div>
        <div class="fitem_building">
            <label>您选择的是:</label>
            <span id="showSelected_punishEvent" style="color: #FF3030;width: 200px;"></span>
        </div>

        <div class="fitem_building">
            <label>建筑名称:</label>
            <input id="comCcBuilding_punish" name="buildingId" class="easyui-combobox"
                   data-options="required:false,editable:false,width:173"/>
        </div>
        
        <div class="fitem_building">
	        <label>执法名称:</label>
	        <input name="name" class="easyui-textbox" required="true">
	    </div>
        <div class="fitem_building">
            <label>场所名称:</label>
            <input name="placeName" class="easyui-textbox" required="true">
        </div>

        <div class="fitem_building">
            <label>责任人:</label>
            <input name="placeOwner" class="easyui-textbox" required="true">
        </div>

        <div class="fitem_building">
            <label>处罚方式:</label>
            <input id="comCcPunishType" name="punishType" class="easyui-combobox"
                   data-options="required:true,editable:false,width:173"/>
        </div>

        <div class="fitem_building">
            <label>发生时间:</label>
            <input class="easyui-datetimebox" name="punishTimeString"
                   data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:173px">
        </div>
        <div class="fitem_building">
            <label>金额:</label>
            <input name="punishAmount" type="text" class="easyui-numberbox"
                   precision="2" size="10" maxlength="8" style="text-align:right;width:173px;"/>
        </div>

        <div class="fitem_building">
            <label>执法人名称:</label>
            <input name="punishPersonName" class="easyui-textbox" required="true">
        </div>

        <div class="fitem">
            <label class="area">备注:</label>
	         <input class="easyui-textbox" data-options="multiline:true" name="remark" style="width:500px;height:70px">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlgPunishEvent').dialog('close');$('#showSelected_punishEvent').text('');" style="width:90px">取消</a>
</div>


<div id="importPunishEvent" class="easyui-dialog" style="width:300px;height:250px;padding:10px 20px"
     closed="true">
    <div class="ftitle">导入数据</div>
    <form id="fm_file_punish_event" method="post" enctype="multipart/form-data" target="ajaxUpload">
        <div style="margin-top:10px;">
			<a href="<%=request.getContextPath() %>/template/punishEvent_import_template.xls"   class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
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
    //初始化月份条件选择
    initDateMonthBox('punishStartMonth');
    initDateMonthBox('punishEndMonth');
	var UrlConfig = {
	    listPage: '<%=request.getContextPath() %>/app/punishEvent/queryPage',
	    insertData: '<%=request.getContextPath() %>/app/punishEvent/insertData',
	    updateData: '<%=request.getContextPath() %>/app/punishEvent/updateData',
	    deleteData: '<%=request.getContextPath() %>/app/punishEvent/deleteData',
	    deleteDataByIds: '<%=request.getContextPath() %>/app/punishEvent/deleteDataByIds',
	    exportLibStreet: '<%=request.getContextPath() %>/app/punishEvent/exportLibStreet',
	    importStreet: '<%=request.getContextPath() %>/app/punishEvent/importStreet',
        exportReport:'<%=request.getContextPath() %>/app/punishEvent/exportReport',
	};
	// 从柱状图点击过来获取参数
	var punishTypeName = '<%=request.getParameter("punishTypeName")%>',
    monthStart = '<%=request.getParameter("monthStart")%>',
    monthEnd = '<%=request.getParameter("monthEnd")%>',
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
	
	var punishTypeCode;
    var dictListPunishEvent;
    var punishTypeData =  new Array(),
    punishTypeDataSearch = new Array();
    // 获取数据字典数据
    $.ajax({
        url:"${pageContext.request.contextPath}/app/config/getAllDictList",
        async: false,      //ajax同步  
        type:"get",
        success: function(data){
            if(data.successful){
                dictListPunishEvent = data.data.rows;
            }
        }
    });
    $.each(dictListPunishEvent,function(n,value) {   
        var name = value.name,
        code = value.code;
        //处罚方式：punish_type
        if ("punish_type" == value.typeCode) {
            punishTypeData.push({name: name, code: code});
            // 搜索框
            punishTypeDataSearch.push({name: name, code: code});
        } 
    });
    var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3&hasBuilding=1",
            buildingQueryAllUrl = "${pageContext.request.contextPath}/app/buildingSubject/queryAll?blockId=";
    $(document).ready(function() {
        queryAreaTree(queryAreaTreeUrl,"#comCcBlock_punish","#showSelected_punishEvent",buildingQueryAllUrl,"#comCcBuilding_punish");

        //处罚方式
        $('#comCcPunishType').combobox({
            data: punishTypeData,
            valueField: 'code',
            textField: 'name'
        });
        punishTypeDataSearch.unshift({code: '0', name: '全部'});
        $('#punishType').combobox({
            data: punishTypeDataSearch,
            valueField: 'code',
            textField: 'name',
            value: '0',
            onLoadSuccess: function () { //数据加载完毕事件
		      		if(punishTypeName && punishTypeName.length > 0 && punishTypeName != 'null'){
		     				var selected = false;
		     				$.each(punishTypeDataSearch,function(i,v){
		     					if(v.name == punishTypeName){
		     						selected = true;
		     						$('#punishType').combobox('select',v.code);
		     						punishTypeCode = v.code;
		     						return ;
		     					}
		     				});
		     			}else{
		     				 $('#punishType').combobox('select', 0);
		     			}
	        }
        });
    	//表单窗口打开前禁用联动查询
    	$('#fm_punish').form({
    		onLoadSuccess : function(){
    			console.info("load sunness");
    			disableLinkage(false);
    		}
    	});
        if(monthStart && monthStart.length == 7){
           $('#punishStartMonth').datebox('setValue',monthStart);
        }
        if(monthEnd && monthEnd.length == 7){
            $('#punishEndMonth').datebox('setValue',monthEnd);
        }
        $('#dg_punish').datagrid({
            url:UrlConfig.listPage,
            queryParams:{
                name: $('#name').val(),
                placeName: $('#placeName').val(),
                streetId: showStreetId,   // 下拉框获取数据ID
                districtId: showDistrictId,
                punishType: punishTypeCode,
                blockId: showBlockId,
                punishStartMonth: monthStart=='null'?'':monthStart,
                punishEndMonth: monthEnd=='null'?'':monthEnd
            }
        });
    });

    //行政区
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
            newDistrictEle:'comCcDistrict_punish', 
            searchDistrictEle:'districtId',
            newStreetEle:'comCcStreet_punish',
            searchStreetEle:'streetId',
            newBlockEle:'comCcBlock_punish', 
            searchBlockEle:'blockId',
            newBuildingEle:'comCcBuilding_punish',
            showDistrictId:showDistrictId,
            showStreetId:showStreetId,
            showBlockId:showBlockId
    };

    initDistrict(params);
//    initBuilding(params,null);


    // 新增
    function newStreet() {
        queryAreaTree(queryAreaTreeUrl,"#comCcBlock_punish","#showSelected_punishEvent",buildingQueryAllUrl,"#comCcBuilding_punish");
        $('#dlgPunishEvent').dialog('open').dialog('setTitle', '新建 执法');
        $('#fm_punish').form('clear');
        url = UrlConfig.insertData;
    }

    // 编辑修改
    function editStreet() {
        var rowNum = $('#dg_punish').datagrid("getSelections").length;
        if (rowNum > 1 || rowNum == 0) {
            $.messager.alert("提示", "请选择一行修改！", "error");
        } else {
            var row = $('#dg_punish').datagrid('getSelected');
            if (row) {
             	//表单加载数据前关掉联动查询
            	disableLinkage(true);
                $('#dlgPunishEvent').dialog('open').dialog('setTitle', '编辑 执法');
                $('#fm_punish').form('load', row);
                url = UrlConfig.updateData + "?id=" + row.id;
            }
        }
    }

    // 保存
    function saveUser() {
        $('#fm_punish').form('submit', {
            url: url,
            onSubmit: function () {
                var t = $('#comCcBlock_punish').combotree('tree'); // 得到树对象
                var n = t.tree('getSelected'); // 得到选择的节点
                //这里经过实践测试应该使用t.tree('getChecked');
                if(queryAreaTreeUrl.indexOf('flag=3')!=-1 && n.text == "天河区") {
                    $.messager.alert("提示", "请下拉选择【"+n.text+"】下的社区数据！", "info");
                    return false;
                }

                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.successful) {
                    $.messager.alert("操作提示", result.data, "info");
                    $('#dlgPunishEvent').dialog('close');        // close the dialog
                    $('#dg_punish').datagrid('reload');    // reload the user data
                } else {
                    if (result.msg) {
                        $.messager.alert("操作提示", result.msg, "error");
                    } else {
                        $('#dlgPunishEvent').dialog('close');        // close the dialog
                        $('#dg_punish').datagrid('reload');    // reload the user data
                    }
                }
            }
        });
    }

    //批量删除
    function destroyStreets() {//返回选中多行
        var selRow = $('#dg_punish').datagrid('getSelections')
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
        	                		$('#dg_punish').datagrid('clearSelections');
        	                   	  	$.messager.alert("操作提示", result.data, "info");
        	                        $('#dg_punish').datagrid('reload');    // reload the user data
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
        var punishStartMonth = $('#punishStartMonth').datebox('getValue');
        var punishEndMonth = $('#punishEndMonth').datebox('getValue');
        $('#dg_punish').datagrid('load', {
            name: $('#name').val(),
            placeName: $('#placeName').val(),
            streetId: $('#streetId').combobox('getValue'),   // 下拉框获取数据ID
            districtId: $('#districtId').combobox('getValue'),
            punishType: $('#punishType').combobox('getValue'),
            blockId: $('#blockId').combobox('getValue'),
            punishStartMonth: punishStartMonth,
            punishEndMonth: punishEndMonth
        });
    }
    
    function doReset() {
//        $("#tblQuery").find("input").val("");
        $("#placeName").val("");
        $("#name").val("");
		params.showDistrictId = undefined; 
		params.showStreetId = undefined;
		params.showBlockId = undefined;
        $("#districtId").combobox('select', 0);
        // 有点小问题
        $("#streetId").combobox('select', 0);
        $("#blockId").combobox('select', 0);
        $('#punishType').combobox('select', 0);
        $('#punishStartMonth').datebox('setValue',undefined);
        $('#punishEndMonth').datebox('setValue',undefined);
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
                        "&punishType=" + $('#punishType').combobox('getValue')
                window.open(openUrl);
            }
        });
    }

    //导入
    function doImport() {
        $('#importPunishEvent').dialog('open').dialog('setTitle', '导入数据');
    }

    function uploadFile() {
        console.log("upload punishEvent");
        $('#fm_file_punish_event').form('submit', {
            url: UrlConfig.importStreet,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.successful) {
                    $.messager.alert("操作提示", result.data, "info");
                    $('#importPunishEvent').dialog('close');        // close the dialog
                    $('#dg_punish').datagrid('reload');    // reload the user data
                } else {
                    if (result.msg) {
                        $.messager.alert("操作提示", result.msg, "error");
                    } else {
                        $('#importPunishEvent').dialog('close');        // close the dialog
                        $('#dg_punish').datagrid('reload');    // reload the user data
                    }
                }
            }
        });
    }

    function doSumPunish() {
        var begin = $('#punishStartMonth').datebox('getValue');
        var end = $('#punishEndMonth').datebox('getValue');
        if( !begin || !end || end < begin ){
            $.messager.alert('错误', '请先选择正确的时间段！' , 'error');
            return ;
        }
        var url = '<%=request.getContextPath() %>/app/data/sumPunishEvent?begin='+begin+'&end='+end;
        $.get(url,function(data){
            if(data && data.successful){
                $.messager.alert('提示', '分析成功！' , 'ok');
            }else{
                $.messager.alert('错误', data.msg , 'error');
            }
        });
    }

    // 导出报表
    function doExportReport(value, name) {
        var districtId = $('#districtId').combobox('getValue');
        var occurStartMonth = $('#punishStartMonth').datebox('getValue');
        var occurEndMonth = $('#punishEndMonth').datebox('getValue');
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

</script>
</body>
</html>