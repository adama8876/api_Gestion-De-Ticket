package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Admin;
import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.modele.Formateur;
import com.kalanso.creationApi.modele.Utilisateur;
import com.kalanso.creationApi.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FormateurService {
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private PasswordEncoder pass;
    @Autowired
    private TicketService ticketService;


    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> findById(Integer id) {
        return formateurRepository.findById(id);
    }

    public Formateur save(Formateur formateur) {
       // ticketService.getAuthenticatedUser();
        Utilisateur user=ticketService.getAuthenticatedUser();
       // ticket.setApprenant((Apprenant) user);
        formateur.setAdmin((Admin) user);
        String EncodePassword = pass.encode(formateur.getPassword());
        formateur.setPassword(EncodePassword);

        return formateurRepository.save(formateur);
    }

    public void deleteById(Integer id) {
        formateurRepository.deleteById(id);
    }
}


