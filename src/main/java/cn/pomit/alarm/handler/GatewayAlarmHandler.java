package cn.pomit.alarm.handler;

import com.alibaba.fastjson.JSONObject;
import com.sunlands.gateway.ApiGatewayClient;

import cn.pomit.alarm.dto.ResultModel;
import cn.pomit.alarm.http.HttpClientPrototype;
import cn.pomit.alarm.service.ApiGatewayService;
import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.annotation.Value;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consul.http.res.ResCode;
import cn.pomit.consul.http.res.ResType;

public class GatewayAlarmHandler extends AbstractResourceHandler {
	@Value("api.gateway.kongUrl")
	private String apiGatewayKongUrl;

	@Value("api.gateway.appKey")
	private String apiGatewayAppKey;

	@Value("api.gateway.appSecret")
	private String apiGatewayAppSecret;

	@Mapping("/alarm/gateway")
	public HttpResponseMessage gateway(HttpRequestMessage httpRequestMessage) {
		try {
			log.info("apiGatewayAppSecret: " + apiGatewayAppSecret + ", apiGatewayAppKey: " + apiGatewayAppKey);

			ApiGatewayClient apiGatewayClient = new ApiGatewayClient.Builder().kongUrl(apiGatewayKongUrl)
					.appKey(apiGatewayAppKey).appSecret(apiGatewayAppSecret).client(HttpClientPrototype.getHttpClient())
					.build();

			ApiGatewayService apiGatewayService = new ApiGatewayService(apiGatewayClient);
			ResultModel resultModel = apiGatewayService.getAlarmInfo(null);
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
			httpResponseMessage.setMessage(JSONObject.toJSONString(ResultModel.error("请求API网关失败")));
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

	public String getApiGatewayKongUrl() {
		return apiGatewayKongUrl;
	}

	public void setApiGatewayKongUrl(String apiGatewayKongUrl) {
		this.apiGatewayKongUrl = apiGatewayKongUrl;
	}

	public String getApiGatewayAppKey() {
		return apiGatewayAppKey;
	}

	public void setApiGatewayAppKey(String apiGatewayAppKey) {
		this.apiGatewayAppKey = apiGatewayAppKey;
	}

	public String getApiGatewayAppSecret() {
		return apiGatewayAppSecret;
	}

	public void setApiGatewayAppSecret(String apiGatewayAppSecret) {
		this.apiGatewayAppSecret = apiGatewayAppSecret;
	}

}
