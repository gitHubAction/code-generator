package com.longfor.codegenerator.generator.impl;

import com.longfor.codegenerator.code.GitignoreCode;
import com.longfor.codegenerator.code.PomCode;
import com.longfor.codegenerator.code.WebCode;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;

public class AppGenerator implements Generator {
    @Override
    public void makeCode(Model model, String author, ProjectConfig projectConfig, DbConfig dbConfig, String fileName, String type, String groupId) {
        CodeUtil.setLinuxFile(fileName);
        WebCode.getController(model, author, projectConfig, fileName, type);
        PomCode.getPom(fileName, type, groupId);
//        WebCode.getLogBack(projectConfig, fileName);
        WebCode.getMybatisConfig(model, author, projectConfig, fileName);
//        WebCode.getFilter(author, projectConfig, fileName, "ServiceFilter");
        WebCode.getFilter(author, projectConfig, fileName, "XssHttpServletRequestWrapper");
        WebCode.getFilter(author, projectConfig, fileName, "TraceIdFilter");
        WebCode.getFilter(author, projectConfig, fileName, "XssFilter");
//        WebCode.getWebMvcConfig(model, author, projectConfig, fileName);
        WebCode.getGlobalExceptionHandler(model, author, projectConfig, fileName);
        WebCode.getApplication(model, author, projectConfig, fileName);
//        WebCode.getApplicationDev(fileName, dbConfig);
        WebCode.getApplicationProperties(model, author, projectConfig, fileName, dbConfig);
        WebCode.getResult(model, author, fileName, projectConfig);
        WebCode.getResponseMsgUtil(model, author, fileName, projectConfig);
        WebCode.getRequestUtil(model, author, fileName, projectConfig);
        GitignoreCode.getGitignore(fileName);
    }
}
