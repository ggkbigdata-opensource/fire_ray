<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>建筑管理</title>
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript"
    src="<%=request.getContextPath()%>/js/common/loading.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/style/main.css">
<script type="text/javascript"
    src="<%=request.getContextPath()%>/js/main/baseData.js"></script>
</head>
<body>
    <div id="tblQuery" style="padding: 3px">
        <div>
            <span style="font-size: 12px">行政区:</span> <input id="districtId"
                class="easyui-combobox" data-options="width:150"> <span
                style="font-size: 12px">街道:</span> <input id="streetId"
                class="easyui-combobox" data-options="width:150"> <span
                style="font-size: 12px">社区:</span> <input id="blockId"
                class="easyui-combobox" data-options="width:150"> <span
                style="font-size: 12px">建筑类别:</span> <input id="baseBuildingClass"
                class="easyui-combobox" data-options="editable:false,width:150">
        </div>
        <div style="margin-top: 10px">
            <div class='inputDivEg'>
                <span style="font-size: 12px">建筑级别:</span> <input id="baseLevel"
                    class="easyui-combobox" data-options="editable:false,width:150">
            </div>
            <div class='inputDivEg'>
                <span style="font-size: 12px">建筑名称:</span> <input id="baseName"
                    class="easyui-textbox" style="line-height: 20px;" />
            </div>
            <a href="#" class="easyui-linkbutton" plain="true"
                iconCls="icon-search" onclick="doSearch()">查询</a> <a href="#"
                class="easyui-linkbutton" plain="true" iconCls="icon-undo"
                onclick="doReset()">重置</a> <a href="#" class="easyui-linkbutton"
                plain="true" iconCls="icon-back" onclick="doExport()">导出</a> <a
                href="#" class="easyui-linkbutton" plain="true" iconCls="icon-print"
                onclick="doImport()">导入</a>
        </div>
    </div>
    <table id="dg_building" title="数据列表" class="easyui-datagrid"
        style="width: auto; height: auto;"
        url='<%=request.getContextPath()%>/app/building/queryPage'
        toolbar="#toolbar" rownumbers="true" fitColumns="true"
        singleSelect="false" autoRowHeight="false" pagination="true"
        striped="true" pageSize="20" checkOnSelect="true" selectOnCheck="true">
        <thead>
            <tr>
                <th field="ck" checkbox="true"></th>
                <th field="baseName" width="50">建筑物名称</th>
                <th field="baseCode" width="50">建筑编码</th>
                <th field="buildingClassName" width="50">建筑类别</th>
                <th field="baseLevelName" width="50">级别</th>
                <th field="baseAddress" width="50">地点</th>
                <th field="districtName" width="50">行政区</th>
                <th field="streetName" width="50">所属街道</th>
                <th field="blockName" width="50">社区名称</th>
                <th field="finishTime" width="50">竣工时间</th>
            </tr>
        </thead>
        <input type="hidden" name="streetId" value="streetId" />
    </table>

    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="newStreet()">新增</a> <a href="#" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true" onclick="editStreet()">修改</a> <a
            href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="destroyStreets()">删除</a>
    </div>

    <!--To create or edit a user, we use the same dialog.  -->

    <div id="dlgBuilding" class="easyui-dialog"
        style="width: 750px; height: 560px; padding: 10px 20px" closed="true"
        buttons="#dlg-buttons">
        <div class="ftitle">建筑主体信息</div>
        <form id="fm_building" method="post" novalidate>
            <%--<div>--%>
            <%--<span  style="color: red;font-size:16px;text-align:center;">温馨提示：请依此选择：行政区-->街道-->社区</span>--%>
            <%--</div>--%>
            <%--<br/>--%>
            <%--<div class="fitem_building">--%>
            <%--<label>行政区:</label>--%>
            <%--<input id="comCcDistrict_building"  name = "districtId"  class="easyui-combobox" --%>
            <%--data-options="required:true,editable:false,width:173" /> --%>
            <%--</div>--%>
            <%--<div class="fitem_building">--%>
            <%--<label>街道:</label>--%>
            <%--<input id="comCcStreet_building"  name = "streetId"  class="easyui-combobox" --%>
            <%--data-options="required:true,editable:false,width:173" /> --%>
            <%--</div>--%>
            <div class="fitem_building">
                <label>社区:</label> <input class="easyui-combotree"
                    id="comCcBlock_building" name="blockId"
                    style="width: 173px; height: 26px" required="true">
            </div>
            <div class="fitem_building">
                <label>您选择的是:</label> <span id="showSelected_building"
                    style="color: #FF3030; width: 200px;"></span>
            </div>
            <div class="fitem_building">
                <label>建筑名称:</label> <input name="baseName" class="easyui-textbox"
                    required="true" />
            </div>
            <div class="fitem_building">
                <label>建筑类别:</label> <input id="comCcBaseBuildingClass"
                    name="baseBuildingClass" class="easyui-combobox"
                    data-options="required:true,editable:false,width:173" />
            </div>
            <div class="fitem_building">
                <label>级别:</label> <input id="comCcBaseLevel" name="baseLevel"
                    class="easyui-combobox"
                    data-options="required:true,editable:false,width:173" />
            </div>
            <div class="fitem_building">
                <label>竣工时间:</label> <input class="easyui-datetimebox"
                    name="finishTimeString"
                    data-options="required:true,showSeconds:false" value="3/4/2010 2:3"
                    style="width: 173px">
            </div>
            <div class="fitem_building">
                <label>经度:</label> <input name="longitude" class="easyui-textbox">
            </div>
            <div class="fitem_building">
                <label>纬度:</label> <input name="latitude" class="easyui-textbox">
            </div>
            <div class="fitem_building">
                <label>消防负责人:</label> <input name="fireManager"
                    class="easyui-textbox">
            </div>
            <div class="fitem_building">
                <label>消防负责人电话:</label> <input name="fireManagerPhone"
                    class="easyui-textbox" data-options="validType:'length[1,11]'"
                    prompt="请输入11位数字">
            </div>
            <div class="fitem_building">
                <label>消防联系人:</label> <input name="fireContact"
                    class="easyui-textbox">
            </div>
            <div class="fitem_building">
                <label>消防联系人电话:</label> <input name="fireContactPhone"
                    class="easyui-textbox" data-options="validType:'length[1,11]'"
                    prompt="请输入11位数字">
            </div>
            <div class="fitem_building">
                <label>工程类别:</label> <input id="comCcConType" name="conType"
                    class="easyui-combobox"
                    data-options="required:true,editable:false,width:173" />
            </div>
            <div class="fitem_building">
                <label>建筑高度:</label> <input name="conBuildHight"
                    class="easyui-numberbox" precision="0" />
            </div>
            <div class="fitem_building">
                <label>建筑面积:</label> <input name="conBuildArea"
                    class="easyui-numberbox" precision="2" />
            </div>
            <div class="fitem_building">
                <label>占地面积:</label> <input name="conCoverArea"
                    class="easyui-numberbox" precision="2" />
            </div>
            <div class="fitem_building">
                <label>地表层数:</label> <input name="conFloors"
                    class="easyui-numberbox" precision="0" />
            </div>
            <div class="fitem_building">
                <label>地下层数:</label> <input name="conUnderFloors"
                    class="easyui-numberbox" precision="0" />
            </div>
            <div class="fitem">
                <label class="area">地址:</label> <input class="easyui-textbox"
                    data-options="multiline:true" name="baseAddress"
                    style="width: 500px; height: 70px">
            </div>
            <div class="fitem">
                <label class="area">说明:</label> <input class="easyui-textbox"
                    data-options="multiline:true" name="baseRemark"
                    style="width: 500px; height: 70px">
            </div>
            <input type="hidden" name="thumbImg" id="building_thumb_img" />
        </form>
        <form id="file_building_img" method="post"
            enctype="multipart/form-data">
            <div class="fitem_building">
                <label>上传缩略图:</label> <input class="easyui-filebox"
                    data-options="buttonText:'选择'" id="thumb_img_input"
                    name="thumb_img" style="width: 200px">
            </div>
        </form>
        <div class="fitem"
            style="margin-top: 30px; margin-left: -335px; margin-bottom: 30px;">
            <label class="area">缩略图展示:</label> <img onfocus=this.blur()
                id="thumb_img_show" border=0 align="middle" src="" alt="没有图片！"
                width="100px" height="100px"> </img>
        </div>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a> <a
            href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel"
            onclick="javascript:$('#dlgBuilding').dialog('close');$('#showSelected_building').text('');"
            style="width: 90px">取消</a>
    </div>


    <div id="import_building" class="easyui-dialog"
        style="width: 300px; height: 250px; padding: 10px 20px" closed="true">
        <div class="ftitle">导入数据</div>
        <form id="fm_file_building" method="post"
            enctype="multipart/form-data" target="ajaxUpload">
            <div style="margin-top: 10px;">
                <a
                    href="<%=request.getContextPath()%>/template/building_import_template.xls"
                    class="easyui-linkbutton" iconCls="icon-add" target='_blank'>导入模板下载</a>
            </div>
            <div style="margin-top: 10px;">
                <input class="easyui-filebox" data-options="buttonText:'选择文件'"
                    id="file" name="file" style="width: 250px; margin: 20px;">
            </div>
            <div style="margin-top: 20px; float: right;">
                <a href="#" class="easyui-linkbutton" iconCls='icon-ok'
                    onclick="uploadFile()">提交</a>
            </div>
        </form>

        <iframe name="ajaxUpload" style="display: none"></iframe>

    </div>
    <script type="text/javascript">
