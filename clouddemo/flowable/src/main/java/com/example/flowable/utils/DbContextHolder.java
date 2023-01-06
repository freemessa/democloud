package com.example.flowable.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbContextHolder {
    private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        log.debug("切换到{}", dbType.name());
        CONTEXT_HOLDER.set(dbType);
    }

    public static DBTypeEnum get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }

}

