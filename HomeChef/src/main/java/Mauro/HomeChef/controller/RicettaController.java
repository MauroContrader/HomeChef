package Mauro.HomeChef.controller;

import Mauro.HomeChef.config.OpenApiConfig;
import Mauro.HomeChef.model.Ricetta;
import Mauro.HomeChef.service.RicettaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
