package Mauro.HomeChef.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotiRicette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int voto;

    @ManyToOne
    private User utente;

    @ManyToOne
    private Ricetta ricetta;

}
