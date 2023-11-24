package Mauro.HomeChef.model;

import Mauro.HomeChef.dto.Enum.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.awt.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String content;

    private String sender;

    private MessageType type;

}
