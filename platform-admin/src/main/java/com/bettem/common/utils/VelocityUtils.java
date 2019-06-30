package com.bettem.common.utils;



import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Velocity渲染模板
 */
public class VelocityUtils {
    /**
     * 渲染Velocity模板
     * @param data
     * @return
     */
    public static StringBuffer getVelocityTemplate(Map<String,Object> data){
        Properties p = new Properties();
        // 设置输入输出编码类型。和这次说的解决的问题无关
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        //p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        //文件缓存
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, "false");
        // 这里加载类路径里的模板而不是文件系统路径里的模板
        p.setProperty("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
        Template template = Velocity.getTemplate((String)data.get("templateName"),"UTF-8");
        VelocityContext context = new VelocityContext(data);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.getBuffer();
    }
}
