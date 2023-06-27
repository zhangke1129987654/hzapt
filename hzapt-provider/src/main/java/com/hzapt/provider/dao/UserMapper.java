package com.hzapt.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzapt.provider.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
