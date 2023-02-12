package com.AE.sgmis.service.impl;

import com.AE.sgmis.exception.NotFindUserException;
import com.AE.sgmis.exception.PasswordErrorException;
import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncryptUtil encryptUtil;

    @Override
    public void loginVerify(User inputUser) {
        QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", inputUser.getAccount());
        User user = userMapper.selectOne(accountQuery);

        if (user == null) {
            throw new NotFindUserException("该用户不存在");
        }

        boolean passVerify = encryptUtil.passwordVerify(inputUser.getPassword(), user);

        if (!passVerify) {
            throw new PasswordErrorException("密码错误");
        }

        encryptUtil.passwordEncrypt(inputUser);
        userMapper.update(inputUser, accountQuery);
    }
}
