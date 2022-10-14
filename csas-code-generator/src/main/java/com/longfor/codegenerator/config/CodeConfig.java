package com.longfor.codegenerator.config;


import com.longfor.codegenerator.util.ConfigUtil;

/**
 * @Author: ChenBin
 * @Date: 2018/6/13/0013 17:17
 */
public class CodeConfig {
    public static String CONTEXT_ID;
    public static String TARGET_RUNTIME;
    public static String CONFIG_URL;
    public static String CONFIGURATION_TYPE;
    public static String ENCODING;
    public static String LOADER_PATH;
    public static String FILE_PATH;

    static {
        CONTEXT_ID = ConfigUtil.getConfig().get("context.id");
        TARGET_RUNTIME = ConfigUtil.getConfig().get("context.target.runtime");
        CONFIG_URL = ConfigUtil.getConfig().get("db.config.url");
        CONFIGURATION_TYPE = ConfigUtil.getConfig().get("java.client.configuration.type");
        ENCODING = ConfigUtil.getConfig().get("encoding");
        LOADER_PATH = ConfigUtil.getConfig().get("loader.template.path");
        FILE_PATH = ConfigUtil.getConfig().get("linux.file.path");
    }

}
