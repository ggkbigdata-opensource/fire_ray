<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消防大数据平台</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/main.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/highcharts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/echarts/echarts.3.2.3.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/common/DateMonthBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/main/MainMap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataLine.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/view/statis/dataPie.js"></script>
     <script type="text/javascript" src="<%=request.getContextPath() %>/resources/jquery.cookie.js"></script>
    <script type="text/javascript">
var datas = "<%=session.getAttribute("fire_menu")%>";
var _menus = eval(decodeURIComponent(datas));
console.log(_menus);
$(function () {
    $("#wnav").accordion({
        animate: false
    });
    addNav(_menus);
    InitLeftMenu();
    tabClose();
    tabCloseEven();
});
function addNav(data) {

    $.each(data, function (i, sm) {
        var menuList1 = "";
        //sm 常用菜单  邮件 列表
        menuList1 = GetMenuList(sm, menuList1);
        menuList1 = "<ul id='tt1' class='easyui-tree' animate='false' dnd='false'>" + menuList1.substring(4);
        $('#wnav').accordion('add', {
            title: sm.menuname,
            content: menuList1,
            iconCls: 'icon ' + sm.icon
        });

    });
    var pp = $('#wnav').accordion('panels');
    var t = pp[0].panel('options').title;
    $('#wnav').accordion('select', t);

}
function GetMenuList(data, menuList) {
    if (data.children == null)
        return menuList;
    else {
        menuList += '<ul>';
        $.each(data.children, function (i, sm) {
            if (sm.url != null) {
                menuList += '<li><a ref="' + sm.menuid + '" href="#" rel="'
                        + sm.url + '" ><span class="nav">' + sm.menuname
                        + '</span></a></li>'
            } else {
                menuList += '<li state="closed"><span class="nav">' + sm.menuname + '</span>'
            }
            menuList = GetMenuList(sm, menuList);
        })
        menuList += '</ul>';
    }
    return menuList;
}
// 初始化左侧
function InitLeftMenu() {
    $('#wnav li').on('click', 'a', function () {
        var tabTitle = $(this).children('.nav').text();
        var url = $(this).attr("rel");
        var menuid = $(this).attr("ref");
        //var icon = getIcon(menuid, icon);
        addTab(tabTitle, url);
        $('#wnav li div').removeClass("selected");
        $(this).parent().addClass("selected");
    });

}
        
       
        
function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0" src=" ' + url + '" style="width:100%;height:90%;"></iframe>'+
    '<div class="login-footer"><div class="login-fri-tips">版权和数据权归属：天河消防安全委员会　　　技术支持：广东广业开元科技有限公司</div></div>';
    return s;
}
		
