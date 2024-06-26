package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Admin;
import com.kalanso.creationApi.modele.BaseDeConnaissance;
import com.kalanso.creationApi.modele.Formateur;
import com.kalanso.creationApi.modele.Utilisateur;
import com.kalanso.creationApi.repository.BaseDeConnaissanceRepository;
import com.kalanso.creationApi.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseDeConnaissanceService {

    @Autowired
    private BaseDeConnaissanceRepository baseDeConnaissanceRepository;
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private TicketService ticketService;

    // Créer une nouvelle entrée dans la base de connaissance
    public BaseDeConnaissance createBaseDeConnaissance(BaseDeConnaissance baseDeConnaissance) {
        Utilisateur user=ticketService.getAuthenticatedUser();
        // ticket.setApprenant((Apprenant) user);
        baseDeConnaissance.setFormateur((Formateur) user);
       // formateur.setAdmin((Admin) user);
        return baseDeConnaissanceRepository.save(baseDeConnaissance);
    }

    // Lire une entrée par ID
    public Optional<BaseDeConnaissance> getBaseDeConnaissanceById(Integer id) {
        return baseDeConnaissanceRepository.findById(id);
    }

    // Lire toutes les entrées
    public List<BaseDeConnaissance> getAllBaseDeConnaissances() {
        return baseDeConnaissanceRepository.findAll();
    }

    // Mettre à jour une entrée
    public BaseDeConnaissance updateBaseDeConnaissance(Integer id, BaseDeConnaissance baseDeConnaissanceDetails) {
        Optional<BaseDeConnaissance> baseDeConnaissance = baseDeConnaissanceRepository.findById(id);

        if (baseDeConnaissance.isPresent()) {
            BaseDeConnaissance baseDeConnaissanceToUpdate = baseDeConnaissance.get();
            baseDeConnaissanceToUpdate.setQuestions(baseDeConnaissanceDetails.getQuestions());
            baseDeConnaissanceToUpdate.setReponse(baseDeConnaissanceDetails.getReponse());
            return baseDeConnaissanceRepository.save(baseDeConnaissanceToUpdate);
        } else {
            throw new RuntimeException("BaseDeConnaissance not found with id " + id);
        }
    }

    // Supprimer une entrée
    public void deleteBaseDeConnaissance(Integer id) {
        baseDeConnaissanceRepository.deleteById(id);
    }
}