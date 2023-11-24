package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.ClassificaRicette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassificaRicetteRepository extends JpaRepository<ClassificaRicette, Long> {

    Optional<ClassificaRicette> findByRicetta_Id(Long id);

    @Query("SELECT cr FROM ClassificaRicette cr ORDER BY cr.voto DESC LIMIT :numeroElementi")
    List<ClassificaRicette> findTopRicette(@Param("numeroElementi") int numeroElementi);

}
