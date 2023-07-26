package Mauro.HomeChef.dto.Requests;

import Mauro.HomeChef.dto.Enum.TipoPiatto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortataRequest {

    @Schema(defaultValue = "")
    private List<String> ingredienti;

    @Enumerated(EnumType.STRING)
    private TipoPiatto tipoPiatto;

    long pageSize;

    long pageNumber;

}
