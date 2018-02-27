/**
 * Created by lean on 2017/9/15.
 */
$(document).ready(function(){
    var SysAjax = function(baseurl,parm,async,success, error) {
        var sysurl = baseurl;
        console.log("传入" + JSON.stringify(parm));
        $.ajax({
            url: sysurl,
            type: "post",
            async: async,
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(parm),
            success: success,
            error: function(xhr, type, errorThrown) {
                console.log(sysurl);
                console.log(type);
                console.log(xhr.status);
                console.log(errorThrown);
            }
        });
    };
    var SysAjaxNoParm = function(baseurl,async,success, error) {
        var sysurl = baseurl;
        $.ajax({
            url: sysurl,
            type: "post",
            async: async,
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: success,
            error: function(xhr, type, errorThrown) {
                console.log(sysurl);
                console.log(type);
                console.log(xhr.status);
                console.log(errorThrown);
            }
        });
    };
    window.SysApi = {
        commenSubmit: function (url,parm, success, error) {
            SysAjax(url,parm,false,success, error)
        },
        commenSubmitNoParm:function (url,success, error) {
            SysAjaxNoParm(url,false,success, error)
        }
    }
});

