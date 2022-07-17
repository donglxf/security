package com.example.sys.controller;

import com.example.sys.entity.Users;
import com.example.sys.vo.SecurityUserInfo;
import com.example.sys.vo.TestVo;
import com.example.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    AuthenticationManager authenticationManager;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/webLogin")
    @ResponseBody
    public JsonResult<SecurityUserInfo> webLogin(Users vo) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(vo.getUsername(), vo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new JsonResult<SecurityUserInfo>(true, (SecurityUserInfo) authenticate.getPrincipal());
    }

    @RequestMapping("/success")
    public String success() {
        return "index";
    }

    @RequestMapping("/fail")
    public String fail() {
        return "fail";
    }


}
