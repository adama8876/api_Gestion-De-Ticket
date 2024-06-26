package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.services.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apprenant")
@AllArgsConstructor
public class apprenantControleur {

    private final ApprenantService apprenantService;
    @PostMapping("/a")
    public Apprenant creer(@RequestBody Apprenant apprenant){
        return apprenantService.CreerApp(apprenant);
    }
    @PutMapping("/b/{id}")
    public Apprenant Modifier(@PathVariable Integer id, @RequestBody Apprenant apprenant){
        return apprenantService.Modifier(id,apprenant);
    }

}