var dictList;
var buildClassData =  new Array(),
buildClassDataSearch = new Array(),
buildingLevelData = new Array(),
buildingLevelDataSearch = new Array(),
buildingConTypeData = new Array();
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
    //级别 ：building_level
    if ("building_level" == value.typeCode) {
        buildingLevelData.push({name: value.name, code: value.code});
        buildingLevelDataSearch.push({name: value.name, code: value.code});
    }
    //工程类别 building_con_type
    if ("building_con_type" == value.typeCode) {
        buildingConTypeData.push({name: value.name, code: value.code});
    }
});

var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
$(document).ready(function() {
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
    // 级别
    $('#comCcBaseLevel').combobox({
        data : buildingLevelData,
        valueField:'code',
        textField:'name'
    });
    
    buildingLevelDataSearch.unshift({code: '0', name: '全部'});
    $('#baseLevel').combobox({
        data : buildingLevelDataSearch,
        valueField:'code',
        textField:'name',
        value:0
    });
    
    //工程类别
    $('#comCcConType').combobox({
            data : buildingConTypeData,
            valueField:'code',
            textField:'name'
    });

    //表单窗口打开前禁用联动查询
    $('#fm_building').form({
        onLoadSuccess : function(){
            disableLinkage(false);
        }
    });

});

