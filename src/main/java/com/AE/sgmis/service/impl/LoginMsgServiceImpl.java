package com.AE.sgmis.service.impl;

import com.AE.sgmis.mapper.LoginMsgMapper;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.service.LoginMsgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginMsgServiceImpl extends ServiceImpl<LoginMsgMapper, LoginMsg> implements LoginMsgService {

    @Autowired
    private LoginMsgMapper loginMsgMapper;
    @Value("${log.maxHistory}")
    private Integer maxHistory;
    @Override
    public int removeForCountTask() {
        //获取历史时间
        LocalDateTime historyTime = LocalDateTime.now().minusDays(maxHistory);

        QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
        wrapper.lt("time", historyTime);

        return loginMsgMapper.delete(wrapper);
    }
}