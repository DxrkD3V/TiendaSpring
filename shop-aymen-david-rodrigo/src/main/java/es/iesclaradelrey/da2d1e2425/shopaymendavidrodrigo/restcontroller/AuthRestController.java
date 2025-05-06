package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.TokensDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.users.AppUserRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt.JwtService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AppUserService appUserService;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    public AuthRestController(AppUserService appUserService, JwtService jwtService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }


    @PostMapping ("/register")
    public ResponseEntity<TokensDto> register(@RequestBody RegisterUserDto registerUserDto){
        AppUser appUser = appUserService.register(registerUserDto);
        String accessToken = jwtService.generatedAccessToken(appUser);
        String refreshToken = jwtService.generatedRefreshToken(appUser);

        return ResponseEntity.ok(TokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<TokensDto> login(@RequestBody LoginUserDto loginUserDto) {
        if (loginUserDto.getEmail() == null || loginUserDto.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        AppUser user = appUserService.login(loginUserDto);
        String accessToken = jwtService.generatedAccessToken(user);
        String refreshToken = jwtService.generatedRefreshToken(user);

        return ResponseEntity.ok(TokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
    }
}
