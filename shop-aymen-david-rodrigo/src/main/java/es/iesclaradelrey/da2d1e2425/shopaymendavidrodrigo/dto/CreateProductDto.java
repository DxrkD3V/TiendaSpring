package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "La URL de la imagen es obligatoria")
    @Size(min = 5, max = 255, message = "La URL de la imagen debe tener entre 5 y 255 caracteres")
    private String imageurl;

    @NotNull(message = "La descripción es obligatoria")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @NotNull(message = "El fabricante es obligatorio")
    @Size(min = 2, max = 100, message = "El fabricante debe tener entre 2 y 100 caracteres")
    private String manufacture;

    @NotNull(message = "El motor es obligatorio")
    @Size(min = 2, max = 50, message = "El motor debe tener entre 2 y 50 caracteres")
    private String motor;

    @NotNull(message = "La potencia es obligatoria")
    @Min(value = 0, message = "La potencia no puede ser negativa")
    private int hp;

    @NotNull(message = "La velocidad máxima es obligatoria")
    @Min(value = 0, message = "La velocidad máxima no puede ser negativa")
    private int maxVelocity;

    @NotNull(message = "La categoría es obligatoria")
    private Category category;
}