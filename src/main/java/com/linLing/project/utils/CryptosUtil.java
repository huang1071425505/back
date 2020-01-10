package com.linLing.project.utils;


import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * 安全加密
 */
public class CryptosUtil {
    public static String sha(String source) throws NoSuchAlgorithmException {
        //创建算法
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        //更新数组
        messageDigest.update(source.getBytes());
        //哈希计算 生成32字符串
        String str = new BigInteger(messageDigest.digest()).toString(32);
        return str;
    }

    /**
     * 过期时间
     */
    private static final long EFFECTIVE_TIME=20*60*1000;
    /**
     * 秘钥
     */
    private static final String TOKEN_PASSWORD="linLingIsPig";

    /**
     *
     * @param userCode 用户
     * @return jwt 令牌
     */
    public static String jwt(String userCode){
        Date date = new Date(EFFECTIVE_TIME+System.currentTimeMillis());
        // 秘钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(TOKEN_PASSWORD);
        JwtBuilder jwt = Jwts.builder();
        jwt.setId(UUID.randomUUID().toString());
        // 用户名
        jwt.setSubject(userCode);
        // 签发者
        jwt.setIssuer("huangwenqi");
        // 签发时间
        jwt.setIssuedAt(new Date(System.currentTimeMillis()));
        // 有效时间
        jwt.setExpiration(date);
        jwt.compressWith(CompressionCodecs.DEFLATE);
        jwt.signWith(SignatureAlgorithm.HS256, secretKeyBytes);
        return jwt.compact();
    }

}
