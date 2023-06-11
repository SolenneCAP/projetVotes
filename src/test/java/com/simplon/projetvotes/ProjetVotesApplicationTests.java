package com.simplon.projetvotes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Classe de tests pour l'application ProjetVotes.
 * Cette classe utilise l'annotation @SpringBootTest pour initialiser l'application Spring Boot lors des tests.
 * Elle utilise également l'annotation @AutoConfigureMockMvc pour configurer automatiquement le MockMvc pour les requêtes HTTP simulées.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ProjetVotesApplicationTests {

    @Autowired
    public MockMvc mockMvc;

    /**
     * Teste la méthode GET pour obtenir un projet.
     * Effectue une requête GET sur l'endpoint "/projets" et vérifie que le statut de la réponse est OK (200) et que le champ "creePar" du premier élément de la réponse correspond à la valeur "Solenne".
     *
     * @throws Exception si une erreur se produit lors de l'exécution du test
     */
    @Test
    public void testGetProjet() throws Exception {
        mockMvc.perform(get("/projets")).andExpect(status().isOk()).andExpect(jsonPath("$[0].creePar", is("Solenne")));
    }
}
