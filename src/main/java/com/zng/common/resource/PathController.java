package com.zng.common.resource;

import com.zng.common.config.ShiroConfig;
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
        return "redirect:" + ShiroConfig.loginUrl;
//        return SysPagePath_.LOGIN;
    }

    @GetMapping(value = "index")
    public String index(HttpServletResponse response){
        return SysPagePath_.INDEX;
    }

}
