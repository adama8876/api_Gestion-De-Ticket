package com.kalanso.creationApi.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Formateur extends Utilisateur {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private long id;
   /* private String prenom;
    private String nom;
    private String email;*/
    @OneToMany(mappedBy = "formateur") // Un formateur peut contribuer à plusieurs bases de connaissance
    private List<BaseDeConnaissance> basesDeConnaissance;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
