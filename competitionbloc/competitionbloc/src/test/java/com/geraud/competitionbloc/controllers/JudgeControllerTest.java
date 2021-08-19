package com.geraud.competitionbloc.controllers;

import com.geraud.competitionbloc.models.Category;
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
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JudgeControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(authorities = { "gestion" })
    void validBoulder() {
        Competitor competitor = new Competitor("nom" , "prenom" , "club" , Set.of("1"));
        Category category = new Category("id1" , null, List.of(competitor), List.of("abc"),null , null);
        Mockito.when(categoryService.addCompetitor(category)).thenReturn(Mono.just(category));

        webTestClient.post().uri("/juges/validation")
                .header(HttpHeaders.ACCEPT, "application/json")
                .bodyValue(category)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(Category.class);
    }
}