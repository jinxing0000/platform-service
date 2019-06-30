package com.bettem.common.excel.util;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * @ClassName Publicity
 * @Decription: 导出Excel表头模板
 * @Date: 2019/5/515:31
 * @Author: 高敏
 * @Version: 1.0
 */
public class ExcelHeadTemp {
//    /**
//     * @Author 高敏
//     * @Description 村级公示表头  该表头为4行7列  headCoulumn为每一列,包含四行数据内容  相邻单元格内容一样会自动合并单元格
//     * @Date 2019/5/5 15:35
//     * @Param []
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> VillagePublicityHeadTemp(String subsidyYear,String VillageName,String publicityDate) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//
//        headCoulumn1.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn1.add(VillageName);headCoulumn1.add("序号");headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn2.add(VillageName);headCoulumn2.add("组");headCoulumn2.add("组");
//        headCoulumn3.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn3.add(VillageName);headCoulumn3.add("农户基本情况");headCoulumn3.add("户主姓名");
//        headCoulumn4.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn4.add(VillageName);headCoulumn4.add("农户基本情况");headCoulumn4.add("身份证号");
//        headCoulumn5.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn5.add(publicityDate);headCoulumn5.add("农户基本情况");headCoulumn5.add("收款人卡号或账号");
//        headCoulumn6.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn6.add(publicityDate);headCoulumn6.add("申报补贴面积");headCoulumn6.add("申报补贴面积");
//        headCoulumn7.add(subsidyYear+"年农业保护补贴面积农户分户登记表");headCoulumn7.add(publicityDate);headCoulumn7.add("发放金额");headCoulumn7.add("发放金额");
//
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        head.add(headCoulumn5);
//        head.add(headCoulumn6);
//        head.add(headCoulumn7);
//        return head;
//    }
//
//    /**
//     * @Author 高敏
//     * @Description 村级公示表头  该表头为4行7列  headCoulumn为每一列,包含四行数据内容  相邻单元格内容一样会自动合并单元格
//     * @Date 2019/5/5 15:35
//     * @Param []
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> VillageExpDtlExcelHeadTemp(String subsidyYear,String VillageName) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn0 = new ArrayList<String>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//        List<String> headCoulumn8 = new ArrayList<String>();
//        List<String> headCoulumn9 = new ArrayList<String>();
//        List<String> headCoulumn10 = new ArrayList<String>();
//        List<String> headCoulumn11 = new ArrayList<String>();
//        List<String> headCoulumn12 = new ArrayList<String>();
//        List<String> headCoulumn13 = new ArrayList<String>();
//        List<String> headCoulumn14 = new ArrayList<String>();
//        List<String> headCoulumn15 = new ArrayList<String>();
//        List<String> headCoulumn16 = new ArrayList<String>();
//        List<String> headCoulumn17 = new ArrayList<String>();
//        List<String> headCoulumn18 = new ArrayList<String>();
//        List<String> headCoulumn19 = new ArrayList<String>();
//        List<String> headCoulumn20 = new ArrayList<String>();
//        List<String> headCoulumn21 = new ArrayList<String>();
//        List<String> headCoulumn22 = new ArrayList<String>();
//        List<String> headCoulumn23 = new ArrayList<String>();
//        List<String> headCoulumn24 = new ArrayList<String>();
//        List<String> headCoulumn25 = new ArrayList<String>();
//        List<String> headCoulumn26 = new ArrayList<String>();
//        List<String> headCoulumn27 = new ArrayList<String>();
//        List<String> headCoulumn28 = new ArrayList<String>();
//        List<String> headCoulumn29 = new ArrayList<String>();
//        List<String> headCoulumn30 = new ArrayList<String>();
//        headCoulumn0.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn0.add("登记时间     年   月   日");headCoulumn0.add(VillageName);headCoulumn0.add("id");headCoulumn0.add("id");
//        headCoulumn1.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn1.add("登记时间     年   月   日");headCoulumn1.add(VillageName);headCoulumn1.add("序号");headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn2.add("登记时间     年   月   日");headCoulumn2.add(VillageName);headCoulumn2.add("组");headCoulumn2.add("组");
//        headCoulumn3.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn3.add("登记时间     年   月   日");headCoulumn3.add(VillageName);headCoulumn3.add("农户基本情况");headCoulumn3.add("户主姓名");
//        headCoulumn4.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn4.add("登记时间     年   月   日");headCoulumn4.add(VillageName);headCoulumn4.add("农户基本情况");headCoulumn4.add("身份证号");
////        headCoulumn9.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn9.add(VillageName);headCoulumn9.add("农户基本情况");headCoulumn9.add("开户行名称");
//        headCoulumn5.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn5.add("登记时间     年   月   日");headCoulumn5.add(VillageName);headCoulumn5.add("农户基本情况");headCoulumn5.add("收款人卡号或账号");
//        headCoulumn8.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn8.add("登记时间     年   月   日");headCoulumn8.add(VillageName);headCoulumn8.add("农户基本情况");headCoulumn8.add("联系电话");
////        headCoulumn10.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn10.add(VillageName);headCoulumn10.add("农户基本情况");headCoulumn10.add("收款行号");
////        headCoulumn11.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn11.add(VillageName);headCoulumn11.add("补贴标准（元/亩）");headCoulumn11.add("补贴标准（元/亩）");
//        headCoulumn12.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn12.add("登记时间     年   月   日");headCoulumn12.add(VillageName);headCoulumn12.add("确权确地实测面积");headCoulumn12.add("确权确地实测面积");
//        headCoulumn13.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn13.add("登记时间     年   月   日");headCoulumn13.add(VillageName);headCoulumn13.add("扣除面积");headCoulumn13.add("合计");
//        headCoulumn14.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn14.add("登记时间     年   月   日");headCoulumn14.add(VillageName);headCoulumn14.add("扣除面积");headCoulumn14.add("畜牧养殖场地用地面积");
//        headCoulumn15.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn15.add("登记时间     年   月   日");headCoulumn15.add(VillageName);headCoulumn15.add("扣除面积");headCoulumn15.add("颁发林权证和退耕还林土地面积");
//        headCoulumn16.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn16.add("登记时间     年   月   日");headCoulumn16.add(VillageName);headCoulumn16.add("扣除面积");headCoulumn16.add("成为转为设施农业用地面积");
//        headCoulumn17.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn17.add("登记时间     年   月   日");headCoulumn17.add(VillageName);headCoulumn17.add("扣除面积");headCoulumn17.add("非农业征用占地面积");
//        headCoulumn18.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn18.add("登记时间     年   月   日");headCoulumn18.add(VillageName);headCoulumn18.add("扣除面积");headCoulumn18.add("常年抛(撂)耕地面积");
//        headCoulumn19.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn19.add("登记时间     年   月   日");headCoulumn19.add(VillageName);headCoulumn19.add("扣除面积");headCoulumn19.add("质量达不到耕种条件面积");
//        headCoulumn20.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn20.add("登记时间     年   月   日");headCoulumn20.add(VillageName);headCoulumn20.add("申报补贴面积");headCoulumn20.add("申报补贴面积");
//
//        headCoulumn21.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn21.add("登记时间     年   月   日");headCoulumn21.add(VillageName);headCoulumn21.add("修正确权确地实测面积");headCoulumn21.add("修正确权确地实测面积");
//        headCoulumn22.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn22.add("登记时间     年   月   日");headCoulumn22.add(VillageName);headCoulumn22.add("修正扣除面积");headCoulumn22.add("合计");
//        headCoulumn23.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn23.add("登记时间     年   月   日");headCoulumn23.add(VillageName);headCoulumn23.add("修正扣除面积");headCoulumn23.add("畜牧养殖场地用地面积");
//        headCoulumn24.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn24.add("登记时间     年   月   日");headCoulumn24.add(VillageName);headCoulumn24.add("修正扣除面积");headCoulumn24.add("颁发林权证和退耕还林土地面积");
//        headCoulumn25.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn25.add("登记时间     年   月   日");headCoulumn25.add(VillageName);headCoulumn25.add("修正扣除面积");headCoulumn25.add("成为转为设施农业用地面积");
//        headCoulumn26.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn26.add("登记时间     年   月   日");headCoulumn26.add(VillageName);headCoulumn26.add("修正扣除面积");headCoulumn26.add("非农业征用占地面积");
//        headCoulumn27.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn27.add("登记时间     年   月   日");headCoulumn27.add(VillageName);headCoulumn27.add("修正扣除面积");headCoulumn27.add("常年抛(撂)耕地面积");
//        headCoulumn28.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn28.add("登记时间     年   月   日");headCoulumn28.add(VillageName);headCoulumn28.add("修正扣除面积");headCoulumn28.add("质量达不到耕种条件面积");
//        headCoulumn29.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn29.add("登记时间     年   月   日");headCoulumn29.add("单位：亩");headCoulumn29.add("修正申报补贴面积");headCoulumn29.add("修正申报补贴面积");
//        headCoulumn30.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn30.add("登记时间     年   月   日");headCoulumn30.add("单位：亩");headCoulumn30.add("签字");headCoulumn30.add("签字");
//
////        headCoulumn6.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn6.add(VillageName);headCoulumn6.add("申报补贴面积");headCoulumn6.add("申报补贴面积");
////        headCoulumn7.add(subsidyYear+"年农业支持保护补贴面积农户分户登记表");headCoulumn7.add(VillageName);headCoulumn7.add("发放金额");headCoulumn7.add("发放金额");
//        head.add(headCoulumn0);
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        //head.add(headCoulumn9);
//        head.add(headCoulumn5);
//        head.add(headCoulumn8);
//        //head.add(headCoulumn10);
////        head.add(headCoulumn6);
////        head.add(headCoulumn7);
//        //head.add(headCoulumn11);
//        head.add(headCoulumn12);
//        head.add(headCoulumn13);
//        head.add(headCoulumn14);
//        head.add(headCoulumn15);
//        head.add(headCoulumn16);
//        head.add(headCoulumn17);
//        head.add(headCoulumn18);
//        head.add(headCoulumn19);
//        head.add(headCoulumn20);
//        head.add(headCoulumn21);
//        head.add(headCoulumn22);
//        head.add(headCoulumn23);
//        head.add(headCoulumn24);
//        head.add(headCoulumn25);
//        head.add(headCoulumn26);
//        head.add(headCoulumn27);
//        head.add(headCoulumn28);
//        head.add(headCoulumn29);
//        head.add(headCoulumn30);
//        return head;
//    }
//    /**
//     * @Author 张瑞
//     * @Description //乡镇导出明细表头
//     * @Date 15:50 2019/5/22
//     * @Param [subsidyYear, VillageName]
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> TownPublicityHeadTemp(String subsidyYear,String TownName) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//        List<String> headCoulumn8 = new ArrayList<String>();
//        List<String> headCoulumn9 = new ArrayList<String>();
//        List<String> headCoulumn10 = new ArrayList<String>();
//        List<String> headCoulumn11 = new ArrayList<String>();
//        List<String> headCoulumn12 = new ArrayList<String>();
//        List<String> headCoulumn13 = new ArrayList<String>();
//
//        headCoulumn1.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn1.add(TownName);headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn2.add(TownName);headCoulumn2.add("村名称");
//        headCoulumn3.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn3.add(TownName);headCoulumn3.add("补贴标准（元/亩）");
//        headCoulumn4.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn4.add(TownName);headCoulumn4.add("补贴户数");
//        headCoulumn5.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn5.add(TownName);headCoulumn5.add("确权面积（亩）");
//        headCoulumn6.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn6.add(TownName);headCoulumn6.add("六不补面积（亩）");
//        headCoulumn7.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn7.add(TownName);headCoulumn7.add("补贴面积（亩）");
//        headCoulumn8.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn8.add(TownName);headCoulumn8.add("补贴金额（元）");
//        headCoulumn9.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn9.add(TownName);headCoulumn9.add("修正户数");
//        headCoulumn10.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn10.add(TownName);headCoulumn10.add("修正确权面积（亩）");
//        headCoulumn11.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn11.add(TownName);headCoulumn11.add("修正六不补面积（亩）");
//        headCoulumn12.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn12.add(TownName);headCoulumn12.add("修正补贴面积（亩）");
//        headCoulumn13.add(subsidyYear+"年农业保护补贴面积乡级合计表");headCoulumn13.add(TownName);headCoulumn13.add("修正补贴金额（元）");
//
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        head.add(headCoulumn5);
//        head.add(headCoulumn6);
//        head.add(headCoulumn7);
//        head.add(headCoulumn8);
//        head.add(headCoulumn9);
//        head.add(headCoulumn10);
//        head.add(headCoulumn11);
//        head.add(headCoulumn12);
//        head.add(headCoulumn13);
//        return head;
//    }
//    /**
//     * @Author 张瑞
//     * @Description //县导出明细表头
//     * @Date 15:50 2019/5/22
//     * @Param [subsidyYear, VillageName]
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> CountyPublicityHeadTemp(String subsidyYear,String TownName) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//        List<String> headCoulumn8 = new ArrayList<String>();
//        List<String> headCoulumn9 = new ArrayList<String>();
//        List<String> headCoulumn10 = new ArrayList<String>();
//        List<String> headCoulumn11 = new ArrayList<String>();
//        List<String> headCoulumn12 = new ArrayList<String>();
//        List<String> headCoulumn13 = new ArrayList<String>();
//
//        headCoulumn1.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn1.add(TownName);headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn2.add(TownName);headCoulumn2.add("乡名称");
//        headCoulumn3.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn3.add(TownName);headCoulumn3.add("补贴标准（元/亩）");
//        headCoulumn4.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn4.add(TownName);headCoulumn4.add("补贴户数");
//        headCoulumn5.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn5.add(TownName);headCoulumn5.add("确权面积（亩）");
//        headCoulumn6.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn6.add(TownName);headCoulumn6.add("六不补面积（亩）");
//        headCoulumn7.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn7.add(TownName);headCoulumn7.add("补贴面积（亩）");
//        headCoulumn8.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn8.add(TownName);headCoulumn8.add("补贴金额（元）");
//        headCoulumn9.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn9.add(TownName);headCoulumn9.add("修正户数");
//        headCoulumn10.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn10.add(TownName);headCoulumn10.add("修正确权面积（亩）");
//        headCoulumn11.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn11.add(TownName);headCoulumn11.add("修正六不补面积（亩）");
//        headCoulumn12.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn12.add(TownName);headCoulumn12.add("修正补贴面积（亩）");
//        headCoulumn13.add(subsidyYear+"年农业保护补贴面积县级合计表");headCoulumn13.add(TownName);headCoulumn13.add("修正补贴金额（元）");
//
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        head.add(headCoulumn5);
//        head.add(headCoulumn6);
//        head.add(headCoulumn7);
//        head.add(headCoulumn8);
//        head.add(headCoulumn9);
//        head.add(headCoulumn10);
//        head.add(headCoulumn11);
//        head.add(headCoulumn12);
//        head.add(headCoulumn13);
//        return head;
//    }
//    /**
//     * @Author 张瑞
//     * @Description //市导出明细表头
//     * @Date 15:50 2019/5/22
//     * @Param [subsidyYear, VillageName]
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> CityPublicityHeadTemp(String subsidyYear,String TownName) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//        List<String> headCoulumn8 = new ArrayList<String>();
//        List<String> headCoulumn9 = new ArrayList<String>();
//        List<String> headCoulumn10 = new ArrayList<String>();
//        List<String> headCoulumn11 = new ArrayList<String>();
//        List<String> headCoulumn12 = new ArrayList<String>();
//        List<String> headCoulumn13 = new ArrayList<String>();
//
//        headCoulumn1.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn1.add(TownName);headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn2.add(TownName);headCoulumn2.add("县名称");
//        headCoulumn3.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn3.add(TownName);headCoulumn3.add("补贴标准（元/亩）");
//        headCoulumn4.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn4.add(TownName);headCoulumn4.add("补贴户数");
//        headCoulumn5.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn5.add(TownName);headCoulumn5.add("确权面积（亩）");
//        headCoulumn6.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn6.add(TownName);headCoulumn6.add("六不补面积（亩）");
//        headCoulumn7.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn7.add(TownName);headCoulumn7.add("补贴面积（亩）");
//        headCoulumn8.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn8.add(TownName);headCoulumn8.add("补贴金额（元）");
//        headCoulumn9.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn9.add(TownName);headCoulumn9.add("修正户数");
//        headCoulumn10.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn10.add(TownName);headCoulumn10.add("修正确权面积（亩）");
//        headCoulumn11.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn11.add(TownName);headCoulumn11.add("修正六不补面积（亩）");
//        headCoulumn12.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn12.add(TownName);headCoulumn12.add("修正补贴面积（亩）");
//        headCoulumn13.add(subsidyYear+"年农业保护补贴面积市级合计表");headCoulumn13.add(TownName);headCoulumn13.add("修正补贴金额（元）");
//
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        head.add(headCoulumn5);
//        head.add(headCoulumn6);
//        head.add(headCoulumn7);
//        head.add(headCoulumn8);
//        head.add(headCoulumn9);
//        head.add(headCoulumn10);
//        head.add(headCoulumn11);
//        head.add(headCoulumn12);
//        head.add(headCoulumn13);
//        return head;
//    }
//    /**
//     * @Author 张瑞
//     * @Description //省导出明细表头
//     * @Date 15:50 2019/5/22
//     * @Param [subsidyYear, VillageName]
//     * @return java.util.List<java.util.List<java.lang.String>>
//     **/
//    public static List<List<String>> ProvincePublicityHeadTemp(String subsidyYear,String TownName) {
//        List<List<String>> head = new ArrayList<List<String>>();
//        List<String> headCoulumn1 = new ArrayList<String>();
//        List<String> headCoulumn2 = new ArrayList<String>();
//        List<String> headCoulumn3 = new ArrayList<String>();
//        List<String> headCoulumn4 = new ArrayList<String>();
//        List<String> headCoulumn5 = new ArrayList<String>();
//        List<String> headCoulumn6 = new ArrayList<String>();
//        List<String> headCoulumn7 = new ArrayList<String>();
//        List<String> headCoulumn8 = new ArrayList<String>();
//        List<String> headCoulumn9 = new ArrayList<String>();
//        List<String> headCoulumn10 = new ArrayList<String>();
//        List<String> headCoulumn11 = new ArrayList<String>();
//        List<String> headCoulumn12 = new ArrayList<String>();
//        List<String> headCoulumn13 = new ArrayList<String>();
//
//        headCoulumn1.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn1.add(TownName);headCoulumn1.add("序号");
//        headCoulumn2.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn2.add(TownName);headCoulumn2.add("市名称");
//        headCoulumn3.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn3.add(TownName);headCoulumn3.add("补贴标准（元/亩）");
//        headCoulumn4.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn4.add(TownName);headCoulumn4.add("补贴户数");
//        headCoulumn5.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn5.add(TownName);headCoulumn5.add("确权面积（亩）");
//        headCoulumn6.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn6.add(TownName);headCoulumn6.add("六不补面积（亩）");
//        headCoulumn7.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn7.add(TownName);headCoulumn7.add("补贴面积（亩）");
//        headCoulumn8.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn8.add(TownName);headCoulumn8.add("补贴金额（元）");
//        headCoulumn9.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn9.add(TownName);headCoulumn9.add("修正户数");
//        headCoulumn10.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn10.add(TownName);headCoulumn10.add("修正确权面积（亩）");
//        headCoulumn11.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn11.add(TownName);headCoulumn11.add("修正六不补面积（亩）");
//        headCoulumn12.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn12.add(TownName);headCoulumn12.add("修正补贴面积（亩）");
//        headCoulumn13.add(subsidyYear+"年农业保护补贴面积省级合计表");headCoulumn13.add(TownName);headCoulumn13.add("修正补贴金额（元）");
//
//        head.add(headCoulumn1);
//        head.add(headCoulumn2);
//        head.add(headCoulumn3);
//        head.add(headCoulumn4);
//        head.add(headCoulumn5);
//        head.add(headCoulumn6);
//        head.add(headCoulumn7);
//        head.add(headCoulumn8);
//        head.add(headCoulumn9);
//        head.add(headCoulumn10);
//        head.add(headCoulumn11);
//        head.add(headCoulumn12);
//        head.add(headCoulumn13);
//        return head;
//    }
//    /**
//     * @Author 张瑞
//     * @Description //乡级excel样式
//     * @Date 16:07 2019/5/22
//     * @Param [wb]
//     * @return java.util.Map<java.lang.String,org.apache.poi.ss.usermodel.CellStyle>
//     **/
//    public static Map<String, CellStyle> townStyle(Workbook wb){
//        Map definedStyle = new HashMap<String,Object>();
//        List<Map<String,CellStyle>> headStyles = new ArrayList<Map<String,CellStyle>>();
//        Map contentStyle = new HashMap<String,Object>();
//        Map endStyle = new HashMap<String,Object>();
//
//        /* 表头样式 */
//        Map<String,CellStyle> headStyle = null;         //表头样式
//        CellStyle headStyleLeft = null;                 //表头左样式
//        CellStyle headStyleCenter = null;               //表头中样式
//        CellStyle headStyleRight = null;                //表头右样式
//        org.apache.poi.ss.usermodel.Font font = null;   //表头字体样式
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setBold(false);
//        font.setFontName("黑体");
//        font.setFontHeightInPoints((short) 24);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//
//        //添加表头第一行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.LEFT); // 水平居左
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 16);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//        //添加表头第二行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleCenter.setFont(font);
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleRight.setFont(font);
//        //添加表头第三行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        /* 内容样式 */
//        CellStyle contentStyleCenter = wb.createCellStyle();
//        contentStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        contentStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        contentStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        contentStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        contentStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        contentStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        contentStyleCenter.setFont(font);
//
//        contentStyle.put("contentStyleLeft",contentStyleCenter);
//        contentStyle.put("contentStyleCenter",contentStyleCenter);
//        contentStyle.put("contentStyleRight",contentStyleCenter);
//
//        /* 尾行样式 */
//        CellStyle endStyleLeft = wb.createCellStyle();
//        endStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleCenter = wb.createCellStyle();
//        endStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleRight = wb.createCellStyle();
//        endStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        endStyleCenter.setFont(font);
//        endStyleLeft.setFont(font);
//        endStyleRight.setFont(font);
//        endStyle.put("endStyleLeft",endStyleLeft);
//        endStyle.put("endStyleCenter",endStyleCenter);
//        endStyle.put("endStyleRight",endStyleRight);
//
//
//        definedStyle.put("headStyles",headStyles);
//        definedStyle.put("contentStyle",contentStyle);
//        definedStyle.put("endStyle",endStyle);
////      List<Map<String,Object>> setHeight=  new LinkedList<>();
//        Map<Integer,Short> setHeight = new HashMap<>();
//        setHeight.put(0,(short)900);
//        setHeight.put(1,(short)500);
//        setHeight.put(2,(short)600);
//
//        definedStyle.put("setHeight",setHeight);
//        return definedStyle;
//    }
//
//    /**
//     * @Author 高敏
//     * @Description 村级公示自定义表头样式
//     *   特殊样式需求的表格分为三部分:表头样式,内容样式和尾行样式,(因处理边框问题,每部分又分为左中右)
//     *      headStyles ---- 表头样式: 表头占几行, List.size()为几,方便定制不同样式
//     *          List里的Map为固定格式,headStyleLeft  headStyleCenter headStyleRight  分别代表某行表头的左中右样式
//     *      contentStyle -- 内容样式:内容样式一般统一,暂时定为Map
//     *          Map为固定格式,contentStyleLeft contentStyleRight  分别代表某行表头的左右样式
//     *      endStyle ------ 尾行数据样式:只有一行,定为Map
//     *          Map为固定格式,endStyleLeft  endStyleCenter  endStyleRight  分别代表尾行的左中右样式
//     *
//     * @Date 2019/5/7 15:21
//     * @Param [wb]
//     * @return java.util.Map<java.lang.String,org.apache.poi.ss.usermodel.CellStyle>
//     **/
//    public static Map<String, CellStyle> villagePublicityStyle(Workbook wb){
//        Map definedStyle = new HashMap<String,Object>();
//        List<Map<String,CellStyle>> headStyles = new ArrayList<Map<String,CellStyle>>();
//        Map contentStyle = new HashMap<String,Object>();
//        Map endStyle = new HashMap<String,Object>();
//
//        /* 表头样式 */
//        Map<String,CellStyle> headStyle = null;         //表头样式
//        CellStyle headStyleLeft = null;                 //表头左样式
//        CellStyle headStyleCenter = null;               //表头中样式
//        CellStyle headStyleRight = null;                //表头右样式
//        org.apache.poi.ss.usermodel.Font font = null;   //表头字体样式
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setBold(false);
//        font.setFontName("黑体");
//        font.setFontHeightInPoints((short) 24);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//
//        //添加表头第一行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.LEFT); // 水平居左
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 14);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.RIGHT); // 水平居左
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleCenter.setFont(font);
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.RIGHT); // 水平居左
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleRight.setFont(font);
//
//        //添加表头第二行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleCenter.setFont(font);
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleRight.setFont(font);
//        //添加表头第三行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//        //添加表头第四行样式
//        headStyles.add(headStyle);
//
//        /* 内容样式 */
//        CellStyle contentStyleCenter = wb.createCellStyle();
//        contentStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        contentStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        contentStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        contentStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        contentStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        contentStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        contentStyleCenter.setFont(font);
//
////        CellStyle contentStyleLeft = wb.createCellStyle();
////        contentStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
////        contentStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
////        contentStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
////        contentStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
////        contentStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
////        contentStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
////        contentStyleLeft.setFont(font);
////
////        CellStyle contentStyleRight = wb.createCellStyle();
////        contentStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
////        contentStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
////        contentStyleRight.setBorderTop(BorderStyle.THIN);//上边框
////        contentStyleRight.setBorderRight(BorderStyle.THIN);//右边框
////        contentStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
////        contentStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
////        contentStyleRight.setFont(font);
//
//        contentStyle.put("contentStyleLeft",contentStyleCenter);
//        contentStyle.put("contentStyleCenter",contentStyleCenter);
//        contentStyle.put("contentStyleRight",contentStyleCenter);
//
//        /* 尾行样式 */
//        CellStyle endStyleLeft = wb.createCellStyle();
//        endStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleCenter = wb.createCellStyle();
//        endStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleRight = wb.createCellStyle();
//        endStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        endStyleCenter.setFont(font);
//        endStyleLeft.setFont(font);
//        endStyleRight.setFont(font);
//        endStyle.put("endStyleLeft",endStyleLeft);
//        endStyle.put("endStyleCenter",endStyleCenter);
//        endStyle.put("endStyleRight",endStyleRight);
//
//
//        definedStyle.put("headStyles",headStyles);
//        definedStyle.put("contentStyle",contentStyle);
//        definedStyle.put("endStyle",endStyle);
////        List<Map<String,Object>> setHeight=  new LinkedList<>();
//        Map<Integer,Short> setHeight = new HashMap<>();
//        setHeight.put(0,(short)900);
//        setHeight.put(1,(short)500);
//        setHeight.put(2,(short)600);
//        setHeight.put(3,(short)800);
//        definedStyle.put("setHeight",setHeight);
//        Map<Integer,String> setBottomSign = new HashMap<>();
//        setBottomSign.put(1,"村会计签字：");
//        setBottomSign.put(4,"村主任签字：");
//        definedStyle.put("setBottomSign",setBottomSign);
//        return definedStyle;
//    }
//
//    /**
//     * @Author 高敏
//     * @Description 村级公示自定义表头样式
//     *   特殊样式需求的表格分为三部分:表头样式,内容样式和尾行样式,(因处理边框问题,每部分又分为左中右)
//     *      headStyles ---- 表头样式: 表头占几行, List.size()为几,方便定制不同样式
//     *          List里的Map为固定格式,headStyleLeft  headStyleCenter headStyleRight  分别代表某行表头的左中右样式
//     *      contentStyle -- 内容样式:内容样式一般统一,暂时定为Map
//     *          Map为固定格式,contentStyleLeft contentStyleRight  分别代表某行表头的左右样式
//     *      endStyle ------ 尾行数据样式:只有一行,定为Map
//     *          Map为固定格式,endStyleLeft  endStyleCenter  endStyleRight  分别代表尾行的左中右样式
//     *
//     * @Date 2019/5/7 15:21
//     * @Param [wb]
//     * @return java.util.Map<java.lang.String,org.apache.poi.ss.usermodel.CellStyle>
//     **/
//    public static Map<String, CellStyle> expDtlExelStyle(Workbook wb){
//        Map definedStyle = new HashMap<String,Object>();
//        List<Map<String,CellStyle>> headStyles = new ArrayList<Map<String,CellStyle>>();
//        Map contentStyle = new HashMap<String,Object>();
//        Map endStyle = new HashMap<String,Object>();
//
//        /* 表头样式 */
//        Map<String,CellStyle> headStyle = null;         //表头样式
//        CellStyle headStyleLeft = null;                 //表头左样式
//        CellStyle headStyleCenter = null;               //表头中样式
//        CellStyle headStyleRight = null;                //表头右样式
//        org.apache.poi.ss.usermodel.Font font = null;   //表头字体样式
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setBold(false);
//        font.setFontName("黑体");
//        font.setFontHeightInPoints((short) 24);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.MEDIUM);//右边框
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//
//        //添加表头第一行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居左
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.NONE); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//        //添加表头第二行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyleRight.setWrapText(true);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//
//
//
//
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.LEFT); // 水平居左
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.NONE);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.LEFT); // 水平居左
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.NONE);//左边框
//        headStyleRight.setBorderRight(BorderStyle.NONE);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.LEFT); // 水平居左
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        //添加表头第三行样式
//        headStyle = new HashMap<>();
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyleRight.setWrapText(true);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleCenter.setFont(font);
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleRight.setFont(font);
//
//        //添加表头第四行样式
//        headStyle = new HashMap<>();
//        headStyleLeft.setWrapText(true);
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyleCenter.setWrapText(true);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyleRight.setWrapText(true);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//
//        headStyleLeft = wb.createCellStyle();
//        headStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 12);
//        headStyleLeft.setFont(font);
//
//        headStyleCenter = wb.createCellStyle();
//        headStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleCenter.setFont(font);
//
//        headStyleRight = wb.createCellStyle();
//        headStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        headStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        headStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        headStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        headStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        headStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        headStyleRight.setFont(font);
//        //添加表头第五行样式
//        headStyle = new HashMap<>();
//        headStyleLeft.setWrapText(true);
//        headStyle.put("headStyleLeft",headStyleLeft);
//        headStyleCenter.setWrapText(true);
//        headStyle.put("headStyleCenter",headStyleCenter);
//        headStyleRight.setWrapText(true);
//        headStyle.put("headStyleRight",headStyleRight);
//        headStyles.add(headStyle);
//
//        /* 内容样式 */
//        CellStyle contentStyleCenter = wb.createCellStyle();
//        contentStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        contentStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        contentStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        contentStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        contentStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        contentStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        contentStyleCenter.setFont(font);
//
////        CellStyle contentStyleLeft = wb.createCellStyle();
////        contentStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
////        contentStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
////        contentStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
////        contentStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
////        contentStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
////        contentStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
////        contentStyleLeft.setFont(font);
////
////        CellStyle contentStyleRight = wb.createCellStyle();
////        contentStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
////        contentStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
////        contentStyleRight.setBorderTop(BorderStyle.THIN);//上边框
////        contentStyleRight.setBorderRight(BorderStyle.THIN);//右边框
////        contentStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
////        contentStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
////        contentStyleRight.setFont(font);
//
//        contentStyle.put("contentStyleLeft",contentStyleCenter);
//        contentStyle.put("contentStyleCenter",contentStyleCenter);
//        contentStyle.put("contentStyleRight",contentStyleCenter);
//
//        /* 尾行样式 */
//        CellStyle endStyleLeft = wb.createCellStyle();
//        endStyleLeft.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleLeft.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleLeft.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleCenter = wb.createCellStyle();
//        endStyleCenter.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleCenter.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleCenter.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleCenter.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleCenter.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        CellStyle endStyleRight = wb.createCellStyle();
//        endStyleRight.setBorderBottom(BorderStyle.THIN); //下边框
//        endStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
//        endStyleRight.setBorderTop(BorderStyle.THIN);//上边框
//        endStyleRight.setBorderRight(BorderStyle.THIN);//右边框
//        endStyleRight.setAlignment(HorizontalAlignment.CENTER); // 水平居中
//        endStyleRight.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        font = wb.createFont();
//        font.setFontHeightInPoints((short) 11);
//        font.setFontName("宋体");
//        endStyleCenter.setFont(font);
//        endStyleLeft.setFont(font);
//        endStyleRight.setFont(font);
//        endStyle.put("endStyleLeft",endStyleLeft);
//        endStyle.put("endStyleCenter",endStyleCenter);
//        endStyle.put("endStyleRight",endStyleRight);
//
//
//        definedStyle.put("headStyles",headStyles);
//        definedStyle.put("contentStyle",contentStyle);
//        definedStyle.put("endStyle",endStyle);
////        List<Map<String,Object>> setHeight=  new LinkedList<>();
//        Map<Integer,Short> setHeight = new HashMap<>();
//        setHeight.put(0,(short)900);
//        setHeight.put(1,(short)500);
//        setHeight.put(2,(short)500);
//        setHeight.put(3,(short)600);
//        setHeight.put(4,(short)2300);
//        definedStyle.put("setHeight",setHeight);
//        Map<Integer,String> signs = new HashMap<>();
//        signs.put(4,"村会计签字：");
//        signs.put(10,"村主任签字：");
//        definedStyle.put("setBottomSign",signs);
//        return definedStyle;
//    }

}
