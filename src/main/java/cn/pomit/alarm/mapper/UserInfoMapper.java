package cn.pomit.alarm.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.pomit.alarm.domain.UserInfo;

@Mapper
public interface UserInfoMapper {
	
	UserInfo selectByUserName(String userName);
	
	int save(UserInfo userInfo);
	
	
}
