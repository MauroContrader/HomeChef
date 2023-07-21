package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.AnagraficaUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaUtenteRepository extends JpaRepository<AnagraficaUtente,Long> {

    AnagraficaUtente findByCodiceFiscale(String codiceFiscale);

}