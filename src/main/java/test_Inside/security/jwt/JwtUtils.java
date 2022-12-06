package test_Inside.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private int expirationMs;

    public String generateJwtToken(String name){
        return Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(
                        new Date().getTime() + expirationMs))
                .signWith(getSigningKey()).compact();
    }

    private Key getSigningKey(){
        return new SecretKeySpec(
                DatatypeConverter.parseBase64Binary(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public boolean validateJwtToken(String jwtToken){
        Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build().parseClaimsJws(jwtToken);
        return true;
    }

    public String getNameFromJwtToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build().parseClaimsJws(jwtToken).getBody().getSubject();
    }
}
