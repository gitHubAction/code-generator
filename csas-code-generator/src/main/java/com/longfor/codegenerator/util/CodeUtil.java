package com.longfor.codegenerator.util;

import com.google.common.base.CaseFormat;
import com.longfor.codegenerator.config.CodeConfig;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.generator.impl.*;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.PathConfig;
import com.longfor.codegenerator.model.ProjectConfig;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChenBin
 * @Date: 2018/6/6/0006 16:25
 */
public class CodeUtil {
    /**
     * 实例化模版工具类
     *
     * @return
     */
    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassLoaderForTemplateLoading(CodeUtil.class.getClassLoader(), CodeConfig.LOADER_PATH);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * 创建文件夹
     *
     * @param path 文件路径
     * @return
     */
    public static File getFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public static void setLinuxFile(String fileName) {
        File file = new File(fileName + PathConfig.JAVA_PATH);
        file.setWritable(true, false);
        if (!file.exists()) {
            file.mkdirs();
        }

        File file1 = new File(fileName + PathConfig.RESOURCES_PATH);
        file1.setWritable(true, false);
        if (!file1.exists()) {
            file1.mkdirs();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                delAllFile(path + File.separator + tempList[i]);
                // 再删除空文件夹
                delFolder(path + File.separator + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    /***
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            // 删除完里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            // 删除空文件夹
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置map
     *
     * @param model
     * @param author
     * @param projectConfig
     * @return
     */
    public static Map<String, Object> setParam(Model model, String author, ProjectConfig projectConfig) {
        String desc = StringUtils.isEmpty(model.getDescription()) ? "" : model.getDescription();
        String domainNameUpperCamel = StringUtils.isEmpty(model.getDomainName()) ? tableNameConvertUpperCamel(model.getTableName()) : model.getDomainName();
        Map<String, Object> data = new HashMap<>(16);
        data.put("description", desc);
        data.put("date", PathConfig.DATE);
        data.put("author", author);
        data.put("pkDataType", model.getPkDataType());
        data.put("domainNameUpperCamel", domainNameUpperCamel);
        data.put("domainNameLowerCamel", tableNameConvertLowerCamel(model.getTableName()));
        data.put("basePackage", projectConfig.getPackAge());
        data.put("baseRequestMapping", domainNameConvertMappingPath(domainNameUpperCamel));
        return data;
    }

    /**
     * 封装模版生成方式
     *
     * @param savePath
     * @param tempName
     * @throws Exception
     */
    public static void tempFactory(String savePath, String tempName, Map<String, Object> data) throws Exception {
        freemarker.template.Configuration cfg = CodeUtil.getConfiguration();

        File file = getFile(savePath);
        FileWriter writer = new FileWriter(file);
        cfg.getTemplate(tempName).process(data, writer);
        writer.flush();
        writer.close();
    }

    public static Generator codeFactory(String type){
        Generator generator = null;
        switch (type){
            case "1":
                generator = new CoreGenerator();
                break;
            case "2":
                generator = new AppGenerator();
                break;
            case "3":
                generator = new AllGenerator();
                break;
            case "4":
                generator = new PartGenerator();
                break;
            case "5":
                generator = new DaoGenerator();
                break;
            default:
                generator = new DaoGenerator();
                break;
        }
        return generator;
    }

    public static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    public static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    public static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return File.separator + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
    }

    public static String domainNameConvertMappingPath(String domainName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, domainName);
        return tableNameConvertMappingPath(tableName);
    }
}
