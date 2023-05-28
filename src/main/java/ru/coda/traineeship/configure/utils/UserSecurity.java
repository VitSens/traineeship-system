package ru.coda.traineeship.configure.utils;

import lombok.AllArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.recruitment.repository.ResumeRepository;

@Component("userSecurity")
@AllArgsConstructor
public class UserSecurity {

  private final ResumeRepository resumeRepository;

  public boolean hasUserId(Authentication authentication, String userId) {
    String keycloakUserId = getUserInfo((KeycloakAuthenticationToken) authentication).getSubject();
    return keycloakUserId.equals(userId);
  }

  public boolean hasUserIdByResume(Authentication authentication, Integer resumeId) {
    Resume resume = resumeRepository.getReferenceById(resumeId);
    return resume.getUserId().equals(getUserInfo((KeycloakAuthenticationToken) authentication).getSubject());
  }

  private static AccessToken getUserInfo(KeycloakAuthenticationToken authentication) {
    return authentication.getAccount()
                                  .getKeycloakSecurityContext()
                                  .getToken();
  }
}