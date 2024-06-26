package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Utilisateur;
import com.kalanso.creationApi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Data
public class UserServiceImplement  implements UserService {
    private final UserRepository userRepository;


    @Override
    public Utilisateur creer(Utilisateur user) {
        user.setPassword((user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<Utilisateur> lire() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Integer id, Utilisateur user) {
        return userRepository.findById(id)
                .map((u)->{
                    u.setNom(u.getNom());
                    u.setPrenom(u.getPrenom());
                    u.setPassword((u.getPassword()));
                    u.setRole(u.getRole());
                    return userRepository.save(u);
                }) .orElseThrow(()->new RuntimeException("USER NON TROUVE"));
    }

    @Override
    public String supprimer(Integer id) {
        userRepository.deleteById(id);
        return "USER SUPRIME";
    }
}
