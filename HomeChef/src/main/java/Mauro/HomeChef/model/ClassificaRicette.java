package Mauro.HomeChef.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
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
