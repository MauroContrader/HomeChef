package Mauro.HomeChef.service;

import Mauro.HomeChef.repository.AnagraficaUtenteRepository;
import Mauro.HomeChef.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AnagraficaUtenteRepository anagraficaUtenteRepository;

}
