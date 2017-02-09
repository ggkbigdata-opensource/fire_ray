<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测报告管理</title>
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
			<input id="blockId" class="easyui-combobox inputDiv-input" data-options="width:100">
		</div>
		<%--<span class="inputLabel">报告类型:</span>
        <input id="reportType" class="easyui-combobox inputDiv-input" data-options="editable:false,width:100">--%>
		<div class='inputDivEg'>
			<span class="inputLabel">开始月份:</span>
			<input id="checkStartMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkStartDate',editable:false" style="width:100px">
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">结束月份:</span>
			<input id="checkEndMonth" class="easyui-datebox easyui-validatebox" data-options="validType:'checkEndDate[\'#checkStartMonth\']',editable:false" style="width:100px">
		</div>
		<div class="height10"></div>
		<div class='inputDivEg'>
			<span class="inputLabel">报告名称:</span>
			<input id="name" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
		</div>
		<div class='inputDivEg'>
			<span class="inputLabel">报告编码:</span>
			<input id="code" class="easyui-textbox inputDiv-input" style="line-height:20px;"/>
		</div>
		<a href="#" class="resetBtn" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="doReset()">重置</a>
		<%--<a href="#" class="queryBtn" plain="true"  iconCls="icon-undo" onclick="editDef()">管理检测项</a>--%>
		<%--<a href="#" class="queryBtn" plain="true"  iconCls="icon-back" onclick="doExport()">导出</a>--%>
		<%--<a href="#" class="queryBtn" plain="true"  iconCls="icon-print" onclick="doImport()">导入</a>--%>
		<%--<a href="#" class="queryBtn" plain="true"  iconCls="icon-sum" onclick="doSumReport()">分析报告数据</a>--%>
	</div>
</div>
<div class="tableClass">
<table id="dg_check" title="数据列表" class="easyui-datagrid"
        url='<%=request.getContextPath() %>/app/checkReportInfo/query'
        		toolbar="#toolbar"
       			rownumbers="true"
        		fitColumns="true"
        		singleSelect="false"
                autoRowHeight = "false"
                pagination="true"
                pageSize="20"
                striped="true"
	   			loadFilter="loadFilter"
                checkOnSelect="true"
                selectOnCheck="true">
    <thead>
        <tr>
        	<th field="ck" checkbox="true"></th>
            <th field="name" width="50">项目名称</th>
			<th field="code" width="50">报告编码</th>
			<th field="projectCode" width="50">工程编码</th>
			<%--<th field="type" width="50">检测类型</th>--%>
			<th field="streetName" width="50">所属街道</th>
			<th field="blockName" width="50">社区名称</th>
			<th field="detectionTime" width="50">报告日期</th>
			<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper_report">操作</th>
        </tr>
    </thead>
          <input type="hidden" name="streetId" value="streetId" />
          <input type="hidden" name="blockId" value="blockId" />
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn"  iconCls="icon-add" plain="true" onclick="doImport()">新增</a>
    <a href="#" class="queryBtn"  iconCls="icon-edit" plain="true" onclick="previewReport()">预览</a>
    <a href="#" class="queryBtn"  iconCls="icon-remove" plain="true" onclick="deleteReport()">删除</a>
</div>
<%--管理检测项--%>
<div id ='check_item_def_window'></div>
<%--预览检测报告--%>
<div id ='preview_report_window'></div>

<div id="import_check_report" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
    closed="true">
<div class="ftitle">导入新增报告数据</div>
<form id="fm_file_check_report" method="post" enctype="multipart/form-data" target="ajaxUpload">
	<div style="margin-top:10px;">
		<div style="padding: 10px 0px">
			<span class="inputLabel">社区:</span>
			<input id="comCcBlock_newReport" class="easyui-combobox inputDiv-input" data-options="width:215">
		</div>
		<div style="padding: 10px 0px">
			<span class="inputLabel">您选择的是:</span><span id="showSelected_checkReport"></span>
		</div>
		<div style="float:left;">
			<input class="easyui-filebox" data-options="buttonText:'选择文件'" id="uploadFileInput" name="uploadFile" style="width:250px;margin:20px;">
		</div>
		<div style="float:right;">
			<a href="#" class="easyui-linkbutton" iconCls='icon-ok' onclick="uploadFile()">提交</a>
		</div>
	</div>
	<div style="padding-top: 40px">
		<span id="checkIng"></span>
	</div>
</form>

<iframe name="ajaxUpload" style="display:none"></iframe>

</div>

<div id="dlg_qrcode_report" class="easyui-dialog" title="发布报告" data-options="iconCls:'icon-save'" style="width:450px;height:350px;padding:10px" closed="true">
	<h3>可以直接把二维码链接复制到Email,或聊天工具中分享给他人</h3>
	<input  id="share_qrcode_report_url" readonly="readonly"  size="58"/>
	<a href="#" class="resetBtn"  iconCls="icon-add" plain="true" onclick="searchReport001()" style="float:right; margin-top: 20px;">查看风险评估报告</a>
	<img id="img_report_qrcode" src="#"/>
