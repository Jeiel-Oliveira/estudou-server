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

public class PreAuthenticatedUserRoleHeaderFilter extends GenericFilterBean {

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
      System.out.println(e.toString());
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