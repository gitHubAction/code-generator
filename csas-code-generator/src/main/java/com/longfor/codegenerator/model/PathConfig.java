package com.longfor.codegenerator.model;

import com.longfor.codegenerator.util.CodeUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ChenBin
 * @Date: 2018/6/6/0006 15:26
 */
public class PathConfig {
    /**
     * java文件路径
     */
    public static final String JAVA_PATH = File.separator + "src"+ File.separator + "main"+ File.separator +"java";
    /**
     * 资源文件路径
     */
    public static final String RESOURCES_PATH = File.separator + "src"+ File.separator +"main"+ File.separator +"resources";
    /**
     * 日期
     */
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    /**
     * 项目包路径
     */
    public static String packAges;
    /**
     * 生成的Service存放路径
     */
    public static String service;
    /**
     * 生成的Service实现存放路径
     */
    public static String serviceImpl;
    /**
     * 实体类路径
     */
    public static String domain;
    /**
     * 生成的DTO存放路径
     */
    public static String dto;
    /**
     * 路由层所在包
     */
    public static String controller;
    /**
     * 工具类所在包
     */
    public static String util;
    /**
     * 配置类所在包
     */
    public static String config;
    /**
     * 全局异常处理所在包
     */
    public static String exception;
    /**
     * 过滤器所在包
     */
    public static String filter;
    /**
     * 功能模块所在包
     */
    public static String core;


    public static ProjectConfig create(String packAge) {
        ProjectConfig projectConfig = new ProjectConfig(packAge);
        packAges = CodeUtil.packageConvertPath(projectConfig.getPackAge());
        service = CodeUtil.packageConvertPath(projectConfig.getService());
        serviceImpl = CodeUtil.packageConvertPath(projectConfig.getServiceImpl());
        domain = CodeUtil.packageConvertPath(projectConfig.getDomain());
        dto = CodeUtil.packageConvertPath(projectConfig.getDto());
        controller = CodeUtil.packageConvertPath(projectConfig.getController());
        util = CodeUtil.packageConvertPath(projectConfig.getUtil());
        config = CodeUtil.packageConvertPath(projectConfig.getConfig());
        exception = CodeUtil.packageConvertPath(projectConfig.getException());
        filter = CodeUtil.packageConvertPath(projectConfig.getFilter());
        core = CodeUtil.packageConvertPath(projectConfig.getCore());
        return projectConfig;
    }

    private PathConfig() {
    }
}
