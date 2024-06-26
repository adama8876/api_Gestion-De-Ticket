package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.BaseDeConnaissance;
import com.kalanso.creationApi.services.BaseDeConnaissanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/base-de-connaissance")
public class BaseDeConnaissanceController {

    @Autowired
    private BaseDeConnaissanceService baseDeConnaissanceService;

    // Créer une nouvelle entrée dans la base de connaissance
    @PostMapping
    public ResponseEntity<BaseDeConnaissance> createBaseDeConnaissance(@RequestBody BaseDeConnaissance baseDeConnaissance) {
        BaseDeConnaissance createdBaseDeConnaissance = baseDeConnaissanceService.createBaseDeConnaissance(baseDeConnaissance);
        return ResponseEntity.ok(createdBaseDeConnaissance);
    }

    // Lire une entrée par ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseDeConnaissance> getBaseDeConnaissanceById(@PathVariable Integer id) {
        Optional<BaseDeConnaissance> baseDeConnaissance = baseDeConnaissanceService.getBaseDeConnaissanceById(id);
        if (baseDeConnaissance.isPresent()) {
            return ResponseEntity.ok(baseDeConnaissance.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Lire toutes les entrées
    @GetMapping
    public ResponseEntity<List<BaseDeConnaissance>> getAllBaseDeConnaissances() {
        List<BaseDeConnaissance> baseDeConnaissances = baseDeConnaissanceService.getAllBaseDeConnaissances();
        return ResponseEntity.ok(baseDeConnaissances);
    }

    // Mettre à jour une entrée
    @PutMapping("/{id}")
    public ResponseEntity<BaseDeConnaissance> updateBaseDeConnaissance(@PathVariable Integer id, @RequestBody BaseDeConnaissance baseDeConnaissanceDetails) {
        try {
            BaseDeConnaissance updatedBaseDeConnaissance = baseDeConnaissanceService.updateBaseDeConnaissance(id, baseDeConnaissanceDetails);
            return ResponseEntity.ok(updatedBaseDeConnaissance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une entrée
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaseDeConnaissance(@PathVariable Integer id) {
        baseDeConnaissanceService.deleteBaseDeConnaissance(id);
        return ResponseEntity.noContent().build();
    }
}