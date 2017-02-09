<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>安全风险评估报告详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<style type="text/css">
.pla-content{ width: 750px;margin: 0 auto; padding: 30px; color: #333333;font-weight: bold;}
.pla-content h2{ font-size: 40px; text-align: center; font-weight: normal;margin:0;}
    .pla-content-p1{ text-align: center; color: #666666; margin-bottom: 30px;}
    .pla-content-p2{ line-height: 20px; padding-left: 30px;font-weight: 500;}
    .pla-content-p2 input{ margin-right: 10px;}
    .pla-content-p3{ line-height: 30px; text-indent: 2em;}
    .pla-content-p4{ margin-top: 30px; line-height: 30px;}
    .pla-content-dl dt{ float: left; width: 130px; height: 130px; background-color: #1b6d85;}
.pla-content-dl dt img{ width: 300px; height: 300px;}
.pla-content-dl dd p{ text-align:right; margin-left: 15px;margin-bottom: 20px;}
    .line{
        width:700px;height:1px;margin:0px auto;padding:0px;background-color:#D5D5D5;overflow:hidden;
    }

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
    <h2><span id="ratingReportName">广东华建电气消防安全检测有限公司</span></h2>
    <h2>消防安全风险等级评估报告</h2>
    <p class="pla-content-p1">CONSULTATIVE REPORTS</p>
    <p>评估结果： 安全等级1</p>
    <p>结论：</p>
        <p class="pla-content-p2">一、本次受委托对项目的系统进行检测。 </p>
        <p class="pla-content-p2">二、经对以上系统进行全面检测，依据检测结果（详见本报告“单项评定结果”）。 </p>
        <p class="pla-content-p2">备注：</p>
        <p class="pla-content-p2">由于现场营业及物业原因，消防系统联动测试未具备检测条件</p>
    <p>不合格项：</p>
        <p class="pla-content-p2">工程编号: 19999990</p>
    <div class="line"></div>
    <p class="pla-content-p2">检 测 项: 6.1.1(消防给水(消防水源)、室外消防水、天然水源作为消防水源时的要求)</p>
    <p class="pla-content-p2">重要等级: B</p>
    <p class="pla-content-p2">规范要求:应采取确保消防车、固定和移动消防泵在枯水位取水的技术措施；当消防 车取水时，最大吸水高度不应超过6.0m</p>
    <p class="pla-content-p2">以下是不符合规范要求的检测点：</p>
    <p class="pla-content-p2">0000001001 第1层、第001号检测点</p>
    <div class="line"></div>
    <p class="pla-content-p2">检 测 项: 6.1.2(消防给水(消防水源)、室外消防水、天然水源取水口的消防车场地 的设置)</p>
    <p class="pla-content-p2"> 重要等级: B</p>
    <p class="pla-content-p2"> 规范要求: 应设置消防车到达取水口的消防车道和消防车回车场或回车道</p>
    <p class="pla-content-p2"> 以下是不符合规范要求的检测点：</p>
    <p class="pla-content-p2">0000001001 第1层、第001号检测点</p>
<%--    <div class="line"></div>
    <p class="pla-content-p2">检 测 项: 6.1.3(消防给水(消防水源)、室外消防水、雨水清水池、中水清水池、水 景和游泳池必须作为消防水源时的要求)</p>
    <p class="pla-content-p2">重要等级: B</p>
    <p class="pla-content-p2">规范要求: 应有保证在任何情况下均能满足消防给水系统所需的水量和水质的技术措 施</p>
    <p class="pla-content-p2">以下是不符合规范要求的检测点：</p>
    <p class="pla-content-p2">0000001001 第1层、第001号检测点</p>--%>
    <p>整改意见：</p>
    <p class="pla-content-p2">
    <p class="pla-content-p2">1、建立并严格执行防火安全制度，如发生火灾事故，公司负全部责任；</p>

    <p class="pla-content-p2">2、加强防火安全教育，宣传火灾事故警示教育案例；</p>

    <p class="pla-content-p2">3、厂区内严禁火源，禁止吸烟；</p>

    <p class="pla-content-p2"> 4、定期排查消防隐患，按期更换灭火器药剂；</p>

    <p class="pla-content-p2">  5、明确宿舍消防责任人，严禁私拉乱接电线，宿舍禁用500W以上大功率电器。</p>
    </p>
    <dl class="pla-content-dl">
        <dd>
            <p>天河区消防安全委员会</p>
            <p>广州市公安局天河区分局</p>
            <p><span id="nowDate"></span></p>
        </dd>
    </dl>
</div>

</body>
</html>














