package cn.wsg.springboot.common;

import cn.wsg.springboot.pojo.LoginUser;
import cn.wsg.springboot.pojo.SysUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class JwtUtil {

    private static final long SECONDS_OF_WEEK = 7 * 24 * 3600;

    public static String createToken(SysUser user, boolean admin) {
        return JWT.create()
                .withClaim("userId", user.getUserId())
                .withClaim("admin", admin)
                .withExpiresAt(Instant.now().plusSeconds(SECONDS_OF_WEEK))
                .sign(Algorithm.HMAC256(user.getPassword()));

    }

    /**
     * todo verify sign
     */
    public static LoginUser parseToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        Integer userId = decode.getClaim("userId").asInt();
        Boolean admin = decode.getClaim("admin").asBoolean();
        return new LoginUser(userId, admin);
    }
}
