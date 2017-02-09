<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<body>
    <script type="application/javascript">
        $(document).ready(function() {
            <%--var noticeId = '<%=request.getParameter("id")%>';--%>
            var uuid = '<%=request.getParameter("uuid")%>';
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath() %>/app/notice/getDataByUUID?uuid="+uuid,
                async: false,
                error: function(request) {
                    $.messager.alert("操作提示", "Connection error", "error");
                },
                success: function(result){
                    $("#previewTitle").html(result.notice.title);
                    $("#previewContent").html(result.notice.content);
                    for(var i=0;i< result.noticeAttachments.length;i++){
                        var attachment = (result.noticeAttachments)[i];
                        var template = "<li data-uuid='"+attachment.uuid+"' style='margin-bottom:15px;list-style-type:none;' value="+attachment.originalName+">"+attachment.originalName+
                                "&nbsp;&nbsp;&nbsp;<a href="+'/resources/notice/attachment/'+attachment.uuid+" download="+attachment.uuid+" style='position: relative;display: inline-block;width: 90px;height: 20px;text-decoration: none;color: white;background: #4CAF50;font-size: 14px;text-align: center;border-radius: 3px;;'>下载</a>"+"</li>"
                        $("#noticeAttachmentUl").append(template);
                    }
                }
            });
       /*     $("#noticeAttachmentUl").on("click", "a", function(){
                var filesaveName = $(this).parent('li').attr("data-uuid");
                location.href = "<%=request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()%>"+"/resources/notice/attachment/"+filesaveName;
            })*/
        });
    </script>
    <%--<h1>查看公告信息</h1>
    <table>
        <tr>
            <th>
                <label style="display:inline-block; width:80px;margin-bottom:5px;font-size:20px;">标题：</label>
            </th>
            <td>
                <div id="previewTitle" style="height: 30px;font-size: 24px;"></div>
            </td>
        </tr>
        <tr>
            <th>
                <label style="display:inline-block; width:80px;margin-bottom:5px;font-size:20px;">内容：</label>
            </th>
            <td>
                <div id="previewContent" style="height: auto;width: 900px;"></div>
            </td>
        </tr>
        <tr>
            <th>
                <label style="display:inline-block; width:80px;margin-bottom:5px;font-size:20px;">附件：</label>
            </th>
            <td>
                <div style="height:auto;width: 900px;">
                    <ul id="noticeAttachmentUl"></ul>
                </div>
            </td>
        </tr>
    </table>--%>
    <div style="margin-left: 10px;">
        <div id="previewTitle" style="height: 30px;font-size: 24px;font-weight:bold;"></div>
        <div id="previewContent" style="height: auto;width: 900px;"></div>
        <span style="font-size: 24px;font-weight:bold;">附件下载：</span>
        <div>
            <ul id="noticeAttachmentUl"></ul>
        </div>
    </div>
</body>
</html>