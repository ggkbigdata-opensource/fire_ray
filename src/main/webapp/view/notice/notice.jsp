<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/ueditor1_4_3-utf8-jsp/ueditor.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/ueditor1_4_3-utf8-jsp/ueditor.parse.js"></script>
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/webuploader-0.1.5/webuploader.css">
</head>
<body>
<div id="tblQuery" style="padding:3px">
    <div class="inputDiv">
        <div class='inputDivEg'>
	        <span class="inputLabel">公告标题:</span>
	        <input id="title" class="easyui-textbox inputDiv-input"/>
        </div>
        <a href="#" class="resetBtn" plain="true" onclick="doSearch()">查询</a>
        <a href="#" class="queryBtn" plain="true" onclick="doReset()">重置</a>
    </div>
</div>
<div class="tableClass">
<table id="dg_notice" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
        url='<%=request.getContextPath() %>/app/notice/queryPage'
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
            <th field="title" width="50">公告标题</th>
            <th field="createdTime" width="50">创建时间</th>
            <th field="modTime" width="50">更新时间</th>
            <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>  
        </tr>
    </thead>
</table>
</div>
<div id="toolbar">
    <a href="#" class="resetBtn" plain="true" onclick="newStreet()">新增</a>
    <a href="#" class="queryBtn" plain="true" onclick="destroyUsers()">批量删除</a>
</div>

<div id="dlgNotice" class="easyui-dialog" style="width:1160px;height:550px;"
        closed="true" buttons="#dlg-buttons">
    <div id="noticeTabs" class="easyui-tabs" data-options="fit:true">
    <%--<div class="ftitle">公告 信息</div>--%>
         <div title="公告基础信息" style="padding-left: 20px;padding-top: 20px;">
            <form id="fmNotice" method="post" novalidate>
                <input name="id" type="hidden"/>
                <table>
                    <tr>
                        <th>
                            <label style="display:inline-block; width:80px;margin-bottom:5px;">标题：</label>
                        </th>
                        <td>
                            <input name="title" class="easyui-textbox" required="true" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label style="display:inline-block; width:80px;margin-bottom:5px;">公告内容:</label>
                        </th>
                        <td>
                            <!-- 加载编辑器的容器 -->
                            <script id="container" name="content" type="text/plain">
                            </script>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        </div>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgNotice').dialog('close')" style="width:90px">取消</a>
</div>

<div id="dlg_qrcode" class="easyui-dialog" title="分享公告" data-options="iconCls:'icon-save'" style="width:450px;height:350px;padding:10px" closed="true">
    <h3>可以直接把二维码链接复制到Email,或聊天工具中分享给他人</h3>
    <input  id="share_qrcode_url" readonly="readonly"  size="58"/>
    <img id="img_qrcode" src="#"/>
</div>
<div id="dlgNoticeAttachment" class="easyui-dialog" style="width:920px;height:550px;"
     closed="true">

    <div id="openRoleDiv" class="easyui-window" closed="true" modal="true" collapsible="false" minimizable="false" maximizable="false"
         title="附件管理" style="width:920px;height:550px;">
        <iframe scrolling="auto" id='noticeAttachmentIframe' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
    </div>
</div>


<script type="text/javascript">
<!-- 实例化编辑器 -->
var ue = UE.getEditor('container');
var noticeId;

var UrlConfig = {
		listPage: '<%=request.getContextPath() %>/app/notice/queryPage',
		insertData: '<%=request.getContextPath() %>/app/notice/insertData',
		updateData: '<%=request.getContextPath() %>/app/notice/updateData',
		deleteData: '<%=request.getContextPath() %>/app/notice/deleteData',
		deleteDataByIds: '<%=request.getContextPath() %>/app/notice/deleteDataByIds',
        listAttachmentPage: '<%=request.getContextPath() %>/app/noticeAttachment/queryPage',
        deleteAttachmentData: '<%=request.getContextPath() %>/app/noticeAttachment/deleteData',
};


function formatOper(val,row,index){  
    return '<a href="#" class="tableBtn" onclick="editBean('+index+')">编辑</a>'+
            '<a href="#" class="tableBtn" onclick="noticeAttachment('+index+')">附件管理</a>'+
            '<a href="#" class="tableBtn" onclick="shareBean('+index+')">分享</a>'+
            '<a href="#" class="tableBtn" onclick="deleteBean('+index+')">删除</a>';
}
// 更新记录
function editBean(index){  
    $('#dg_notice').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg_notice').datagrid('getSelected');  
    if (row){  
        $('#dlgNotice').dialog('open').dialog('setTitle','更新公告信息');
        $('#fmNotice').form('load',row);
        ue.setContent(row.content);
        url = UrlConfig.updateData + "?id=" + row.id;
        $('#noticeTabs').tabs('select','公告基础信息');
    }
}

