package com.longfor.csas.feign.fallback;

import com.longfor.csas.dto.IamCode;
import com.longfor.csas.dto.func.*;
import com.longfor.csas.dto.role.IamRole;
import com.longfor.csas.dto.role.IamRoleReq;
import com.longfor.csas.dto.role.IamRoleUsersReq;
import com.longfor.csas.dto.role.IamRoleUsersResp;
import com.longfor.csas.dto.tag.IamTagData;
import com.longfor.csas.dto.tag.IamTagResponse;
import com.longfor.csas.feign.IamAuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 15:26
 */
@Slf4j
@Component
public class IamAuthFeignClientFallBack implements IamAuthFeignClient {
    @Override
    public IamResponse getFuncByUsernameAppCodes(IamAuthFuncReq request, String apiKey) {
        log.warn("IAM fallBack getFuncByUsernameAppCodes request:{}, apiKey:{}",request, apiKey);
        return new IamResponse();
    }

    @Override
    public IamResponse getDataAccessByUserFuncOper(IamAuthDataAccesReq request, String apiKey) {
        log.warn("IAM fallBack getDataAccessByUserFuncOper request:{}, apiKey:{}",request, apiKey);
        return new IamResponse();
    }

    @Override
    public IamOperResponse canOperComponentByUserName(IamOperReq request, String apiKey) {
        log.warn("IAM fallBack canOperComponentByUserName request:{}, apiKey:{}",request, apiKey);
        return new IamOperResponse();
    }

    @Override
    public IamCode authUserNameRole(IamOperReq request, String apiKey) {
        log.warn("IAM fallBack authUserNameRole request:{}, apiKey:{}",request, apiKey);
        return new IamCode();
    }

    @Override
    public IamTagResponse<List<IamRole>> getRolesByAppCode(IamRoleReq roleReq, String apiKey) {
        log.warn("IAM fallBack 获取应用下角色 roleReq:{}, apiKey:{}", roleReq, apiKey);
        return new IamTagResponse<>();
    }

    @Override
    public IamTagResponse<List<IamRoleUsersResp>> hasRole(IamRoleUsersReq roleUsersReq, String apiKey) {
        log.warn("IAM fallBack 获取角色所有用户列表 roleUsersReq:{}, apiKey:{}", roleUsersReq, apiKey);
        return new IamTagResponse<>();
    }

    @Override
    public IamTagResponse createOrUpdateTag(IamTagData tagData, String amApiKey) {
        log.warn("IAM fallBack 创建数据标签 tagData:{}, apiKey:{}", tagData, amApiKey);
        return new IamTagResponse<>();
    }

    @Override
    public IamTagResponse<List<IamTagData>> getTagListByUserName(IamOperReq request, String apiKey) {
        log.warn("IAM fallBack getTagListByUserName request:{}, apiKey:{}",request, apiKey);
        return new IamTagResponse<>();
    }
}
