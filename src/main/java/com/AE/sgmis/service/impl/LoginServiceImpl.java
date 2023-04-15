package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.NotFindUserException;
import com.AE.sgmis.exceptions.PasswordErrorException;
import com.AE.sgmis.exceptions.PasswordUpdateFailException;
import com.AE.sgmis.mapper.LoginMsgMapper;
import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncryptUtil encryptUtil;


    @Override
    public User loginVerify(User inputUser) {
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

        //传入密码以更新加密
        user.setPassword(inputUser.getPassword());

        return user;
    }

    @Override
    public void updateEncrypt(User user) {
        encryptUtil.passwordEncrypt(user);
        int updateSuccess = userMapper.updateById(user);
        if (updateSuccess != 1) {
            log.error("传入用户参数 {} 时发生系统更新错误", user);
            throw new PasswordUpdateFailException("密码更新发生错误");
        }
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
