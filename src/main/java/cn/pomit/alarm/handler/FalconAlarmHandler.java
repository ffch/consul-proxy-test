package cn.pomit.alarm.handler;

import com.alibaba.fastjson.JSONObject;

import cn.pomit.alarm.dto.ResultModel;
import cn.pomit.alarm.http.HttpClientPrototype;
import cn.pomit.alarm.service.FalconHostsAlarmService;
import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.annotation.Value;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consul.http.res.ResCode;
import cn.pomit.consul.http.res.ResType;

public class FalconAlarmHandler extends AbstractResourceHandler {
	

	@Value("falcon.alarm.base")
	private String falconAlarmBase;

	@Value("falcon.alarm.hosts")
	private String falconAlarmHosts;

	@Value("falcon.alarm.token")
	private String falconAlarmToken;

	@Value("falcon.alarm.services")
	private String falconAlarmServices;

	@Mapping("/alarm/hosts")
	public HttpResponseMessage hosts(HttpRequestMessage httpRequestMessage) {
		try {
			log.info("falconAlarmBase: " + falconAlarmBase + ", falconAlarmHosts: " + falconAlarmHosts);
			FalconHostsAlarmService falconHostsAlarmService = new FalconHostsAlarmService(falconAlarmBase,
					falconAlarmHosts, falconAlarmToken, HttpClientPrototype.getOkHttpClient());
			ResultModel resultModel = falconHostsAlarmService.getAlarmInfo(httpRequestMessage.getParameterInt("range"));
			HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
			httpResponseMessage.setResCode(ResCode.OK.getValue());
			httpResponseMessage.setResType(ResType.JSON.getValue());
			httpResponseMessage.setMessage(JSONObject.toJSONString(resultModel));
			return httpResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
			httpResponseMessage.setResCode(ResCode.OK.getValue());
			httpResponseMessage.setResType(ResType.JSON.getValue());
			httpResponseMessage.setMessage(JSONObject.toJSONString(ResultModel.error("请求falcon机器告警失败")));
			return httpResponseMessage;
		}
	}

	@Mapping("/alarm/services")
	public HttpResponseMessage services(HttpRequestMessage httpRequestMessage) {
		try {
			log.info("falconAlarmBase: " + falconAlarmBase + ", falconAlarmServices: " + falconAlarmServices);
			FalconHostsAlarmService falconHostsAlarmService = new FalconHostsAlarmService(falconAlarmBase,
					falconAlarmServices, falconAlarmToken, HttpClientPrototype.getOkHttpClient());
			ResultModel resultModel = falconHostsAlarmService.getAlarmInfo(httpRequestMessage.getParameterInt("range"));
			HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
			httpResponseMessage.setResCode(ResCode.OK.getValue());
			httpResponseMessage.setResType(ResType.JSON.getValue());
			httpResponseMessage.setMessage(JSONObject.toJSONString(resultModel));
			return httpResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
			httpResponseMessage.setResCode(ResCode.OK.getValue());
			httpResponseMessage.setResType(ResType.JSON.getValue());
			httpResponseMessage.setMessage(JSONObject.toJSONString(ResultModel.error("请求falcon实例告警失败")));
			return httpResponseMessage;
		}
	}

	@Mapping("/health")
	public HttpResponseMessage health(HttpRequestMessage httpRequestMessage) {
		HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setResCode(ResCode.OK.getValue());
		httpResponseMessage.setResType(ResType.TEXT.getValue());
		httpResponseMessage.setMessage("操作成功！");
		return httpResponseMessage;
	}

	public String getFalconAlarmBase() {
		return falconAlarmBase;
	}

	public void setFalconAlarmBase(String falconAlarmBase) {
		this.falconAlarmBase = falconAlarmBase;
	}

	public String getFalconAlarmHosts() {
		return falconAlarmHosts;
	}

	public void setFalconAlarmHosts(String falconAlarmHosts) {
		this.falconAlarmHosts = falconAlarmHosts;
	}

	public String getFalconAlarmToken() {
		return falconAlarmToken;
	}

	public void setFalconAlarmToken(String falconAlarmToken) {
		this.falconAlarmToken = falconAlarmToken;
	}

	public String getFalconAlarmServices() {
		return falconAlarmServices;
	}

	public void setFalconAlarmServices(String falconAlarmServices) {
		this.falconAlarmServices = falconAlarmServices;
	}

}
