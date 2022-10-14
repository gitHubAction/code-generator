package com.longfor.csas.service.impl;

import com.google.common.collect.Lists;
import com.longfor.csas.dto.func.IamOperReq;
import com.longfor.csas.dto.role.IamRole;
import com.longfor.csas.dto.role.IamRoleReq;
import com.longfor.csas.dto.role.IamRoleUsersReq;
import com.longfor.csas.dto.role.IamRoleUsersResp;
import com.longfor.csas.dto.tag.IamTagData;
import com.longfor.csas.dto.tag.IamTagResponse;
import com.longfor.csas.feign.IamAuthFeignClient;
import com.longfor.csas.service.IamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/30 13:00
 */
@Slf4j
@Service
public class IamServiceImpl implements IamService {

    @Resource
    private IamAuthFeignClient authFeignClient;

    @Value("${longfor.web.feign.clients.iam-auth-feign.apikey}")
    private String amApiKey;

    @Override
    public List<String> getRolesByAppCode(String appCode, Integer roleType) {
        log.info("查询应用下角色列表 appCode:{}, roleType:{}", appCode, roleType);
        IamRoleReq roleReq = IamRoleReq.builder()
                .appCode(appCode)
                .roleType(roleType)
                .build();
        IamTagResponse<List<IamRole>> resp = authFeignClient.getRolesByAppCode(roleReq, amApiKey);
        if(resp == null || resp.getRespCode() == null || !"1".equals(resp.getRespCode().getCode())){
            log.info("appCode:{} 暂无任何【{}】类型角色!", appCode, roleType);
            return Lists.newArrayList();
        }
        log.info("查询应用下角色列表 appCode:{}, roleList:{}", appCode, resp.getData());
        return resp.getData().stream().map(IamRole::getCode).collect(Collectors.toList());
    }

    @Override
    public Boolean hasRole(String userName, Integer newDeptFlag, String roleCode, Integer roleType) {
        log.info("查询用户是否有该角色 userName:{}, deptFlag:{}, roleCode:{}, roleType:{}", userName, newDeptFlag, roleCode, roleType);
        List<IamRoleUsersResp> roleList = this.getUserRoles(userName, newDeptFlag, Lists.newArrayList(roleCode), roleType);
        return CollectionUtils.isNotEmpty(roleList);
    }

    @Override
    public List<IamRoleUsersResp> getUserRoles(String userName, Integer newDeptFlag, List<String> roleCode, Integer roleType) {
        log.info("查询用户角色列表 userName:{}, deptFlag:{}, roleCode:{}, roleType:{}", userName, newDeptFlag, roleCode, roleType);
        IamRoleUsersReq req = IamRoleUsersReq.builder()
                .newDeptFlag(newDeptFlag)
                .userNames(Lists.newArrayList(userName))
                .roleCodes(roleCode)
                .roleType(roleType).build();
        IamTagResponse<List<IamRoleUsersResp>> roleResp = authFeignClient.hasRole(req, amApiKey);
        log.info("查询用户角色列表 roleResp:{}", roleResp);
        if(roleResp == null || roleResp.getRespCode() == null || !"1".equals(roleResp.getRespCode().getCode())){
            log.info("查询用户是否有该角色 userName:{}, deptFlag:{}, roleCode:{}, roleType:{} 异常", userName, newDeptFlag, roleCode, roleType);
            return Lists.newArrayList();
        }
        return roleResp.getData();
    }

    @Override
    public Boolean createOrUpdateTag(IamTagData tagData) {
        log.info("新增数据标签 tagData:{}", tagData);
        IamTagResponse response = authFeignClient.createOrUpdateTag(tagData, amApiKey);
        if(response == null || response.getRespCode() == null || !"1".equals(response.getRespCode().getCode())){
            log.info("新增数据标签 tagData:{} 失败  response:{}", tagData, response);
            return false;
        }
        return true;
    }

    @Override
    public List<String> getDataTagCodeList(IamOperReq req) {
        return getDataTag(req)
                .stream()
                .map(IamTagData::getCode)
                .collect(Collectors.toList());
    }

    @Override
    public List<IamTagData> getDataTag(IamOperReq req) {
        log.info("获取数据标签权限 req:{}", req);
        IamTagResponse<List<IamTagData>> authTagData = authFeignClient.getTagListByUserName(req, amApiKey);
        log.info("获取数据标签权限 authTagData:{}", authTagData);
        if(authTagData == null || authTagData.getRespCode() == null || !"1".equals(authTagData.getRespCode().getCode())){
            log.info("userName:{} 暂无任何数据标签权限!", req.getUserName());
            return Lists.newArrayList();
        }
        return authTagData.getData();
    }
}