// 附件管理
function noticeAttachment(index) {
    $('#dg_notice').datagrid('selectRow',index);// 关键在这里
    var row = $('#dg_notice').datagrid('getSelected');
    if (row){
        $('#noticeAttachmentIframe')[0].src="http://"+window.location.host+ "<%=request.getContextPath() %>/view/notice/attachmentUploader.jsp?noticeId="+row.id;
        $('#openRoleDiv').dialog('open');
    }
}
function shareBean(index){
    $('#dg_notice').datagrid('selectRow',index);// 关键在这里
    var row = $('#dg_notice').datagrid('getSelected');
    if (row){
        console.log("noticeId:"+row.id);
        $('#dlg_qrcode').dialog('open');
        $("#share_qrcode_url").attr("value" ,"http://"+window.location.host+ "<%=request.getContextPath() %>/view/notice/preview.jsp?uuid="+row.uuid);
        $("#img_qrcode").attr("src", "<%=request.getContextPath() %>/app/notice/createQRCode?uuid="+row.uuid);
    }
}

//单个删除
function deleteBean(index){
	 $('#dg_notice').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg_notice').datagrid('getSelected');
    if (row){
        $.messager.confirm('确认','你确定要删除公告标题为【'+row.title+'】数据吗？',function(r){
            if (r){
                $.post(UrlConfig.deleteData,{id:row.id},function(result){
                    if (result.successful){
                        $('#dg_notice').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.data
                        });
                    }
                },'json');
            }
        });
    }
}

function deleteAttachment(index) {
    $('#dg_notice_attachment').datagrid('selectRow',index);// 关键在这里
    var row = $('#dg_notice_attachment').datagrid('getSelected');
    if (row){
        $.messager.confirm('确认','你确定要删除文件名称为【'+row.originalName+'】数据吗？',function(r){
            if (r){
                $.post(UrlConfig.deleteAttachmentData,{id:row.id},function(result){
                    if (result.successful){
                        $('#dg_notice_attachment').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.data
                        });
                    }
                },'json');
            }
        });
    }
}

// 新增
function newStreet(){
    $('#dlgNotice').dialog('open').dialog('setTitle','新建公告');
    $('#fmNotice').form('clear');
    ue.setContent("");
    url =UrlConfig.insertData;
}

// 保存
function saveUser(){
    // 内容
    var data = ue.getContent();
    // 公告标题
    var titleValue = $("input[name='title']").val();
    if(!titleValue){
        $.messager.alert("操作提示", "请填写公告标题", "info");
    }else if(!ue.hasContents()){
        $.messager.alert("操作提示", "请填写公告内容", "info");
    }else{
        // 都不为空保存
        $.ajax({
            cache: true,
            type: "POST",
            url: url,
            data: {
                "content": data,
                "title":titleValue
            },
            async: false,
            error: function(request) {
                $.messager.alert("操作提示", "Connection error", "error");
            },
            success: function(result){
                console.log(result);
                if(result.successful){
                    $.messager.alert("操作提示", result.data, "info");
                    $('#dlgNotice').dialog('close');        // close the dialog
/*                    $.messager.confirm('操作提示',  result.data+'！是否继续添加附件?', function(r){
                        // 是的话跳转tab到人员信息
                        if (r){
//                            $('#noticeTabs').tabs('select','附件管理');
                            addTab('附件上传',"http://"+window.location.host+ "<%=request.getContextPath() %>/view/notice/attachmentUploader.jsp?noticeId="+result.total);
                        }
                    });*/
                    $('#dg_notice').datagrid('reload');    // reload the user data
                }else{
                    if (result.msg){
                        $.messager.alert("操作提示", result.msg, "error");
                    } else {
                        $('#dlgNotice').dialog('close');        // close the dialog
                        $('#dg_notice').datagrid('reload');    // reload the user data
                    }
                }
            }
        });
    }
}

function addTab(title, url){
    if ($('#noticeTabs').tabs('exists', title)){
        $('#noticeTabs').tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        $('#noticeTabs').tabs('add',{
            title:title,
            content:content,
            cache:false
        });
    }
}

//批量删除
function destroyUsers(){//返回选中多行
    var selRow = $('#dg_notice').datagrid('getSelections')
    //判断是否选中行
    if (selRow.length==0) {
        $.messager.alert("提示", "请选择要删除的行！", "info");
        return;
    }else{    
        var deleteIds=[];
        for (i = 0; i < selRow.length;i++) {
            deleteIds.push(selRow[i].id);
        }
        console.log("destroy：ids="+JSON.stringify(deleteIds));
        $.messager.confirm('提示', '是否删除选中数据?', function (r) {
            if (!r) {
                return;
            }else{
                $.ajax({
                    url:UrlConfig.deleteDataByIds,
                    async: false,      //ajax同步
                    type:"POST",
                    contentType:"application/json;charset=UTF-8",
                    data : JSON.stringify(deleteIds),
                    success: function(result){
                        if(result.successful){
                   	  	$.messager.alert("操作提示", result.data, "info");
                		$('#dg_notice').datagrid('clearSelections');
                        $('#dg_notice').datagrid('reload');    // reload the user data
         	          }else{
         	          		if (result.msg){
         	              	  $.messager.alert("操作提示", result.msg, "error");
         	                }
         	          }
                    }
                });
            }
        });
    }
 }

// 查找
function doSearch(value,name){
	$('#dg_notice').datagrid('load',{
		title: $('#title').val()
	});
}
// 重置
function doReset(){
    $("#tblQuery").find("input").val("");
    doSearch();
}
</script>
</body>
</html>