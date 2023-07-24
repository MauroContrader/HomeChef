package Mauro.HomeChef.controller;

import Mauro.HomeChef.config.OpenApiConfig;
import Mauro.HomeChef.model.Ricetta;
import Mauro.HomeChef.service.RicettaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ricetta")
public class RicettaController {

    @Autowired
    RicettaService ricettaService;

//    @GetMapping("/importa-ricette")
//    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
//    public ResponseEntity<Void> importaRicette() {
//        ricettaService.importaRicette("/home/maurocontrader/Scrivania/ricette.txt");
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/ricette")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    ResponseEntity<List<Ricetta>> ricette(@RequestParam String tipoPiatto,
                                          @RequestParam(required = false) List<String> ingredienti,
                                          @RequestParam(defaultValue = "20") long pageSize,
                                          @RequestParam(defaultValue = "0") long pageNumber) {
        return ResponseEntity.ok(ricettaService.ricette(ingredienti, tipoPiatto, pageSize, pageNumber));
    }

}
