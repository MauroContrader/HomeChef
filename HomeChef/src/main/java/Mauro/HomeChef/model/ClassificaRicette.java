package Mauro.HomeChef.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificaRicette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroVoti;

    private Long sommaVoti;

    private Double voto;

    @ManyToOne
    private Ricetta ricetta;

}
