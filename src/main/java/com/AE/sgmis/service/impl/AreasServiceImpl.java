package com.AE.sgmis.service.impl;

import com.AE.sgmis.pojo.Areas;
import com.AE.sgmis.service.AreasService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AreasServiceImpl extends ServiceImpl<BaseMapper<Areas>, Areas> implements AreasService {
}
