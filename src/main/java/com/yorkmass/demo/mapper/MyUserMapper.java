package com.yorkmass.demo.mapper;

import com.yorkmass.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
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
    @Select("SELECT roles.rolename FROM roles INNER JOIN urmap ON urmap.roleid = roles.roleid INNER JOIN `user` ON urmap.userid = `user`.userid\n" +
            "WHERE `user`.username = #{username}")
    List<String> getRolesbyUsername(String username);
    /*
    通过用户名获取用户
     */
    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);

    /*
    更新用户信息
     */
    @Update("update user set name=#{name},gender=#{gender},birth=#{birth},phone=#{phone},email=#{email} where username=#{username}")
    int setUserInfo(User user);

    /**
     * 修改密码
     */
    @Update("update user set password=#{password} where username=#{username}")
    int changePwd(@Param("password") String password, @Param("username") String username);

}
