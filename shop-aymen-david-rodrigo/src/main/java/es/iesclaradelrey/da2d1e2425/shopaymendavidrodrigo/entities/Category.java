package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false, length = 1000)
    private String description;
    private String image;
    public Category(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    @OneToMany(mappedBy = "category")
    List<Product> products = new ArrayList<>();
}