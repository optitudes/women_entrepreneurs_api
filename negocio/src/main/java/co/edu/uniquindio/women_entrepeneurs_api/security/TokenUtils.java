package co.edu.uniquindio.women_entrepeneurs_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenUtils {

    public static String getEmailFromJWTToken(String token) {
        String secretKey = "estaEsMiClaveSecretaCon512BitsDeLongitudParaHS512a;lsdkj;alksdj309708d9d9d9n";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
