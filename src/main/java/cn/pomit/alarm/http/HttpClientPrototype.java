package cn.pomit.alarm.http;

import java.util.concurrent.TimeUnit;

import com.sunlands.gateway.okhttp.OkHttp3ClientHttpRequest;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

public class HttpClientPrototype {
	private final int DEFAULT_TIMEOUT = 5000;
	
	private OkHttp3ClientHttpRequest okHttp3ClientHttpRequest;
	
	private OkHttpClient client;
	
	private HttpClientPrototype(){
		ConnectionPool connectionPool = new ConnectionPool(100, 30, TimeUnit.MINUTES);
		client = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // 设置连接超时
				.readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // 设置读超时
				.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // 设置写超时
				.retryOnConnectionFailure(true) // 是否自动重连
				.connectionPool(connectionPool).build();
		okHttp3ClientHttpRequest = new OkHttp3ClientHttpRequest(client);
	}
	
	private static class OkHttpHolder {
        private static HttpClientPrototype instance = new HttpClientPrototype();
    }

	public static OkHttp3ClientHttpRequest getHttpClient() {
		return OkHttpHolder.instance.okHttp3ClientHttpRequest;
	}
	
	public static OkHttpClient getOkHttpClient() {
		return OkHttpHolder.instance.client;
	}
}
