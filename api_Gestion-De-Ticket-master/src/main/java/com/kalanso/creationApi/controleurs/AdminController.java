package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.Admin;
import com.kalanso.creationApi.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint pour récupérer tous les administrateurs
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    // Endpoint pour récupérer un administrateur par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        Optional<Admin> adminOptional = adminService.findById(id);
        return adminOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour créer un nouvel administrateur
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addAdmin")
    public Admin createAdmin(@RequestBody Admin admin) {
        //Admin createdAdmin = adminService.save(admin);
        return adminService.AjouterAdmin(admin);
    }

    // Endpoint pour mettre à jour un administrateur par son identifiant
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody Admin adminDetails) {
        Optional<Admin> updatedAdmin = adminService.updateAdmin(id, adminDetails);
        return updatedAdmin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer un administrateur par son identifiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
