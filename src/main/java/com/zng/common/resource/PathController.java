package com.zng.common.resource;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.common.entity.SysPagePath_;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by John.Zhang on 2017/12/18.
 */
@Controller
public class PathController {

    @GetMapping({"","login"})
    public String getLoginPath(){
        return SysPagePath_.LOGIN;
    }

    @GetMapping(value = "noAuth")
    @ResponseBody
    public ResponseModel noAuth(HttpServletResponse response){
        try {
            response.sendRedirect("http://127.0.0.1:8020/web/noAuth.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseModel(ResponseCode.NoAuth,"未认证");
    }

}
