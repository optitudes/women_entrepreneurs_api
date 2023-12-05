package co.edu.uniquindio.women_entrepeneurs_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class TokenUtils {
    private static String  ENCRYPT_KEY="undcvnpadjnvaljk";

    public static String getEmailFromJWTToken(String token) {
        String secretKey = "estaEsMiClaveSecretaCon512BitsDeLongitudParaHS512a;lsdkj;alksdj309708d9d9d9n";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static String encriptAES(String toEncrypt)throws Exception{
        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());

        return Base64.getUrlEncoder().encodeToString(encrypted);    }

    public static String decryptAES(String encrypted) throws Exception {
        byte[] encryptedBytes=Base64.getUrlDecoder().decode( encrypted.replace("\n", "") );

        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }

    public static String getEmailFromAuthorization(String authorization) {
        String jwtToken = authorization.substring(7); // eliminamos el prefijo "Bearer "
        return getEmailFromJWTToken(jwtToken);
    }
}
