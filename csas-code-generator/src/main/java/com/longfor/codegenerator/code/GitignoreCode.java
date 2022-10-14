package com.longfor.codegenerator.code;

import com.longfor.codegenerator.exception.GeneratorException;
import com.longfor.codegenerator.util.CodeUtil;

import java.io.File;

public class GitignoreCode {

    public static void getGitignore(String fileName) {
        try {
            String savePath = fileName + File.separator +".gitignore";
            String tempName = "config"+ File.separator +"gitignore.ftl";
            CodeUtil.tempFactory(savePath, tempName, null);
            System.out.println("gitignore 生成成功");
        }catch (Exception e){
            throw new GeneratorException("生成gitignore失败");
        }
    }
}
