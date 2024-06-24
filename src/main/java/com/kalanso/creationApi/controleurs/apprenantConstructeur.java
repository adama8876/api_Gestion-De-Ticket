package com.kalanso.creationApi.controleurs;

import com.kalanso.creationApi.modele.Apprenant;
import com.kalanso.creationApi.services.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apprenant")
@AllArgsConstructor
public class apprenantConstructeur {

    private final ApprenantService AS;
    @PostMapping("/a")
    public Apprenant creer(@RequestBody Apprenant A){
        return AS.CreerApp(A);
    }
    @PutMapping("/b/{id}")
    public Apprenant Modifier(@PathVariable Integer id, @RequestBody Apprenant A){
        return AS.Modifier(id,A);
    }

}
