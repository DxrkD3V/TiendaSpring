package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemCartDTO {
    private Long productId;
    private int addUnits;
}