//上传缩略图
$('#thumb_img_input').filebox({
    'accept':['image/png','image/jpeg'],
    'onChange':function() {
        uploadImgFile();
    }
});
var UrlConfig = {
        listPage: '<%=request.getContextPath()%>/app/building/queryPage',
        insertData: '<%=request.getContextPath()%>/app/building/insertData',
        updateData: '<%=request.getContextPath()%>/app/building/updateData',
        deleteData: '<%=request.getContextPath()%>/app/building/deleteData',
        deleteDataByIds: '<%=request.getContextPath()%>/app/building/deleteDataByIds',
        exportLibStreet: '<%=request.getContextPath()%>/app/building/exportLibStreet',
        importStreet: '<%=request.getContextPath()%>/app/building/importStreet',
        uploadImgUrl: '<%=request.getContextPath()%>
        /app/building/upload',
        };
        var thumbImg = "";
        // 上传缩略图
        function uploadImgFile() {
            console.log("uploadthumbImg");
            $('#file_building_img').form('submit', {
                url : UrlConfig.uploadImgUrl,
                success : function(result) {
                    var rt = $.parseJSON(result);
                    if (rt.successful) {
                        $.messager.alert("提示", "图片上传成功！继续填写其他信息", "info");
                        $("#building_thumb_img").val(rt.data);//填充内容
                    }
                }
            });
        }

        var districtUrl = "${pageContext.request.contextPath}/app/area/district/queryAll";
        var streetUrl = "${pageContext.request.contextPath}/app/area/street/queryAll";
        var blockUrl = "${pageContext.request.contextPath}/app/area/block/queryAll";

        var params = {
            districtUrl : districtUrl,
            streetUrl : streetUrl,
            blockUrl : blockUrl,
            newDistrictEle : 'comCcDistrict_building',
            searchDistrictEle : 'districtId',
            newStreetEle : 'comCcStreet_building',
            searchStreetEle : 'streetId',
            newBlockEle : 'comCcBlock_building',
            searchBlockEle : 'blockId',
        };

        initDistrict(params);

        // 新增
        function newStreet() {
            $('#dlgBuilding').dialog('open').dialog('setTitle', '新建建筑主体');
            $('#fm_building').form('clear');
            queryAreaTree(queryAreaTreeUrl, "#comCcBlock_building",
                    "#showSelected_building");
            url = UrlConfig.insertData;
        }

        // 编辑修改
        function editStreet() {
            var thumbImgUrl = "http://" + window.location.host
                    + $('#dg_building').datagrid("getSelections")[0].thumbImg;
            console.log(thumbImgUrl);
            $("#thumb_img_show").attr("src", thumbImgUrl);
            var rowNum = $('#dg_building').datagrid("getSelections").length;
            if (rowNum > 1 || rowNum == 0) {
                $.messager.alert("提示", "请选择一行修改！", "error");
            } else {
                var row = $('#dg_building').datagrid('getSelected');
                if (row) {
                    //表单加载数据前关掉联动查询
                    disableLinkage(true);
                    $('#dlgBuilding').dialog('open').dialog('setTitle',
                            '编辑建筑主体');
                    $('#fm_building').form('load', row);
                    url = UrlConfig.updateData + "?id=" + row.id;
                }
            }
        }

        // 保存
        function saveUser() {
            $('#fm_building')
                    .form(
                            'submit',
                            {
                                url : url,
                                onSubmit : function() {
                                    var t = $('#comCcBlock_building')
                                            .combotree('tree'); // 得到树对象
                                    var n = t.tree('getSelected'); // 得到选择的节点
                                    //这里经过实践测试应该使用t.tree('getChecked');
                                    if (queryAreaTreeUrl.indexOf('flag=3') != -1
                                            && n.text == "天河区") {
                                        $.messager.alert("提示", "请下拉选择【"
                                                + n.text + "】下的社区数据！", "info");
                                        return false;
                                    }
                                    var flag = false;
                                    // 经度纬度 验证
                                    var longitude = $("input[name='longitude']")
                                            .val();
                                    var latitude = $("input[name='latitude']")
                                            .val();
                                    //            // 街道id的验证，由于禁用下拉框，验证不到必填项
                                    //            var streetId = $('#comCcStreet_building').combobox('getValue');
                                    //            if(!streetId){
                                    //                   $.messager.alert("提示", "街道数据必填，请选择正确的行政区！", "info");
                                    //                   return false;
                                    //            }
                                    //            var blockId = $('#comCcBlock_building').combobox('getValue');
                                    //            if(!blockId){
                                    //                 $.messager.alert("提示", "社区数据必填，请选择正确的街道！", "info");
                                    //                 return false;
                                    //            }

                                    //用来验证数字，包括小数的正则
                                    var reg = /^[0-9]+\.?[0-9]*$/;
                                    if (longitude) {
                                        if (!reg.test(longitude)) {
                                            $.messager.alert("提示",
                                                    "请输入正确的数字格式！", "info");
                                            return false;
                                        } else {
                                            flag = true;
                                        }
                                    } else {
                                        flag = true;
                                    }
                                    if (latitude) {
                                        if (!reg.test(latitude)) {
                                            $.messager.alert("提示",
                                                    "请输入正确的数字格式！", "info");
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
                                success : function(result) {
                                    var result = eval('(' + result + ')');
                                    if (result.successful) {
                                        $.messager.alert("操作提示", result.data,
                                                "info");
                                        $('#dlgBuilding').dialog('close'); // close the dialog
                                        $('#dg_building').datagrid('reload'); // reload the user data
                                    } else {
                                        if (result.msg) {
                                            $.messager.alert("操作提示",
                                                    result.msg, "error");
                                        } else {
                                            $('#dlgBuilding').dialog('close'); // close the dialog
                                            $('#dg_building')
                                                    .datagrid('reload'); // reload the user data
                                        }
                                    }
                                }
                            });
        }

        //批量删除
        function destroyStreets() {//返回选中多行
            var selRow = $('#dg_building').datagrid('getSelections')
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
                $.messager.confirm('提示', '是否删除选中数据?', function(r) {
                    if (!r) {
                        return;
                    } else {
                        $.post(UrlConfig.deleteDataByIds, {
                            deleteIds : deleteIds
                        }, function(result) {
                            if (result.successful) {
                                $.messager.alert("操作提示", result.data, "info");
                                $('#dg_building').datagrid('clearSelections');
                                $('#dg_building').datagrid('reload'); // reload the user data
                            } else {
                                if (result.msg) {
                                    $.messager.alert("操作提示", result.msg,
                                            "error");
                                }
                            }
                        });
                    }
                });
            }
        }

        function doSearch(value, name) {
            $('#dg_building').datagrid(
                    'load',
                    {
                        baseName : $('#baseName').val(),
                        streetId : $('#streetId').combobox('getValue'), // 下拉框获取数据ID
                        blockId : $('#blockId').combobox('getValue'),
                        districtId : $('#districtId').combobox('getValue'),
                        baseBuildingClass : $('#baseBuildingClass').combobox(
                                'getValue'),
                        baseLevel : $('#baseLevel').combobox('getValue'),
                    });
        }

        function doReset() {
            //      $("#tblQuery").find("input").val("");
            $("#districtId").combobox('select', 0);
            $("#streetId").combobox('select', 0);
            $("#blockId").combobox('select', 0);
            $("#baseBuildingClass").combobox('select', 0);
            $("#baseLevel").combobox('select', 0);
            $("#baseName").val("");
            doSearch();
        }
        // 导出
        function doExport(value, name) {
            $.messager.confirm('提示', '是否导出查询出来数据?', function(r) {
                if (!r) {
                    return;
                } else {
                    var exportBaseName = $('#baseName').val();
                    var openUrl = UrlConfig.exportLibStreet + "?baseName="
                            + encodeURI(exportBaseName) + "&streetId="
                            + $('#streetId').combobox('getValue') + "&blockId="
                            + $('#blockId').combobox('getValue')
                            + "&districtId="
                            + $('#districtId').combobox('getValue')
                            + "&baseBuildingClass="
                            + $('#baseBuildingClass').combobox('getValue')
                            + "&baseLevel="
                            + $('#baseLevel').combobox('getValue')
                    window.open(openUrl);
                }
            });
        }

        //导入
        function doImport() {
            $('#import_building').dialog('open').dialog('setTitle', '导入数据');
        }

        function uploadFile() {
            console.log("upload building");
            $('#fm_file_building').form('submit', {
                url : UrlConfig.importStreet,
                onSubmit : function() {
                    return $(this).form('validate');
                },
                success : function(result) {
                    var result = eval('(' + result + ')');
                    if (result.successful) {
                        $.messager.alert("操作提示", result.data, "info");
                        $('#import_building').dialog('close'); // close the dialog
                        $('#dg_building').datagrid('reload'); // reload the user data
                    } else {
                        if (result.msg) {
                            $.messager.alert("操作提示", result.msg, "error");
                        } else {
                            $('#import_building').dialog('close'); // close the dialog
                            $('#dg_building').datagrid('reload'); // reload the user data
                        }
                    }
                }
            });
        }

        //function keyUnitsBackbround(value, row, index){
        //    console.log(value);
        //    if (value == "重点消防单位") {
        //        return 'background-color:blue;';
        //    }
        //}

        //$('#dg_building').datagrid({
        //    rowStyler:function(index,row){
        //        if (row.baseLevelName =="重点消防单位" ){
        //            return 'background-color:    #FFDEAD;';
        //        }
        //    }
        //})
    </script>
</body>
</html>