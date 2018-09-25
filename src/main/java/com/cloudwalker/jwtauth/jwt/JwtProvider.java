/**
 * 
 */
package com.cloudwalker.jwtauth.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.cloudwalker.jwtauth.services.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author 553243
 *
 */
@Component
public class JwtProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

  @Value("${cloudwalkers.app.jwtSecret}")
  private String jwtSecret;

  @Value("${cloudwalkers.app.jwtExpiration}")
  private int jwtExpiration;

  public String generateJwtToken(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

    return Jwts.builder().setSubject(principal.getUsername()).setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + jwtExpiration))
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUsernameFromJwrToken(String jwt) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
  }

  public boolean validateJwtToken(String jwt) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature -> Message: {} ", e);
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token -> Message: {}", e);
    } catch (ExpiredJwtException e) {
      logger.error("Expired JWT token -> Message: {}", e);
    } catch (UnsupportedJwtException e) {
      logger.error("Unsupported JWT token -> Message: {}", e);
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty -> Message: {}", e);
    }
    return false;
  }

}
