package cn.pomit.alarm.service;

import java.io.IOException;

import cn.pomit.alarm.dto.ResultModel;

public interface AlarmService {
	public static int DAY_SECOND=86400;
	ResultModel getAlarmInfo(Integer range) throws IOException;
}
