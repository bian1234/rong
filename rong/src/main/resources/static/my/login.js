var prePath = "http://localhost:10000/";
function register() {
    alert("跳转注册界面");
    $.ajax({
        type: "POST",
        url: prePath+"toRegister"
        // // data: $('#signupForm').serialize(),
        // success: function (r) {
        //     if (r.code == 20000) {
        //         var index = layer.load(1, {
        //             shade: [0.1,'#fff'] //0.1透明度的白色背景
        //         });
        //         parent.location.href = '/index';
        //     } else {
        //         layer.msg(r.msg);
        //     }
        // },
    });

}

function thirdParty() {
layer.msg("抱歉，暂未开放！");
}