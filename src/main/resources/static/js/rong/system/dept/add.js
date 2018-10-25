var prefix = "/system/dept";



// 新增菜单信息提交，此处应该加校验
function submit01() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/insert",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			laryer.alert("未知错误");
		},
		success : function(data) {
			if (data.code == 2000) {
				parent.layer.msg("保存成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				layer.alert(data.msg)
			}
		}
	});
}