</div>
<script type="text/javascript">
var dictListCheckReport;
var reportTypeData =  new Array(),
reportTypeDataSearch = new Array();
// 获取数据字典数据
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getAllDictList",
    async: false,      //ajax同步
    type:"get",
    success: function(data){
        if(data.successful){
            dictListCheckReport = data.data.rows;
            $.each(dictListCheckReport,function(n,value) {
                var name = value.name,
                    code = value.code;
                // 检测类型：初检/复检：report_type
                if ("report_type" == value.typeCode) {
                    reportTypeData.push({name: name, code: code});
                    // 搜索框
                    reportTypeDataSearch.push({name: name, code: code});
                }

            });
        }
    }
});

var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
$(document).ready(function() {
	queryAreaTree(queryAreaTreeUrl,"#comCcBlock_newReport","#showSelected_checkReport");
    // 检测类型
  	$('#comCcReportType').combobox({
		data: reportTypeData,
		valueField:'code',
		textField:'name'
	});
    reportTypeDataSearch.unshift({code: '0', name: '全部'});
    $('#reportType').combobox({
        data : reportTypeDataSearch,
        valueField:'code',
        textField:'name',
        value:0
    });
});

var UrlConfig = {
		deleteDataByIds: '<%=request.getContextPath() %>/app/checkReportInfo/deleteReport',
		importStreet: '<%=request.getContextPath() %>/app/checkReportInfo/uploadReportFile?blockId=',
        checkProgress:'<%=request.getContextPath() %>/app/checkReportInfo/progress',
};

var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";
//建筑
var buildingUrl = "${pageContext.request.contextPath}/app/building/queryPage";
var params = {
		districtUrl : districtUrl,
		streetUrl:streetUrl,
		blockUrl:blockUrl,
		buildingUrl:buildingUrl,
		newDistrictEle:'comCcDistrict_checkReport',
		searchDistrictEle:'districtId',
		searchStreetEle:'streetId',
		searchBlockEle:'blockId',
};

function formatOper_report(val,row,index){
	return '<a href="#" class="tableBtn" onclick="publishReport('+index+')">发布报告</a>'+
			'<a href="#" class="tableBtn" onclick="uploadReportDemoPdf('+index+')">下载报告</a>';
}

function searchReport001() {
	var share_qrcode_report_url = $("#share_qrcode_report_url").val();
	console.log("share_qrcode_report_url = "+ share_qrcode_report_url);
//	location.href = share_qrcode_report_url;
	window.open(share_qrcode_report_url);
}


function uploadReportDemoPdf(index) {
	$('#dg_check').datagrid('selectRow',index);// 关键在这里
	var row = $('#dg_check').datagrid('getSelected');
	if (row){
		location.href = "<%=request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()%>"+"/resources/fire_report_demo_V2.pdf";
	}
}

function publishReport(index){
	$('#dg_check').datagrid('selectRow',index);// 关键在这里
	var row = $('#dg_check').datagrid('getSelected');
	if (row){
		$('#dlg_qrcode_report').dialog('open');
		$("#share_qrcode_report_url").attr("value" ,"http://"+window.location.host+ "<%=request.getContextPath() %>/view/check/front/platform.jsp?id="+
				row.id+"&code="+row.code+"&name="+encodeURI(row.name)+"&detectionTime="+encodeURI(row.detectionTime));
		$("#img_report_qrcode").attr("src", "<%=request.getContextPath() %>/app/checkReport/createQRCode?id="+row.id);
	}
}



initDistrict(params);

//初始化月份选择
initDateMonthBox('checkStartMonth');
initDateMonthBox('checkEndMonth');

