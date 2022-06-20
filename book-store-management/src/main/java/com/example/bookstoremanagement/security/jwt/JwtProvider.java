package com.example.bookstoremanagement.security.jwt;
import com.example.bookstoremanagement.security.userprinciple.AccountPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "codeGym";
    private int jwtExpiration = 86400;
    public String createJwtToken(Authentication token){
        AccountPrinciple accountPrinciple = (AccountPrinciple) token.getPrincipal();
        return Jwts.builder().setSubject(accountPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000L))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    // kiem tra tinhs hop le cua token gui len cua moi request
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid jwt signature ->message",e);
        }catch (MalformedJwtException e){
            logger.error("Invalid format token -> message",e);
        }catch (ExpiredJwtException e){
            logger.error("Expired Jwt token -> message",e);

        }catch (UnsupportedJwtException e){
        logger.error("unsupported jwt token -> message",e);
        }catch (IllegalArgumentException e){
            logger.error("jwt claims string is empty->message",e);
        }
        return false;
    }

    //tra ve user name tu token
    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
