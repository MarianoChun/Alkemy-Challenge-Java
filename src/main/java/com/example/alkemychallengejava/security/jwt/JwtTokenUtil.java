package com.example.alkemychallengejava.security.jwt;

import io.jsonwebtoken.*;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Métodos para la creación y validación de JWT Token
 */
@Component
public class JwtTokenUtil {

    private static final Logger log = LoggerFactory.logger(JwtTokenUtil.class);
    @Value("alkemyJava")
    private String jwtSecret;

    @Value("850000000")
    private Integer jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            log.error("Signatura JWT inválida {}" + e.getMessage());
        } catch (MalformedJwtException e){
            log.error("El JWT token inválido {}" + e.getMessage());
        } catch (ExpiredJwtException e){
            log.error("El JWT token ha expirado {}" + e.getMessage());
        } catch (UnsupportedJwtException e){
            log.error("El JWT token no esta soportado {}" + e.getMessage());
        } catch (IllegalArgumentException e){
            log.error("La String 'claims' de JWT esta vacía {}" + e.getMessage());
        }

        return false;
    }
}
