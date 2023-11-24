package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RicettaRepository extends JpaRepository<Ricetta, Long> {

    @Query("SELECT r FROM Ricetta r WHERE r.ingredienti LIKE '%'||:ingrediente||'%'")
    List<Ricetta> findRicettaByIngrediente(@Param("ingrediente") String ingrediente1);

    @Query("SELECT r FROM Ricetta r WHERE " +
        "r.tipoPiatto LIKE '%'||:tipoPiatto||'%' " +
        "AND r.ingredienti LIKE '%'||:ingrediente1||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente2||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente3||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente4||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente5||'%'"
    )
    List<Ricetta> findRicettaBy5IngredientiAndTipoPiatto(@Param("tipoPiatto") String tipoPiatto,
                                                         @Param("ingrediente1") String ingrediente1,
                                                         @Param("ingrediente2") String ingrediente2,
                                                         @Param("ingrediente3") String ingrediente3,
                                                         @Param("ingrediente4") String ingrediente4,
                                                         @Param("ingrediente5") String ingrediente5);

    @Query("SELECT r FROM Ricetta r WHERE r.tipoPiatto = :tipoPiatto ORDER BY RAND() LIMIT 1")
    Ricetta findRicettaRandomByTipologia(@Param("tipoPiatto") String tipoPiatto);

    @Query("SELECT r FROM Ricetta r WHERE r.tipoPiatto = :tipoPiatto " +
        "AND r.ingredienti LIKE '%'||:ingrediente1||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente2||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente3||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente4||'%'" +
        "AND r.ingredienti LIKE '%'||:ingrediente5||'%'" +
        "ORDER BY RAND() LIMIT 1")
    Ricetta findRicettaRandomByTipologiaEdIngredienti(@Param("tipoPiatto") String tipoPiatto,
                                                      @Param("ingrediente1") String ingrediente1,
                                                      @Param("ingrediente2") String ingrediente2,
                                                      @Param("ingrediente3") String ingrediente3,
                                                      @Param("ingrediente4") String ingrediente4,
                                                      @Param("ingrediente5") String ingrediente5);
}

