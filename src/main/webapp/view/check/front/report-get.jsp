<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写报告提取码</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<style type="text/css">
    .downloadBuildingTemplate{
        padding: 25px 100px;
        color: #fff;
        background: #57b6f7;
        border-radius: 10px;
        font-size: 32px;
        text-decoration: none;
        margin-right: 10px;
        display: inline-block;
        margin: 0 auto;
    }
    .downloadBuildingTemplate:hover{
        background:#E08216;
    }
    input{
        width: 360px;
        height: 60px;
        line-height: 40px;
        border: 1px solid #dddddd;
        padding-left: 5px;
        font-size: 24px;
        border-radius: 10px;
        color: #0b7da9;
        font-weight:bold;
    }
</style>
<script type="application/javascript">
    function searchReport() {
        var report_uuid = $("#report_uuid").val();
        if(report_uuid && "EXTV" == report_uuid){
            location.href = "rating-report.jsp";//location.href实现客户端页面的跳转
        }else{
            alert("请输入正确的提取码！");
        }
    }
</script>
<body style="text-align:center">
    <div style="margin-top: 30px;">
        <span style="font-size: 28px;font-weight:bold;">提取码：</span>
        <input type="text" id="report_uuid">
    </div>
    <div style="margin-top:40px;">
        <a class="downloadBuildingTemplate" onclick="searchReport()">提交</a>
    </div>
    <div style="font-size: 28px;font-weight:bold;margin-top:40px;">
        <span style="color: red;">温馨提示：</span>请正确输入您的风险评估报告提取码！
    </div>
        <img src="../../../images/qrcode_860.jpg">
</body>
</html>