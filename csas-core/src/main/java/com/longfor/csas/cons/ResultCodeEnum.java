package com.longfor.csas.cons;

import com.longfor.csas.exception.CommonError;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description 返回结果枚举
 * @date 2021/2/4 9:16
 */
public enum ResultCodeEnum implements CommonError {


	/* E10开头 */
	E10200("10200", "[E10200]正常"),
	E10400("10400", "[E10400]异常"),
	E10401("10401", "[E10401]Token无效"),
	E10402("10402", "[E10402]Token已过期"),
	E10403("10403", "[E10403]不支持该方法请求"),
	E10404("10404", "[E10404]资源不存在"),
	E10405("10405", "[E10405]参数缺失"),
	E10500("10500", "[E10500]服务器内部异常"),
    E10506("10506", "[E10506]SQL异常"),
    E10507("10507", "[E10507]非法操作"),
	/* E11开头 */
    ;
    private String code;

	private String msg;

	ResultCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
    public String getCode() {
		return this.code;
	}

	@Override
    public String getMsg() {
		return this.msg;
	}

    @Override
    public CommonError setMsg(String errMsg) {
		this.msg = errMsg;
		return this;
    }

	@Override
	public CommonError setCode(String code) {
		this.code = code;
		return this;
	}
}
