package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.Formateur;
import com.kalanso.creationApi.services.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    @Autowired
    private FormateurService formateurService;

    @GetMapping("/afficher")
    public List<Formateur> getAllFormateurs() {
        return formateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formateur> getFormateurById(@PathVariable Integer id) {
        Optional<Formateur> formateur = formateurService.findById(id);
        if (formateur.isPresent()) {
            return ResponseEntity.ok(formateur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("creer")
    public Formateur createFormateur(@RequestBody Formateur formateur) {
        return formateurService.save(formateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formateur> updateFormateur(@PathVariable Integer id, @RequestBody Formateur formateurDetails) {
        Optional<Formateur> formateur = formateurService.findById(id);
        if (formateur.isPresent()) {
            Formateur formateurToUpdate = formateur.get();
            formateurToUpdate.setPrenom(formateurDetails.getPrenom());
            formateurToUpdate.setNom(formateurDetails.getNom());
            formateurToUpdate.setEmail(formateurDetails.getEmail());
            Formateur updatedFormateur = formateurService.save(formateurToUpdate);
            return ResponseEntity.ok(updatedFormateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateur(@PathVariable Integer id) {
        formateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}