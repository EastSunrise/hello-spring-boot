package cn.wsg.springboot.common;

import cn.wsg.springboot.pojo.dto.LoggedUserDTO;
import cn.wsg.springboot.pojo.enums.UserScope;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import org.springframework.lang.Nullable;

/**
 * Utility for JSON Web Token.
 *
 * @author Kingen
 */
public class JwtUtil {

    private static final String SECRET = "secret";
    private static final long SECONDS_OF_DAY = 86400;

    /**
     * Creates a token for the logged user.
     */
    public static String createToken(LoggedUserDTO user) {
        return JWT.create()
            .withSubject(String.valueOf(user.getUserId()))
            .withClaim("scope", user.getScope().name())
            .withExpiresAt(Instant.now().plusSeconds(SECONDS_OF_DAY))
            .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * Verifies the specified token and resolves the logged user from the token.
     */
    @Nullable
    public static LoggedUserDTO parseToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
        int userId = Integer.parseInt(jwt.getSubject());
        UserScope scope = UserScope.valueOf(jwt.getClaim("scope").asString());
        return new LoggedUserDTO(userId, scope);
    }
}
