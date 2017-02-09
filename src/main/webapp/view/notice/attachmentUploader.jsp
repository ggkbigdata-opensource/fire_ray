<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件上传</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/webuploader-0.1.5/webuploader.css">
</head>
<body>
<script type="application/javascript">
    var UrlConfig = {
        listAttachmentPage: '<%=request.getContextPath() %>/app/noticeAttachment/queryPage',
        deleteAttachmentData: '<%=request.getContextPath() %>/app/noticeAttachment/deleteData',
    };
    var noticeId = "<%=request.getParameter("noticeId")%>";
    console.log("noticeAttachment GET noticeId = "+noticeId);
    $(document).ready(function() {
        var uploader = WebUploader.create({

            // swf文件路径
            swf: '<%=request.getContextPath() %>/resources/webuploader-0.1.5/js/Uploader.swf',

            // 文件接收服务端。
            server: '<%=request.getContextPath() %>/app/notice/upload?noticeId='+noticeId,

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false
        });

        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            var $list = $("#thelist");
            $list.append( '<div id="' + file.id + '" class="item" style="margin-bottom: 10px;">' +
                    '<h6 class="info" style="margin: 0;width: 400px;">' + file.name + '</h6>' +
                    '<h6 class="info" style="margin: 0 20px;width: 50px;">' + file.size + '</h6>' +
//                    '<div style="display: inline-block;width: 120px;height: 18px;border: 1px solid #D6D6D6; border-radius: 5px;background: #F2F2F2;vertical-align: middle;">'+'</div>'+
                    '<p class="state" style="font-size: 14px;margin: 0 0 0 20px;width: 300px;">等待上传...</p>' +
//                    '<a href="javascript:;" onclick="uploadAttachmentFile(this);"style="display: inline-block;width: 60px;height: 20px;text-decoration: none;color: white;background: #4CAF50;font-size: 14px;text-align: center;border-radius: 5px;">上传</a>'+
                    '</div>');
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });

        uploader.on( 'uploadSuccess', function( file,ret ) {
            var $file = $('#' + file.id);
            try {
                if (ret.successful) {
                    $file.find('p.state').text('上传成功');
                    $('#dg_notice_attachment').datagrid('reload');    // reload the user data
                } else {
                    $file.find('p.state').text(ret.msg);
                }
            } catch (e) {
                $file.find('p.state').text("系统异常");
            }
        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        $('#ctlBtn').on( 'click', function() {
            console.log(uploader.getFiles());
            uploader.upload();
        });
    });

    // 附件操作
    function formatOperAttachment(val,row,index){
        return '<a href="#" class="tableBtn" onclick="downloadAttachment('+index+')">下载</a>'+
                '<a href="#" class="tableBtn" onclick="deleteAttachment('+index+')">删除</a>';
    }

    // 删除附件文件
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

    // 下载附件文件
    function downloadAttachment(index) {
        $('#dg_notice_attachment').datagrid('selectRow',index);// 关键在这里
        var row = $('#dg_notice_attachment').datagrid('getSelected');
        if (row){
            console.log(row);
            <%request.setCharacterEncoding("UTF-8");%>
            location.href = "<%=request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()%>"+"/resources/notice/attachment/"+row.uuid;
        }
    }
</script>
    <div id="uploader" class="wu-example">
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list">
            <div class="title" style="
                font-size: 14px;
                margin-bottom: 10px;
                padding-bottom: 10px;
                border-bottom: 1px solid #d6d6d6;
            ">
                <div style="
                width: 400px;
            ">文件名</div><div style="

                width: 50px;
                margin: 0 20px;
            ">大小</div><%--<div style="
                display: inline-block;
                width: 122px;
            ">进度</div>--%><div style="
                margin-left: 20px;
                width: 300px;
             ">状态</div><%--<div style="
                display: inline-block;
                margin-left: 40px;
            ">操作</div>--%></div>

        </div>
        <div class="btns">
            <div id="picker">选择文件</div>
            <button id="ctlBtn" class="btn btn-default">开始上传</button>
        </div>
    </div>


    <div class="tableClass">
        <table id="dg_notice_attachment" title="数据列表" class="easyui-datagrid" style="width:auto;height:auto;"
               url='<%=request.getContextPath() %>/app/noticeAttachment/queryPage?noticeId=<%=request.getParameter("noticeId")%>'
               rownumbers="true"
               fitColumns="true"
               singleSelect="false"
               autoRowHeight = "false"
               pagination="true"
               pageSize="20"
               striped="true"
               checkOnSelect="true"
               selectOnCheck="true"
               width="800"
        >
            <thead>
            <tr>
                <th field="ck" checkbox="true"></th>
                <th field="originalName" width="60">原始文件名称</th>
                <th field="uuid" width="100">保存文件名称</th>
                <th field="fileSize" width="20">文件大小</th>
                <th data-options="field:'_operate',width:80,align:'center',formatter:formatOperAttachment">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</body>
</html>