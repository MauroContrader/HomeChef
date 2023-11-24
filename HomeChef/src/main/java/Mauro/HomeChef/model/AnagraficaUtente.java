package Mauro.HomeChef.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AnagraficaUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    private String codiceFiscale;

    private String citta;

    private String indirizzo;

    private String email;

    private String cellulare;

}
