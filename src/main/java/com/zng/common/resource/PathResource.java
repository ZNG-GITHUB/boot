package com.zng.common.resource;

import com.zng.common.entity.SysPagePath_;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by John.Zhang on 2017/12/14.
 */
@Path("test")
public class PathResource {

    @GET
    public String test(){
        return "test";
    }
}
