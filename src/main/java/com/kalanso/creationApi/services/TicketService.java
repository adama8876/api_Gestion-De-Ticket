package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.*;
import com.kalanso.creationApi.repository.TicketRepository;
import com.kalanso.creationApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {
    private MailService mailService;
    private TicketRepository ticketRepository;
    private final UserRepository userRepo;
    // Recuperer tous les tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    //Récupérer tickets par Id
    public Optional<Ticket> getTicketById(Integer id) {

        return ticketRepository.findById(id);
    }

    //Créer un nouveau ticket



    public Utilisateur getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            System.out.println(email + " mon mail");
            return userRepo.findByEmail(email);
        }
        return null;
    }

    // Exemple de méthode utilisant les informations de l'utilisateur authentifié

    public Ticket createTicket(Ticket ticket) {
        Utilisateur user = getAuthenticatedUser();
        mailService.sendMail(user.getEmail(), " resolu !");
        //ticket.getApprenant().setRole(Role.APPRENANT);
        ticket.setApprenant((Apprenant) user);
        ticket.setDateDeCreation(new Date());
        return ticketRepository.save(ticket);
    }

    // Modifier un ticket par son Id
    public Ticket updateTicket(Integer id, Ticket ticketDetails) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setCategorie(ticketDetails.getCategorie());
            ticket.setDescription(ticketDetails.getDescription());
            ticket.setApprenant(ticketDetails.getApprenant());
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("Ticket not found with id " + id);
        }
    }

    // Supprimer un ticket par son Id
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}
