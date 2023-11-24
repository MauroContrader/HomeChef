package Mauro.HomeChef.dto.Requests;

import Mauro.HomeChef.dto.Enum.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private Role role;

}

