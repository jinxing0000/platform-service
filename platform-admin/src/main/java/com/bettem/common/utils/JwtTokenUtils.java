package com.bettem.common.utils;


import com.bettem.common.exception.RRException;
import io.jsonwebtoken.*;
import org.apache.commons.collections.map.HashedMap;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/21 14:11 <br>
 * @Author: 颜金星
 */
public class JwtTokenUtils {
    // 生成jwt.jks 文件命令   keytool -genkey -alias jwt -keyalg  RSA -keysize 1024 -validity 365 -keystore jwt.jks
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;
    private static final String JWT_PASSWORD="bettem";

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, JWT_PASSWORD.toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", JWT_PASSWORD.toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Param [data, expirationSeconds]
     * @Return: java.lang.String
     * @Decription:  生成token
     * @CreateDate: Created in 2019/3/21 14:39
     * @Author: 颜金星
     */
    public static String generateToken(Map<String,Object> data, int second) {
        String userId=(String)data.get("userId");
        return Jwts.builder()
                .setClaims(data)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + second * 1000 ))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
    /**
     * @Param [token]
     * @Return: java.lang.String
     * @Decription:  从token中读取userId
     * @CreateDate: Created in 2019/3/21 14:49
     * @Author: 颜金星
     */
    public static String getUserIdByToken(String token) {
        Map<String,Object> data=getUserInfoByToken(token);
        return (String)data.get("userId");
    }
    /**
     * @Param [token]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Decription:  从token中获取user数据
     * @CreateDate: Created in 2019/3/21 15:09
     * @Author: 颜金星
     */
    public static Map<String,Object> getUserInfoByToken(String token) {
        Map<String,Object> data = new HashMap<>();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            data=claims;
        }
        catch (ExpiredJwtException ej){
            ej.printStackTrace();
            throw new RRException(ErrorCodeConstant.SESSION_FAILURE,"会话失效，请重新登陆！！"+"-"+ErrorCodeConstant.SESSION_FAILURE);
        }
        catch (MalformedJwtException me){
            me.printStackTrace();
            throw new RRException(ErrorCodeConstant.TOKEN_INVALID,"Authorization Token 无效！！"+"-"+ErrorCodeConstant.TOKEN_INVALID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @Param [token]
     * @Return: java.lang.String
     * @Decription: 获取验证码
     * @CreateDate: Created in 2019/3/26 16:14
     * @Author: 颜金星
     */
    public static String getVerifyCodeByToken(String token) {
        Map<String,Object> data = new HashedMap();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            data=claims;
        }
        catch (ExpiredJwtException ej){
            ej.printStackTrace();
            throw new RRException(ErrorCodeConstant.CAPTCHA_INVALID,"验证码已失效！！");
        }
        catch (MalformedJwtException me){
            me.printStackTrace();
            throw new RRException(ErrorCodeConstant.TOKEN_INVALID,"verifyCodeToken Token 无效！！"+"-"+ErrorCodeConstant.TOKEN_INVALID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (String)data.get("verifyCode");
    }

    public static void main(String[] args) {
        Map<String,Object> data=new HashedMap();
        data.put("userId","1");
        data.put("userName","admin");
        String token=JwtTokenUtils.generateToken(data,1);
        System.out.println(token );
//        System.out.println(JwtTokenUtils.getUserIdByToken("eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE1NTMxNTU3NTcsInVzZXJOYW1lIjoiYWRtaW4iLCJ1c2VySWQiOiIxIiwic3ViIjoiMSIsImlhdCI6MTU1MzE1MjE1N30.R7k3JgfVbt-18Rd2nvvO5BgsRg7IcY5X1JUN5sLn5Z02GbGpNaLvkFTB38-JdsmaDj70GN40yXigHGFy-RvsywO-IkxYbdhRQiakLQ_PqIoEHrpD-dEcb7uaklciCzdwgI8UqdZR6emF9JV8vIbMmUdSBUKrFgXaFdQAHe8BpAc"));
        System.out.println(JwtTokenUtils.getUserInfoByToken("1111111"));
    }
}
