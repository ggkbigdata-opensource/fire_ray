<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>预览检测报告</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/baseData.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">


</head>
<body>

<div id="report_tab" class="easyui-tabs" style="width:100%;height: 100%;">
    <div title="主要信息" style="padding: 10px;">
        <form id="fm_report_info_tab1" method="post" novalidate>
            <div style="margin-top: 10px;margin-left:10px;">
            </div>
            <div class="fitem_building">
                <label>行政区:</label>
                <input name="districtName" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>街道:</label>
                <input name="streetName" class="easyui-textbox reportInputClass"  readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>社区:</label>
                <input name="blockName" class="easyui-textbox reportInputClass"  readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>报告编号:</label>
                <input name="code" class="easyui-textbox reportInputClass"  readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>报告类型:</label>
                <input name="type" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>工程编号:</label>
                <input name="projectCode" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>项目名称:</label>
                <input name="name" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>项目地址:</label>
                <input name="address" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>委托单位:</label>
                <input name="delegate" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测单位:</label>
                <input name="detectionUnit" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测单位地址:</label>
                <input name="duAddress" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测单位电话:</label>
                <input name="duTel" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测单位传真:</label>
                <input name="duFax" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测单位邮编:</label>
                <input name="duZipCode" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>检测时间:</label>
                <input name="detectionTime" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem_building">
                <label>报告创建时间:</label>
                <input name="createTime" class="easyui-textbox reportInputClass" readonly="true"/>
            </div>
            <div class="fitem" >
                <label class="area" style="float: left;">检测结论说明:</label>
                <input class="easyui-textbox" data-options="multiline:true" name="resultDesc" readonly="true" style="width:700px;height:200px">
            </div>
        </form>
    </div>
    <div title="检测情况统计表" style="padding: 10px;">
        <table id="check_report_item_result_statis"></table>
    </div>
    <div title="单项评定结果" style="padding: 10px;">
        <table id="check_report_item_result"></table>
    </div>
    <div title="不符合规范要求项目" style="padding: 10px;">
        <table id="check_report_item_unqualified"></table>
    </div>
    <div title="消防设备登记表" id="check_report_equipment" style="padding: 10px;">
        <%--<table id="check_report_equipment"></table>--%>
    </div>
</div>
<style type="text/css">
    .windownTab .panel-header {
        background: url("../../images/partition.png") 0 13px no-repeat;
        padding-left: 20px;
        border:none !important;
        border-bottom: 1px dotted #b9cbe4 !important;
        margin-bottom: 10px;
        padding-bottom: 10px;
        width: auto !important;
    }
