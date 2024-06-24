package com.kalanso.creationApi.services;

import com.kalanso.creationApi.modele.Formateur;
import com.kalanso.creationApi.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FormateurService {
    @Autowired
    private FormateurRepository formateurRepository;

    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> findById(Integer id) {
        return formateurRepository.findById(id);
    }

    public Formateur save(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public void deleteById(Integer id) {
        formateurRepository.deleteById(id);
    }
}