function tabClose() {
    /* 双击关闭TAB选项卡 */
    $(".tabs-inner").dblclick(function() {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    });
    /* 为选项卡绑定右键 */
    $(".tabs-inner").bind('contextmenu', function(e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children(".tabs-closable").text();

        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}

        //绑定右键菜单事件
 function tabCloseEven()
 {
     /*//关闭当前
     $('#mm-tabclose').click(function(){
         var currtab_title = $('#mm').data("currtab");
         $('#tabs').tabs('close',currtab_title);
     })*/
     
     //全部关闭
     $('#mm-tabcloseall').click(function(){
         $('.tabs-inner span').each(function(i,n){
             var t = $(n).text();
             if(t!='首页') {
                 $('#tabs').tabs('close', t);
             }
         });    
     });
     //关闭除当前之外的TAB
     $('#mm-tabcloseother').click(function(){
         var currtab_title = $('#mm').data("currtab");
         $('.tabs-inner span').each(function(i,n){
             var t = $(n).text();
             if(t!=currtab_title && '首页'!=t)
                 $('#tabs').tabs('close',t);
         });    
     });

     /*//关闭当前右侧的TAB
     $('#mm-tabcloseright').click(function(){
         var nextall = $('.tabs-selected').nextAll();
         if(nextall.length==0){
        	 	$.messager.alert("系统提示",'后边没有啦~~', "info");
             return false;
         }
         nextall.each(function(i,n){
             var t=$('a:eq(0) span',$(n)).text();
             $('#tabs').tabs('close',t);
         });
         return false;
     });
     //关闭当前左侧的TAB
     $('#mm-tabcloseleft').click(function(){
         var prevall = $('.tabs-selected').prevAll();
         if(prevall.length==0){
        	 $.messager.alert("系统提示",'到头了，前边没有啦~~', "info");
             return false;
         }
         prevall.each(function(i,n){
             var t=$('a:eq(0) span',$(n)).text();
             $('#tabs').tabs('close',t);
         });
         return false;
     });*/

     //右键刷新
    $('#mm-tabupdate').click(function () {
        var currTab = $('#tabs').tabs('getSelected');
        if(currTab.panel('options').title!="首页"){
	        var url = $(currTab.panel('options').content).attr('src');
	        $('#tabs').tabs('update', {
	            tab: currTab,
	            options: {
	                content: createFrame(url)
	            }
	        });
        }else{
        	loadMap();
        }
    });
 }
 

// var _menus = [{
//         "menuid": "10",
//         "icon": "icon-sys",
//         "menuname": "消防分析",
//         "menus":
//                  [{
//                      "menuid": "112",
//                      "menuname": "数据统计",
//                      "icon": "icon-nav",
//                      "url": "app/page/fireEventSum"
//                  }]
//          },{
//         "menuid": "20",
//         "icon": "icon-sys",
//         "menuname": "应用数据",
//         "menus": 
//                 [{
// 		            "menuid": "211",
// 		            "menuname": "检测报告",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/checkReport"
// 		             },{
// 		            "menuid": "212",
// 		            "menuname": "消防火情",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/fireEvent"
// 		             },{
// 		            "menuid": "213",
// 		            "menuname": "消防执法",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/punishEvent"
// 		        	}]
//         },{
//         "menuid": "30",
//         "icon": "icon-sys",
//         "menuname": "基础数据",
//         "menus": 
//                 [{
// 		            "menuid": "311",
// 		            "menuname": "行政区",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/district"
// 		             },{
// 		            "menuid": "312",
// 		            "menuname": "街道",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/street"
// 		             },{
// 		            "menuid": "313",
// 		            "menuname": "社区",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/block"
// 		        },{
// 		            "menuid": "314",
// 		            "menuname": "建筑主体",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/building"
// 		        },{
// 		            "menuid": "315",
// 		            "menuname": "微型消防站",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/fireStation"
// 		        }]
//         },{
//         "menuid": "40",
//         "icon": "icon-sys",
//         "menuname": "系统管理",
//         "menus": 
//                 [{
// 		            "menuid": "411",
// 		            "menuname": "用户管理",
// 		            "icon": "icon-nav",
// 		            "url": "app/page/user"
// 		             }]
//         }];

    </script>
</head>
<body class="easyui-layout">

<div data-options="region:'north'" class="head-bg">
	<img class="home_logo" src="images/home_logo.png" >
    <div id="login_user_info">
		<span class="login_user_info_username">欢迎你,${currentUser.username}</span><a class="login_user_info_logout" href="<%=request.getContextPath() %>/app/logout">退出</a>
    </div>
</div>


<div class="mainLeftMenu" data-options="region:'west',split:true,title:'首页'"
     style="width:248px;text-align:left;">
    <div id='wnav' class="easyui-accordion nav_div" fit="false" border="false">

    </div>
</div>
<div id="mainPanle" data-options="region:'center'" style="text-align:left;">
    <div id="tabs" class="easyui-tabs" data-options='fit:true,border:false,plain:false'>
        <div title="首页" style="width:inherit;height:inherit;">
            <div style="width:inherit;height:4%;text-align: right">
                <a href="#" id="updateAllData" class="easyui-linkbutton"
                   data-options="plain:true" onclick="updateAll()">分析所有数据</a>
            </div>
            <div id="echartsMap" style="width:inherit;height:90%;">
            </div>
             <div class="login-fri-tips-div">
				<div class="login-fri-tips">版权和数据权归属：天河消防安全委员会　　　技术支持：广东广业开元科技有限公司</div>
			</div>
        </div>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width: 150px;">
 	 <div id="mm-tabupdate">刷新</div>
 	 <div class="menu-sep"></div>
     <div id="mm-tabcloseall">全部关闭</div>
     <div id="mm-tabcloseother">除此之外全部关闭</div>

<!--  <div id="mm-tabupdate">刷新</div>
        <div class="menu-sep"></div>
        <div id="mm-tabclose">关闭</div> -->
    </div>

<script>
    var myChart , mapName='tianhe';
    $.get('resources/echarts/map/tianhe.json',function(mapJson){
        echarts.registerMap(mapName,mapJson);

    });
    function loadMap(){
    	var mapDom = $('#echartsMap')[0];
    	var instance = echarts.getInstanceByDom(mapDom);   	
    	if(instance){
    		instance.dispose();
    	}
    	var u = 'app/query/queryAreaRiskIndex';
        $.getJSON(u,function(data){
            var mapData = [];
            if(data || data.successful){
                $.each(data.data,function (i,v) {
                    mapData.push([v.center.longitude,v.center.latitude,v.riskLevel,v.areaName]);
                });
                console.info("mapData",mapData);
            }
            myChart = initMap(mapDom,mapData,mapName);
            myChart.on('dblclick',function(params){
                console.info("params",params);
                if(params.name){
                	 addTab('数据统计','app/page/fireEventSum?showDistrict=天河区&showStreet=' + params.name+'街道');
                }else{
                	 addTab('数据统计','app/page/fireEventSum?showDistrict=天河区&showStreet=' + params.data[3]+'街道');
                }
            });
        });
    };
    loadMap();
//    //自适应窗口
    $(window).resize(function() {
        resize();
    });
    function resize(id){
        var currTab = $('#tabs').tabs('getSelected');
        if(currTab.panel('options').title=="首页") {
            setTimeout(function () {
                console.info("resize");
                myChart.resize();
            }, 300);
        }else{
            setTimeout(function () {
                console.info("resize");
//                $(".easyui-datagrid",$("iframe").contents()).datagrid("resize");
            }, 300);
        }
    }

    function updateAll() {
        if(!$('#updateAllData').linkbutton("options").disabled) {
            $.messager.alert("提示", "已提交请求，请稍等！", "info");
            $('#updateAllData').linkbutton("disable");
//            $('#updateAllData').linkbutton("options",{"text":"分析中..."});
            var updateUrl = '<%=request.getContextPath() %>/app/data/calcAll';
            $.get(updateUrl, function (data) {
                if (data && data.successful) {
                    $.messager.alert("提示", "所有数据已分析完成", "info");
                } else {
                    $.messager.alert("提示", "系统出错。分析失败！", "error");
                }
                $('#updateAllData').linkbutton("enable");
//                $('#updateAllData').linkbutton("options",{"text":"分析所有数据"});
            });
        }else{
            $.messager.alert("提示", "后台正在执行分析，请稍等！", "info");
        }
    }
    
    setInterval(function () {
        var url = '<%=request.getContextPath() %>/app/checkSession';
        $.get(url, function (result) {
            if (!result.successful) {
                window.location.href = "<%=request.getContextPath() %>";
            }
        }, 'json');
    }, 60000);
</script>
</body>
</html>
