var prefix = "/system/menu";


// 菜单图标弹窗
function choseIcon() {
    layer.open({
        type: 2,
        title:'图标列表',
        content: prefix+'/fontIcoList',
        area: ['480px', '90%'],
        success: function(layero, index){
            //var body = layer.getChildFrame('.ico-list', index);
            //console.log(layero, index);
        }
    });
}

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			laryer.alert("未知错误");
		},
		success : function(data) {
            if (data.code ==2000) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

		}
	});

}
