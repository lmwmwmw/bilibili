package com.lmw.common.utils;

import com.lmw.common.exception.BilibiliException;
import com.lmw.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static SecretKey secretKey = Keys.hmacShaKeyFor("bHHSKD4Mky2Ud85MX8U2Iapq3EDBt9r".getBytes());

    public static String createToken(Long userId, String userName) {
        String jwt = Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + 36000000)).
                setSubject("LOGIN_USER").
                claim("userId", userId).
                claim("userName", userName).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
        return jwt;
    }

    public static Claims parseToken(String token) {
        if (token == null) {
            throw new BilibiliException(ResultCodeEnum.LOGIN_AUTH);
        }

        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            Claims jwsBody = claimsJws.getBody();
            return jwsBody;
        }catch(ExpiredJwtException e){
            throw new BilibiliException(ResultCodeEnum.TOKEN_EXPIR);
        }catch (JwtException e){
            throw new BilibiliException(ResultCodeEnum.TOKEN_INVAILID);
        }

    }


}
