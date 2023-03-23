package com.AE.sgmis.service.impl;

import com.AE.sgmis.pojo.Urban;
import com.AE.sgmis.service.UrbanService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UrbanServiceImpl extends ServiceImpl<BaseMapper<Urban>, Urban> implements UrbanService {
}
