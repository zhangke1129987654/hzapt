package com.hzapt.provider.logic;

import com.hzapt.client.domain.vo.UserVo;
import com.hzapt.client.logic.UserLogic;
import com.hzapt.common.utils.IdUtils;
import com.hzapt.provider.domain.entity.User;
import com.hzapt.provider.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@DubboService
@Transactional(rollbackFor = Exception.class)
public class UserLogicImpl implements UserLogic {

	@Autowired
	private UserService userService;

	@Override
	public void setUser(UserVo userVo) {
		User user = new User();
		user.setId(IdUtils.id());
		user.setName(userVo.getName());
		user.setGender(userVo.getGender());
		user.setAge(userVo.getAge());
		user.setCity(userVo.getCity());
		user.setPhoneNumber(userVo.getPhoneNumber());
		user.setCreateTime(new Date());
		userService.save(user);
	}

}
