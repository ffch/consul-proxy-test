package cn.pomit.alarm.dto;


/**
 * 响应码及其描述
 */
public enum ResultCode {

    /**
     * 通用
     */
	CODE_00000(0, "操作成功"),
	CODE_00001(1, "参数错误"),
	CODE_00002(2, "请求时间超过合理值"),
	CODE_00003(3, "获取上游数据失败"),
	CODE_00401(401, "未授权"),
	CODE_10000(10000, "请求失败"),
	CODE_99999(99999, "系统错误");


    private int code;
	private String desc;

	ResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
