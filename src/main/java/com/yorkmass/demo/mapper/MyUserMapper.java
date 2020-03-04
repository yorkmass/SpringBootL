package com.yorkmass.demo.mapper;

import com.yorkmass.demo.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface MyUserMapper {
    @Select("select password from user where username=#{username}")
    String getPasswordByUsername(String username);

    @Select("SELECT roles.rolename FROM `user` INNER JOIN roles ON `user`.roleid = roles.roleid WHERE `user`.username = #{username}")
    String getRolebyUsername(String username);

    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);

}
