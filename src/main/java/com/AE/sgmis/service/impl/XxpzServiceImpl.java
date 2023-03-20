package com.AE.sgmis.service.impl;

import com.AE.sgmis.mapper.XxpzMapper;
import com.AE.sgmis.pojo.Xxpz;
import com.AE.sgmis.service.XxpzService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XxpzServiceImpl extends ServiceImpl<BaseMapper<Xxpz>, Xxpz> implements XxpzService {
    @Autowired
    private XxpzMapper xxpzMapper;

    @Override
    public List<Xxpz> dynamicList(String tableName) {
        return xxpzMapper.dynamicSelectList(tableName);
    }

    @Override
    public boolean dynamicRemoveById(Long id, String tableName) {
        return xxpzMapper.dynamicDeleteById(id, tableName) == 1;
    }

    @Override
    public boolean dynamicSave(Xxpz xxpz, String tableName) {
        long id = IdWorker.getId();
        xxpz.setId(id);
        return xxpzMapper.dynamicInsert(xxpz, tableName) == 1;
    }
}
