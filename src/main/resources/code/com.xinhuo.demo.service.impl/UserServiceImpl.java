package com.xinhuo.demo.service.impl;

import com.xinhuo.demo.model.User;
import com.xinhuo.demo.dao.UserMapper;
import com.xinhuo.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 描述: 
 * author: 新林
 * date: 2019-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
