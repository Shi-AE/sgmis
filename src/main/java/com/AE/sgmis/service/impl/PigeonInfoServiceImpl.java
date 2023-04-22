package com.AE.sgmis.service.impl;

import com.AE.sgmis.mapper.PigeonInfoMapper;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.service.PigeonInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PigeonInfoServiceImpl extends ServiceImpl<PigeonInfoMapper, PigeonInfo> implements PigeonInfoService {
}