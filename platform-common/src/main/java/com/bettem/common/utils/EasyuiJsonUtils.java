package com.bettem.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName EasyuiJsonUtils
 * @Author lihuiyong
 * @Date 2019/5/4 15:06
 * @Version 1.0
 **/
public class EasyuiJsonUtils {

    public static void writeJsonEasyuiDatagrid(HttpServletResponse response,PageUtils pageUtils) throws Exception{
        if(pageUtils != null){
            JSONObject data=new JSONObject();
            data.put("rows",JSONArray.toJSON(pageUtils.getList()));
            data.put("total",pageUtils.getTotalCount());
            EasyuiJsonUtils.write(response,data.toJSONString());
        }
    }
    /**
     * 向response对象写入数据
     * @param response
     * @throws Exception
     */
    public static void write(HttpServletResponse response, String content)throws Exception{
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(content);
        pw.flush();
    }
}
