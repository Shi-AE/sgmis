package com.AE.sgmis.service.impl;

import com.AE.sgmis.exception.NotFindUserException;
import com.AE.sgmis.exception.PasswordErrorException;
import com.AE.sgmis.mapper.LoginMsgMapper;
import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncryptUtil encryptUtil;


    @Override
    public Long loginVerify(User inputUser) {
        //查询用户
        QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", inputUser.getAccount());
        User user = userMapper.selectOne(accountQuery);

        if (user == null) {
            throw new NotFindUserException("该用户不存在");
        }

        //验证密码
        boolean passVerify = encryptUtil.passwordVerify(inputUser.getPassword(), user);

        if (!passVerify) {
            throw new PasswordErrorException("密码错误");
        }

        return user.getId();
    }

    @Override
    public void updateEncrypt(User user) {
        QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", user.getAccount());
        encryptUtil.passwordEncrypt(user);
        userMapper.update(user, accountQuery);
    }

    @Autowired
    private LoginMsgMapper loginMsgMapper;

    @Override
    public void addLoginMsg(LoginMsg loginMsg) {
        loginMsgMapper.insert(loginMsg);
    }

    @Override
    public List<LoginMsg> getLoginMsgList() {
        QueryWrapper<LoginMsg> list = new QueryWrapper<LoginMsg>()
                .orderByDesc("date")
                .last("limit 5");
        return loginMsgMapper.selectList(list);
    }
}
