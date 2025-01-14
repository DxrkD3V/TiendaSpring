package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false, length = 255)
    private String imageurl;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(length = 10)
    private Double price;
    @Column(nullable = false, length = 50)
    private String manufacture;

    public Product(String name, String imageurl, String description, Double price, String manufacture, Category category) {
        this.name = name;
        this.imageurl = imageurl;
        this.description = description;
        this.price = price;
        this.manufacture = manufacture;
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    List<Rating> ratings;
}
