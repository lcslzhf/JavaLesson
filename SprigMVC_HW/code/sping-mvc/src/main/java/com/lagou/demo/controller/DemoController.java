package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;
import com.lagou.edu.mvcframework.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LagouController
@LagouRequestMapping("/demo")
public class DemoController {
    @LagouAutowired
    private IDemoService demoService;

    /**
     * URL: /demo/query?name=lisi
     *
     * @param request
     * @param response
     * @param name
     * @return
     */
    @LagouRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, String name) throws IOException {
        response.getWriter().write(demoService.get(name));
    }

    /**
     * URL: /demo/handle01?username=lisi
     *
     * @param request
     * @param response
     * @param username
     * @return
     */
    @Security({"lisi"})
    @LagouRequestMapping("/handle01")
    public void handle01(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        response.getWriter().write(demoService.get(username));
    }

    /**
     * URL: /demo/handle02?username=zhangsan
     *
     * @param request
     * @param response
     * @param username
     * @return
     */
    @Security({"zhangsan"})
    @LagouRequestMapping("/handle02")
    public void handle02(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        response.getWriter().write(demoService.get(username));
    }

    /**
     * URL: /demo/handle03?username=zhangsan
     *
     * @param request
     * @param response
     * @param username
     * @return
     */
    @Security({"zhangsan", "lisi"})
    @LagouRequestMapping("/handle03")
    public void handle03(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        response.getWriter().write(demoService.get(username));
    }
}
