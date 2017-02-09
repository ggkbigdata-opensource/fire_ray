<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目概况表上传</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
    <style type="text/css">
        /*a  upload */
        .a-upload {
            padding: 25px 100px;
            height: 20px;
            line-height: 20px;
            position: relative;
            cursor: pointer;
            color: #888;
            background: #fafafa;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
            display: inline-block;
            text-decoration: none;
            margin-top: 40px;
            font-size: 28px;
            *display: inline;
            *zoom: 1
        }

        .a-upload  input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer
        }

        .a-upload:hover {
            color: #444;
            background: #eee;
            border-color: #ccc;
            text-decoration: none
        }
        .showFileName{
            margin-top: 15px;
            font-size: 24px;
        }
        .fileerrorTip{
            margin-top: 15px;
            color: red;
        }

        .uploadBuildingBtn{
            padding: 25px 100px;
            color: #fff;
            background: #57b6f7;
            border-radius: 10px;
            font-size: 28px;
            text-decoration: none;
            margin-right: 10px;
            display: inline-block;
        }
        .uploadBuildingBtn:hover{
            background:#E08216;
        }
    </style>
</head>
<body style="text-align:center">
    <div style="font-size: 28px;font-weight:bold;margin-top: 40px;">项目概况表上传：</div>
    <form id="building_upload_template" method="post" enctype="multipart/form-data" target="ajaxUpload">
<%--        <div style="margin-top:10px;">
            <input type="file" name="file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"style="width:400px;margin:20px;">
        </div>--%>
        <a href="javascript:;" class="a-upload">
            <input type="file"  id="file" name="file" accept="application/vnd.ms-excel" >点击这里上传文件
        </a>
        <div class="showFileName"></div>
        <div class="fileerrorTip"></div>
        <div style="margin-top:20px;">
          <a href="#" class="uploadBuildingBtn" plain="true"  iconCls="icon-print" onclick="uploadBuildingFile()">确认上传</a>
        </div>
    </form>

    <script type="text/javascript">
        $(".a-upload").on("change","input[type='file']",function(){
            var filePath=$(this).val();
            if(filePath.indexOf("xls")!=-1 || filePath.indexOf("xlsx")!=-1){
                $(".fileerrorTip").html("").hide();
                var arr=filePath.split('\\');
                var fileName=arr[arr.length-1];
                $(".showFileName").html(fileName);
            }else{
                $(".showFileName").html("");
                $(".fileerrorTip").html("上传文件类型有误！").show();
                return false
            }
        })


        function uploadBuildingFile(){
            $('#building_upload_template').form('submit',{
                url: '<%=request.getContextPath() %>/app/buildingSubject/uploadTemplate',
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if(result.successful){
                        alert("上传成功！");
                        $("#file").val("");
                    }else{
                        alert(result.msg);
                }
                }
            });
        }

    </script>
</body>
</html>