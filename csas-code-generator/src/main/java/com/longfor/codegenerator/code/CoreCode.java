package com.longfor.codegenerator.code;

import com.longfor.codegenerator.config.CodeConfig;
import com.longfor.codegenerator.exception.GeneratorException;
import com.longfor.codegenerator.exception.SqlErrorException;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.Model;
import com.longfor.codegenerator.model.PathConfig;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.plugin.DaoExtPlugin;
import com.longfor.codegenerator.plugin.DbRemarksCommentGenerator;
import com.longfor.codegenerator.plugin.MyIntrospectedTableImpl;
import com.longfor.codegenerator.util.CodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.PluginAggregator;

import java.io.File;
import java.util.*;

public class CoreCode {


    public static void getDomainAndMapper(Model model, DbConfig dbConfig, ProjectConfig projectConfig, String fileName, String type) {
        Context context = new Context(ModelType.FLAT);
        context.setId(CodeConfig.CONTEXT_ID);
//        context.setTargetRuntime(CodeConfig.TARGET_RUNTIME);
        context.setTargetRuntime("com.longfor.codegenerator.plugin.MyIntrospectedTableImpl");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        context.addProperty("autoDelimitKeywords", "true");
        PluginConfiguration propertyHolder = new PluginConfiguration();
        propertyHolder.setConfigurationType("com.longfor.codegenerator.plugin.DaoExtPlugin");
        context.addPluginConfiguration(propertyHolder);

        String connectionURL = "jdbc:" + dbConfig.getDbType() + "://" + dbConfig.getUrl() + ":"
                + dbConfig.getPort() + "/" + dbConfig.getDataBase()
                + CodeConfig.CONFIG_URL;
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(connectionURL);
        jdbcConnectionConfiguration.setUserId(dbConfig.getUser());
        jdbcConnectionConfiguration.setPassword(dbConfig.getPassWord());
        jdbcConnectionConfiguration.setDriverClass(dbConfig.getDriverClass());
        // 默认使用当前库的catalog,避免多库中表名相同导致生成多个同名方法  还可以直接在数据库连接信息上拼接 nullCatalogMeansCurrent=true
        jdbcConnectionConfiguration.addProperty("nullCatalogMeansCurrent", "true");
        // useInformationSchema可以拿到表注释，从而生成类注释可以使用表的注释
        jdbcConnectionConfiguration.addProperty("useInformationSchema", "true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setConfigurationType(CodeConfig.CONFIGURATION_TYPE);

        if ("4".equals(type)){
            javaModelGeneratorConfiguration.setTargetProject(fileName);
            javaModelGeneratorConfiguration.setTargetPackage(projectConfig.getDomain());
            sqlMapGeneratorConfiguration.setTargetProject(fileName);
            sqlMapGeneratorConfiguration.setTargetPackage(projectConfig.getPackAge() + "/mapper/xml");
            javaClientGeneratorConfiguration.setTargetProject(fileName);
            javaClientGeneratorConfiguration.setTargetPackage(projectConfig.getMapper());
        }else {
            javaModelGeneratorConfiguration.setTargetProject(fileName + PathConfig.JAVA_PATH);
            javaModelGeneratorConfiguration.setTargetPackage(projectConfig.getDomain());
            sqlMapGeneratorConfiguration.setTargetProject(fileName + PathConfig.RESOURCES_PATH);
            sqlMapGeneratorConfiguration.setTargetPackage("mapper");
            javaClientGeneratorConfiguration.setTargetProject(fileName + PathConfig.JAVA_PATH);
            javaClientGeneratorConfiguration.setTargetPackage(projectConfig.getMapper());
        }
        // Table configuration
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setSchema(dbConfig.getDataBase());
        tableConfiguration.setTableName(model.getTableName());
        if (StringUtils.isNotEmpty(model.getDomainName())) {
            tableConfiguration.setDomainObjectName(model.getDomainName());
        }
        // 自动生成主键
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "JDBC", true, null));
        // 不适用example做 Update、Count、Delete、Select
        tableConfiguration.setUpdateByExampleStatementEnabled(false);
        tableConfiguration.setCountByExampleStatementEnabled(false);
        tableConfiguration.setDeleteByExampleStatementEnabled(false);
        tableConfiguration.setSelectByExampleStatementEnabled(false);

        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        context.addTableConfiguration(tableConfiguration);
        // 注释
        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
        commentConfig.setConfigurationType(DbRemarksCommentGenerator.class.getName());
        commentConfig.addProperty("columnRemarks", "true");
        commentConfig.addProperty("annotations", "true");
        context.setCommentGeneratorConfiguration(commentConfig);

        // Domain实现序列化接口
        PluginConfiguration serializablePlugin = new PluginConfiguration();
        serializablePlugin.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        serializablePlugin.addProperty("domain", PathConfig.domain);
        context.addPluginConfiguration(serializablePlugin);

        //Domain实现equals、hashCode生成
//        PluginConfiguration equalsHashCodePlugin = new PluginConfiguration();
//        equalsHashCodePlugin.addProperty("type", "org.mybatis.generator.plugins.EqualsHashCodePlugin");
//        equalsHashCodePlugin.setConfigurationType("org.mybatis.generator.plugins.EqualsHashCodePlugin");
//        context.addPluginConfiguration(equalsHashCodePlugin);

        //lombok插件
        PluginConfiguration lombokPlugin = new PluginConfiguration();
        lombokPlugin.addProperty("type", "com.softwareloop.mybatis.generator.plugins.LombokPlugin");
        lombokPlugin.setConfigurationType("com.softwareloop.mybatis.generator.plugins.LombokPlugin");
        context.addPluginConfiguration(lombokPlugin);

