package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.UserNameAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.users.AppUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(RegisterUserDto registerUserDto) {
        if (appUserRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new UserNameAlreadyExistsException(registerUserDto.getEmail());
        }
        AppUser appUser = AppUser.builder()
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .firstName(registerUserDto.getFirstname())
                .lastName(registerUserDto.getLastname())
                .build();

        return appUserRepository.save(appUser);
    }

}
