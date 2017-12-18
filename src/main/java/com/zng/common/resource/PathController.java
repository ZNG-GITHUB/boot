package com.zng.common.resource;

import com.zng.common.entity.SysPagePath_;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by John.Zhang on 2017/12/18.
 */
@Controller
public class PathController {

    @GetMapping({"","login"})
    public String getLoginPath(){
       return SysPagePath_.LOGIN;
    }

}