</style>
<script type="text/javascript">
    var initedTabs = [];
    var tab2Merge = [];
    var reportId = '<%=request.getParameter("reportId")%>';
    var previewUrlConfig = {
        queryReportBean: '<%=request.getContextPath() %>/app/checkReportInfo/getBean',
        queryItemResultBean: '<%=request.getContextPath() %>/app/checkItemResult/getResultBean',
        queryStatisBean: '<%=request.getContextPath() %>/app/checkItemResultStatis/getResultBean',
        queryBeanInfoById: '<%=request.getContextPath() %>/app/checkReportInfo/getBeanInfo?reportId=',
        queryUnqualifiedBean:'<%=request.getContextPath() %>/app/checkItemUnqualified/getUnqualifiedBean',
        queryEquipmentBean:'<%=request.getContextPath() %>/app/equipmentEnrolment/getBeans',
        queryEquipmentBean2:'<%=request.getContextPath() %>/app/equipmentEnrolment/getBeans2',
    };

    //初始化数据
    var loadFilter = function(data){
        console.info("data",data);
        if(!data || !data.successful){
            return {
                total:0,
                rows:[]
            }
        }else{
            return {
                rows:data.data,
                total:data.total
            }
        }
    };

    //初始化数据 合并行
    var loadFilterAndMerge = function(data){
        //清空合并列表
        tab2Merge = [];
        if(!data || !data.successful){
            return {
                total:0,
                rows:[]
            }
        }else{
            //记录行数
            var rowNum = 0;
            //实际返回给表格控件的data
            var rows = [];
            $.each(data.data,function(index,item){
                //开始合并单元格的行数
                var startRow;
                //子集对象。现有两个表需要合并。item.statisList:统计表子集,item.unqualifiedList:不合格表子集
                var list = item.statisList?item.statisList:item.unqualifiedList;
                //如果行数大于2，需要合并，记录开始合并行数
                if(list && list.length>=2){
                    startRow = rowNum;
                }
                $.each(list,function(row,i){
                    //平铺数据
                    rows.push({
                        code:item.code,
                        name:item.name,
                        standard:item.standard,
                        level:i.level?i.level:item.level,
                        checkNum:i.checkNum,
                        unqualifiedNum:i.unqualifiedNum,
                        checkCode:i.code,
                        position:i.position
                    });
                    rowNum = rowNum + 1 ;
                });
                //保存合并单元格option
                if(startRow != undefined){
                    var merge = {
                        index: startRow,
                        rowspan: list.length
                    };
                    tab2Merge.push(merge);
                }
            });
            return {
                rows:rows,
                total:data.total
            }
        }
    };

    //合并表格单元格 ， 表格ID，需要合并的字段，合并单元格的option
    var merge = function(id,fields,item){
        $.each(fields,function(i,field){
            $(id).datagrid('mergeCells',{
                index: item.index,
                field: field,
                rowspan: item.rowspan,
                colspan:item.colspan
            });
        });
    };

    //检测情况统计表 表格
    var  initResultItemStatisTab = function () {
        $('#check_report_item_result_statis').datagrid({
            url: previewUrlConfig.queryStatisBean,
            method:'get',
            queryParams: {reportId: reportId},
            loadFilter: loadFilterAndMerge,
            fitColumns:true,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:10,
            striped:true,
            checkOnSelect:false,
            selectOnCheck:false,
            nowrap:false,
            columns:[[
                {title:'系统编号', field:'code',width:10},
                {title:'单项名称', field:'name' ,width:60},
                {title:'重要等级', field: 'level',width:10},
                {title:'检测点数', field: 'checkNum',width:10},
                {title:'不合格点数', field: 'unqualifiedNum',width:10}

            ]],
            onLoadSuccess:function(data){
                $.each(tab2Merge,function(index,item){
                    merge('#check_report_item_result_statis',['code','name'],item);
                });
            }
        });
    };

    //单项评定结果 表格
    var initResultItemTab = function () {
        $('#check_report_item_result').datagrid({
            url: previewUrlConfig.queryItemResultBean,
            method:'get',
            queryParams: {reportId: reportId},
            loadFilter: loadFilter,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:10,
            striped:true,
            checkOnSelect:false,
            selectOnCheck:false,
            nowrap:false,
            columns:[[
                {title:'项目编号', field:'code'},
                {title:'检测项', field:'name'},
                {title:'重要等级', field:'level'},
                {title:'检测标准', field:'standard'},
                {title:'检测点数', field:'checkNum'},
                {title:'不合格点数', field:'unqualifiedNum'}
            ]]
        });
    };

    //不符合规范要求项目 表格
    var initUnqualifiedTab = function(){
        $('#check_report_item_unqualified').datagrid({
            url: previewUrlConfig.queryUnqualifiedBean,
            method:'get',
            queryParams: {reportId: reportId},
            loadFilter: loadFilterAndMerge,
            singleSelect:true,
            fitColumns:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:10,
            striped:true,
            checkOnSelect:false,
            selectOnCheck:false,
            nowrap:false,
            columns:[[
                {title:'系统编号', field:'code',rowspan:2,width:8},
                {title:'单项名称', field:'name',rowspan:2,width:20},
                {title:'重要等级', field:'level',rowspan:2,width:8},
                {title:'规范要求', field:'standard',rowspan:2,width:40},
                {title:'不合格项',align:'center',colspan:2,width:30},
            ],[
                {title:'检测点编码', field: 'checkCode',width:10,align:'center'},
                {title:'检测点信息', field: 'position',width:20,align:'center'}

            ]],
            onLoadSuccess:function(data){
                $.each(tab2Merge,function(index,item){
                    merge('#check_report_item_unqualified',['code','name','level','standard'],item);
                });
            }
        });
    };

    var loadEquipmentDataFilter = function (item) {
        var data = [];
        $.each(item.equipmentList,function(index,listItem){
            data.push({info:listItem.info})
        });
        return {
            rows:data,
            total:item.equipmentList?item.equipmentList.length:0
        }
    };

    //消防设备登记 表格
    var initEquipmentTab = function(){
//        $('#check_report_equipment').datagrid({
//            url: previewUrlConfig.queryEquipmentBean,
//            method:'get',
//            queryParams: {reportId: reportId},
//            loadFilter: loadFilter,
//            singleSelect:true,
//            fitColumns:true,
//            autoRowHeight:true,
//            pagination:true,
//            pageSize:10,
//            striped:true,
//            checkOnSelect:false,
//            selectOnCheck:false,
//            columns:[[
//                {title:'编号', field:'typeCode'},
//                {title:'分类名称', field:'typeName'},
//                {title:'信息', field:'info'},
//            ]],
//        });
        $.getJSON(previewUrlConfig.queryEquipmentBean2,{reportId:reportId},function(data){
            console.info("data",data);
            if(data.successful){
                var id_ = 'equipment_type_';
                $.each(data.data,function(index,item){
                    var id = id_ + item.code;
                    var html = '<div class="windownTab"><table id="'+ id +'"></table></div>';
                    $('#check_report_equipment').append(html);
                    $('#'+id).datagrid({
                        title: item.code + " " + item.name,
                        fitColumns:true,
                        singleSelect: true,
                        autoRowHeight:false,
                        fitColumns:true,
                        rownumbers: true,
                        collapsible:true,
                        columns:[[
                            {title:'信息', field:'info',width:100}
                        ]]
                    });
                    $('#'+id).datagrid('loadData',loadEquipmentDataFilter(item));
                });
            }
        });
    };


    var tabInitFunc = [initResultItemStatisTab,initResultItemTab,initUnqualifiedTab,initEquipmentTab];
    $('#report_tab').tabs({
        onSelect:function(title,index){
            if(index == 0){
                $.ajax({
                    url:previewUrlConfig.queryBeanInfoById+reportId,
                    async: false,      //ajax同步
                    type:"get",
                    success: function(result){
                        $('#fm_report_info_tab1').form('load', result);
                    }
                });
            }else{
                if(initedTabs[index]==undefined){
                    tabInitFunc[index-1]();
                    initedTabs[index] = true;
                }
            }
        }
    });



</script>
</body>
</html>