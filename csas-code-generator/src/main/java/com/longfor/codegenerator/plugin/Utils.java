package com.longfor.codegenerator.plugin;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.io.File;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/25 21:09
 */
public class Utils {

    public static boolean isNotExists(String path,String fileName){
        String bn = System.getProperty("user.dir");
        String file = bn+"."+path;
        String pathName = file.replace(".","/").replace("\\","/")+"/"+fileName;
        File f = new File(pathName);
        return !f.exists();
    }

    public static void getAttribute(XmlElement xmlElement , String name, String value){
        xmlElement.addAttribute(new Attribute(name,value));
    }
}
