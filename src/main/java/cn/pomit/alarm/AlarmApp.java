package cn.pomit.alarm;

import cn.pomit.alarm.handler.FalconAlarmHandler;
import cn.pomit.alarm.handler.GatewayAlarmHandler;
import cn.pomit.alarm.handler.UserInfoTestHandler;
import cn.pomit.consul.ConsulProxyApplication;
import cn.pomit.consul.annotation.EnableMybatis;
import cn.pomit.consul.annotation.EnableServer;

@EnableServer(handler={FalconAlarmHandler.class,GatewayAlarmHandler.class, UserInfoTestHandler.class})
@EnableMybatis(mapperScan = "cn.pomit.alarm.mapper")
public class AlarmApp {
	public static void main(String[] args) {
		ConsulProxyApplication.run(AlarmApp.class, args);
	}

}
