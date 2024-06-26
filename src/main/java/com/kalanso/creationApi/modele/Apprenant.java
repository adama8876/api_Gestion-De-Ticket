package com.kalanso.creationApi.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Apprenant extends Utilisateur {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
   /* private String prenom;
    private String nom;
    private String email;*/
    @OneToMany(mappedBy = "apprenant")
    private List<Ticket> tickets;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
