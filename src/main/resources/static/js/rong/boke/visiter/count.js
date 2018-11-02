$().ready(function() {
    weekDetails();
});

//默认显示最近七天的数据
function weekDetails() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/boke/visiter/weekDetails",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:"最近七天的访问人数"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['访问人数']
                },
                grid: {
                    left: '4%',
                    right: '4%',
                    bottom: '4%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'访问人数',
                        type:'line',
                        data:map.visiter
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

}


//查询最近30天的数据
function moothDetails() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/boke/visiter/moothDetails",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:"最近三十天的访问人数"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['访问人数']
                },
                grid: {
                    left: '4%',
                    right: '4%',
                    bottom: '4%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'访问人数',
                        type:'line',
                        data:map.visiter
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
}

//显示最近的12个月份数据
function yearDetails() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/boke/visiter/yearDetails",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(map) {
            var myChart = echarts.init(document.getElementById('test'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text:"最近十二个月的访问人数"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['访问人数']
                },
                grid: {
                    left: '4%',
                    right: '4%',
                    bottom: '4%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: map.dates     //=======================这里是日期信息
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'访问人数',
                        type:'line',
                        data:map.visiter
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

}