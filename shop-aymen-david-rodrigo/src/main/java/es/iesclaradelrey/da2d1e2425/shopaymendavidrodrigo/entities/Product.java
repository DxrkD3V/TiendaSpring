package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Entity<Long> {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String manufactur;

    @Override
    public Long getId() {
        return this.id;
    }
}
