package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Apprenant;

import java.util.List;

public interface ApprenantService {

    public Apprenant CreerApp(Apprenant A);

    List<Apprenant> AfficherApp();

    public Apprenant Modifier (Integer id, Apprenant A);

    String SupApp(Integer id);
}
