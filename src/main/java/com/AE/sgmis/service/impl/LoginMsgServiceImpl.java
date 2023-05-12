package com.AE.sgmis.service.impl;

import com.AE.sgmis.mapper.LoginMsgMapper;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.service.LoginMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LoginMsgServiceImpl extends ServiceImpl<LoginMsgMapper, LoginMsg> implements LoginMsgService {
}