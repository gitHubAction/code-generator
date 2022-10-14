package com.longfor.csas.feign;

import com.longfor.csas.config.FeignConfig;
import com.longfor.csas.config.IamConfiguration;
import com.longfor.csas.dto.IamCode;
import com.longfor.csas.dto.func.*;
import com.longfor.csas.dto.role.IamRole;
import com.longfor.csas.dto.role.IamRoleReq;
import com.longfor.csas.dto.role.IamRoleUsersReq;
import com.longfor.csas.dto.role.IamRoleUsersResp;
import com.longfor.csas.dto.tag.IamTagData;
import com.longfor.csas.dto.tag.IamTagResponse;
import com.longfor.csas.feign.fallback.IamAuthFeignClientFallBack;
import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @Date 2021/6/8 15:24
 */
@LFFeignClient(name = IamConfiguration.IAM_URL_FEIGN_NAME,configuration = {FeignConfig.class, LFFeignConfiguration.class},fallback = IamAuthFeignClientFallBack.class)
public interface IamAuthFeignClient {


    /**
     * v2 用户账号查询用户数据标签列表
     * @param req
     * @param apiKey
     * @return com.longfor.csdi.dto.iam.tag.IamTagResponse<java.util.List < com.longfor.csdi.dto.iam.func.IamOperReq>>
     * @author zhangshihao01
     * @date 2021/11/29 18:38
     */
    @PostMapping(value = "/public/v2/tag/getListByUserName", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamTagResponse<List<IamTagData>> getTagListByUserName(@RequestBody IamOperReq req,
                                                          @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);


    /**
     * 用户账号获取(多个)应用下功能授权列表
     * @param request
     * @param apiKey
     * @return com.longfor.csdi.dto.iam.func.IamResponse
     * @author zhangshihao01
     * @date 2021/11/29 18:38
     */
    @PostMapping(value = "/public/v2/accessControl/getByUsernameAppCodes", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamResponse getFuncByUsernameAppCodes(@RequestBody IamAuthFuncReq request,
                                          @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);


    /**
     * 查询用户的数据权限范围
     * @param request
     * @param apiKey
     * @return com.longfor.csdi.dto.iam.func.IamResponse
     * @author zhangshihao01
     * @date 2021/11/29 18:38
     */
    @PostMapping(value = "/public/v3/dataAccess/getByUserFuncOper", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamResponse getDataAccessByUserFuncOper(@RequestBody IamAuthDataAccesReq request,
                                            @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);


    /**
     * 用户账号判断是否具备组件操作权限
     * @param request
     * @param apiKey
     * @return com.longfor.csdi.dto.iam.func.IamOperResponse
     * @author zhangshihao01
     * @date 2021/11/29 18:38
     */
    @PostMapping(value = "/public/v2/accessControl/canOperComponentByUserName", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamOperResponse canOperComponentByUserName(@RequestBody IamOperReq request,
                                               @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);

    /**
     *
     * @param request
     * @param apiKey
     * @return com.longfor.csdi.dto.iam.IamCode
     * @author zhangshihao01
     * @date 2021/11/29 18:38
     */
    @PostMapping(value = "/public/v2/role/authByUserNameRoleCode", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamCode authUserNameRole(@RequestBody IamOperReq request,
                             @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);


    /**
     * 查询应用所有自定义角色
     * @param roleReq
     * @param apiKey
     * @return com.longfor.csas.dto.tag.IamTagResponse<java.util.List < com.longfor.csas.dto.role.IamRole>>
     * @author zhangshihao01
     * @date 2021/12/28 10:12
     */
    @PostMapping(value = "/public/v3/role/getRolesByAppCode", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamTagResponse<List<IamRole>> getRolesByAppCode(@RequestBody IamRoleReq roleReq,
                                                    @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);




    @PostMapping(value = "/public/v3/oru/getOrgRoleUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamTagResponse<List<IamRoleUsersResp>> hasRole(@RequestBody IamRoleUsersReq roleUsersReq,
                                                   @RequestHeader(value = "X-Gaia-Api-Key") String apiKey);

    /**
     * 新增数据权限
     * @param tagData
     * @param amApiKey
     * @return com.longfor.csas.dto.tag.IamTagResponse
     * @author zhangshihao01
     * @date 2022/2/9 16:33
     */
    @PostMapping(value = "/public/v2/tag/createOrUpdateTag", consumes = MediaType.APPLICATION_JSON_VALUE)
    IamTagResponse createOrUpdateTag(@RequestBody IamTagData tagData,
                                     @RequestHeader(value = "X-Gaia-Api-Key") String amApiKey);
}
