<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目概况表下载</title>
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
/*
        position: relative;
        display: inline-block;
        width: 180px;
        height: 36px;
        text-decoration: none;
        color: white;
        background: #39a4ed;
        font-size: 24px;
        text-align: center;
        border-radius: 3px;*/

    }
    .downloadBuildingTemplate:hover{
        background:#E08216;
    }
</style>
<body style="text-align:center">
    <div style="font-size: 34px;font-weight:bold;margin-top: 40px;">项目概况表模板下载：</div>
    <div style="margin-top:40px;">
        <a href="<%=request.getContextPath() %>/template/building_subject_import_template.xls" class="downloadBuildingTemplate">下载</a>
    </div>
    <div style="font-size: 28px;font-weight:bold;margin-top:40px;">
        <span style="color: red;">温馨提示：</span>填写完后请重命名文件后上传
        <div class="xian "style="width:1000px;margin:0 auto;padding:0 200px; border-top:1px solid #ddd" ></div>
        <span style="margin-top: 30px;">上传文件入口：关注微信公众号：天河防火墙GZ，回复：上传。</span>
    </div>
        <img src="../../images/qrcode_860.jpg">
</body>
</html>