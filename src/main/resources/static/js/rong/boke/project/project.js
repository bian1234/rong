var prefix = "/boke/project";
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : {"offset":0,"limit":999},
                // 返回false将会终止请求
                columns : [
                    { // 列配置项
                        // 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
                        checkbox : true
                        // 列表中显示复选框
                    },
                    // {
                    //     title : '缩略图',
                    //     field : 'id',
                    //     align : 'center',
                    //     formatter : function(value, row, index) {
                    //         var e = '<image src="\''+row.pageAddress+row.pageName+'\'"/>';
                    //         return e;
                    //     }
                    // },
                    {
                        field : 'projectName',
                        title : '项目名称'
                    },

                    {
                        field : 'pageName',
                        title : '图片名称'
                    },
                    {
                        field : 'pageAddress',
                        title : '图片地址'
                    },
                    {
                        field : 'pageLink',
                        title : '项目链接地址'
                    },
                    {
                        field : 'pageClass',
                        title : '项目分类'
                    },
                    {
                        field : 'targetLink',
                        title : '打开方式',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            if (item == "_blank") {
                                return '<p>新窗口</p>';
                            }
                            if (item == '_top') {
                                return '<p>打开新的浏览器</p>';
                            }
                            if (item == "_parent") {
                                return '<p>本页面打开</p>';
                            }
                        }
                    },

                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-mini btn-info'
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class=" icon-pencil"></i></a> ';
                            var d = '<a class="btn btn-mini btn-danger'
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class=" icon-remove"></i></a> ';
                            return e + d;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    // iframe层
    layer.open({
        type : 2,
        title : '添加项目经验',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' //
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/delete",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 2000) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg("删除失败");
                }
            }
        });
    })

}
function edit(id) {
    layer.open({
        type : 2,
        title : '角色修改',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}
function batchRemove() {

    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
    }, function() {
        var ids = new Array();
        $.each(rows, function(i, row) {
            ids[i] = row['id'];
        });
        console.log(ids);
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/deleteBatch',
            success : function(r) {
                if (r.code == 2000) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg("删除失败");
                }
            }
        });
    }, function() {});
}