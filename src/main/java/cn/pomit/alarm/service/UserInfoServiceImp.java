package cn.pomit.alarm.service;

import cn.pomit.alarm.domain.UserInfo;
import cn.pomit.alarm.mapper.UserInfoMapper;
import cn.pomit.mybatis.ProxyHandlerFactory;
import cn.pomit.mybatis.annotation.Transactional;

@Transactional
public class UserInfoServiceImp implements UserInfoService{

	UserInfoMapper userInfoMapper = ProxyHandlerFactory.getMapper(UserInfoMapper.class);

	public UserInfo findUser(String userName) {		
		return userInfoMapper.selectByUserName(userName);
	}

	public int save(UserInfo user) throws Exception{		
		UserInfo test = userInfoMapper.selectByUserName(user.getUserName());
		System.out.println(test);
		if(test == null){
			int ret = userInfoMapper.save(user);
			System.out.println(ret);
			return ret;
		}

		return -1;
	}

}
