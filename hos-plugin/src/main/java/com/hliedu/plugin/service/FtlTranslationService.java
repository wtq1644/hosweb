package com.hliedu.plugin.service;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 逆向生成模板类
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class FtlTranslationService {

    public static final String UTF_8 = "UTF-8";
    private Configuration conf = new Configuration(Configuration.VERSION_2_3_23);

    public void init() {
        conf.setOutputEncoding(UTF_8);
        conf.setDefaultEncoding(UTF_8);
        conf.setClassForTemplateLoading(FtlTranslationService.class, "/freemarker/");
        try {
            ClassPathResource res = new ClassPathResource("freemarker/prop/freemarker.properties");
            conf.setSettings(res.getInputStream());
        } catch (Exception ex) {
            System.out.println("freemarker setting file error!  please check /resources/freemarker/prop/freemarker.properties!");
        }
    }

    public void translate(Object data, String tplName, String output) throws Exception {
        Map<String, Object> rootObj = new HashMap<String, Object>(3);
        rootObj.put("enums", BeansWrapper.getDefaultInstance().getEnumModels());
        rootObj.put("static", BeansWrapper.getDefaultInstance().getStaticModels());
        rootObj.put("data", data);
        Template t = conf.getTemplate(tplName, UTF_8);
        String s = new File("").getCanonicalPath() + "\\" + output;
        File outputFile = new File(s);
        outputFile.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(outputFile);
        t.process(rootObj, writer);
        rootObj.clear();
    }
}