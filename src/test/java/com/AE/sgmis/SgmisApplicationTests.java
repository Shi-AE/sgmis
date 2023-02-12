package com.AE.sgmis;

import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.util.EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SgmisApplicationTests {
    @Autowired
    private EncryptUtil encryptUtil;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setPassword(new byte[]{49, 50, 51, 52, 53, 54});
        user.setAccount("root");
        QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", user.getAccount());
        encryptUtil.passwordEncrypt(user);
        userMapper.update(user, accountQuery);
    }

}
