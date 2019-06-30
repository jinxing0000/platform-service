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
     *  补贴状态-(补贴年度)
     */
    public static int SUBSIDY_YEAR = 0;
    /**
     * 静态方法获取当前年份
     */
    static{
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        SUBSIDY_YEAR=year;
    }
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
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
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
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
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
     *  补贴状态-(未生成明细)
     */
    public static final String SUBSIDY_STATE_NOT_GENER = "100";
    /**
     *  补贴状态-(已生成明细)
     */
    public static final String SUBSIDY_STATE_GENER = "110";
    /**
     *  补贴状态-(待下发)
     */
    public static final String SUBSIDY_STATE_NO_LOWER = "200";
    /**
     *  补贴状态-(下发)
     */
    public static final String SUBSIDY_STATE_LOWER = "210";
    /**
     *  补贴状态-(待公示)
     */
    public static final String SUBSIDY_STATE_NO_PUBLIC = "220";
    /**
     *  补贴状态-(设定公示时间)
     */
    public static final String SUBSIDY_STATE_PUBLIC_SET_UP = "300";
    /**
     *  补贴状态-(公示中)
     */
    public static final String SUBSIDY_STATE_PUBLIC = "310";
    /**
     *  补贴状态-(公示结束-待上报)
     */
    public static final String SUBSIDY_STATE_PUBLIC_END = "320";
    /**
     *  补贴状态-(提交到乡镇)
     */
    public static final String SUBSIDY_STATE_COMMIT_TOWN = "400";
    /**
     *  补贴状态-(乡镇驳回)
     */
    public static final String SUBSIDY_STATE_TOWN_REJECT = "410";
    /**
     *  补贴状态-(乡镇通过)
     */
    public static final String SUBSIDY_STATE_TOWN_AGREE = "420";
    /**
     *  补贴状态-(乡镇待上报)
     */
    public static final String SUBSIDY_STATE_TOWN_AGREE_ALL = "430";
    /**
     *  补贴状态-(乡镇上报到县主管)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY = "440";
    /**
     *  补贴状态-(县主管驳回)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_REJECT = "441";
    /**
     *  补贴状态-(县主管通过)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_AGREE = "449";
    /**
     *  补贴状态-(县主管待上报)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_ALL = "450";
    /**
     *  补贴状态-(县主管上报到县财政)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_FINANCE = "455";
    /**
     *  补贴状态-(县财政驳回)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_REJECT = "456";
    /**
     *  补贴状态-(县财政通过待上报)
     */
    public static final String SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_ALL = "459";
    /**
     *  补贴状态-(县财政上报到市)
     */
    public static final String SUBSIDY_STATE_COMMIT_CITY = "460";
    /**
     *  补贴状态-(市驳回)
     */
    public static final String SUBSIDY_STATE_COMMIT_CITY_REJECT = "461";
    /**
     *  补贴状态-(市通过)
     */
    public static final String SUBSIDY_STATE_COMMIT_CITY_AGREE = "469";
    /**
     *  补贴状态-(市待上报)
     */
    public static final String SUBSIDY_STATE_COMMIT_CITY_ALL = "470";
    /**
     *  补贴状态-(市上报到省)
     */
    public static final String SUBSIDY_STATE_COMMIT_PROVINCE = "480";
    /**
     *  补贴状态-(省驳回)
     */
    public static final String SUBSIDY_STATE_COMMIT_PROVINCE_REJECT = "481";
    /**
     *  补贴状态-(省通过)
     */
    public static final String SUBSIDY_STATE_COMMIT_PROVINCE_AGREE = "489";
    /**
     *  补贴状态-(市全部上报到省)
     */
    public static final String SUBSIDY_STATE_COMMIT_PROVINCE_ALL = "490";
    /**
     *  补贴状态-(已完成)
     */
    public static final String SUBSIDY_STATE_END = "990";

    /**
     *  下发业务区域code长度和补贴状态Map
     */
    public static final Map<Integer, String[]> lowerRegionSubsidyStateMap = new HashMap<Integer, String[]>() {
        {
            //省级用户
            put(2,new String[]{});
            //市级用户
            //put(4,new String[]{Constant.SUBSIDY_STATE_GENER,Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_CITY_ALL,Constant.SUBSIDY_STATE_COMMIT_PROVINCE,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_COMMIT_PROVINCE_REJECT,Constant.SUBSIDY_STATE_COMMIT_PROVINCE_AGREE});
            put(4,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_CITY_ALL,Constant.SUBSIDY_STATE_COMMIT_PROVINCE,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_COMMIT_PROVINCE_REJECT,Constant.SUBSIDY_STATE_COMMIT_PROVINCE_AGREE});
            //县级用户
            put(6,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_COUNTY,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_COUNTY_ALL,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_CITY_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY_AGREE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_ALL});
            //乡镇级用户
            put(9,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_TOWN,Constant.SUBSIDY_STATE_COMMIT_COUNTY,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_TOWN_AGREE_ALL,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_PUBLIC ,Constant.SUBSIDY_STATE_PUBLIC_END,Constant.SUBSIDY_STATE_NO_PUBLIC,Constant.SUBSIDY_STATE_COMMIT_COUNTY_AGREE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_REJECT,Constant.SUBSIDY_STATE_PUBLIC_SET_UP});
            //村级用户
            put(12,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_NO_PUBLIC,Constant.SUBSIDY_STATE_PUBLIC,Constant.SUBSIDY_STATE_PUBLIC_SET_UP,Constant.SUBSIDY_STATE_PUBLIC_END,Constant.SUBSIDY_STATE_COMMIT_TOWN,Constant.SUBSIDY_STATE_TOWN_AGREE,Constant.SUBSIDY_STATE_END});
        }
    };

    /**
     *  角色id和补贴状态Map
     */
    public static final Map<String, String[]> regionRoleSubsidyStateMap = new HashMap<String, String[]>() {
        {
            //县财政局
            put(Constant.COUNTY_FINANCE_ID,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_COUNTY,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_COUNTY_ALL,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_CITY_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY_AGREE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_ALL});
            //县主管部门
            put(Constant.COUNTY_ID,new String[]{Constant.SUBSIDY_STATE_NO_LOWER,Constant.SUBSIDY_STATE_LOWER,Constant.SUBSIDY_STATE_COMMIT_COUNTY,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_COUNTY_ALL,Constant.SUBSIDY_STATE_END,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY,Constant.SUBSIDY_STATE_COMMIT_CITY_REJECT,Constant.SUBSIDY_STATE_COMMIT_CITY_AGREE,Constant.SUBSIDY_STATE_COMMIT_COUNTY_FINANCE_ALL});
        }
    };
    /**
     *  下发分级查询业务
     */
    public static final String BUSINESS_TYPE_LOWER_SELECT_LIST = "lowerSelectList";

    /**
     *  县主管部门角色id
     */
    public static final String COUNTY_ID = "5cd64d3a4244499195aa49f7bcd0e3f5";
    /**
     *  县财政部门角色id
     */
    public static final String COUNTY_FINANCE_ID = "85dfe73f0a7242c38123c4c5bd438e14";

    /**
     * 补贴数据状态（1为初始）
     */
    public static final String SUBSIDY_DATA_STATE_INIT = "1";
    /**
     * 补贴数据状态（2为修改）
     */
    public static final String SUBSIDY_DATA_STATE_UPDATE = "2";
    /**
     * 补贴数据状态（3为新增）
     */
    public static final String SUBSIDY_DATA_STATE_ADD = "3";
    /**
     * 补贴数据状态（4为删除）
     */
    public static final String SUBSIDY_DATA_STATE_DELETE = "4";

    /**
     * 身份证号码隐藏长度
     */
    public static final Integer CARD_NUMBER_HIDE_LENGTH = 4;
    /**
     * 18身份证号码从倒数第4位开始隐藏
     */
    public static final Integer CARD_NUMBER_EIGHTEEN_HIDE_END = 4;
    /**
     * 15身份证号码从倒数第3位开始隐藏
     */
    public static final Integer CARD_NUMBER_FIFTEEN_HIDE_END = 3;

    /**
     * 生成耕地地力补贴明细数据PDF模板路径
     */
    public static final String GENERATE_GROUND_DTL_PDF_TEMPLATE_PATH = "freemarker/villageDetailedPdf.html";

    /**
     * 生成耕地地力补贴明细数据特殊县PDF模板路径
     */
    public static final String GENERATE_SPECIAL_GROUND_DTL_PDF_TEMPLATE_PATH = "freemarker/villageDetailedPdf_140429.html";

    /**
     * 检查excel表错误提示
     */
    public static final Map<String, String>  EXCEL_ERROR_MSG = new HashMap<String, String>() {
        {
            put("STRING","字符类型");
            put("NUMERIC","数值类型");
        }
    };
    /**
     * 不包含拓展表年份
     */
    public static final String NO_EXT_YEAR = "2018";

    /**
     * 武乡县RegionCode
     */
    public static final String WU_XIANG_REGION_CODE = "140429";

    /**
     *  补贴类型
     */
    public enum SubidyType {
        /**
         * 耕地地力补贴
         */
        GROUND("ground");

        private String value;

        SubidyType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     *  补贴类型
     */
    public enum GroundTemplateType {
        /**
         * 明细
         */
        DTL("dtl"),
        /**
         * 村级合计
         */
        VILLAGE("village"),
        /**
         * 乡级合计
         */
        TOWN("town"),
        /**
         * 县级合计
         */
        COUNTY("county"),
        /**
         * 市级合计
         */
        CITY("city"),

        /**
         * 乡镇级公示
         */
        PUBLICITY("publicity");

        private String value;

        GroundTemplateType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
