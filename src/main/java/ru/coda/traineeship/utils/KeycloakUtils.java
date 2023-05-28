package ru.coda.traineeship.utils;

import javax.servlet.http.HttpServletRequest;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

public class KeycloakUtils {

  public static AccessToken getKeycloakUser(HttpServletRequest request) {
    KeycloakAuthenticationToken user = (KeycloakAuthenticationToken) request.getUserPrincipal();
    return user.getAccount()
               .getKeycloakSecurityContext()
               .getToken();
  }
}
