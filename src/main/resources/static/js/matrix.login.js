$(document).ready(function () {
    var login = $('#loginform');
    var recover = $('#recoverform');
    var speed = 400;
    // 忘记密码
    $('#to-recover').click(function () {
        $("#loginform").slideUp();
        $("#recoverform").fadeIn();
    });
    // 返回登录页
    $('#to-login').click(function () {
        $("#recoverform").hide();
        $("#loginform").fadeIn();
    });

    if ($.browser.msie == true && $.browser.version.slice(0, 3) < 10) {
        $('input[placeholder]').each(function () {
            var input = $(this);
            $(input).val(input.attr('placeholder'));
            $(input).focus(function () {
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            });
            $(input).blur(function () {
                if (input.val() == '' || input.val() == input.attr('placeholder')) {
                    input.val(input.attr('placeholder'));
                }
            });
        });
    }

    // 点击登录
    $('#login').click(function () {
        $.ajax({
            type: "POST",
            url: "/login",
            data: $('#loginform').serialize(),
            success: function (r) {
                if (r.code == 2000) {
                    var index = layer.load(1, {
                        shade: [0.1,'#fff'] //0.1透明度的白色背景
                    });
                    parent.location.href = '/index';
                } else if (r.code == 4004){
                    layer.msg("请检查用户名和密码");
                }else {
                    layer.msg("未知错误，请联系管理员");
                }
            },
        });
    });


});