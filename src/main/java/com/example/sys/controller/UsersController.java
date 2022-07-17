package com.example.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.sys.mapper.UsersMapper;
import com.example.sys.vo.SecurityUserInfo;
import com.example.sys.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dyb
 * @since 2022-07-07
 */
@Controller
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;



    @PreAuthorize("hasAuthority('tbs')")
    @RequestMapping("/test")
    public String test(TestVo vo, Model model) {
        System.out.println(JSONObject.toJSONString(vo));
        model.addAttribute("swt", "admin");
        List<TestVo> list = new ArrayList<>();
        list.add(vo);
        model.addAttribute("list", list);
        return "test";
    }



    @RequestMapping("/regedit")
    public String regedit(TestVo vo, Model model) {
        String pwd = new BCryptPasswordEncoder().encode(vo.getPwd());

        return "test";
    }

}
