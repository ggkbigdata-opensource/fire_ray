<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>警情详情统计</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/highcharts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/exporting.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/export-csv.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/fireEventSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/punishEventSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/checkReportSumColumn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataLine.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataPie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
</head>
<body>
<div id="tblQuery" style="padding:3px">
    <div>
        <span style="font-size:12px">行政区:</span>
        <input id="districtId" class="easyui-combobox"
               data-options="editable:false,width:100">
        <span style="font-size:12px">街道:</span>
        <input id="streetId" class="easyui-combobox"
               data-options="editable:false,width:100">
        <span style="font-size:12px">社区:</span>
        <input id="blockId" class="easyui-combobox"
               data-options="editable:false,width:120">
        <span style="font-size:12px">火灾类型:</span>
        <input id="fireType" class="easyui-combobox"
               data-options="editable:false,width:120">
        <%--<span style="font-size:12px">年份:</span>--%>
        <%--<input id="year" class="easyui-combobox"--%>
               <%--data-options="editable:false,width:120">--%>
        <span style="font-size:12px">开始月份:</span>
        <input id="fireEventDetailPieStartMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkStartDate',editable:false"
               style="width:100px">
        <span style="font-size:12px">结束月份:</span>
        <input id="fireEventDetailPieEndMonth" class="easyui-datebox easyui-validatebox"
               data-options="validType:'checkEndDate[\'#fireEventDetailPieStartMonth\']',editable:false"
               style="width:100px">
    </div>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="doReset()">重置</a>
    </div>
</div>
<div style=" width: 1200px; margin: 0 auto; overflow: hidden;">
<div id="containerFireReasonTypePie" style="width: 550px; height: 40%;float:left;"></div>
<div id="containerFireBuildingClassPie" style="width: 550px; height: 40%;float:left;"></div>
</div>
<script type="text/javascript">
//    初始化月份条件选择
initDateMonthBox('fireEventDetailPieStartMonth');
initDateMonthBox('fireEventDetailPieEndMonth');
// 行政区
var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll",
// 街道
streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll",
// 社区
blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll",

fireTypeName = '<%=request.getParameter("fireTypeName")%>',
monthStart = '<%=request.getParameter("monthStart")%>',
monthEnd = '<%=request.getParameter("monthEnd")%>',
// 区域信息
showDistrictId = '<%=request.getParameter("districtId")%>',
showStreetId = '<%=request.getParameter("streetId")%>',
showBlockId = '<%=request.getParameter("blockId")%>';
$(function(){
    if(monthStart && monthStart.length==7){
        $('#fireEventDetailPieStartMonth').datebox('setValue',monthStart);
    }
    if(monthEnd && monthEnd.length==7){
        $('#fireEventDetailPieEndMonth').datebox('setValue',monthEnd);
    }
});
var params = {
        districtUrl : districtUrl, 
        streetUrl:streetUrl,
        blockUrl:blockUrl,
        searchDistrictEle:'districtId',
        searchStreetEle:'streetId',
        searchBlockEle:'blockId',
        showDistrictId:showDistrictId,
        showStreetId:showStreetId,
        showBlockId:showBlockId
};

initDistrict(params);

var fireType;
if(fireTypeName && fireTypeName == "原始火灾警情"){
	fireType = "original";
	}else if(fireTypeName == "确认火灾警情"){
		fireType = "confirm";
	}else if(fireTypeName == "冒烟火灾警情"){
		fireType = "smoke";
}

