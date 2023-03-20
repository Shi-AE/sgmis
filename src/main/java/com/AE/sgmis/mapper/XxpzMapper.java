package com.AE.sgmis.mapper;

import com.AE.sgmis.pojo.Xxpz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XxpzMapper extends BaseMapper<Xxpz> {
    /**
     * 根据表名查该表的所有选项配置
     */
    @Select("select id,name,author from ${tableName}")
    List<Xxpz> dynamicSelectList(String tableName);

    @Delete("delete from ${tableName} where id=#{id}")
    int dynamicDeleteById(Long id, String tableName);

    @Insert("insert into ${tableName} (id,name,author) value (#{xxpz.id}, #{xxpz.name}, #{xxpz.author})")
    int dynamicInsert(Xxpz xxpz, String tableName);
}
