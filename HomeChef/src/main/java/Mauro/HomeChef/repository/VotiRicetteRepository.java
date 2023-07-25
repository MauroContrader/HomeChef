package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.VotiRicette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface VotiRicetteRepository extends JpaRepository<VotiRicette, Long> {

    boolean existsByUtente_IdAndRicetta_Id(Long id, Long id1);
    

}
