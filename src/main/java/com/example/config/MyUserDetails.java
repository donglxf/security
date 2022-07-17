package com.example.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.entity.UserInfo;
import com.example.sys.entity.Users;
import com.example.sys.mapper.UserInfoMapper;
import com.example.sys.mapper.UsersMapper;
import com.example.sys.vo.SecurityUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called()" + username);
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Users userInfo = userInfoMapper.selectOne(queryWrapper);
        if (null == userInfo) {
            throw new UsernameNotFoundException("");
        }
        // 权限字符串
        String permission[] = {"user", "admin", "tbs"};
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        Arrays.stream(permission).forEach(item -> {
            list.add(new SimpleGrantedAuthority(item));
        });
        System.out.println("zs:==" + passwordEncoder.encode("zs"));
        return new SecurityUserInfo(username, userInfo.getPassword(), list);
//        return new User(username, userInfo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
