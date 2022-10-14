package com.longfor.codegenerator.generator.impl;


import com.longfor.codegenerator.code.CoreCode;
import com.longfor.codegenerator.code.GitignoreCode;
import com.longfor.codegenerator.code.PomCode;
import com.longfor.codegenerator.code.WebCode;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;

public class AllGenerator implements Generator {
    @Override
    public void makeCode(Model model, String author, ProjectConfig projectConfig, DbConfig dbConfig, String fileName, String type, String groupId) {
        CodeUtil.setLinuxFile(fileName);
        // pom
        PomCode.getPom(fileName, type, groupId);
        // mybatis
        CoreCode.getDomainAndMapper(model, dbConfig, projectConfig, fileName, type);
        // code
        CoreCode.getService(model, author, projectConfig, fileName, type);
        CoreCode.getDTO(model, author, projectConfig, fileName, type);
        WebCode.getController(model, author, projectConfig, fileName, type);
        //        CoreCode.getMapper(projectConfig, fileName);
        CoreCode.getAbService(projectConfig, fileName);
        CoreCode.getOrderBy(projectConfig, fileName);
        CoreCode.getQueryService(projectConfig, fileName);
//        WebCode.getLogBack(projectConfig, fileName);
        // com.lonfor.csdi.config
        WebCode.getMybatisConfig(model, author, projectConfig, fileName);
//        WebCode.getWebMvcConfig(model, author, projectConfig, fileName);
//        WebCode.getFilter(author, projectConfig, fileName, "ServiceFilter");
        WebCode.getFilter(author, projectConfig, fileName, "TraceIdFilter");
        WebCode.getFilter(author, projectConfig, fileName, "XssHttpServletRequestWrapper");
        WebCode.getFilter(author, projectConfig, fileName, "XssFilter");
        GitignoreCode.getGitignore(fileName);
        // spring
        WebCode.getApplication(model, author, projectConfig, fileName);
        // spring/exception
        WebCode.getGlobalExceptionHandler(model, author, projectConfig, fileName);
        // 配置文件
        WebCode.getApplicationProperties(model, author, projectConfig, fileName, dbConfig);

        //生成环境配置
//        WebCode.getApplicationDev(fileName, dbConfig);
        // spring/util
        WebCode.getResult(model, author, fileName, projectConfig);
        WebCode.getResponseMsgUtil(model, author, fileName, projectConfig);
        WebCode.getRequestUtil(model, author, fileName, projectConfig);
        WebCode.getBaseRequest(model, author, fileName, projectConfig);
    }
}
