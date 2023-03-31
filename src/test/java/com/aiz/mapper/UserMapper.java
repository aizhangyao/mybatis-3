package com.aiz.mapper;

import com.aiz.pojo.User;

import java.util.List;

/**
 * @author ZhangYao
 * @version 1.0
 * @className UserMapper
 * @description UserMapper
 * @date Create in 11:38 2023/3/31
 */
public interface UserMapper {

  User findUserById(int id);

  List<User> findAllUser();
}
