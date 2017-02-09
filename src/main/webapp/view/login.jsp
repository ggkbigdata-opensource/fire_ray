<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登录</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<%--<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<body class="login-bg">
<div id='d'>
    <div style="width: 484px;">
        <div class="login-logo">
        </div>
        <div id="dlg" class="easyui-panel" style="width: 391px;height: 325px;"
                data-options="headerCls:'login-head',bodyCls:'login-body'"
                title="<div class='login-head-font'>登录系统</div>" >
            <form id="loginform" method="post" action="<%=request.getContextPath() %>/login">
                <div class="login-item" style="margin-top: 10px">
                    <input type="text" name="username" placeholder="账号" class="easyui-validatebox login-input" required="true" />
                </div>
                <div class="login-item">
                    <input type="password" id="pwd" name="password" placeholder="密码" class="easyui-validatebox login-input" required="true"  />
                </div>
            </form>
            <div id="dlg-buttons" class="login-item" style="padding-left: 37px;">
                <button class="login-btn" onclick="login()">登录</button>
            </div>
        </div>
    </div>
</div>
<div class="login-footer" style="background: none;margin-bottom:20px; position: fixed; bottom: 0; z-index: 10;">
    <div class="login-fri-tips">版权和数据权归属：天河消防安全委员会　　　技术支持：广东广业开元科技有限公司</div>
</div>
<script type="text/javascript">

//计算登录面板位置
function calcPosition(){
    var wWidth = $(window).width();
    var wHeight = $(window).height();
    var pWidth = $('#dlg').width();
    var pHeight = $('#dlg').height();
    var height = (wHeight-pHeight)/3;
    var width = (wWidth-pWidth)*0.5;
    $('#d').attr('style',"padding: " + height + "px 0px 0px " + (width - 47) + "px");
};
calcPosition();

$(window).resize(function() {
    calcPosition();
});

$('#pwd').keypress(function(event){
    //回车
    if(event.keyCode == 13){
        login();
    }
});
function login() {
    var url = '<%=request.getContextPath() %>/app/login';
    if ($('#loginform').form('validate')) {
        $.ajax(url, {
            type: 'POST',
            dataType: 'json',
            data: $('#loginform').serialize(),
            success: function (data) {
                if (data.successful) {
                    window.location.href = "<%=request.getContextPath() %>/";
                } else {
                    $.messager.show({title: '操作结果', msg: data.msg});
                }
            },
            error: function (data) {
                $.messager.show({title: '错误', msg: '请求错误'});
            }
        });
    } else {
        $.messager.show({title: '错误', msg: '请输入正确的账号和密码'});
    }
};
</script>

</body>
</html>