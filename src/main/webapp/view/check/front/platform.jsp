<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>安全风险评估报告</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<style type="text/css">
.pla-content{ width: 750px;margin: 0 auto; padding: 30px; color: #333333;font-weight: bold;}
.pla-content h2{ font-size: 48px; text-align: center; font-weight: normal;margin:0;}
    .pla-content-p1{ text-align: center; color: #666666; margin-bottom: 30px;}
    .pla-content-p2{ line-height: 30px; padding-left: 30px;}
    .pla-content-p2 input{ margin-right: 10px;}
    .pla-content-p3{ line-height: 30px; text-indent: 2em;}
    .pla-content-p4{ margin-top: 30px; line-height: 30px;}
    .pla-content-dl dt{ float: left; width: 130px; height: 130px; background-color: #1b6d85;}
.pla-content-dl dt img{ width: 300px; height: 300px;}
.pla-content-dl dd p{ text-align:right; margin-left: 15px;margin-bottom: 20px;}

</style>
    <script type="application/javascript">
        $(document).ready(function() {
            var code = '<%=request.getParameter("code")%>';
            if(code && code !=　"null"){
                $("#reportCode").text(code);
            }

            var name = '<%=request.getParameter("name")%>';
            name = decodeURI(name);
            if(name  && name !=　"null"){
                $("#reportName").text(name);
            }


            var detectionTime = '<%=request.getParameter("detectionTime")%>';
            detectionTime = decodeURI(detectionTime);
            if(detectionTime && detectionTime !=　"null"){
                $("#reportDetectionTime").text(detectionTime);
            }


            $("#nowDate").text(getNowFormatDate());
            // 获取当前日期
            function getNowFormatDate() {
                var date = new Date();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = date.getFullYear() + "年" + month + "月" + strDate
                        + "日";
                return currentdate;
            }
        });




    </script>
</head>
<body>
<div class="pla-content">
    <p style="text-align:right;">报告编号：<span id="reportCode" style="text-decoration: underline;">天消 16HJA156</span></p>
    <h2>天河区开展第三方机构消防</h2>
    <h2>安全风险评估报告</h2>
    <p class="pla-content-p1">CONSULTATIVE REPORTS</p>
    <p><span id="reportName">广东华建电气消防安全检测有限公司</span>：</p>
    <p class="pla-content-p3">
        兹有我办委托第三方技术机构于<span id="reportDetectionTime">2017 年 1 月 5 日</span>对贵单位进行消防设施安全检查及评估，感谢贵单位对天河区消防安全工作的支持与配合！经现场专业化信息采集及后期严谨评估，贵司消防安全风险等级评估结果为：
    </p>
    <p class="pla-content-p2"><input type="checkbox" checked="checked">安全等级1</p>
    <p class="pla-content-p2"><input type="checkbox">安全等级2</p>
    <p class="pla-content-p2"><input type="checkbox">安全等级3</p>
    <p class="pla-content-p2"><input type="checkbox">安全等级4</p>
    <p class="pla-content-p4">详情请关注微信公众号“天河防火墙GZ”，或扫下方二维码，获取更多信息。回复“风险评估报告”，输入“EXTV”查看消防安全风险等级评估报告。</p>
    <dl class="pla-content-dl">
        <dt> <img src="../../../images/qrcode_860.jpg"></dt>
        <dd>
            <p>天河区消防安全委员会</p>
            <p>广州市公安局天河区分局</p>
            <p><span id="nowDate"></span></p>
        </dd>
    </dl>
</div>

</body>
</html>














