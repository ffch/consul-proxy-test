package cn.pomit.alarm;

import cn.pomit.alarm.handler.AlarmHandler;
import cn.pomit.consul.ConsulProxyApplication;
import cn.pomit.consul.annotation.JsonServer;

@JsonServer(handler=AlarmHandler.class)
public class AlarmApp {
	public static void main(String[] args) {
		ConsulProxyApplication.run(AlarmApp.class);
	}

}
