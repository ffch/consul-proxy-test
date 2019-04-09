package cn.pomit.alarm.service;

import cn.pomit.alarm.domain.UserInfo;

public interface UserInfoService {

	public UserInfo findUser(String userName);

	public int save(UserInfo user) throws Exception;
}
