package com.AE.sgmis;

import com.AE.sgmis.mapper.UserMapper;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.PigeonService;
import com.AE.sgmis.util.EncryptUtil;
import com.AE.sgmis.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    void idTest() {
        for (int i = 0; i < 13; i++) {
            System.out.println(IdWorker.getId());
        }
    }

    @Autowired
    PigeonService pigeonService;

    @Test
    void pageTest() {
        //设置分页条件
        Page<Pigeon> page = new Page<>();
        page.setCurrent(2)
                .setSize(5)
                .setSearchCount(false);
        //条件 gid = gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", 1640546214887645185L);
        page = pigeonService.page(page, wrapper);
        System.out.println(page.getRecords());
        System.out.println(page.getRecords().size());
        System.out.println(page.getTotal());
    }
}