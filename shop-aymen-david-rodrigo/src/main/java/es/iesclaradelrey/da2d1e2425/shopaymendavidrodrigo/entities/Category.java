package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private UUID id;
    private String name;
    private String description;
    private String image;
}
