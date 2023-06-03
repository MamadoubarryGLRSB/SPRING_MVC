package ism.gestion_pedagogique.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
public class SalleDTO {

    private Long id;
    private int numero;
    private int nombrePlace;



    public SalleDTO(Long id, int numero, int nombrePlace) {
        this.id = id;
        this.numero = numero;
        this.nombrePlace = nombrePlace;
    }
}
