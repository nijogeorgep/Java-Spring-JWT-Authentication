/**
 * 
 */
package com.cloudwalker.jwtauth.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cloudwalker.jwtauth.services.UserDetailsServiceImpl;

/**
 * @author 553243
 *
 */
public class JwtAuthTokenFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

  @Autowired
  private JwtProvider jwtProvider;

  @Autowired
  private UserDetailsServiceImpl userDetailService;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwt(request);
      if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
        String username = jwtProvider.getUsernameFromJwrToken(jwt);

        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    } catch (Exception e) {
      logger.error("Can NOT set user authentication -> Message: {}", e);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwt(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.replace("Bearer ", "");
    }
    return null;
  }
}
