package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
