package cn.pomit.alarm.handler;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.pomit.alarm.domain.UserInfo;
import cn.pomit.alarm.dto.ResultModel;
import cn.pomit.alarm.service.UserInfoService;
import cn.pomit.alarm.service.UserInfoServiceImp;
import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consul.http.res.ResCode;
import cn.pomit.consul.http.res.ResType;
import cn.pomit.mybatis.ProxyHandlerFactory;

public class UserInfoTestHandler extends AbstractResourceHandler {

	@Mapping(value = "/user/findUser")
	public HttpResponseMessage findUser(HttpRequestMessage httpRequestMessage) {
		UserInfoService userInfoService = ProxyHandlerFactory.getTransaction(UserInfoServiceImp.class);
		String userName = httpRequestMessage.getParameterString("userName");

		ResultModel resultModel = ResultModel.ok(userInfoService.findUser(userName));
		HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setResCode(ResCode.OK.getValue());
		httpResponseMessage.setResType(ResType.JSON.getValue());
		httpResponseMessage.setMessage(JSONObject.toJSONString(resultModel));
		return httpResponseMessage;
	}
	
	@Mapping(value = "/user/add")
	public HttpResponseMessage add(HttpRequestMessage httpRequestMessage) throws Exception {
		UserInfoService userInfoService = ProxyHandlerFactory.getTransaction(UserInfoServiceImp.class);
		String content = httpRequestMessage.getContent();
		UserInfo user = JSONObject.toJavaObject(JSON.parseObject(content), UserInfo.class);
		ResultModel resultModel = ResultModel.ok(userInfoService.save(user));
		HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setResCode(ResCode.OK.getValue());
		httpResponseMessage.setResType(ResType.JSON.getValue());
		httpResponseMessage.setMessage(JSONObject.toJSONString(resultModel));
		return httpResponseMessage;
	}

}
