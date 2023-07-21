package Mauro.HomeChef.security;

import Mauro.HomeChef.config.JwtService;
import Mauro.HomeChef.dto.Enum.Role;
import Mauro.HomeChef.model.AnagraficaUtente;
import Mauro.HomeChef.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityPrincipal implements Serializable {

    @Autowired
    JwtService jwtService;

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public String getUsername() {
        return user.getUsername();
    }

    public Role getRole() {
        return user.getRole();
    }

    public Long getId() {
        return user.getId();
    }

    public AnagraficaUtente getAnagraficaUtente() {
        return user.getAnagraficaUtente();
    }

}
