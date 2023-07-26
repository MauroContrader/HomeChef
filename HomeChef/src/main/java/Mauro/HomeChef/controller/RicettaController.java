package Mauro.HomeChef.controller;

import Mauro.HomeChef.config.OpenApiConfig;
import Mauro.HomeChef.dto.Requests.PortataRequest;
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

    @PostMapping("/ricette")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    ResponseEntity<List<Ricetta>> ricette(@RequestBody PortataRequest request) {
        return ResponseEntity.ok(ricettaService.ricette(
            request.getIngredienti(),
            request.getTipoPiatto().name(),
            request.getPageSize(),
            request.getPageNumber()));
    }

    @PostMapping("/imposta-ricetta-preferita")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<String> salvaRicettaPreferita(@RequestBody Long idRicetta) {
        return ResponseEntity.ok(ricettaService.salvaRicettaPreferita(idRicetta));
    }

    @PostMapping("/imposta-ricetta-non-preferita")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<String> rimuoviRicettaPreferita(@RequestBody Long idRicetta) {
        return ResponseEntity.ok(ricettaService.rimuoviRicettaPreferita(idRicetta));
    }

    @GetMapping("/vota-ricetta")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<String> votaRicetta(@RequestParam int voto,
                                              @RequestParam Long idRicetta) {
        return ResponseEntity.ok(ricettaService.votaRicetta(voto, idRicetta));
    }

    @GetMapping("/ricette-preferite")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<List<String>> ricettePreferite() {
        return ResponseEntity.ok(ricettaService.listaRicettePreferite());
    }

    @GetMapping("/top-ricette")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<List<String>> topRicette(@RequestParam int numeroElementi) {
        return ResponseEntity.ok(ricettaService.ricettePiuVotate(numeroElementi));
    }

    @PostMapping("/ricetta-casuale-tipologia-ingredienti")
    @SecurityRequirement(name = OpenApiConfig.HC_SECURITY_SCHEME)
    public ResponseEntity<Ricetta> ricettaCasualeByTipologiaIngredienti(@RequestBody PortataRequest request) {
        return ResponseEntity.ok(ricettaService.ricettaCasuale(request.getIngredienti(), request.getTipoPiatto().name()));
    }
}
