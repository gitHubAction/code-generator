package com.longfor.codegenerator.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;

/**
 * @Author: ChenBin
 * @Date: 2018/6/6/0006 16:54
 */
public class CodeGenerator {


    /**
     * 解析传入的二维JSON数组，转换成相应的值
     *
     * @param jsonData      二维JSON数组
     * @param dbConfig      数据库配置信息
     * @param fileName      文件夹名称
     * @param projectConfig 项目包的文件夹名称
     * @param author        创建人
     */
    public static void getCode(String jsonData, DbConfig dbConfig, String fileName, ProjectConfig projectConfig,
                               String author, String type, String groupId) {
        JSONArray array = JSONArray.parseArray(jsonData);

        for (int i = 0; i < array.size(); i++) {
            Model model = new Model();
            JSONObject object = array.getJSONObject(i);
            model.setTableName(object.getString("tableName"));
            model.setDomainName(object.getString("domainName"));
            model.setDescription(object.getString("description"));
            model.setPkDataType(object.getString("pkDataType"));
            Generator generator= CodeUtil.codeFactory(type);
            generator.makeCode(model, author, projectConfig, dbConfig, fileName, type, groupId);
        }
    }



}
