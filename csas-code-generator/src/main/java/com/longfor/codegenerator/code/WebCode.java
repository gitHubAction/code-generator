package com.longfor.codegenerator.code;

import com.longfor.codegenerator.exception.GeneratorException;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.PathConfig;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebCode {

    public static void getController(Model model, String author, ProjectConfig projectConfig, String fileName, String type) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            String savePath, tempName;
            if ("4".equals(type)){
                savePath = fileName + PathConfig.controller + data.get("domainNameUpperCamel") + "Controller.java";
                tempName = "code"+ File.separator +"controller.ftl";
            }else {
                savePath = fileName + PathConfig.JAVA_PATH + PathConfig.controller + data.get("domainNameUpperCamel") + "Controller.java";
                tempName = "code"+ File.separator +"controller.ftl";
            }
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println(data.get("domainNameUpperCamel") + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成Controller失败");
        }
    }

    public static void getLogBack(ProjectConfig projectConfig, String fileName) {

        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("basePackage", projectConfig.getPackAge());
            data.put("LOG_LEVEL_PATTERN", "${LOG_LEVEL_PATTERN:-%5p}");
            data.put("PID", "${PID:- }");
            data.put("LOG_EXCEPTION_CONVERSION_WORD", "${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}");
            data.put("APP_HOME", "${APP_HOME}");
            data.put("APP_NAME", "${APP_NAME}");
            data.put("LOG_HOME_PATH", "${LOG_HOME_PATH}");
            data.put("FILE_LOG_PATTERN", "${FILE_LOG_PATTERN}");
            data.put("ERROR_LOG_FILE", "${ERROR_LOG_FILE}");
            data.put("INFO_LOG_FILE", "${INFO_LOG_FILE}");
            data.put("WARN_LOG_FILE", "${WARN_LOG_FILE}");
            data.put("DEBUG_LOG_FILE", "${DEBUG_LOG_FILE}");
            data.put("RUN_LOG_FILE", "${RUN_LOG_FILE}");
            data.put("SEC_LOG_FILE", "${SEC_LOG_FILE}");
            data.put("START_LOG_FILE", "${START_LOG_FILE}");

            String savePath = fileName + PathConfig.RESOURCES_PATH + File.separator + "logback.xml";
            String tempName = "config"+ File.separator +"logback.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("logback.xml 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成logback.xml失败");
        }
    }


    public static void getMybatisConfig(Model model, String author, ProjectConfig projectConfig, String fileName) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            data.put("springDatasourceUrl", "${spring.datasource.url}");
            data.put("springDatasourceUsername", "${spring.datasource.username}");
            data.put("springDatasourcePassword", "${spring.datasource.password}");
            data.put("springDatasourceDriverClassName", "${spring.datasource.driver-class-name}");
            data.put("springDatasourceInitialSize5", "${spring.datasource.initialSize:5}");
            data.put("springDatasourceMinIdle5", "${spring.datasource.minIdle:5}");
            data.put("springDatasourceMaxActive20", "${spring.datasource.maxActive:20}");
            data.put("springDatasourceMaxWait60000", "${spring.datasource.maxWait:60000}");
            data.put("springDatasourceTimeBetweenEvictionRunsMillis60000", "${spring.datasource.timeBetweenEvictionRunsMillis:60000}");
            data.put("springDatasourceMinEvictableIdleTimeMillis300000", "${spring.datasource.minEvictableIdleTimeMillis:300000}");
            data.put("springDatasourceValidationQuerySELECT_1_FROM_DUAL", "${spring.datasource.validationQuery:SELECT 1 FROM DUAL}");
            data.put("springDatasourceTestWhileIdleTrue", "${spring.datasource.testWhileIdle:true}");
            data.put("springDatasourceTestOnBorrowFalse", "${spring.datasource.testOnBorrow:false}");
            data.put("springDatasourceTestOnReturnFalse", "${spring.datasource.testOnReturn:false}");
            data.put("springDatasourceFiltersStatWallLog4j", "${spring.datasource.filters:stat,wall,log4j}");
            data.put("springProfilesActive", "${spring.profiles.active}");
            data.put("serverErrorPathErrorPathError", "${server.error.path:${error.path:/error}}");

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.config + "MybatisConfig.java";
            String tempName = "config"+ File.separator +"MybatisConfig.ftl";

            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("MybatisConfig.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成MybatisConfig.java失败");
        }
    }


    public static void getFilter(String author, ProjectConfig projectConfig, String fileName, String filterName) {

        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("author", author);
            data.put("basePackage", projectConfig.getPackAge());

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.filter + filterName +".java";
            String tempName = "config"+ File.separator +filterName+".ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println(filterName+".java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成"+filterName+".java失败");
        }
    }


    public static void getWebMvcConfig(Model model, String author, ProjectConfig projectConfig, String fileName) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            data.put("springProfilesActive", "${spring.profiles.active}");

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.config + "WebMvcConfig.java";
            String tempName = "config"+ File.separator +"WebMvcConfig.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("WebMvcConfig.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成WebMvcConfig.java失败");
        }
    }


    public static void getGlobalExceptionHandler(Model model, String author, ProjectConfig projectConfig, String fileName) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            data.put("serverErrorPathErrorPathError", "${server.error.path:${error.path:/error}}");

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.exception + "GlobalExceptionHandler.java";
            String tempName = "spring"+ File.separator +"exception"+ File.separator +"GlobalExceptionHandler.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("GlobalExceptionHandler.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成GlobalExceptionHandler.java失败");
        }
    }

    public static void getApplication(Model model, String author, ProjectConfig projectConfig, String fileName) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.packAges + "Application.java";
            String tempName = "spring"+ File.separator +"Application.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("Application.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成Application.java失败");
        }
    }


    public static void getApplicationSit(String fileName, DbConfig dbConfig) {
        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("url", dbConfig.getUrl());
            data.put("port", dbConfig.getPort());
            data.put("dataBase", dbConfig.getDataBase());
            data.put("user", dbConfig.getUser());
            data.put("passWord", dbConfig.getPassWord());
            data.put("driverClass", dbConfig.getDriverClass());

            String savePath = fileName + PathConfig.RESOURCES_PATH + File.separator +"application-sit.properties";
            String tempName = "spring"+ File.separator +"application-sit-properties.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("application-dev.properties 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成application-dev.properties失败");
        }
    }


    public static void getApplicationProperties(Model model, String author, ProjectConfig projectConfig, String fileName, DbConfig dbConfig) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
            String projectName = fileName.substring(fileName.lastIndexOf("/") + 1);
            data.put("projectName", projectName);
            data.put("url", dbConfig.getUrl());
            data.put("port", dbConfig.getPort());
            data.put("dataBase", dbConfig.getDataBase());
            data.put("user", dbConfig.getUser());
            data.put("passWord", dbConfig.getPassWord());
            data.put("driverClass", dbConfig.getDriverClass());
            String savePath = fileName + PathConfig.RESOURCES_PATH + File.separator +"application.properties";
            String tempName = "spring"+ File.separator +"application-properties.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("application.properties 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成application.properties失败");
        }
    }
    public static void getResult(Model model, String author, String fileName, ProjectConfig projectConfig) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.util + File.separator +"Result.java";
            String tempName = "spring"+ File.separator +"util"+ File.separator +"Result.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("Result.ftl 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成Result失败");
        }
    }
    public static void getResponseMsgUtil(Model model, String author, String fileName, ProjectConfig projectConfig) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.util + File.separator + "ResponseMsgUtil.java";
            String tempName = "spring"+ File.separator +"util" + File.separator +"ResponseMsgUtil.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("ResponseMsgUtil.ftl 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成ResponseMsgUtil失败");
        }
    }
    public static void getRequestUtil(Model model, String author, String fileName, ProjectConfig projectConfig) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.util + File.separator +"RequestUtil.java";
            String tempName = "spring"+ File.separator +"util"+ File.separator +"RequestUtil.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("RequestUtil.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成RequestUtil失败");
        }
    }

    public static void getBaseRequest(Model model, String author, String fileName, ProjectConfig projectConfig) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.util + File.separator +"BaseRequest.java";
            String tempName = "spring"+ File.separator +"util"+ File.separator +"BaseRequest.ftl";
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("BaseReuest.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成BaseReuest失败");
        }
    }
}
