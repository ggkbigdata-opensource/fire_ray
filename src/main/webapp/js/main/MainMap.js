/**
 * Created by ZKT on 2016/10/18 018.
 */
// var darks=['#a3e0e0','#c2e0e0','#ffc2a3','#a3c2e0','#ffa3a3','#ffffa3','#e0e085','#e0c285','#a3a385','#66a3e0','#e0e0ff','#e0ffe0','#c2e0c2','#c2e0a3','#ffe066','#e085c2','#c285a3','#ffc2e0','#66a366','#8585c2','#c2e0ff','#ffe0c2','#c2e0c2','#a3e0ff','#e0e0c2','#a3a3c2','#a38566','#c2a3c2','#ffa366','#66c2e0'];
// var lights=['#daf3f3','#e7f3f3','#ffe7da','#dae7f3','#ffdada','#ffffda','#f3f3ce','#f3e7ce','#dadace','#c2daf3','#f3f3ff','#f3fff3','#e7f3e7','#e7f3da','#fff3c2','#f3cee7','#e7ceda','#ffe7f3','#c2dac2','#cecee7','#e7f3ff','#fff3e7','#fff3f3','#daf3ff','#f3f3e7','#dadae7','#dacec2','#e7dae7','#ffdac2','#c2e7f3'];
var colorLevel = ['#79d2ff','#e8ec39','#ff3e3e','#60a7cc','#a2a52a','#ad2a2a'];
function initMap(dom,data,mapName){
    var homeMap = echarts.init(dom);
    var option = {
        backgroundColor: '#FFFFFF',
        title: {
            text: '天河区消防安全热力图',
            subtext: '',
            sublink: '',
            left: 'center',
            textStyle: {
                color: '#f04836'
            }
        },
        /*tooltip : {
            trigger: 'item',
            formatter:function(params){
                var html = '<b>' + params.seriesName + '</b><br>';
                html = html + params.data[3] + 'Level : ' + params.data[2];
                return html;
            },
            textStyle:{
                color:'#f09836'
            }
        },*/
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x:'left',
            data:['警告'],
            textStyle: {
                color: '#ff6600'
            }
        },
       /* visualMap: {
            min:0,
            max:3,
            splitNumber: 6,
            inRange: {
                color: ['#ff0000','#ff6600','#ffc600','#ffea00','#00a608','#0106fd'].reverse()
            },
            textStyle: {
                color: '#f04836'
            }
        },*/
        geo: {
            map: mapName,
            // center:[113.326933,23.145313,9],//当前视角的中心点
            label: {
                normal:{
                    show:true //是否在普通状态下显示标签
                },
                emphasis: {
                    show: true
                }
            },
            roam: true,
            // regions:[{"name":"沙河街道","itemStyle":{"normal":{"areaColor":"#bebccc","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"林和街道","itemStyle":{"normal":{"areaColor":"#dacffb","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"沙东街道","itemStyle":{"normal":{"areaColor":"#aaadeb","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"兴华街道","itemStyle":{"normal":{"areaColor":"#eccfed","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"天河南街道","itemStyle":{"normal":{"areaColor":"#bddded","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"冼村街道","itemStyle":{"normal":{"areaColor":"#bacfed","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}},{"name":"猎德街道","itemStyle":{"normal":{"areaColor":"#dbecfd","borderColor":"#111"},"emphasis":{"areaColor":"#b0dc75"}}}]
        },
        series: [{
            name:'正常',
            type: 'effectScatter',
            data:data,
            coordinateSystem: 'geo',
            symbolSize:function(val){
                if(val[2] == 5){
                    return 10;
                }else{
                    return 0;
                }
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#79d2ff'
                }
            }
        },{
            name:'警告',
            type: 'effectScatter',
            data:data,
            coordinateSystem: 'geo',
            symbolSize:function(val){
                if(val[2] == 5){
                    return 10;
                }else{
                    return 0;
                }
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#e8ec39'
                }
            }
        },{
            name:'危险',
            type: 'effectScatter',
            data:data,
            coordinateSystem: 'geo',
            symbolSize:function(val){
                if(val[2] >= 5){
                    return 10;
                }else{
                    return 0;
                }
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#ff3e3e'
                }
            }
        }]
    };
    //获取颜色
    var maps = echarts.getMap(mapName);
    var regions = [];
    if(maps && maps.geoJson && maps.geoJson && maps.geoJson.features) {
        var t = convertData(data);
        $.each(maps.geoJson.features,function(i,v){
            var temp = {
                name:v.properties.name,
                itemStyle:{
                    normal:{
                        areaColor: colorLevel[t[v.properties.name] - 1],
                        borderColor: '#111'
                    },
                    emphasis: {
                        areaColor: colorLevel[t[v.properties.name] + 2]
                    }
                }
            };
            regions.push(temp);
        })
    }
    option.geo.regions = regions;
    // console.info("option",JSON.stringify(regions));
    // console.info("option",option);
    homeMap.setOption(option);
    return homeMap;
}

var getRandomColor = function(){
    return (function(m,s,c){
        return (c ? arguments.callee(m,s,c-1) : '#') +
            s[m.floor(m.random() * 16)]
    })(Math,'abcdefabcdefbcdef',5)
};

// var getColor = function(i){
//   if(i >= darks.length){
//       i = i%darks.length;
//   }
//   return {
//       dark:darks[i],
//       light:lights[i]
//   }
// };

var convertData = function (data) {
    var res = [];
    $.each(data,function (i,v) {
      //Array[4]
        res[v[3]] = v[2];
    });
    return res;
};

var geoCoordMap = {
    "从化市":[113.59286,23.554007],
    "花都区":[113.226783,23.409758],
    "增城市":[113.707065,23.331931],
    "白云区":[113.345156,23.298611],
    "萝岗区":[113.487145,23.187259],
    "荔湾区":[113.227586,23.104229],
    "越秀区":[113.270716,23.135463],
    "天河区":[113.368571,23.130388],
    "海珠区":[113.323465,23.089195],
    "黄埔区":[113.466087,23.112407],
    "番禺区":[113.391089,22.943638],
    "南沙区":[113.531584,22.808383]
};