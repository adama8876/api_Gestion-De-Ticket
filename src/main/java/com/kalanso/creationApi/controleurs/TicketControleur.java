package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.modele.Ticket;
import com.kalanso.creationApi.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ticket")
@AllArgsConstructor
public class TicketControleur {
    private final TicketService ticketService;

    @PostMapping("/creer")
    public Ticket creer(@RequestBody Ticket T){
        return ticketService.createTicket(T);
    }

    @PutMapping("/modifier/{id}")
    public Ticket Modifier(@PathVariable Integer id, @RequestBody Ticket T){ return ticketService.updateTicket(id,T);
    }

}
