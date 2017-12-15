package com.zng.common.resource;

import com.zng.common.entity.SysPagePath_;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by John.Zhang on 2017/12/14.
 */
@Path("")
public class PathResource {

    @GET
    @Path("say")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(){
        return SysPagePath_.LOGIN;
    }

}
