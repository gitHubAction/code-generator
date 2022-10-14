package com.longfor.codegenerator.controller;

import com.longfor.codegenerator.config.CodeConfig;
import com.longfor.codegenerator.core.CodeGenerator;
import com.longfor.codegenerator.core.ResponseMsgUtil;
import com.longfor.codegenerator.core.Result;
import com.longfor.codegenerator.model.DbConfig;
import com.longfor.codegenerator.model.PathConfig;
import com.longfor.codegenerator.model.ProjectConfig;
import com.longfor.codegenerator.util.CodeUtil;
import com.longfor.codegenerator.util.ZipUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 生成SpringBoot-Controller类
 *
 * @author chenbin on 2018
 */
@Api(description = "代码生成器")
@RestController
@RequestMapping("/springBoot")
public class SpringController {

    @ApiOperation(value = "创建", notes = "四种类型项目创建")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "packAge", value = "包路径，如：com.uhope.rl.demo", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "jsonData", value = "json数组", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "fileName", value = "项目名称，如：rl-demo", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "author", value = "创建人，如：ChenBin", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "项目类型，1、core层；2、app层；3、独立项目；4、代码片段；5、dao层", required = true, dataType = "String")
    })
    @GetMapping("/create")
    public Result createProject(String packAge, String jsonData, DbConfig dbConfig, String fileName, String author, String type,
                                HttpServletResponse response, String groupId) throws UnsupportedEncodingException {
        //初始化项目名称以及各个文件的路径
        ProjectConfig projectConfig = PathConfig.create(packAge);
        String newFileName = CodeConfig.FILE_PATH + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;

        //生成代码
        CodeGenerator.getCode(jsonData, dbConfig, newFileName, projectConfig, author, type, groupId);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".zip", CodeConfig.ENCODING));
        response.setCharacterEncoding(CodeConfig.ENCODING);

        try {
            File file = new File(newFileName + ".zip");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fous = new FileOutputStream(file);

            //压缩导出
            ZipUtil.toZip(newFileName, fous, true);
            response.setContentType("application/x-msdownload");
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(file);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte[] buffer = new byte[1024];
            int len;
            //循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMsgUtil.failure();
        }finally {
            String delFileName = newFileName.substring(0, newFileName.lastIndexOf("/"));
            //删除临时文件夹
            CodeUtil.delFolder(delFileName);
        }
        return ResponseMsgUtil.success(null);
    }


}
