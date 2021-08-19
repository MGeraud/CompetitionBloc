package com.geraud.competitionbloc.repositories;

import com.geraud.competitionbloc.models.Competition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *  Interface pour la gestion de la database concernant les compétitions
 *  extension de SpringData gérant les BDD MongoDB en réactive programming
 */
public interface CompetitionRepository extends ReactiveMongoRepository<Competition , String> {
}
