package com.geraud.competitionbloc.service;

import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Flux<Competition> listAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Mono<Competition> saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
