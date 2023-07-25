package Mauro.HomeChef.controller;

import Mauro.HomeChef.config.OpenApiConfig;
import Mauro.HomeChef.dto.Enum.Role;
import Mauro.HomeChef.dto.Requests.AnagraficaUtenteRequest;
import Mauro.HomeChef.model.AnagraficaUtente;
import Mauro.HomeChef.model.User;
import Mauro.HomeChef.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/anagraficaUtente")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<AnagraficaUtente> creaAnagraficaUtente(AnagraficaUtenteRequest anagraficaUtenteRequest) {
        return ResponseEntity.ok(userService.creaAnagraficaUtente(anagraficaUtenteRequest));
    }

    @GetMapping("/users")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<List<User>> getAll(@RequestParam(defaultValue = "20") int pageSize,
                                             @RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "USER") Role role) {
        return ResponseEntity.ok(userService.getAll(pageSize, pageNumber, role));
    }

    @GetMapping("/anagraficaUtente-loggato")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<AnagraficaUtente> getAnagraficaUtente() {
        return ResponseEntity.ok(userService.getAnagraficaUtente());
    }

    @PatchMapping(value = "/anagraficaUtente",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<AnagraficaUtente> patchAnagraficaUtente(@RequestBody HashMap<String, Object> request) {
        return ResponseEntity.ok(userService.patchAnagraficaUtenteLoggato(request));
    }

    @GetMapping("/anagraficaUtente")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<AnagraficaUtente> getAnagraficaUtenteById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getAnagraficaUtenteById(id));
    }

}
