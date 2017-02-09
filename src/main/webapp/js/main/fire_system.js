var column =[
    { field: 'id', title: 'ID', hidden: true },
    { field: 'systemConstituentName', title: '系统组件名称',width:30},
    { field: 'modelSize', title: '型号规格' ,editor:"text",width:30},
    { field: 'amount', title: '数量（台/个）' ,editor:"numberbox",width:30},
    { field: 'manufacturer', title: '生产厂家' ,editor:"text",width:30},
    { field: 'position', title: '设置位置' ,editor:"text",width:30},
    { field: 'useTime', title: '投入使用时间(年月)' ,editor:"text",width:40},
    { field: 'useSituation', title: '使用状况' ,editor:"text",width:30},
    { field: 'volume', title: '容积' ,editor:
            {
                type:'numberbox',
                options:{
                    precision:2
                }
            },
        width:30},
    { field: 'remark', title: '备注' ,editor:"text",width:30}
];

var tbSaveChangesUrl;
//下面是单元格编辑
var editIndex = undefined;
function bindData(title,tableEle,url,saveChangesUrl) {
    tbSaveChangesUrl = saveChangesUrl;
    $(tableEle).datagrid({
        title:title,
        url:url,
        fitColumns:true,
        singleSelect: true,
        autoRowHeight:false,
        rownumbers: true,
        collapsible:true,
        onClickRow: onClickRow,
        columns:[column]
    });

}

//编辑状态
function endEditing(tableEle) {
    if (editIndex == undefined) { return true }
    if ($(tableEle).datagrid('validateRow', editIndex)) {
        var ed = $(tableEle).datagrid('getEditor', { index: editIndex, field: 'id' });
        $(tableEle).datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

//单击某行进行编辑  table 的id 跟数据库系统名称一样
function onClickRow(index,row) {
    // var table_id = $(this).parents(".datagrid-view").children("table").attr("id");
    if (editIndex != index) {
        if (endEditing('#'+row.systemName)) {
            $('#'+row.systemName).datagrid('selectRow', index)
                .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#'+row.systemName).datagrid('selectRow', editIndex);
        }
    }
}

//撤销编辑   重置按钮事件
function reject(tableEle) {
    $(tableEle).datagrid('rejectChanges');
    editIndex = undefined;
}

//获得编辑后的数据,并提交到后台
function saveChanges(tableEle) {
    if (endEditing(tableEle)) {
        var $dg = $(tableEle);
        var rows = $dg.datagrid('getChanges');
        if (rows.length) {
            var effectRow = new Object();
//            var inserted = $dg.datagrid('getChanges', "inserted");
//            var len =  inserted.length;
//            for(var i = 0; i < len; i++){
//                // 默认传值过去
//                // （一）消火栓系统  hydrant
//                inserted[i]['systemName'] = 'hydrant';
//            }
//            if (inserted.length) {
//                effectRow["inserted"] = JSON.stringify(inserted);
//            }
//
//            var deleted = $dg.datagrid('getChanges', "deleted");
//            if (deleted.length) {
//                effectRow["deleted"] = JSON.stringify(deleted);
//            }

            var updated = $dg.datagrid('getChanges', "updated");
            if (updated.length) {
                effectRow["updated"] = JSON.stringify(updated);
            }
        }
    }
    // 更新保存数据
    $.post(
        tbSaveChangesUrl,
        effectRow,
        function (rsp) {
            if (rsp) {
                if(rsp.successful){
                    $.messager.alert("操作提示", rsp.data, "info");
                    $dg.datagrid('acceptChanges');
                    $(tableEle).datagrid('reload');
                }else{
                    $.messager.alert("操作提示", "输入有误【"+rsp.msg+"】", "error");
                }
            }
        }, "JSON").error(function () {
        $.messager.alert("提示", "提交错误了！","error");
    });

}



//以下五个方法暂时没用到
function bindSaveButton() {
    $("#saveButton").click(function () {
        saveChanges();
    });
}

function bindAddButton() {
    $("#addButton").click(function () {
        append();
    });
}
function bindDelButton() {
    $("#delButton").click(function () {
        remove();
    });
}

//添加一行
function append() {
    if (endEditing('#dg_fire_system_001')) {
        $('#dg_fire_system_001').datagrid('appendRow', {  });
        editIndex = $('#dg_fire_system_001').datagrid('getRows').length - 1;
        $('#dg_fire_system_001').datagrid('selectRow', editIndex)
            .datagrid('beginEdit', editIndex);
    }
}
//删除一行
function remove() {
    if (editIndex == undefined) { return }
    $('#dg_fire_system_001').datagrid('cancelEdit', editIndex)
        .datagrid('deleteRow', editIndex);
    editIndex = undefined;
}