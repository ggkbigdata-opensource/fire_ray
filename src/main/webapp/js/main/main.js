function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url,subtitle),
            // href:url,
            closable: true
            //icon: icon
        });
    } else {
        var tab = $('#tabs').tabs('getTab',subtitle);
        $('#tabs').tabs('update',{tab:tab,options:{content: createFrame(url,subtitle)}});
        $('#tabs').tabs('select', subtitle);
        $('#mm-tabupdate').click();
    }
    tabClose();
}

//用于统计页面月份字段获取月份，value 2015-01 --> 01
function getMonth(value) {
    if (value) {
        var ym = value.split("-");
        if (ym && ym.length == 2) {
            return ym[1];
        }
    }
    return value;
}