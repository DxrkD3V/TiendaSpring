package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;

public interface JwtService{
    String generatedAccessToken(AppUser user);

    String generatedRefreshToken(AppUser user);

    void validateAccessToken(String token);

    void validateRefreshToken(String token);

    String extractUsername(String token);
}
