package com.kalanso.creationApi.repository;

import com.kalanso.creationApi.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Integer> {
    //Optional<Utilisateur> findByEmail = ;

    //Optional<Utilisateur> findByNom(String nom); // Utilisez findByNom si le nom d'utilisateur est stocké dans 'nom'
    //@Query(value = "select * from  Utilisateur where email = ?1", nativeQuery = true)
    Utilisateur findByEmail(String email);
    //Utilisateur findByEmail(String email);
}
