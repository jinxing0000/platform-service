/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bettem.common.utils;
import com.google.common.collect.ImmutableMap;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2016-11-15
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN = "1";
    /**
     * 数据权限过滤
     */
    public static final String SQL_FILTER = "sql_filter";

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG("0"),
        /**
         * 菜单
         */
        MENU("1"),
        /**
         * 按钮
         */
        BUTTON("2");

        private String value;

        MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    /**
     * 数据删除状态（删除）
     */
    public static final String DELETE_STATE_YES = "1";
    /**
     * 数据删除状态（显示）
     */
    public static final String DELETE_STATE_NO = "0";

    /**
     * 用户正常状态
     */
    public static final String USER_STATE_YES = "01";

    /**
     * 用户正常禁用
     */
    public static final String USER_STATE_NO = "00";


    /**
     * redis启动状态
     */
    public static boolean REDIS_OPEN;
    /**
     * 登陆短信验证码模板
     */
    public static final String SMS_TEMPLATE_LOGIN = "1";
    /**
     * 上传文件头编码
     */
    public static final Map<String, String> fileTypeCode = new HashMap<String, String>() {
        {
            put("D0CF11E0", "doc");
            put("504B0304", "docx");
            put("D0CF11E0", "xls");
            put("504B0304", "xlsx");
            put("D0CF11E0", "ppt");
            put("504B0304", "pptx");
            put("25504446", "pdf");
            put("75736167", "text");
            put("7B5C727466", "rtf");
            put("D0CF11E0", "vsd");
            put("504B0304", "vsdx");

            put("464F524D", "aiff");
            put("49443303", "mp3");
            put("00000020", "mp4");
            put("00000018", "mp4");
            put("3026B275", "asf");
            put("504B0304", "fla");
            put("89504E47","flv");
            put("2E524D46","rmvb");
            put("2E524D46","rm");
            put("000001BA","mpg");
            put("000001BA","mpeg");
            put("46575306","swf");

            put("424D3859","bmp");
            put("47494638", "gif");
            put("FFD8FFE0", "jpg");
            put("FFD8FFE1", "jpeg");
            put("49492A00", "tif");
            put("49492A00", "tiff");
            put("89504E47", "png");
            put("1F8B0800", "gz");
            put("1F8B0800", "gzip");
            put("52617221", "rar");
            put("504B0304", "zip");
        }
    };
    /**
     * 供应商角色id
     */
    public static final String SUPPLIER_ROLE_ID = "7f97ee9779504115b2e3e909459d3d97";
}
