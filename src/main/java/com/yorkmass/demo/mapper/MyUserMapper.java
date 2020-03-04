package com.yorkmass.demo.mapper;

import com.yorkmass.demo.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface MyUserMapper {
    /*
    通过用户名获取密码
     */
    @Select("select password from user where username=#{username}")
    String getPasswordByUsername(String username);
    /*
    通过用户名获得角色名
     */
    @Select("SELECT roles.rolename FROM `user` INNER JOIN roles ON `user`.roleid = roles.roleid WHERE `user`.username = #{username}")
    String getRolebyUsername(String username);
    /*
    通过用户名获取用户
     */
    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);

}
