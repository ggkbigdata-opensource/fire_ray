<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
<head>  
    <title></title>  
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
</head>  
<body>  
<form id="building_img"  method="post" enctype="multipart/form-data">  
    <table>  
        <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="thumb_img"/>      <button type="button" onclick="uploadFile()">提交</button></td>  
        </tr>  
    </table>  
</form>  
  <script type="text/javascript">
  var UrlConfig = {
			uploadImgUrl: '<%=request.getContextPath() %>/app/building/upload',
	};

  function uploadFile(){
		console.log("upload building111");
	    $('#building_img').form('submit',{
	        url: UrlConfig.uploadImgUrl,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	        }
	    });
	}
  
  </script>
</body>  
</html> 