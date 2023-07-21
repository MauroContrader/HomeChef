package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByAnagraficaUtente_CodiceFiscale(String codiceFiscale);



}
