package com.AE.sgmis.result;

public enum LogType {
    /**
     * 新增
     */
    INSERT(0, "新增鸽子"),
    /**
     * 修改
     */
    UPDATE(1, "修改鸽子"),
    /**
     * 删除
     */
    DELETE(2, "删除鸽子"),
    /**
     * 共享
     */
    SHARE(3, "共享血统"),
    /**
     * 接收血统
     */
    RECEIVE(4, "接收血统"),
    /**
     * 关联血亲
     */
    RELATE(5, "关联血亲"),
    /**
     * 解除血亲关系
     */
    UNPARENT(6, "解除血亲关系"),
    /**
     * 转移鸽棚巢箱
     */
    TRANSFER(7, "转移鸽棚巢箱");


    private final int index;
    private final String tip;

    LogType(int index, String tip) {
        this.index = index;
        this.tip = tip;
    }

    public int getIndex() {
        return index;
    }

    public String getTip() {
        return tip;
    }
}
