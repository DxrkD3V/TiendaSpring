package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Entity<Long> {
    private Long id;
    private String name;
    private String description;
    private String image;
}
