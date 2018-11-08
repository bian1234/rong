var prefix = "/boke/project";



// 新增菜单信息提交，此处应该加校验
function submit01() {
	var formData = new FormData();
	var fileObj = $("#file_img").get(0).files[0];
    formData.append("fileObj",fileObj);
    formData.append("projectName",$("#projectName").val());
    formData.append("pageLink",$("#pageLink").val());
    formData.append("pageClass",$("#pageClass").val());
    formData.append("targetLink",$("#targetLink").val());
    if (fileObj.size > 1048576){
    	layer.msg("图片不能超过1M");
    }
        $.ajax({
            cache : true,
            type : "POST",
            url : prefix + "/insert",
            data : formData,
            async : false,
            processData : false,
            contentType : false,
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
