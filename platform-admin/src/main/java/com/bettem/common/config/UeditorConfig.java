package com.bettem.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: bettem-security
 * @CreateDate: Created in 2018/12/20 13:08 <br>
 * @Author: 颜金星
 */
public enum  UeditorConfig {
    CONFIG();

    private JSONObject config;

    UeditorConfig() {
        StringBuilder config = new StringBuilder();

        try {
            //此处即为获取配置文件，因为springboot的原因，目前我只能用流来获取文件内容
            InputStream stream = getClass().getClassLoader().getResourceAsStream("config.json");
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
            String temp = null;
            while ((temp = buff.readLine()) != null) {
                config.append(temp);
            }
            //把配置文件中的注释去掉
            String configStr = config.toString().replaceAll("/\\*[\\s\\S]*?\\*/", "");
            this.config = JSON.parseObject(configStr);
        } catch (Exception e) {
            this.config = null;
        }
    }

    public JSONObject getConfig() {
        return config;
    }

    public String getConfigStr() {
        if (this.config == null)
            return ("(读取配置文件失败);");
        return this.config.toJSONString();
    }
}
