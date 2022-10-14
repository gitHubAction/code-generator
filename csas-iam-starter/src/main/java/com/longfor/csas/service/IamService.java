package com.longfor.csas.service;

import com.longfor.csas.dto.func.IamOperReq;
import com.longfor.csas.dto.role.IamRoleUsersResp;
import com.longfor.csas.dto.tag.IamTagData;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @Date 2021/11/30 12:59
 */
public interface IamService {

    /**
     * 根据用户名获取菜单列表
     * @param userName
     * @return java.util.List<MenuConfig>
     * @author zhangshihao01
     * @date 2021/11/30 13:07
     */
//    List<MenuConfig> getAuthMenuList(String userName);


    /**
     * 获取用户所有的数据标签授权
     * @param req
     * @return java.util.List<com.longfor.csdi.dto.tag.IamTagData>
     * @author zhangshihao01
     * @date 2021/11/30 13:07
     */
    List<IamTagData> getDataTag(IamOperReq req);

    /**
     * 获取用户所有的数据标签授权编码集合
     * @param req
     * @return java.util.List<java.lang.String>
     * @author zhangshihao01
     * @date 2021/11/30 13:22
     */
    List<String> getDataTagCodeList(IamOperReq req);


    /**
     * 获取应用下对应类型角色列表
     * @param appCode
     * @param roleType 角色类型，0：全部，1：标准角色，2：自定义角色，不传默认 1：标准角色
     * @return java.util.List<java.lang.String>
     * @author zhangshihao01
     * @date 2021/12/28 10:35
     */
    List<String> getRolesByAppCode(String appCode, Integer roleType);

    /**
     * 用户是否有对应角色
     * @param userName OA账号
     * @param newDeptFlag 0：旧组架；1：新组架
     * @param roleCode 角色编码
     * @param roleType 角色类型，0：全部，1：标准角色，2：自定义角色，不传默认查询全部
     * @return java.lang.Boolean
     * @author zhangshihao01
     * @date 2021/12/28 10:36
     */
    Boolean hasRole(String userName, Integer newDeptFlag, String roleCode, Integer roleType);

    /**
     * 获取用户角色列表
     * @param userName OA账号
     * @param newDeptFlag 0：旧组架；1：新组架
     * @param roleCodes 角色编码列表
     * @param roleType 角色类型，0：全部，1：标准角色，2：自定义角色，不传默认查询全部
     * @return java.util.List<IamRoleUsersResp>
     * @author zhangshihao01
     * @date 2021/12/28 11:21
     */
    List<IamRoleUsersResp> getUserRoles(String userName, Integer newDeptFlag, List<String> roleCodes, Integer roleType);

    /**
     * 新增数据标签
     * @param tagData
     * @return
     */
    Boolean createOrUpdateTag(IamTagData tagData);
}
