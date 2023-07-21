package Mauro.HomeChef.repository;

import Mauro.HomeChef.model.AttivazioneAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AttivazioneAccountRepository extends JpaRepository<AttivazioneAccount, Long> {

    long deleteByCodiceDiSicurezza(String codiceDiSicurezza);

    AttivazioneAccount findByCodiceDiSicurezza(String codiceDiSicurezza);
}
