package com.example.sys.service.impl;

import com.example.sys.entity.UserInfo;
import com.example.sys.mapper.UserInfoMapper;
import com.example.sys.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyb
 * @since 2022-07-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
