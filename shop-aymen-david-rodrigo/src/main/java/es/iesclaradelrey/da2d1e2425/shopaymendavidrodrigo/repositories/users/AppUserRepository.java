package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.users;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    boolean existByEmail(String email);
}
