<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域下拉样式参考</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>
<body>
	<h2>区域下拉样式参考案例V1.0</h2>
	<h4>说明：</h4>
	<p>1、树结构非常直观展示各级数据多少有无；</p>
	<p>2、默认展示天河区数据；</p>
	<p>3、可输入名称进行模糊搜索；</p>
	<p>4、设置只可以选择子节点，目前是三级树结构，即有社区数据的话只能选择社区数据；</p>
	<p>5、选择完成后展示选择的层级数据；</p>
	<div style="margin:20px 0"></div>
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
	<input class="easyui-combotree" id="areaTree"  style="width:15%;height:26px"><br/><br/>
	<label id="fm_AETypePath" />
	
	<input type="radio" name="b_group" value="2">模板颜色：<select class="easyui-combobox"  name="state" style="width:200px;"   
        data-options="textField: 'text',   
                     formatter: formatItem">  
           <option value="" selected="selected">==请选择==</option>  
           <option value="red">red</option>  
           <option value="green">green</option>  
           <option value="gray">gray</option>  
           <option value="yellow">yellow</option>  
           <option value="blue">blue</option>  
           <option value="aqua">aqua</option>  
           <option value="purple">purple</option>  
           <option value="fuchsia">fuchsia</option>  
       </select><br/><br/> 

	<script type="text/javascript">
	function formatItem(row){  
        var s = '<div style="background-color:'+row.text+'";>' + row.text + '</div>';  
         return s;         
	} 
	$(function(){
		var queryAreaTreeUrl = "${pageContext.request.contextPath}/app/area/block/queryAreaTree?flag=3";
		$.getJSON(queryAreaTreeUrl , function (json) {
	           	$('#areaTree').combotree({
	              	 data: json.data,
	              	 required: true,
//	               //选择树节点触发事件
//	             	onBeforeSelect:function(node){
//						// 判断是否是叶子节点
//						var isLeaf = $(this).tree('isLeaf', node.target);
//						if (!isLeaf) {
//							 $.messager.alert("操作提示", "请选择叶子节点！", "error");
////							 返回false表示取消本次选择操作
//							return false;
//						}
//					},
					onLoadSuccess: function (node,data) {
						// 先关闭全部菜单
	                   	$('#areaTree').combotree('tree').tree("collapseAll");
//	                   	var t = $('#areaTree').combotree('tree');//获取tree对象
//	                    for (var i=0;i<data.length ;i++ ){
//	                        node= t.tree("find",data[i].id);
//	                        t.tree('expandAll',node.target);//展开所有节点
//	                     }
	                    $("#areaTree").combotree('setValue',"天河区");//在设置一下combotree的值即可，value为想选中的那个值，一般从后台取出来在设置的 。
	               	},
                	onSelect: function (item) {
	                    var parent = item;
	                    var tree = $('#areaTree').combotree('tree');
	                    var path = new Array();
	                    do {
	                        path.unshift(parent.text);
	                        var parent = tree.tree('getParent', parent.target);
	                    } while (parent);
	                    var pathStr = '';
	                    for (var i = 0; i < path.length; i++) {
	                        pathStr += path[i];
	                        if (i < path.length - 1) {
	                            pathStr += ' - ';
	                        }
	                    }
	                    $('#fm_AETypePath').text("您选择的是："+pathStr);
	                }
	           });
		});
	});
	
	function saveUser(){
		var t = $('#areaTree').combotree('tree'); // 得到树对象 
		var n = t.tree('getSelected'); // 得到选择的节点 
		//这里经过实践测试应该使用t.tree('getChecked');
		console.log(n);
		alert(n.text+"---"+n.id);
	}
	/** 
	 * combobox和combotree模糊查询 
	 */  
	(function(){  
	    //combobox可编辑，自定义模糊查询  
	    $.fn.combobox.defaults.editable = true;  
	    $.fn.combobox.defaults.filter = function(q, row){  
	        var opts = $(this).combobox('options');  
	        return row[opts.textField].indexOf(q) >= 0;  
	    };  
	    //combotree可编辑，自定义模糊查询  
	    $.fn.combotree.defaults.editable = true;  
	    $.extend($.fn.combotree.defaults.keyHandler,{  
	        up:function(){  
	            console.log('up');  
	        },  
	        down:function(){  
	            console.log('down');  
	        },  
	        enter:function(){  
	            console.log('enter');  
	        },  
	        query:function(q){  
	            var t = $(this).combotree('tree');  
	            var nodes = t.tree('getChildren');  
	            for(var i=0; i<nodes.length; i++){  
	                var node = nodes[i];  
	                if (node.text.indexOf(q) >= 0){  
	                    $(node.target).show();  
	                } else {  
	                    $(node.target).hide();  
	                }  
	            }  
	            var opts = $(this).combotree('options');  
	            if (!opts.hasSetEvents){  
	                opts.hasSetEvents = true;  
	                var onShowPanel = opts.onShowPanel;  
	                opts.onShowPanel = function(){  
	                    var nodes = t.tree('getChildren');  
	                    for(var i=0; i<nodes.length; i++){  
	                        $(nodes[i].target).show();  
	                    }  
	                    onShowPanel.call(this);  
	                };  
	                $(this).combo('options').onShowPanel = opts.onShowPanel;  
	            }  
	        }  
	    });  
	})(jQuery);   
	</script>
</body>
</html>