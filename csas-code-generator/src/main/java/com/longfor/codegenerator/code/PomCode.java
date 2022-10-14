package com.longfor.codegenerator.code;


import com.longfor.codegenerator.exception.GeneratorException;
import com.longfor.codegenerator.util.CodeUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PomCode {

    public static void getPom(String fileName, String type, String groupId) {
        try {
            String projectName = fileName.substring(fileName.lastIndexOf("/") + 1);
            Map<String, Object> data = new HashMap<>(16);
            data.put("projectName", projectName);
            data.put("artifactId", "${project.artifactId}");
            data.put("version", "${project.version}");
            data.put("springbootVersion", "${springboot.version}");
            data.put("groupId", groupId);
            String savePath = fileName + File.separator + "pom.xml";
            String tempName = "", num1 = "1", num2 = "2", num3 = "3";
            if (num1.equals(type)) {
                tempName = "spring"+ File.separator +"pom"+ File.separator +"core-pom.ftl";
            }
            if (num2.equals(type)) {
                tempName = "spring"+ File.separator +"pom"+ File.separator +"app-pom.ftl";
            }
            if (num3.equals(type)) {
                tempName = "spring"+ File.separator +"pom"+ File.separator +"pom.ftl";
            }
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("pom.xml 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成pom.xml失败");
        }
    }
}
