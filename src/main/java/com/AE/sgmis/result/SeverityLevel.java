package com.AE.sgmis.result;

public enum SeverityLevel {
    /**
     * 严重
     */
    Blocker(3600),
    /**
     * 中级
     */
    Critical(1800),
    /**
     * 初级
     */
    Major(600);

    private final long expire;

    SeverityLevel(long expire) {
        this.expire = expire;
    }

    public long getExpire() {
        return expire;
    }
}
