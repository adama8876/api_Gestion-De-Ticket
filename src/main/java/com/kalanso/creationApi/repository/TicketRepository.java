package com.kalanso.creationApi.repository;

import com.kalanso.creationApi.modele.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
