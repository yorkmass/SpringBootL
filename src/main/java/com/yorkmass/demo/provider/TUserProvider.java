package com.yorkmass.demo.provider;
/**
import com.yorkmass.demo.domain.TUser;
import org.apache.ibatis.jdbc.SQL;


 * user构建动态sql语句

public class TUserProvider {
    public String updateTUser(final TUser tUser){
        return new SQL(){
            {
                UPDATE("t_user");
                if(tUser.getId()!=null){
                    SET("id=#{id}");
                }
                if(tUser.getEmpId()!=null){
                    SET("emp_Id=#{empId}");
                }
                if(tUser.getUsername()!=null){
                    SET("username=#{username}");
                }
                if(tUser.getPassword()!=null){
                    SET("password=#{password}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

}
 */