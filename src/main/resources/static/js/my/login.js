// $(document).ready(function () {
//     $("#login").on('click',function(){$("#signupForm").submit();});
//     validateRule();
// });
//
// $.validator.setDefaults({
//     submitHandler: function () {
//         login();
//     }
// });

function login() {
    layer.alert("hah");
    $.ajax({
        type: "POST",
        url: "http://localhost:8888/login",
        data: $('#signupForm').serialize(),
        success: function (r) {
            if (r.code == 20000) {
                // var index = layer.load(1, {
                //     shade: [0.1,'#fff'] //0.1透明度的白色背景
                // });
                parent.location.href = '/index';
            } else {
                layer.msg("用户名或密码错误");
            }
        },
    });
}


function myRegister() {
    $.ajax({
        type: "get",
        url: "http://localhost:8888/register",
        // error: function () {
        //     if (r.code == 20000) {
        //         // var index = layer.load(1, {
        //         //     shade: [0.1,'#fff'] //0.1透明度的白色背景
        //         // });
        //         parent.location.href = '/index';
        //     } else {
        //         layer.msg("用户名或密码错误");
        //     }
        // },
    });
}
// function validateRule() {
//     var icon = "<i class='fa fa-times-circle'></i> ";
//     $("#signupForm").validate({
//         rules: {
//             username: {
//                 required: true
//             },
//             password: {
//                 required: true
//             }
//         },
//         messages: {
//             username: {
//                 required: icon + "请输入您的用户名",
//             },
//             password: {
//                 required: icon + "请输入您的密码",
//             }
//         }
//     })
// }