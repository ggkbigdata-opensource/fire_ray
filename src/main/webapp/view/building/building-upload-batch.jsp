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
    $(document).ready(function() {
        var uploader = WebUploader.create({

            // swf文件路径
            swf: '<%=request.getContextPath() %>/resources/webuploader-0.1.5/js/Uploader.swf',

            // 文件接收服务端。
            server: '<%=request.getContextPath() %>/app/buildingSubject/uploadTemplate',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker_building',

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false
        });

        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            var $list = $("#thelist");
            $list.append( '<div id="' + file.id + '" class="item" style="margin-bottom: 10px;">' +
                    '<h6 class="info" style="margin: 0;width: 400px;">' + file.name + '</h6>' +
                    '<h6 class="info" style="margin: 0 20px;width: 50px;">' + file.size + '</h6>' +
                    '<p class="state" style="font-size: 14px;margin: 0 0 0 20px;width: 300px;">等待上传...</p>' +
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
</script>
    <div id="uploader_building" class="wu-example">
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list">
            <div class="title" style="
                font-size: 18px;
                margin-bottom: 10px;
                padding-bottom: 10px;
                border-bottom: 1px solid #d6d6d6;
            ">
                <div style="
                width: 400px;
            ">文件名</div><div style="

                width: 50px;
                margin: 0 20px;
            ">大小</div><div style="
                margin-left: 20px;
                width: 300px;
             ">状态</div>
            </div>
        </div>
        <div class="btns">
            <div id="picker_building">选择文件</div>
            <button id="ctlBtn" class="btn btn-default">开始上传</button>
        </div>
    </div>
</body>
</html>