package com.zng.common.resource;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.common.entity.SysPagePath_;
import com.zng.system.user.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by John.Zhang on 2017/12/18.
 */
@Controller
public class PathController {

    @RequestMapping("login")
    public String getLoginPath(){
        return SysPagePath_.LOGIN;
    }

    @RequestMapping("/")
    public String getIndexPath(){
        return SysPagePath_.INDEX;
    }

    @RequestMapping("noAuth")
    @ResponseBody
    public ResponseModel noAuth(){
        return new ResponseModel(ResponseCode.NoAuth,"未授权");
    }

}
