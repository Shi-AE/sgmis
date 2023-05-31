package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_pigeon")
public class Pigeon {
    private Long id;
    /**
     * 更新日期
     */
    private LocalDate updateData;
    /**
     * 鸽棚巢箱名
     */
    private String gpcx;
    /**
     * 鸽子图片url
     */
    private String pictureUrl;
    /**
     * 足环
     */
    private String ringNumber;
    /**
     * 名称
     */
    private String name;
    /**
     * 血统名称
     */
    private String bloodline;
    /**
     * 性别
     */
    private String sex;
    /**
     * 父鸽id
     */
    private Long fid;
    /**
     * 母鸽id
     */
    private Long mid;
    /**
     * 眼色名称
     */
    private String yan;
    /**
     * 羽色名称
     */
    private String ys;
    /**
     * 类型名称
     */
    private String lx;
    /**
     * 状态名称
     */
    private String state;
    /**
     * 级别名称
     */
    private String jb;
    /**
     * 是否赛绩鸽
     */
    private String isGrade;
    /**
     * 备注
     */
    private String remark;
    /**
     * 组id
     */
    private Long gid;
}
