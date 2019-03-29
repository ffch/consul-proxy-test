package cn.pomit.alarm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sunlands.gateway.ApiGatewayClient;
import com.sunlands.gateway.ClientHttpResponse;
import com.sunlands.gateway.util.NameValuePair;

import cn.pomit.alarm.dto.ResultModel;
import cn.pomit.alarm.dto.alarm.ApiAlarmModel;
import io.netty.util.internal.StringUtil;

public class ApiGatewayService implements AlarmService {
	private ApiGatewayClient apiGatewayClient;

	public ApiGatewayService(ApiGatewayClient apiGatewayClient) {
		this.apiGatewayClient = apiGatewayClient;
	}

	@Override
	public ResultModel getAlarmInfo(Integer range) throws IOException {
		ClientHttpResponse response = null;

		List<NameValuePair> nameValuePairList = new ArrayList<>();

		response = apiGatewayClient.get("gateway/alarm/history", nameValuePairList);
		String responseBodyStr = (response == null) ? null : response.getBodyStr();
		if (response == null || StringUtil.isNullOrEmpty(responseBodyStr)) {
			return ResultModel.error("请求网关接口返回为空");
		}

		ApiAlarmModel apiAlarmModel = JSONObject.parseObject(responseBodyStr, ApiAlarmModel.class);

		if (apiAlarmModel == null) {
			apiAlarmModel = new ApiAlarmModel();
			apiAlarmModel.setCode(1);
			apiAlarmModel.setMessage("请求网关接口返回数据为空");
		}
		if (response.getStatusCode() != 200) {
			apiAlarmModel.setCode(1);
		}
		if (apiAlarmModel.getCode() == 0) {
			return ResultModel.ok(apiAlarmModel.getData());
		} else {
			return ResultModel.error(apiAlarmModel.getMessage());
		}

	}

}
