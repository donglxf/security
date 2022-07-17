package com.example.sys.service.impl;

import com.example.sys.entity.Users;
import com.example.sys.mapper.UsersMapper;
import com.example.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyb
 * @since 2022-07-07
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
