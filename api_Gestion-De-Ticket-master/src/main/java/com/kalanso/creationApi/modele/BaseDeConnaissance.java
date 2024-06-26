package com.kalanso.creationApi.modele;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "BaseDeConnaissance")
@Data
public class BaseDeConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questions;
    private String reponse;
    @ManyToOne // Plusieurs formateurs peuvent contribuer à une seule base de connaissance
    @JoinColumn(name = "formateur_id") // Clé étrangère vers Formateur
    private Formateur formateur;


}
