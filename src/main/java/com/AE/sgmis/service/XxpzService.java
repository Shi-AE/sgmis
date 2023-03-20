package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Xxpz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统选项配置服务
 */
public interface XxpzService extends IService<Xxpz> {
    /**
     * 根据类型加载所有系统选项
     */
    List<Xxpz> dynamicList(String tableName);

    /**
     * 根据类型，id删除系统选项
     */
    boolean dynamicRemoveById(Long id, String tableName);

    /**
     * 根据类型添加对应系统选项
     */
    boolean dynamicSave(Xxpz xxpz,String tableName);
}