        // 公共DAO接口
        PluginConfiguration commonDAOPlugin = new PluginConfiguration();
        commonDAOPlugin.addProperty("useExample", "false");
        commonDAOPlugin.addProperty("type", "com.longfor.codegenerator.plugin.CommonDAOInterfacePlugin");
        commonDAOPlugin.setConfigurationType("com.longfor.codegenerator.plugin.CommonDAOInterfacePlugin");
        context.addPluginConfiguration(commonDAOPlugin);

        List<String> warnings;
        MyBatisGenerator generator;

        String usingPassword = "using password";
        String unknownDatabase = "Unknown database";
        String linkFailure = "link failure";
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            if (e.getMessage().contains(usingPassword)) {
                throw new SqlErrorException("数据库用户名或密码错误！");
            }
            if (e.getMessage().contains(unknownDatabase)) {
                throw new SqlErrorException("找不到数据库名" + e.getMessage().substring((e.getMessage().indexOf("'")) + 1, e.getMessage().lastIndexOf("'")));
            }
            if (e.getMessage().contains(linkFailure)) {
                throw new SqlErrorException("数据库连接失败，请检查IP和端口是否正确！");
            }
            throw new GeneratorException("数据库连接失败，请检查配置是否正确" + e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new GeneratorException("生成Domain和Mapper失败：" + warnings);
        }

        String domainName = CodeUtil.tableNameConvertUpperCamel(model.getTableName());
        System.out.println(domainName + ".java 生成成功");
        System.out.println(domainName + "Mapper.java 生成成功");
        System.out.println(domainName + "Mapper.xml 生成成功");
    }

    public static void getService(Model model, String author,ProjectConfig projectConfig, String fileName, String type) {
        Map<String, Object> data = CodeUtil.setParam(model, author, projectConfig);
        String savePath, savePath1, tempName, tempName1;
        if ("4".equals(type)){
            savePath = fileName + PathConfig.service + data.get("domainNameUpperCamel") + "Service.java";
            tempName = "code"+ File.separator +"service.ftl";

            savePath1 = fileName + PathConfig.serviceImpl + data.get("domainNameUpperCamel") + "ServiceImpl.java";
            tempName1 = "code"+ File.separator +"service-impl.ftl";
        }else {
            savePath = fileName + PathConfig.JAVA_PATH + PathConfig.service + data.get("domainNameUpperCamel") + "Service.java";
            tempName = "code"+ File.separator +"service.ftl";

            savePath1 = fileName + PathConfig.JAVA_PATH + PathConfig.serviceImpl + data.get("domainNameUpperCamel") + "ServiceImpl.java";
            tempName1 = "code"+ File.separator +"service-impl.ftl";
        }

        try {
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println(data.get("domainNameUpperCamel") + "Service.java 生成成功");

            CodeUtil.tempFactory(savePath1, tempName1, data);
            System.out.println(data.get("domainNameUpperCamel") + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成Service失败");
        }

    }

    public static void getDTO(Model model, String author, ProjectConfig projectConfig, String fileName, String type) {
        try {
            Map<String, Object> data = CodeUtil.setParam(model, author,projectConfig);
            String savePath, tempName;
            if ("4".equals(type)){
                savePath = fileName + PathConfig.dto + data.get("domainNameUpperCamel") + "DTO.java";
                tempName = "code"+ File.separator +"dto.ftl";
            }else {
                savePath = fileName + PathConfig.JAVA_PATH + PathConfig.dto + data.get("domainNameUpperCamel") + "DTO.java";
                tempName = "code"+ File.separator +"dto.ftl";
            }
            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println(data.get("domainNameUpperCamel") + "DTO.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成DTO失败");
        }
    }

//    public static void getMapper(ProjectConfig projectConfig, String fileName){
//        try {
//            Map<String, Object> data = new HashMap<>(16);
//            data.put("basePackage", projectConfig.getPackAge());
//
//            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.core + "Mapper.java";
//            String tempName = "code/Mapper.ftl";
//
//            CodeUtil.tempFactory(savePath, tempName, data);
//            System.out.println("Mapper.java 生成成功");
//        } catch (Exception e) {
//            throw new GeneratorException("生成Mapper失败");
//        }
//    }
    public static void getOrderBy(ProjectConfig projectConfig, String fileName){
        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("basePackage", projectConfig.getPackAge());

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.core + "OrderBy.java";
            String tempName = "code"+ File.separator +"OrderBy.ftl";

            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("OrderBy.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成OrderBy失败");
        }
    }
    public static void getQueryService(ProjectConfig projectConfig, String fileName){
        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("basePackage", projectConfig.getPackAge());

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.core + "Service.java";
            String tempName = "code"+ File.separator +"QueryService.ftl";

            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("QueryService.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成Service失败");
        }
    }
    public static void getAbService(ProjectConfig projectConfig, String fileName){
        try {
            Map<String, Object> data = new HashMap<>(16);
            data.put("basePackage", projectConfig.getPackAge());

            String savePath = fileName + PathConfig.JAVA_PATH + PathConfig.core + "AbstractService.java";
            String tempName = "code"+ File.separator +"AbstractService.ftl";

            CodeUtil.tempFactory(savePath, tempName, data);
            System.out.println("AbstractService.java 生成成功");
        } catch (Exception e) {
            throw new GeneratorException("生成AbstractService失败");
        }
    }
}
