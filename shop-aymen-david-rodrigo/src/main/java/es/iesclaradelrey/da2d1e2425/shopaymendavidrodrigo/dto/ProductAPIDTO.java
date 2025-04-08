package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAPIDTO {
        private Long id;
        private String name;
        private String imageurl;
        private String description;
        private Double price;
        private int stock;
        private String manufacture;
        private String motor;
        private int hp;
        private int maxVelocity;
        private Long categoryId;
}
