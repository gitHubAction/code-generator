package ${basePackage}.web;

import com.github.pagehelper.PageInfo;
import ${basePackage}.core.OrderBy;
import ${basePackage}.domain.${domainNameUpperCamel};
import ${basePackage}.service.${domainNameUpperCamel}Service;
import ${basePackage}.utils.BaseRequest;
import ${basePackage}.utils.ResponseMsgUtil;
import ${basePackage}.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ${author}
 * @description ${description}-Controller
 * @date ${date}
 */
@Api(description = "${description}")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${domainNameUpperCamel}Controller {

    @Resource
    private ${domainNameUpperCamel}Service ${domainNameLowerCamel}Service;

    @ApiOperation(value = "新增", notes = "单表新增")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "${domainNameUpperCamel}", value = "${domainNameUpperCamel}对象", required = true, dataType = "Object")
    })
    @PostMapping("/add")
    public Result<${domainNameUpperCamel}> add(@RequestBody ${domainNameUpperCamel} ${domainNameLowerCamel}) {
        ${domainNameLowerCamel}Service.insert(${domainNameLowerCamel});
        return ResponseMsgUtil.success(${domainNameLowerCamel});
    }

    @ApiOperation(value = "删除", notes = "单表删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键", required = true, dataType = "${pkDataType}")
    })
    @DeleteMapping("/delete")
    public Result delete(@RequestParam ${pkDataType} id) {
        ${domainNameLowerCamel}Service.remove(id);
        return ResponseMsgUtil.success(null);
    }

    @ApiOperation(value = "更新", notes = "单表更新")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "${domainNameUpperCamel}", value = "${domainNameUpperCamel}对象", required = true, dataType = "Object")
    })
    @PostMapping("/update")
    public Result<${domainNameUpperCamel}> update(@RequestBody ${domainNameUpperCamel} ${domainNameLowerCamel}) {
        ${domainNameLowerCamel}Service.update(${domainNameLowerCamel});
        return ResponseMsgUtil.success(${domainNameLowerCamel});
    }

    @ApiOperation(value = "详情", notes = "单表详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键", required = true, dataType = "${pkDataType}")
    })
    @GetMapping("/detail")
    public Result<${domainNameUpperCamel}> detail(@RequestParam ${pkDataType} id) {
        ${domainNameUpperCamel} ${domainNameLowerCamel} = ${domainNameLowerCamel}Service.get(id);
        return ResponseMsgUtil.success(${domainNameLowerCamel});
    }

    @ApiOperation(value = "列表条件查询", notes = "单表列表条件查询,支持单字段排序")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "sidx", value = "排序字段", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sort", value = "升降序", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "${domainNameUpperCamel}", value = "TPmApp对象", required = true, dataType = "Object")
    })
    @PostMapping("/list")
    public Result<List<${domainNameUpperCamel}>> list(@RequestParam(name = "sidx", defaultValue = "id", required = false) String sidx,
                                                      @RequestParam(name = "sort", defaultValue = "0", required = false) String sort,
                                                      @RequestBody ${domainNameUpperCamel} query) {
        OrderBy orderBy = new OrderBy();
        orderBy.add(sidx, sort);
        List<${domainNameUpperCamel}> tPmApp = ${domainNameLowerCamel}Service.selectAll(query,orderBy);
        return ResponseMsgUtil.success(tPmApp);
    }

    @ApiOperation(value = "分页条件查询", notes = "单表分页条件查询,支持单字段排序")
    @ApiImplicitParams({
    @ApiImplicitParam(paramType = "query", name = "BaseRequest<${domainNameUpperCamel}>", value = "TPmApp对象", required = true, dataType = "Object")
    })
    @PostMapping("/pageList")
    public Result<PageInfo<${domainNameUpperCamel}>> pageList(@RequestBody BaseRequest<${domainNameUpperCamel}> request) {
        return ResponseMsgUtil.success(${domainNameLowerCamel}Service.selectByPage(request));
    }
}
