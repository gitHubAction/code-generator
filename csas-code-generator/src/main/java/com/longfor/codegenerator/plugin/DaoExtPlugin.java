package com.longfor.codegenerator.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/25 20:57
 */
public class DaoExtPlugin extends PluginAdapter {

    private String daoTargetProject = null;
    private String daoTargetPackage = null;
    private String mapperTargetProject = null;
    private String mapperTargetPackage = null;

    @Override
    public boolean validate(List<String> warnings) {
        daoTargetProject = context.getJavaClientGeneratorConfiguration().getTargetProject();
        daoTargetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        mapperTargetProject = context.getSqlMapGeneratorConfiguration().getTargetProject();
        mapperTargetPackage = context.getSqlMapGeneratorConfiguration().getTargetPackage();
        return true;
    }

    /**
     * 生产MapperExt.java
     * @param introspectedTable
     * @return java.util.List<org.mybatis.generator.api.GeneratedJavaFile>
     * @author zhangshihao01
     * @date 2021/11/26 14:30
     */
//    @Override
//    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
//        List<GeneratedJavaFile> answer = new ArrayList<>();
//        context.getTableConfigurations().stream().forEach(table->{
//            String daoName = table.getDomainObjectName();
//            String filePath = daoTargetPackage;
//            String file = daoTargetProject + "." + filePath;
//            String fileName = daoName + "MapperExt.java";
//            String supperFilePath = daoTargetPackage + "." + daoName + "Mapper";
//            if(Utils.isNotExists(file, fileName)){
//                // 定义Ext接口
//                Interface face = new Interface(filePath + "." + daoName + "MapperExt");
//                // 设置父接口
//                face.addSuperInterface(new FullyQualifiedJavaType(supperFilePath));
//                // 设置接口修饰符
//                face.setVisibility(JavaVisibility.PUBLIC);
//                face.addAnnotation(properties.getProperty("annotation", "@Resource"));
//                // 导包
//                face.addImportedType(new FullyQualifiedJavaType(properties.getProperty("import","javax.annotation.Resource")));
//                face.addImportedType(new FullyQualifiedJavaType(supperFilePath));
//                // 构造自定义java文件对象
//                GeneratedJavaFile javaFile = new GeneratedJavaFile(face, daoTargetProject, context.getJavaFormatter());
//                answer.add(javaFile);
//            }
//        });
//        return answer;
//    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
        List<GeneratedXmlFile> list = new ArrayList<>();
        context.getTableConfigurations().stream().forEach(table->{
            String daoName = table.getDomainObjectName();
            String nameSpace = daoTargetPackage + "." + daoName + "Mapper";
            String fileName = daoName + "MapperExt.xml";
            String filePath = mapperTargetProject + "." + mapperTargetPackage;
            if(Utils.isNotExists(filePath, fileName)){
                Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
                XmlElement root = new XmlElement("mapper");
                Utils.getAttribute(root, "namespace", nameSpace);
                document.setRootElement(root);
                GeneratedXmlFile xmlFile = new GeneratedXmlFile(document, fileName, mapperTargetPackage, mapperTargetProject, false, context.getXmlFormatter());
                list.add(xmlFile);
            }
        });
        return list;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String daoPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String daoName = introspectedTable.getTableConfiguration().getDomainObjectName();
        XmlElement rootElement = document.getRootElement();
        XmlElement select = new XmlElement("select");
        Utils.getAttribute(select, "resultMap", "BaseResultMap");
        Utils.getAttribute(select, "id", "selectAll");
        Utils.getAttribute(select, "parameterType", daoPackage + "." + daoName);
        TextElement selectStr = new TextElement("select ");
        TextElement columns = new TextElement("<include refid=\"Base_Column_List\" /> ");
        TextElement from = new TextElement("from ");
        TextElement table = new TextElement(introspectedTable.getTableConfiguration().getTableName());
        select.addElement(selectStr);
        select.addElement(columns);
        select.addElement(from);
        select.addElement(table);
        select.addElement(getWhere(introspectedTable));
        rootElement.addElement(select);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private Element getWhere(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        XmlElement where = new XmlElement("where");
        allColumns.stream().forEach(column->{
            XmlElement ifFlag = new XmlElement("if");
            if(column.getJdbcTypeName() == "VARCHAR"){
                Utils.getAttribute(ifFlag, "test", column.getJavaProperty() + " != null and " + column.getJavaProperty() +" != ''");
            }else{
                Utils.getAttribute(ifFlag, "test", column.getJavaProperty() + " != null");
            }
            String andText = "and " + column.getActualColumnName() + "= #{" + column.getJavaProperty() + ",jdbcType=" + column.getJdbcTypeName() + "}";
            TextElement and = new TextElement(andText);
            ifFlag.addElement(and);
            where.addElement(ifFlag);
        });
        return where;
    }


    /**
     * 生成额外的自定义方法
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return boolean
     * @author zhangshihao01
     * @date 2021/11/26 14:35
     */
//    @Override
//    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        Method selectAll = new Method("selectAll");
//        selectAll.setVisibility(selectAll.getVisibility());
//        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
//        selectAll.setReturnType(new FullyQualifiedJavaType("List<"+ domainObjectName +">"));
//        selectAll.addParameter(new Parameter(new FullyQualifiedJavaType(domainObjectName),
//                domainObjectName.substring(0,1).toLowerCase() + domainObjectName.substring(1)));
//        interfaze.addMethod(selectAll);
//        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
//        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
//    }
}
