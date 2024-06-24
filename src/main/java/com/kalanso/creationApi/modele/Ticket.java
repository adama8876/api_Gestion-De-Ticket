package com.kalanso.creationApi.modele;

import com.kalanso.creationApi.Enum.Categorie;
import com.kalanso.creationApi.Enum.EtatTicket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    private String description;
    @Enumerated(EnumType.STRING)
    private EtatTicket statut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeCreation;
    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;
    @PrePersist
    protected void onCreate() {
        dateDeCreation = new Date(); // Initialise la date de création avec la date courante
    }


}

