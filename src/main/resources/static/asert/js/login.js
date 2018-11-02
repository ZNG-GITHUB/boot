function login() {
    if(!checkInput('login-form')){
        return;
    }
    var formdata = $('#login-form').serializeArray();
    var parm = formFormate(formdata);
    SysApi.commenSubmit("login",parm,function (data) {
        if(data.code == 200){
            localStorage.setItem("session-id",data.data);
            formFadeOut();
        }else{
            $('#login-tip').html(data.message);
            $('#login-tip').fadeIn(1000,function () {
                $('#login-tip').fadeOut(3000);
            });
        }
    });
    event.preventDefault();
}

function buildQrCode() {
    SysApi.getSubmitNoParm("api/loginQRCode",function (data) {
        if(data.code == 200){
            var key = data.data;
            $("#qrCode").val(key);

            var query = function () {
                SysApi.getSubmitNoParm("api/queryQRCodeStatus/"+key,function (data) {
                    if(data.code == 200){
                        var loginData = data.data;
                        if(loginData.code == 1){
                            console.log("正在等待确认");
                        }
                        if(loginData.code == 2){
                            console.log("登录成功:"+loginData.value);
                            localStorage.setItem("session-id",loginData.value);
                            formFadeOut();
                            return false;
                        }
                        if(loginData.code == 3){
                            console.log("二维码已失效")
                        }
                    }
                });
            };

            setInterval(query,2000);
        }
    });
}

function checkInput(formId) {
    var f = true;
    var requireds = $('#'+formId+ ' .required');
    requireds.each(function () {
        var em = $(this);
        if(em.val() == null || em.val() == "" || em.val() == undefined){
            em.select();
            f = false;
            return false;
        }
    });
    return f;
}

function formFadeOut() {
    $('form').fadeOut(500);
    $('.wrapper').addClass('form-success');
    setTimeout(function () {
        location.href = "Index.html";
    },500);
}
