package com.zng.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by John.Zhang on 2017/12/14.
 */
@RestController
public class PathController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello,Zng!";
    }
}
