package com.AE.sgmis.service.impl;

import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
