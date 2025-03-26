package de.conciso.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@RequestScope
@Component
public class AuthorizationTokenHolder {
  String token;
}