//批量删除
function deleteReport(){//返回选中多行
    var selRow = $('#dg_check').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else {
        var deleteIds = [];
        $.each(selRow, function (index, item) {
            deleteIds.push(item.id);
        });
		$.messager.confirm('提示', '是否删除选中数据?', function (r) {
			if (!r) {
				return;
			}else{
				$.ajax(UrlConfig.deleteDataByIds, {
                    contentType:'application/json;charset=UTF-8',
					type:'POST',
					data: JSON.stringify(deleteIds),
                    dataType:'JSON',
                    success:function(result) {
                        if (result.successful) {
                            $('#dg_check').datagrid('clearSelections');
                            $.messager.alert("操作提示", result.data, "info");
                            $('#dg_check').datagrid('reload');    // reload the user data
                        } else {
                            if (result.msg) {
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
	var checkStartMonth = $('#checkStartMonth').combobox('getValue');
	var checkEndMonth = $('#checkEndMonth').combobox('getValue');
	$('#dg_check').datagrid('load',{
		name: $('#name').val(),
		streetId:$('#streetId').combobox('getValue'),   // 下拉框获取数据ID
		blockId:$('#blockId').combobox('getValue'),
		districtId: $('#districtId').combobox('getValue'),
		code: $('#code').val(),
		checkStartMonth:checkStartMonth,
		checkEndMonth:checkEndMonth
	});
}

//检测结果列表刷新
function doSearchResult(){
	$('#dg_check_result').datagrid('load',{
		reportId:$('#reportId').val(),
		sortCode:$('#sortCodeInput').combobox('getValue'),
		typeCode:$('#typeCodeInput').combobox('getValue'),
		name:$('#resultName').val()
	});
}

function doSearchReset() {
	$('#sortCodeInput').combobox('select',0);
	$('#resultName').val("");
	doSearchResult();
}

function doReset(){
//	  $("#tblQuery").find("input").val("");
		params.showDistrictId = undefined;
		params.showStreetId = undefined;
		params.showBlockId = undefined;
	    $("#districtId").combobox('select', 0);
        $('#reportType').combobox('select', 0);
	    doSearch();
}
//// 导出
//function doExport(value,name){
//	var checkStartMonth = $('#checkStartMonth').combobox('getValue');
//	var checkEndMonth = $('#checkEndMonth').combobox('getValue');
//	$.messager.confirm('提示', '是否导出查询出来数据?', function (r) {
//        if (!r) {
//            return;
//        }else{
//        	var exportName = $('#name').val();
//        	var openUrl = UrlConfig.exportLibStreet+
//        	"?name="+encodeURI(exportName)+
//        	"&streetId="+$('#streetId').combobox('getValue')+
//        	"&blockId="+$('#blockId').combobox('getValue')+
//           	"&districtId="+$('#districtId').combobox('getValue')+
//           	"&reportType="+$('#reportType').combobox('getValue')+
//           	"&checkStartMonth="+checkStartMonth+
//           	"&checkEndMonth="+checkEndMonth;
//        	window.open(openUrl);
//        }
//    });
//}

//导入
function doImport(){
    //fm_file_check_report
    $('#import_check_report').dialog({
        title:'导入数据',
        onClose:function(){
            $('#fm_file_check_report').form('clear');
            $('#showSelected_checkReport').html("");
            $('#checkIng').html("");
            queryAreaTree(queryAreaTreeUrl,"#comCcBlock_newReport","#showSelected_checkReport");
		}
	}).dialog('open');
}

//管理检测项
<%--function editDef(){--%>
	<%--$('#check_item_def_window').window({--%>
		<%--width:1050,--%>
		<%--height:500,--%>
		<%--collapsible:false,--%>
		<%--minimizable:false,--%>
		<%--title:"管理检测项",--%>
		<%--href:"<%=request.getContextPath() %>/app/page/checkItemDef.jsp"--%>
	<%--});--%>
<%--}--%>

//预览检测报告
function previewReport(reportId) {
	if(!reportId || reportId.length == 0){
        var rowNum = $('#dg_check').datagrid("getSelections").length;
        if(rowNum > 1 || rowNum == 0){
            $.messager.alert("提示","请选择一行预览！","error");
            return ;
		}
		reportId = $('#dg_check').datagrid('getSelected').id;
	}
	$('#preview_report_window').window({
		width:1050,
		height:550,
        maximized:true,
		collapsible:false,
		minimizable:false,
		title:"预览检测报告",
		href:"<%=request.getContextPath() %>/app/page/previewReport.jsp?reportId=" + reportId
	});
}


function uploadFile(){
    var text = $('#showSelected_checkReport').text();
    if(!text || text.length == 0){
        $.messager.alert("错误", "先选择地址！", "error");
        return ;
	}
	$('#fm_file_check_report').form('submit',{
        url: UrlConfig.importStreet + $('#comCcBlock_newReport').combobox("getValue"),
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if(result.successful){
	          	$.messager.alert("操作提示", result.msg, "info");
				$('#checkIng').html("处理中");
                checkProgress(result.data);
	          }else {
                if (result.msg) {
                    $.messager.alert("操作提示", result.msg , "error");
                } else {
                    $('#import_check_report').dialog('close');        // close the dialog
                    $('#dg_check').datagrid('reload');    // reload the user data
                }
            }
        }
    });
}

var checkProgress = function(key){
	var span = $('#checkIng');
    setTimeout(function(){
        console.info("checkProgress -->");
        $.getJSON(UrlConfig.checkProgress+"?fileName="+key,function(data){
            if(data){
                if(data.successful){
                    $.messager.alert("处理成功", "处理成功！", "info");
					span.html("完成")
                    doReset();
                }else if(!data.msg){
                    checkProgress(key);
					span.html(span.html() + ".")
                }else{
                    span.html("处理失败!");
                    $.messager.alert("错误", data.msg, "error");
				}
            }else {
                $.messager.alert("错误", "处理错误", "error");
            }
        })
    },1000);
};

$('#dg_check').datagrid({
	loadFilter: function(data){
		return {
			rows:data.data,
			total:data.total
		}
	}
});
</script>
</body>
</html>