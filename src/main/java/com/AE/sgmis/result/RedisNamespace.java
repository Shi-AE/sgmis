package com.AE.sgmis.result;

/**
 * redis命名空间枚举
 */
public enum RedisNamespace {

    Ancestor(null, ""),
    Whitelist(Ancestor, "whitelist:"),
    Blacklist(Ancestor, "blacklist:");

    RedisNamespace(RedisNamespace parent, String value) {
        this.value = parent == null ? "" : parent.value + value;
    }
    private final String value;

    public String getValue() {
        return value;
    }
}