var dictListFirePie,
fireTypeDataSearch = new Array();
// 获取数据字典数据
$.ajax({
    url:"${pageContext.request.contextPath}/app/config/getAllDictList",
    async: false,      //ajax同步  
    type:"get",
    success: function(data){
        if(data.successful){
        	dictListFirePie = data.data.rows;
        }
    }
});
$.each(dictListFirePie,function(n,value) {   
    var name = value.name,
    code = value.code;
    //火灾类型：fire_type
    if ("fire_type" == value.typeCode) {
    	fireTypeDataSearch.push({name: name, code: code});
    } 
});  
$(document).ready(function() { 
	fireTypeDataSearch.unshift({code: '0', name: '全部'});
    $('#fireType').combobox({
        data : fireTypeDataSearch,
        valueField:'code',
        textField:'name',
        value:0,
        onLoadSuccess: function () { //数据加载完毕事件
        	// 从柱状图点击过来的数据
    			if(fireType && fireType != 'null'){
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
    },
    });
    if (monthStart && monthStart.length == 7) {
        $('#fireEventDetailPieStartMonth').datebox('setValue', monthStart);
    }
    if (monthEnd && monthEnd.length == 7) {
        $('#fireEventDetailPieEndMonth').datebox('setValue', monthEnd);
    }
    showPieCharts(showDistrictId,showStreetId,showBlockId,fireType,monthStart,monthEnd);
});

    /*var yaerDatas = [{ "value":2017, "name":"2017"},{ "value":2016, "name":"2016"},{ "value":2015, "name":"2015" },{ "value":2014, "name":"2014" }];
    yaerDatas.unshift({value: '0', name: '全部'});
    $('#year').combobox({
            data: yaerDatas,
            valueField: 'value',
            textField: 'name',
            value:0,
            onLoadSuccess: function () { //数据加载完毕事件
            // 从柱状图点击过来的数据
            if(year && year != 'null'){
              var selected = false;
              $.each(yaerDatas,function(i,v){
                if(v.name == year){
                  selected = true;
                  $('#year').combobox('select',v.value);
                  return ;
                }
              });
          }else{
              $('#year').combobox('select', 0);
          }
      },
    });*/

    function doSearch(value, name) {
        var fireEventDetailPieStartMonth = $('#fireEventDetailPieStartMonth').datebox('getValue');
        var fireEventDetailPieEndMonth = $('#fireEventDetailPieEndMonth').datebox('getValue');
        var districtId = $('#districtId').combobox('getValue'),
                streetId = $('#streetId').combobox('getValue'),
                blockId = $('#blockId').combobox('getValue'),
                fireType = $('#fireType').combobox('getValue');
//                year = $('#year').combobox('getValue');

        // 搜索
        showPieCharts(districtId, streetId, blockId, fireType, monthStart,monthEnd);
    }

function doReset() {
//    $("#tblQuery").find("input").val("");
	params.showDistrictId = undefined;
	params.showStreetId = undefined;
	params.showBlockId = undefined;
    $("#districtId").combobox('select', 0);
  	$("#streetId").combobox('select', 0);
  	$("#blockId").combobox('select', 0);
 	  $("#fireType").combobox('select', 0);
    $('#fireEventDetailPieEndMonth').datebox('setValue','');
    $('#fireEventDetailPieStartMonth').datebox('setValue','');
    doSearch();
}


function showPieCharts(_districtId,_streetId,_blockId,_fireType,monthStart,monthEnd){
	var areaType,
	areaId;
	if(_blockId != "null" && _blockId != 0 && _blockId != "undefined"){
		areaType = 2;
		areaId = _blockId;
	}else if(_streetId != "null" && _streetId != 0 && _streetId != "undefined"){
		areaType = 1;
		areaId = _streetId;
	}else if(_districtId != "null" && _districtId != 0 && _districtId != "undefined"){
		areaType = 0;
		areaId = _districtId;
	}else{
		areaType = 0;
		areaId = 1;
	}
	// 起火原因
	var fireReasonTypePieData;
	var getFireReasonTypePercentUrl = "${pageContext.request.contextPath}/app/statis/getFireReasonTypePercent?areaType="+
				areaType+"&areaId="+areaId+"&fireType="+_fireType+"&monthBegin="+monthStart+"&monthEnd="+monthEnd;
	$.ajax({
		url:getFireReasonTypePercentUrl,
		async: false,      //ajax同步  
		type:"get",
		success: function(data){
				var obj=eval(data);
				if(obj.successful){
					fireReasonTypePieData = obj.data;
				}
		}
	});
	// 起火原因分类
    var plotOptionsFireReasonType = {
   		 pie: {
               allowPointSelect: true,
               cursor: 'pointer',
               events: {
            	   click: function(e) { 
            			console.log("起火原因分类编码："+e.point.code);
            			// 跳转页面：起火原因列表
	        			window.parent.addTab('消防火情列表','app/page/fireEvent?areaType='+areaType+"&areaId="+areaId+"&fireType="+_fireType+"&monthStart="+monthStart+
	        					"&monthEnd="+monthEnd+"&fireReasonType="+e.point.code+"&districtId="+_districtId+"&streetId="+_streetId+"&blockId="+_blockId);
            	   }
            	},
            	  dataLabels: {
                      enabled: true,
                      format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                      style: {
                          color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                      }
                  }
         }
    };
    // 起火场所 管理建筑的场所类别
    var plotOptionsFirePlaceType = {
      		 pie: {
                  allowPointSelect: true,
                  cursor: 'pointer',
                  events: {
               	   click: function(e) { 
   	        			window.parent.addTab('消防火情列表','app/page/fireEvent?areaType='+areaType+"&areaId="+areaId+"&fireType="+_fireType+"&monthStart="+monthStart+
                                "&monthEnd="+monthEnd+"&placeUseType="+e.point.code+"&districtId="+_districtId+"&streetId="+_streetId+"&blockId="+_blockId);
               	   }
               	},
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
       };
    
    //使用性质
	var buildingClassPieData;
    var getFirePlaceTypePercentUrl = "${pageContext.request.contextPath}/app/statis/getPlaceUseTypePercent?areaType="+areaType+"&areaId="+areaId
    				+"&fireType="+_fireType+"&monthStart="+monthStart+"&monthEnd="+monthEnd;
	$.ajax({
		url:getFirePlaceTypePercentUrl,
		async: false,      //ajax同步  
		type:"get",
		success: function(data){
				var obj=eval(data);
				if(obj.successful){
					buildingClassPieData = obj.data;
				}
		}
	});
	if(buildingClassPieData.length != 0 || fireReasonTypePieData.length != 0){
		// 展示图表
		showDataPieCharts("containerFireReasonTypePie",fireTypeName+"警情起火原因分析",fireReasonTypePieData,plotOptionsFireReasonType);
		// 展示图表
		showDataPieCharts("containerFireBuildingClassPie",fireTypeName+"警情使用性质分析",buildingClassPieData,plotOptionsFirePlaceType);
	}else{
		  $.messager.alert("操作提示", "暂无数据", "info");
	}
}
</script>
</body>
</html>