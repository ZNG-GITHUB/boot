/**
 * Created by lean on 2017/9/15.
 */
$(document).ready(function(){
    var SysAjax = function(baseurl,type,parm,async,success, error) {
        var sysurl = baseurl;
        $.ajax({
            url: sysurl,
            type: type,
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
    var SysAjaxNoParm = function(baseurl,type,async,success, error) {
        var sysurl = baseurl;
        $.ajax({
            url: sysurl,
            type: type,
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
            SysAjax(url,"post",parm,false,success, error)
        },
        commenSubmitNoParm:function (url,success, error) {
            SysAjaxNoParm(url,"post",false,success, error)
        },
        getSubmit: function (url,parm, success, error) {
            SysAjax(url,"get",parm,false,success, error)
        },
        getSubmitNoParm:function (url,success, error) {
            SysAjaxNoParm(url,"get",false,success, error)
        }
    }
});

