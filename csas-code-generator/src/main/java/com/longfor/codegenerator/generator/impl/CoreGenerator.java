package com.longfor.codegenerator.generator.impl;

import com.longfor.codegenerator.code.CoreCode;
import com.longfor.codegenerator.code.GitignoreCode;
import com.longfor.codegenerator.code.PomCode;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;

public class CoreGenerator implements Generator {
    @Override
    public void makeCode(Model model, String author, ProjectConfig projectConfig, DbConfig dbConfig, String fileName, String type, String groupId) {
        CodeUtil.setLinuxFile(fileName);
        CoreCode.getDomainAndMapper(model, dbConfig, projectConfig, fileName, type);
        CoreCode.getService(model, author, projectConfig, fileName, type);
        CoreCode.getDTO(model, author, projectConfig, fileName, type);
//        CoreCode.getMapper(projectConfig, fileName);
        CoreCode.getAbService(projectConfig, fileName);
        CoreCode.getOrderBy(projectConfig, fileName);
        CoreCode.getQueryService(projectConfig, fileName);
        PomCode.getPom(fileName, type, groupId);
        GitignoreCode.getGitignore(fileName);

    }




}
