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
            var sessionId = localStorage.getItem("session-id");
            if(sessionId){
                url = url+"?JSESSIONID="+sessionId;
            }
            SysAjax(url,parm,false,success, error)
        },
        commenSubmitNoParm:function (url,success, error) {
            var sessionId = localStorage.getItem("session-id");
            if(sessionId){
                url = url+"?JSESSIONID="+sessionId;
            }
            SysAjaxNoParm(url,"post",false,success, error)
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

