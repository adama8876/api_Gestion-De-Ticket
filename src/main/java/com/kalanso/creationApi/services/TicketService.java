package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Ticket;
import com.kalanso.creationApi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Recuperer tous les tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    //Récupérer tickets par Id
    public Optional<Ticket> getTicketById(Integer id) {

        return ticketRepository.findById(id);
    }

    //Créer un nouveau ticket
    public Ticket createTicket(Ticket ticket) {
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
