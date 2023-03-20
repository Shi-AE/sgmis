package com.AE.sgmis;

import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.impl.XxpzServiceImpl;
import com.AE.sgmis.util.EncryptUtil;
import com.AE.sgmis.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SgmisApplicationTests {
    @Autowired
    private EncryptUtil encryptUtil;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setPassword(new byte[]{55, 57, 57, 56, 50, 51});
        user.setAccount("root");
        QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", user.getAccount());
        encryptUtil.passwordEncrypt(user);
        userMapper.update(user, accountQuery);
    }

    @Test
    void testJwtUtil() {
        String token = jwtUtil.getToken("root");
        System.out.println(token);
    }

    @Test
    void idTest() {
        for (int i = 0; i < 25; i++) {
            System.out.println(IdWorker.getId());
        }
    }
}
