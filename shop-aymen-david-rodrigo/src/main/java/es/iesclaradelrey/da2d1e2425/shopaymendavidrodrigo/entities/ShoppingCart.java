package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int units;
    @Column(columnDefinition = "timestamp default current_timestamp()", nullable = false)
    private LocalDateTime addAt;
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp() ",nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    public ShoppingCart(int units ,Product product) {
        this.units = units;
        this.product = product;
        this.updateAt = LocalDateTime.now();
        this.addAt = LocalDateTime.now();
    }

}
