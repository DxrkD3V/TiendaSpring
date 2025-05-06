package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;
}
