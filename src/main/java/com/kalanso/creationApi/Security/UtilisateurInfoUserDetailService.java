package com.kalanso.creationApi.Security;

import com.kalanso.creationApi.modele.Utilisateur;
import com.kalanso.creationApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@AllArgsConstructor
public class UtilisateurInfoUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email);
        if (user != null) {
            System.out.println("User found 3: " + user.getEmail()); // Log de vérification
            return new UtilsateurInfoDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
