package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "product_id"})})
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double rating;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 1000)
    private String comment;
    @Column(nullable = false)
    private Date date;

    public Rating(double rating, String name, String comment, Date date, Product product) {
        this.rating = rating;
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.product = product;
    }

    @ManyToOne
    Product product;
}
