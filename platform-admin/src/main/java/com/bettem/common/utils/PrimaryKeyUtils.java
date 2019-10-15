package com.bettem.common.utils;

import java.util.UUID;

/**
 * 主键生成策略
 */
public class PrimaryKeyUtils {
    /** 生成主键策略 */
    public static String createId() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }
}
