package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokensDto {
    private String acessToken;
    private String refreshToken;
}
