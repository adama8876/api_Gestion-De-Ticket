package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Admin;
import com.kalanso.creationApi.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;
    private PasswordEncoder pass;



    // Méthode pour trouver tous les administrateurs
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // Méthode pour trouver un administrateur par son identifiant
    public Optional<Admin> findById(Integer id) {
        return adminRepository.findById(id);
    }

    // Méthode pour enregistrer un nouvel administrateur ou mettre à jour un existant




    public Admin AjouterAdmin(Admin admin) {
        //admin.setMdp(pass.encode(admin.getMdp()));
        String EncodePassword = pass.encode(admin.getPassword());
        admin.setPassword(EncodePassword);
        return adminRepository.save(admin);
    }


    // Méthode pour supprimer un administrateur par son identifiant
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

    // Méthode pour mettre à jour les informations d'un administrateur existant par son identifiant
    public Optional<Admin> updateAdmin(Integer id, Admin adminDetails) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setPrenom(adminDetails.getPrenom());
            admin.setNom(adminDetails.getNom());
            admin.setEmail(adminDetails.getEmail());
            return Optional.of(adminRepository.save(admin));
        } else {
            return Optional.empty();
        }
    }
}