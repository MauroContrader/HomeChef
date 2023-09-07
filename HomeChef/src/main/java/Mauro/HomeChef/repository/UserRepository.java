package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
