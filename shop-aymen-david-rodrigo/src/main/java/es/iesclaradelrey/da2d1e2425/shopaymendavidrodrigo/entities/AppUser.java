package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @Size(max = 200)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 200)
    private String password;

}