package com.noellimx.main.configuration.security.filters;

import com.noellimx.main.service.JwtAuthService;
import com.noellimx.main.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  final JwtAuthService jwtService;

  final MyUserDetailsService userDetailsService;


  @Autowired
  public JwtAuthenticationFilter(JwtAuthService jwtService,
      MyUserDetailsService userDetailsService, PasswordEncoder pwe) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);
    boolean isValid = jwtService.isTokenValid(jwt);

    if (!isValid) {
      throw new SecurityException("JWT not valid");
    }

    String username = jwtService.extractUsername(jwt);
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities()
    );

    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(token);

    filterChain.doFilter(request, response);
  }
}
