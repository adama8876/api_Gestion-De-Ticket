package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Utilisateur;

import java.util.List;

public interface UserService {
    Utilisateur creer(Utilisateur user);
    List<Utilisateur> lire();
    Utilisateur modifier(Integer id, Utilisateur user);
    String supprimer(Integer id);
}
