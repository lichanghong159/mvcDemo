package com.lch.service.user.impl;

import com.alibaba.druid.util.StringUtils;
import com.lch.dao.mapper.UserMapper;
import com.lch.entity.User;
import com.lch.entity.UserExample;
import com.lch.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @createby: lichanghong on 2018-06-15 22:45
 **/
@Service
public class UserServiceImpl implements IUserService {
    /**
     * 自动注入userMapper
     */
    @Autowired UserMapper userMapper;
    @Override
    public List<User> queryUserById(Integer userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(!"".equals(userId==null?"":userId)){
            criteria.andIdEqualTo(userId);
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public int insertUser(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public boolean login(String userName, String password) {

        if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(password)) return false;
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andPasswordEqualTo(password);

        return userMapper.selectByExample(example).size()==0?false:true;
    }
}
