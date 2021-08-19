package com.geraud.competitionbloc.service;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.models.ResultDto;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategories(Competition competition) {
         competition.getCategories().forEach(
                category ->
                        categoryRepository.save(new Category(category.toLowerCase(), competition.getCompetitionName() + " " + competition.getYear()))
                                .subscribe()
        );

    }

    @Override
    public Flux<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> addBoulder(Category category) {
        return categoryRepository.addNewBoulder(category);
    }

    @Override
    public Mono<Category> deleteBoulder(Category category) {
        return categoryRepository.deleteBoulder(category);
    }

    @Override
    public Mono<Category> addCompetitor(Category category) {
        return categoryRepository.addNewCompetitor(category);
    }

    @Override
    public Mono<Category> addBoulderSucces(ResultDto resultDto) {
        return categoryRepository.validBoulder(resultDto);
    }
}
