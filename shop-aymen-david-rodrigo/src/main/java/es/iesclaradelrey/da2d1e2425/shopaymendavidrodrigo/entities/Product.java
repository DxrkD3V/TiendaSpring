package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @Column(nullable = false, length = 50)
    private String motor;
    @Column(nullable = false)
    private int hp;
    @Column(nullable = false)
    private int maxVelocity;


    public Product(String name, String imageurl, String description, Double price, String manufacture, String motor , int hp, int maxVelocity, Category category) {
        this.name = name;
        this.imageurl = imageurl;
        this.description = description;
        this.price = price;
        this.manufacture = manufacture;
        this.category = category;
        this.motor = motor;
        this.hp = hp;
        this.maxVelocity = maxVelocity;

    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<Rating> ratings = new ArrayList<>();
}
