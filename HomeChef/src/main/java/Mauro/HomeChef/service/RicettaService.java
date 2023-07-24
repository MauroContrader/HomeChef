package Mauro.HomeChef.service;

import Mauro.HomeChef.model.Ricetta;
import Mauro.HomeChef.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RicettaService {

    @Autowired
    RicettaRepository ricettaRepository;

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
