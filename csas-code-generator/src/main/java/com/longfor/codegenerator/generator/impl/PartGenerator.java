package com.longfor.codegenerator.generator.impl;


import com.longfor.codegenerator.code.CoreCode;
import com.longfor.codegenerator.code.WebCode;
import com.longfor.codegenerator.generator.Generator;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;

import java.io.File;

public class PartGenerator implements Generator {
    @Override
    public void makeCode(Model model, String author, ProjectConfig projectConfig, DbConfig dbConfig, String fileName, String type, String groupId) {
        File file = new File(fileName);
        if (!file.exists()){
            file.mkdirs();
        }
        CoreCode.getDomainAndMapper(model, dbConfig, projectConfig, fileName, type);
        CoreCode.getService(model, author, projectConfig, fileName, type);
        CoreCode.getDTO(model, author, projectConfig, fileName, type);
        WebCode.getController(model, author, projectConfig, fileName, type);
    }
}
