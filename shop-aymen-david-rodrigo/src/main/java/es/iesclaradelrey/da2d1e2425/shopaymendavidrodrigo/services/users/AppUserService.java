package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;

public interface AppUserService {
    AppUser register(RegisterUserDto registerUserDto);
}
