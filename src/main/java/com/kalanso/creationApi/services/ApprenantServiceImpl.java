package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Admin;
import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.modele.Utilisateur;
import com.kalanso.creationApi.repository.ApprenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ApprenantServiceImpl implements ApprenantService{

    private final ApprenantRepository ApRep;
    private PasswordEncoder pass;
    @Autowired
    private TicketService ticketService;

    @Override
    public Apprenant CreerApp(Apprenant apprenant) {
        ticketService.getAuthenticatedUser();
        ticketService.getAuthenticatedUser();
        Utilisateur user=ticketService.getAuthenticatedUser();
         apprenant.setAdmin((Admin) user);
        String EncodePassword = pass.encode(apprenant.getPassword());
        apprenant.setPassword(EncodePassword);

        return ApRep.save(apprenant);
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
