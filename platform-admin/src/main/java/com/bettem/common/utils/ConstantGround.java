package com.bettem.common.utils;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 *   耕地地力补贴模块常量
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2016-11-15
 */
public class ConstantGround {

    /**
     * 武乡县RegionCode
     */
    public static final String WU_XIANG_XIAN_REGION_CODE = "140429";


    /**
     *  导入基础excel获取标题
     */
    public static final Map<Integer, Map<String,Object>> BASE_TITLE_MAP=new HashMap<Integer,Map<String,Object>>(){{
        put(0,ImmutableMap.of("titleName", "户主姓名", "type", "String", "attribute", "fullName","isNotNull",true,"length","30"));
        put(1,ImmutableMap.of("titleName", "身份证号", "type", "String", "attribute", "cardNumber","isNotNull",true,"length","18"));
        put(2,ImmutableMap.of("titleName", "收款人卡号或账号", "type", "String", "attribute", "bankCardNumber","isNotNull",true,"length","60"));
        put(3,ImmutableMap.of("titleName", "联系电话", "type", "String", "attribute", "contactNumber","isNotNull",true,"length","30"));
        put(4,ImmutableMap.of("titleName", "确权确地实测面积", "type", "number", "attribute", "confirmAreaM","isNotNull",true,"length","10,4"));
        put(5,ImmutableMap.of("titleName", "承包国有农场和良种场已确权的耕地", "type", "number", "attribute", "stateOwnAreaM","isNotNull",false,"length","10,4"));
        put(6,ImmutableMap.of("titleName", "流转转入耕地", "type", "number", "attribute", "changeIntoAreaM","isNotNull",false,"length","10,4"));
        put(7,ImmutableMap.of("titleName", "流转转出耕地", "type", "number", "attribute", "changeOutAreaM","isNotNull",false,"length","10,4"));
        put(8,ImmutableMap.of("titleName", "合计", "type", "number", "attribute", "noSubsidyAreaM","isNotNull",false,"length","10,4"));
        put(9,ImmutableMap.of("titleName", "畜牧养殖场地用地面积", "type", "number", "attribute", "noLivestockAreaM","isNotNull",false,"length","10,4"));
        put(10,ImmutableMap.of("titleName", "颁发林权证和退耕还林土地面积", "type", "number", "attribute", "noForestOwnerAreaM","isNotNull",false,"length","10,4"));
        put(11,ImmutableMap.of("titleName", "成为转为设施农业用地面积", "type", "number", "attribute", "noFacilitiesAreaM","isNotNull",false,"length","10,4"));
        put(12,ImmutableMap.of("titleName", "非农业征用占地面积", "type", "number", "attribute", "noAgricultureAreaM","isNotNull",false,"length","10,4"));
        put(13,ImmutableMap.of("titleName", "常年抛(撂)耕地面积", "type", "number", "attribute", "noAbandonAreaM","isNotNull",false,"length","10,4"));
        put(14,ImmutableMap.of("titleName", "质量达不到耕种条件面积", "type", "number", "attribute", "noCultivationAreaM","isNotNull",false,"length","10,4"));
        put(15,ImmutableMap.of("titleName", "申报补贴面积", "type", "number", "attribute", "subsidyAreaM","isNotNull",true,"length","10,4"));
        put(17,ImmutableMap.of("titleName", "id", "type", "String", "attribute", "id","isNotNull",false,"length","32"));
    }};
    /**
     *  导入基础excel获取其他信息
     */
    public static final Map<String,Integer[]> BASE_OTHERINFO_MAP=new HashMap<String,Integer[]>(){
        {
            put("villageName",new Integer[]{2,0});
            put("subsidyYear",new Integer[]{0,0});
        }
    };
    /**
     *  导入基础excel参数设置
     */
    public static final Map<String,Object> BASE_ANALYSIS_PARAMS=new HashMap<String,Object>(){{
        put("headTitleNum",5);
        put("lastTitleNum",2);
        put("OtherInfo",BASE_OTHERINFO_MAP);
        put("titleMap",BASE_TITLE_MAP);
    }};

    /**
     *  导入武乡县excel获取标题
     */
    public static final Map<Integer, Map<String,Object>> WU_XIANG_XIAN_TITLE_MAP=new HashMap<Integer,Map<String,Object>>(){{
        put(1,ImmutableMap.of("titleName", "户主姓名", "type", "String", "attribute", "fullName","isNotNull",true,"length","30"));
        put(2,ImmutableMap.of("titleName", "身份证号码", "type", "String", "attribute", "cardNumber","isNotNull",true,"length","18"));
        put(3,ImmutableMap.of("titleName", "银行“一折通”卡号", "type", "String", "attribute", "bankCardNumber","isNotNull",true,"length","60"));
        put(4,ImmutableMap.of("titleName", "确权面积（亩）", "type", "number", "attribute", "confirmAreaM","isNotNull",true,"length","10,4"));
        put(5,ImmutableMap.of("titleName", "“六不准”扣除面积", "type", "number", "attribute", "noSubsidyAreaM","isNotNull",false,"length","10,4"));
        put(6,ImmutableMap.of("titleName", "补贴面积", "type", "number", "attribute", "subsidyAreaM","isNotNull",true,"length","10,4"));
        put(7,ImmutableMap.of("titleName", "玉米", "type", "number", "attribute", "crainCerealCom","isNotNull",false,"length","10,4"));
        put(8,ImmutableMap.of("titleName", "谷子", "type", "number", "attribute", "crainCerealMillet","isNotNull",false,"length","10,4"));
        put(9,ImmutableMap.of("titleName", "小麦", "type", "number", "attribute", "crainCerealWheat","isNotNull",false,"length","10,4"));
        put(10,ImmutableMap.of("titleName", "其他作物", "type", "number", "attribute", "others","isNotNull",false,"length","10,4"));
        put(12,ImmutableMap.of("titleName", "联系电话", "type", "String", "attribute", "contactNumber","isNotNull",true,"length","30"));
        put(14,ImmutableMap.of("titleName", "id", "type", "String", "attribute", "id","isNotNull",false,"length","32"));
    }};
    /**
     *  导入武乡县excel获取其他信息
     */
    public static final Map<String,Integer[]> WU_XIANG_XIAN_OTHERINFO_MAP=new HashMap<String,Integer[]>(){
        {
            put("villageName",new Integer[]{1,0});
            put("subsidyYear",new Integer[]{0,0});
        }
    };
    /**
     *  导入武乡县excel参数设置
     */
    public static final Map<String,Object> WU_XIANG_XIAN_ANALYSIS_PARAMS=new HashMap<String,Object>(){{
        put("headTitleNum",4);
        put("lastTitleNum",4);
        put("OtherInfo",WU_XIANG_XIAN_OTHERINFO_MAP);
        put("titleMap",WU_XIANG_XIAN_TITLE_MAP);
        put("titleText","武乡县,年农业支持保护补贴种植作物面积核实清册表");
        put("villageText","（镇）,（章）");
    }};


    /**
     *  特殊县导入模板设置
     */
    public static final Map<String,Object> SPECIAL_COUNTY_EXCEL_MAP=new HashMap<String,Object>(){{
        put(WU_XIANG_XIAN_REGION_CODE,WU_XIANG_XIAN_ANALYSIS_PARAMS);
    }};
}
