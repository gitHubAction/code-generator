package com.longfor.csas.exception;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/2/7 14:24
 */
public interface CommonError {

    /**
     * 获取异常编码
     * @return string
     * @author zhangshihao01
     * @date 2021/2/7 14:24
     */
    String getCode();

    /**
     * 获取异常信息
     * @return java.lang.String
     * @author zhangshihao01
     * @date 2021/2/7 14:25
     */
    String getMsg();

    /**
     * 设置异常信息
     * @param errMsg errMsg
     * @return com.longfor.smp.exception.CommonError
     * @author zhangshihao01
     * @date 2021/2/7 14:25
     */
    CommonError setMsg(String errMsg);

    /**
     * 设置异常信息
     * @param code
     * @return com.longfor.smp.exception.CommonError
     * @author zhangshihao01
     * @date 2021/2/7 14:25
     */
    CommonError setCode(String code);
}
