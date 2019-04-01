package cn.pomit.alarm;

import cn.pomit.alarm.handler.FalconAlarmHandler;
import cn.pomit.alarm.handler.GatewayAlarmHandler;
import cn.pomit.consul.ConsulProxyApplication;
import cn.pomit.consul.annotation.JsonServer;

@JsonServer(handler={FalconAlarmHandler.class,GatewayAlarmHandler.class})
public class AlarmApp {
	public static void main(String[] args) {
		ConsulProxyApplication.run(AlarmApp.class);
	}

}
