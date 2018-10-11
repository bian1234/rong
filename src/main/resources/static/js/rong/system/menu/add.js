$(function() {
    // validateRule();
    //打开图标列表
    $("#ico-btn").click(function(){
        alert("hahha");

        layer.open({
            type: 2,
            title:'图标列表',
            content: '/FontIcoList.html',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });
});