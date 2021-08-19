package com.geraud.competitionbloc.controllers;

import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.models.Competitor;
import com.geraud.competitionbloc.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InscriptionControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getallCategories() {
        Category category = new Category("id1" , null, null, List.of("abc"),null , null);
        Flux<Category> categoryFlux = Flux.just(category);

        Mockito.when(categoryService.listAllCategories())
                .thenReturn(categoryFlux);

        webTestClient.get().uri("/categorie/all")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Category.class);

        Mockito.verify(categoryService, times(1)).listAllCategories();
    }

    @Test
    @WithMockUser(authorities = { "gestion" })
    void addCompetitor() {
        Competitor competitor = new Competitor("nom" , "prenom" , "club");
        Category category = new Category("id1" , null, List.of(competitor), List.of("abc"),null , null);
        Mockito.when(categoryService.addCompetitor(category)).thenReturn(Mono.just(category));

        webTestClient.post().uri("/categorie/inscription")
                .header(HttpHeaders.ACCEPT, "application/json")
                .bodyValue(category)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Category.class);
    }

    @Test
    @WithMockUser( authorities = { "juge" })
    void nonAuthorisedUserOnCreateCompetitions() {

        webTestClient.get().uri("/categorie/inscription")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isForbidden();

    }
}