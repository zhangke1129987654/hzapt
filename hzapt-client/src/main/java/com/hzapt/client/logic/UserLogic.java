package com.hzapt.client.logic;

import com.github.pagehelper.PageInfo;
import com.hzapt.client.domain.vo.UserVo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 无对外服务API
 *
 * @author 
 *
 */
@Validated
public interface UserLogic {

	void setUser(UserVo userVo);

}
