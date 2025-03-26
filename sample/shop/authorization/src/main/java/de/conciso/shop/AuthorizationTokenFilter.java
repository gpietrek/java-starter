package de.conciso.shop;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationTokenFilter implements Filter {

  private final AuthorizationTokenHolder tokenHolder;

  public AuthorizationTokenFilter(AuthorizationTokenHolder tokenHolder) {
    this.tokenHolder = tokenHolder;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    tokenHolder.setToken(((HttpServletRequest) servletRequest).getHeader("Authorization"));
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
