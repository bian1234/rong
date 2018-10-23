var prefix = "/system/menu"
$(document).ready(function () {
    load();
});

//  这里使用到了treegird 表格插件
var load = function () {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'id',
                code: 'id',
                parentCode: 'parentId',
                type: "GET", // 请求数据的ajax类型
                url: prefix + '/list', // 请求数据的ajax的url
                ajaxParams: {offset:0,limit:10}, // 请求数据的ajax的data属性
                expandColumn: '0',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: false, // 是否全部展开
              // toolbar : '#exampleToolbar',  //顶部工具条
                expanderExpandedClass: 'icon-chevron-down', // 展开的按钮的图标
                expanderCollapsedClass: 'icon-chevron-right' ,// 缩起的按钮的图标
                columns: [
                    // {
                    //     title: '编号',
                    //     field: 'id',
                    //     visible: true,
                    //     align: 'center',
                    //     valign: 'center',
                    //     width: '5%'
                    // },
                    {
                        title: '名称',
                        valign: 'center',
                        visible: false,
                        field: 'name',
                        width: '20%'
                    },

                    {
                        title: '图标',
                        field: 'icon',
                        align: 'center',
                        valign: 'center',
                        width : '5%',
                        formatter: function (item, index) {           // 格式化数据
                            return item.icon == null ? '': '<i class="icon '+item.icon+'"></i>';
                        }
                    },
                    {
                        title: '类型',
                        field: 'type',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {
                            if (item.type === 0) {
                                return '<span class="btn btn-mini btn-primary">目录</span>';
                            }
                            if (item.type === 1) {
                                return '<span class="btn btn-mini btn-success">菜单</span>';
                            }
                            if (item.type === 2) {
                                return '<span class="btn btn-mini btn-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        title: '地址',
                        valign: 'center',
                        width : '20%',
                        field: 'url',
                        formatter: function (item, index) {           // 格式化数据
                            return item.url == null ? '--': item.url;
                        }
                    },
                    {
                        title: '权限标识',
                        valign: 'center',
                        width : '20%',
                        field: 'perms',
                        formatter: function (item, index) {           // 格式化数据
                            return item.perms == null ? '--': item.perms;
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-mini btn-info'
                                // + s_edit_h
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.id
                                + '\')"><i class=" icon-pencil"></i></a> ';
                            var p = '<a class="btn btn-mini btn-success'
                                // + s_add_h
                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                                + item.id
                                + '\')"><i class=" icon-sitemap"></i></a> ';
                            var d = '<a class="btn btn-mini btn-danger'
                                // + s_remove_h
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item.id
                                + '\')"><i class=" icon-remove"></i></a> ';
                            return e + d + p;
                        }
                    }]
            });
}

function reLoad() {
    load();
}

function add(pId) {
    layer.open({
        type: 2,
        title: '增加菜单',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/delete",
            type: "post",
            data: {
                'id': id
            },
            success: function (data) {
                if (data.code == 2000) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    })
}

function edit(id) {
    layer.open({
        type: 2,
        title: '菜单修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function batchRemove() {
    // var rows = $('#exampleTable').bootstrapTable('getSelections');

}