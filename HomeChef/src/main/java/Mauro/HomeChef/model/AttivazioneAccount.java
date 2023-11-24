package Mauro.HomeChef.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AttivazioneAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codiceDiSicurezza;

    private LocalDateTime dataIscrizione;

    @OneToOne
    private User user;

}
