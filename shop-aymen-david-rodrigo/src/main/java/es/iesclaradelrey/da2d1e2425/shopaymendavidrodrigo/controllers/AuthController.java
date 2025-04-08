package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.TokensDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping ("/register")
    public ResponseEntity<TokensDto> register(@RequestBody RegisterUserDto registerUserDto){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @PostMapping("/login")
    public ResponseEntity<TokensDto> login(@RequestBody LoginUserDto loginUserDto){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @PostMapping("/refresh")
    public ResponseEntity<TokensDto> refresh(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @PostMapping("/revoke")
    public ResponseEntity<TokensDto> revoke(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
