package com.yorkmass.demo.utils;

import com.yorkmass.demo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * token的生成与解密
 */
public class JwtUtils {
    public static final String SUBJECT="yorkmass";
    //过期时间
    public static final long EXPIRE=1000*60*60*24*7;
    //秘钥
    public static final String APPSECRET="1218544729";

    /**
     * 根据tuser创建秘钥
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        if(user==null||user.getUsername()==""||user.getPassword()==""){
            return null;
        }
        String token=Jwts.builder().setSubject(SUBJECT)
                .claim("username",user.getUsername())
                .claim("password",user.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();
        return token;
    }

    /**
     * 解密
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try {
            final Claims claims=Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){}
        return null;
    }
}
