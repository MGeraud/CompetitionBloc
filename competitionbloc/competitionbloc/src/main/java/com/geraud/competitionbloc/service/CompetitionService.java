package com.geraud.competitionbloc.service;

import com.geraud.competitionbloc.models.Competition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompetitionService {
    Flux<Competition> listAll();
    Mono<Competition> saveCompetition(Competition competition);

}
