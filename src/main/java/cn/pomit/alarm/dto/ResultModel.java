package cn.pomit.alarm.dto;

/**
 * @author yujinlong
 */
public class ResultModel {

	private int code;
	private String msg;
	private Object data;
	
	public ResultModel(){
		
	}

	public ResultModel(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultModel(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultModel(ResultCode resultCodeEnum, Object data) {
		this.code = resultCodeEnum.getCode();
		this.msg = resultCodeEnum.getDesc();
		this.data = data;
	}

	public ResultModel(ResultCode resultCodeEnum) {
		this.code = resultCodeEnum.getCode();
		this.msg = resultCodeEnum.getDesc();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultModel ok() {
		return new ResultModel(ResultCode.CODE_00000);
	}

	public static ResultModel ok(Object data) {
		return new ResultModel(ResultCode.CODE_00000, data);
	}

	public static ResultModel error() {
		return new ResultModel(ResultCode.CODE_00001);
	}

	public static ResultModel error(String msg) {
		return new ResultModel(ResultCode.CODE_00001.getCode(), msg);
	}

	public static ResultModel error(String msg, Object data) {
		return new ResultModel(ResultCode.CODE_00001.getCode(), msg, data);
	}
	
	public static ResultModel unAuth() {
		return new ResultModel(ResultCode.CODE_00401);
	}
}
