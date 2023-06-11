package com.simplon.projetvotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application ProjetVotes.
 * Cette classe initialise et lance l'application Spring Boot.
 */
@SpringBootApplication
public class ProjetVotesApplication {

    /**
     * Point d'entrée de l'application.
     *
     * @param args les arguments de ligne de commande (facultatif)
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjetVotesApplication.class, args);
    }

}

