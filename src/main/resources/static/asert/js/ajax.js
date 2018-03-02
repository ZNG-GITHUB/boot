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
                if(xhr.status == 401){
                    localStorage.removeItem("session-id");
                    location.href = "http://192.168.6.68:8089"
                }
            }
        });
    };
    window.SysApi = {
        commenSubmit: function (url,parm, success, error) {
            var sessionId = localStorage.getItem("session-id");
            if(sessionId){
                url = url+"?JSESSIONID="+sessionId;
            }
            SysAjax(url,"post",parm,false,success, error)
        },
        commenSubmitNoParm:function (url,success, error) {
            SysAjaxNoParm(url,"post",false,success, error)
        },
        getSubmit: function (url,parm, success, error) {
            SysAjax(url,"get",parm,false,success, error)
        },
        getSubmitNoParm:function (url,success, error) {
            var sessionId = localStorage.getItem("session-id");
            if(sessionId){
                url = url+"?JSESSIONID="+sessionId;
            }
            SysAjaxNoParm(url,"get",false,success, error)
        }
    }
});

