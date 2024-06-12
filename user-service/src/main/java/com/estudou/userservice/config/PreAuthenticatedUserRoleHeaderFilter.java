package com.estudou.userservice.config;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Filter that extracts JWT tokens from the HTTP request header, validates them,
 * and sets the authentication in the security context.
 *
 * <p>
 * This filter retrieves the JWT token from the "Authorization" header, parses
 * the token to extract user roles and username, and then sets the
 * authentication in the security context.
 * </p>
 */
@Slf4j
public class PreAuthenticatedUserRoleHeaderFilter extends GenericFilterBean {

  /**
   * Filters incoming HTTP requests to extract and validate JWT tokens.
   *
   * <p>
   * This method retrieves the token from the request, parses it to extract
   * claims, sets the authentication in the security context, and proceeds with
   * the filter chain. If an exception occurs, the filter chain continues without
   * setting the authentication.
   * </p>
   *
   * <p>
   * Extract the JWT roles of the token comming in the header from the API
   * gateway. Is necessary to configure the mapper in the keycloak to put the
   * roles in the token. More details in the KEYCLOAK.md documentation
   * </p>
   *
   * @param servletRequest  the request to process
   * @param servletResponse the response associated with the request
   * @param chain           the filter chain
   * @throws IOException      if an I/O error occurs during processing
   * @throws ServletException if a servlet error occurs during processing
   */
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain chain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;

    try {
      String token = getTokenFromRequest(request);

      JWTClaimsSet jwtSet = getClaimsFromToken(token);

      String rolesString = jwtSet.getClaim("roles").toString();
      String userName = jwtSet.getClaim("preferred_username").toString();

      List<GrantedAuthority> authorities = AuthorityUtils
          .commaSeparatedStringToAuthorityList(rolesString);

      PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
          userName, null, authorities);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(servletRequest, servletResponse);
    } catch (Exception e) {
      log.info(e.toString());
      chain.doFilter(servletRequest, servletResponse);
    }
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    return null;
  }

  private JWTClaimsSet getClaimsFromToken(String token) throws ParseException, JOSEException {
    SignedJWT signedJwt = SignedJWT.parse(token);
    return signedJwt.getJWTClaimsSet();
  }
}