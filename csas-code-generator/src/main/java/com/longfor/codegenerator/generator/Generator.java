package com.longfor.codegenerator.generator;

import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.ProjectConfig;

public interface Generator {

    void makeCode(Model model, String author, ProjectConfig projectConfig, DbConfig dbConfig, String fileName, String type, String groupId);

}
