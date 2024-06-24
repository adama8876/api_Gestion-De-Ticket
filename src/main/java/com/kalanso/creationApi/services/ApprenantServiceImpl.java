package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.repository.ApprenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ApprenantServiceImpl implements ApprenantService{

    private final ApprenantRepository ApRep;
    @Override
    public Apprenant CreerApp(Apprenant A) {
        return ApRep.save(A);
    }

    @Override
    public List<Apprenant> AfficherApp() {
        return ApRep.findAll();
    }

    @Override
    public Apprenant Modifier(Integer id, Apprenant A) {

        return ApRep.findById(id)
                .map(App ->{
                    App.setPrenom(A.getPrenom());
                    App.setNom(A.getNom());
                    App.setEmail(A.getEmail());
                    return ApRep.save(App);
                }).orElseThrow(() -> new RuntimeException("Apprenant non trouvé"));
    }

    @Override
    public String SupApp(Integer id) {
        ApRep.deleteById(id);
        return "Apprenant supprimer !";
    }
}
