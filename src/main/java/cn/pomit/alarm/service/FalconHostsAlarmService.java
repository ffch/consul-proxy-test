package cn.pomit.alarm.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.sunlands.gateway.util.URLUtil;

import cn.pomit.alarm.dto.ResultModel;
import cn.pomit.alarm.dto.alarm.FalconAlarmModel;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FalconHostsAlarmService implements AlarmService {
	private String falconAlarmBase;

	private String falconAlarmHosts;

	private String falconAlarmToken;

	private OkHttpClient okHttpClient;

	public FalconHostsAlarmService(String falconAlarmBase, String falconAlarmHosts, String falconAlarmToken,
			OkHttpClient okHttpClient) {
		this.falconAlarmBase = falconAlarmBase;
		this.falconAlarmHosts = falconAlarmHosts;
		this.falconAlarmToken = falconAlarmToken;
		this.okHttpClient = okHttpClient;
	}

	@Override
	public ResultModel getAlarmInfo(Integer range) throws IOException {
		if (range == null || range < 1)
			range = DAY_SECOND;

		String urlStr = falconAlarmBase + URLUtil.completePath(falconAlarmHosts);
		HttpUrl.Builder urlBuilder =HttpUrl.parse(urlStr)
                 .newBuilder();
		urlBuilder.addQueryParameter("history_range", String.valueOf(range));
		Request request = new Request.Builder().url(urlBuilder.build()).addHeader("cookie", "SESSION=" + falconAlarmToken).build();

		Response response = okHttpClient.newCall(request).execute();
		if (response == null || !response.isSuccessful()) {
			return ResultModel.error("请求falcon接口失败");
		}

		FalconAlarmModel falconAlarmModel = JSONObject.parseObject(response.body().string(), FalconAlarmModel.class);

		if (falconAlarmModel != null && falconAlarmModel.getMeta() != null) {
			if (falconAlarmModel.getMeta().getSuccess()) {
				return ResultModel.ok(falconAlarmModel.getData());
			} else {
				return ResultModel.error(falconAlarmModel.getMeta().getMessage());
			}
		}
		return ResultModel.error("请求falcon接口返回数据为空");
	}

}
