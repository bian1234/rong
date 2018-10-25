var prefix = "/system/dept"
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
                ajaxParams: {offset:0,limit:999}, // 请求数据的ajax的data属性
                expandColumn: '0',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: false, // 是否全部展开
              // toolbar : '#exampleToolbar',  //顶部工具条
                expanderExpandedClass: 'icon-chevron-down', // 展开的按钮的图标
                expanderCollapsedClass: 'icon-chevron-right' ,// 缩起的按钮的图标
                columns: [
                    {
                        title: '部门名称',
                        valign: 'center',
                        visible: false,
                        field: 'deptName',
                        width: '20%'
                    },
                    {
                        title: '权重',
                        valign: 'center',
                        visible: false,
                        field: 'orderNum',
                        width: '20%'
                    },
                    {
                        title: '备注信息',
                        valign: 'center',
                        visible: false,
                        field: 'remaks',
                        width: '20%'
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
        title: '增加部门',
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
        title: '修改部门信息',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function batchRemove() {
    // var rows = $('#exampleTable').bootstrapTable('getSelections');

}