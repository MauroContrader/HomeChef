package Mauro.HomeChef.service;

import Mauro.HomeChef.dto.Enum.TipoPiatto;
import Mauro.HomeChef.model.ClassificaRicette;
import Mauro.HomeChef.model.Ricetta;
import Mauro.HomeChef.model.User;
import Mauro.HomeChef.model.VotiRicette;
import Mauro.HomeChef.repository.ClassificaRicetteRepository;
import Mauro.HomeChef.repository.RicettaRepository;
import Mauro.HomeChef.repository.UserRepository;
import Mauro.HomeChef.repository.VotiRicetteRepository;
import Mauro.HomeChef.security.HCSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class RicettaService {

    @Autowired
    RicettaRepository ricettaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VotiRicetteRepository votiRicetteRepository;

    @Autowired
    ClassificaRicetteRepository classificaRicetteRepository;

    public Ricetta ricettaCasuale(List<String> ingredienti, TipoPiatto tipoPiatto) {
        if (Objects.nonNull(ingredienti)) {
            switch (ingredienti.size()) {
                case 1:
                    return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), ingredienti.get(0), "", "", "", "");
                case 2:
                    return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), ingredienti.get(0), ingredienti.get(1), "", "", "");
                case 3:
                    return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), "", "");
                case 4:
                    return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), ingredienti.get(3), "");
                case 5:
                    return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), ingredienti.get(3), ingredienti.get(4));
            }
        }
        return ricettaRepository.findRicettaRandomByTipologiaEdIngredienti(tipoPiatto.name(), "", "", "", "", "");
    }

    public List<Ricetta> ricette(List<String> ingredienti, String tipoPiatto, long pageSize, long pageNumber) {
        if (ingredienti.size() < 6) {
            switch (ingredienti.size()) {
                case 0:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, "", "", "", "", "")
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
                case 1:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, ingredienti.get(0), "", "", "", "")
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
                case 2:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, ingredienti.get(0), ingredienti.get(1), "", "", "")
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
                case 3:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), "", "")
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
                case 4:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), ingredienti.get(3), "")
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
                case 5:
                    return ricettaRepository.findRicettaBy5IngredientiAndTipoPiatto(tipoPiatto, ingredienti.get(0), ingredienti.get(1), ingredienti.get(2), ingredienti.get(3), ingredienti.get(4))
                        .stream().skip(pageNumber * pageSize).limit(pageSize).toList();
            }
        }
        throw new RuntimeException("Il limite di ingredienti è 5.");
    }

    public String salvaRicettaPreferita(Long idRicetta) {
        Ricetta ricetta = ricettaRepository.findById(idRicetta)
            .orElseThrow(() -> new RuntimeException("Ricetta non trovata."));
        User user = userRepository.findById(HCSecurityContext.getPrincipal().getId())
            .orElseThrow(() -> new RuntimeException("User non trovato"));
        if (!user.getRicettePreferite().contains(ricetta)) {
            user.getRicettePreferite().add(ricetta);
            userRepository.save(user);
            return ricetta.getNome() + " salvata nei preferiti!";
        } else
            throw new RuntimeException("Ricetta già preferita.");

    }

    public String rimuoviRicettaPreferita(Long idRicetta) {
        Ricetta ricetta = ricettaRepository.findById(idRicetta)
            .orElseThrow(() -> new RuntimeException("Ricetta non trovata."));
        User user = userRepository.findById(HCSecurityContext.getPrincipal().getId())
            .orElseThrow(() -> new RuntimeException("User non trovato"));
        user.getRicettePreferite().remove(ricetta);
        userRepository.save(user);
        return ricetta.getNome() + " rimossa dai preferiti";
    }

    public String votaRicetta(int voto, Long idRicetta) {
        User user = userRepository.findById(HCSecurityContext.getPrincipal().getId())
            .orElseThrow(() -> new RuntimeException("Utente non trovato."));
        Ricetta ricetta = ricettaRepository.findById(idRicetta)
            .orElseThrow(() -> new RuntimeException("Ricetta non trovata."));
        if (!votiRicetteRepository.existsByUtente_IdAndRicetta_Id(user.getId(), ricetta.getId())) {
            votiRicetteRepository.save(VotiRicette.builder()
                .voto(voto)
                .utente(user)
                .ricetta(ricetta)
                .build());
            ClassificaRicette classificaRicette = classificaRicetteRepository.findByRicetta_Id(idRicetta)
                .orElse(new ClassificaRicette());
            if (Objects.isNull(classificaRicette.getVoto())) {
                classificaRicette.setNumeroVoti(1L);
                classificaRicette.setSommaVoti((long) voto);
                classificaRicette.setVoto((double) voto);
                classificaRicette.setRicetta(ricetta);
                classificaRicetteRepository.save(classificaRicette);
            } else {
                classificaRicette.setNumeroVoti(classificaRicette.getNumeroVoti() + 1);
                classificaRicette.setSommaVoti(classificaRicette.getSommaVoti() + (long) voto);
                classificaRicette.setVoto((double) (classificaRicette.getSommaVoti() / classificaRicette.getNumeroVoti()));
                classificaRicetteRepository.save(classificaRicette);
            }
            return "Voto inserito con successo!";
        } else
            throw new RuntimeException("Voto già inserito per questa ricetta.");
    }

    public List<String> listaRicettePreferite() {
        List<Ricetta> ricettePreferite = userRepository.findById(HCSecurityContext.getPrincipal().getId()).get().getRicettePreferite();
        List<String> nomiRicettePreferite = new ArrayList<>();
        ricettePreferite.forEach(ricetta ->
            nomiRicettePreferite.add(ricetta.getNome())
        );
        return nomiRicettePreferite;
    }

    public List<String> ricettePiuVotate(int numeroElementi) {
        List<ClassificaRicette> cRicette = classificaRicetteRepository.findTopRicette(numeroElementi);
        List<String> nomiRicette = new ArrayList<>();
        cRicette.forEach(cRicetta -> nomiRicette.add(cRicetta.getRicetta().getNome()));
        return nomiRicette;
    }

    public List<String> ingredientiPrincipaliRicettePreferite() {
        User user = userRepository.findById(HCSecurityContext.getPrincipal().getId())
            .orElseThrow(() -> new RuntimeException("Utente loggato non trovato"));
        List<Ricetta> ricettePreferite = user.getRicettePreferite();
        return ricettePreferite.stream().map(Ricetta::getIngPrincipale).toList();
    }

    public List<String> ingredientiRicettePreferite() {
        User user = userRepository.findById(HCSecurityContext.getPrincipal().getId())
            .orElseThrow(() -> new RuntimeException("Utente loggato non trovato"));
        List<Ricetta> ricettePreferite = user.getRicettePreferite();
        List<String> ingredienti = new ArrayList<>();
        List<String> risultato = new ArrayList<>();
        ricettePreferite.forEach(ricetta ->
            ingredienti.addAll(Stream.of(ricetta.getIngredienti().split("\n")).toList()));
        if (!ingredienti.isEmpty()) {
            ingredienti.forEach(ingrediente -> {
                int indice = ingrediente.indexOf("====");
                String sottostringa = ingrediente.substring(indice +5).trim();
                risultato.add(sottostringa);
            });
        }
        return risultato.stream().distinct().sorted().toList();
    }

