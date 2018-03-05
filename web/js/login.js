function login() {
    if(!checkInput('login-form')){
        return;
    }
    var formdata = $('#login-form').serializeArray();
    var parm = formFormate(formdata);
    SysApi.commenSubmit("http://192.168.6.68:8088/login",parm,function (data) {
        if(data.code == 200){
            formFadeOut();
            localStorage.setItem("session-id",data.data);
        }else{
            $('#login-tip').html(data.message);
            $('#login-tip').fadeIn(1000,function () {
                $('#login-tip').fadeOut(3000);
            });
        }
    })
    event.preventDefault();
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
        location.href = "index";
    },500);
}
