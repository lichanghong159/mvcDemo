package com.lch.service.user;

import com.lch.entity.User;

import java.util.List;

/**
 * @createby: lichanghong on 2018-06-15 22:40
 **/
public interface IUserService {
    /**
     * 查询用户
     * @param userId
     * @return
     */
    List<User> queryUserById(Integer userId);
    public int insertUser(User user);

    public boolean login(String userName,String password);

}
