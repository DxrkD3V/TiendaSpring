package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.App;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.users.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final App app;

    public AppUserDetailsService(AppUserRepository appUserRepository, App app) {
        this.appUserRepository = appUserRepository;
        this.app = app;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar el usuario con el servicio / repositorio de usuarios de la aplicacion
        //usando como username lo que se haya establecido. En este caso se busca por email

        AppUser appUser = appUserRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return User.builder()
                .username(username)
                .password(appUser.getPassword())
                //.roles("USER")
                .build();
    }
}
