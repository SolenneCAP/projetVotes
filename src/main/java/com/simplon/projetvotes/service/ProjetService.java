package com.simplon.projetvotes.service;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.repository.ProjetRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.Optional;

@Data
@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    public Optional<Projet> getProjet(final Long id) {
        return projetRepository.findById(id);
    }

    public Iterable<Projet> getProjets() {
        return projetRepository.findAll();
    }

    public void deleteProjet(final Long id) {
        projetRepository.deleteById(id);
    }

    public Projet saveProjet(Projet projet) {
        return projetRepository.save(projet);
    }
}
