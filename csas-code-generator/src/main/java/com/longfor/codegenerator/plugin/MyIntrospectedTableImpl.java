package com.longfor.codegenerator.plugin;

import com.longfor.codegenerator.plugin.xmlmapper.MyXMLMapperGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/25 20:53
 */
public class MyIntrospectedTableImpl extends IntrospectedTableMyBatis3Impl {

    @Override
    public String getMyBatis3SqlMapNamespace() {
        // 获取mapper.java包名
        String targetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        StringBuilder stringBuilder = new StringBuilder();
        String nameSpace = this.getMyBatis3JavaMapperType();
        if(nameSpace == null){
            nameSpace = this.getMyBatis3FallbackSqlMapNamespace();
        }
        String daoName = nameSpace.substring(nameSpace.lastIndexOf("."));
        stringBuilder.append(targetPackage).append(daoName);
        return stringBuilder.toString();
    }

    @Override
    public void calculateGenerators(List<String> warnings,
                                    ProgressCallback progressCallback) {
        calculateJavaModelGenerators(warnings, progressCallback);

        AbstractJavaClientGenerator javaClientGenerator =
                calculateClientGenerators(warnings, progressCallback);

        calculateXmlMapperGenerator(javaClientGenerator, warnings, progressCallback);
    }

    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator,
                                               List<String> warnings,
                                               ProgressCallback progressCallback) {
        // 设置自己的可以针对varchar if标签 !='' 的判断 xmlMapperGenerator
        xmlMapperGenerator = new MyXMLMapperGenerator();

        initializeAbstractGenerator(xmlMapperGenerator, warnings,
                progressCallback);
    }

}