//    public void importaRicette(String path) {
//        List<Ricetta> ricette = new ArrayList<>();
//
//        Ricetta ricettaCorrente = null;
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line;
//            ricettaCorrente = null;
//
//            while ((line = br.readLine()) != null) {
//                if (line.startsWith(":Ricette")) {
//                    if (ricettaCorrente != null) {
//                        ricette.add(ricettaCorrente);
//                    }
//                    ricettaCorrente = new Ricetta();
//                } else if (line.startsWith("-Nome")) {
//                    line = br.readLine();
//                    ricettaCorrente.setNome(line);
//                } else if (line.startsWith("-Tipo_Piatto")) {
//                    line = br.readLine();
//                    ricettaCorrente.setTipoPiatto(line);
//                } else if (line.startsWith("-Ing_Principale")) {
//                    line = br.readLine();
//                    ricettaCorrente.setIngPrincipale(line);
//                } else if (line.startsWith("-Persone")) {
//                    line = br.readLine();
//                    ricettaCorrente.setPersone(Integer.parseInt(line));
//                } else if (line.startsWith("-Note")) {
//                    line = br.readLine();
//                    ricettaCorrente.setNote(line);
//                } else if (line.startsWith("-Ingredienti")) {
//                    line = br.readLine();
//                    StringBuilder ingredienti = new StringBuilder(line);
//                    line = br.readLine();
//                    while (!line.startsWith("-")) {
//                        ingredienti.append("\n" + line);
//                        line = br.readLine();
//                    }
//                    ricettaCorrente.setIngredienti(ingredienti.toString());
//                    if (line.startsWith("-Preparazione")) {
//                        line = br.readLine();
//                        ricettaCorrente.setPreparazione(line);
//                    }
//                }
//            }
//            if (ricettaCorrente != null) {
//                ricette.add(ricettaCorrente);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ricettaRepository.saveAll(ricette);
//    }

}
