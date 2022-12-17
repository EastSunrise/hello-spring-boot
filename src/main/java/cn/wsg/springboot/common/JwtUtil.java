package cn.wsg.springboot.common;

import cn.wsg.springboot.pojo.LoggedUser;
import cn.wsg.springboot.pojo.UserScope;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import org.springframework.lang.Nullable;

public class JwtUtil {

    private static final String SECRET = "secret";
    private static final long SECONDS_OF_DAY = 86400;

    public static String createToken(LoggedUser user) {
        return JWT.create()
            .withSubject(String.valueOf(user.getUserId()))
            .withClaim("scope", user.getScope().name())
            .withExpiresAt(Instant.now().plusSeconds(SECONDS_OF_DAY))
            .sign(Algorithm.HMAC256(SECRET));
    }

    @Nullable
    public static LoggedUser parseToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
        int userId = Integer.parseInt(jwt.getSubject());
        UserScope scope = UserScope.valueOf(jwt.getClaim("scope").asString());
        return new LoggedUser(userId, scope);
    }
}
