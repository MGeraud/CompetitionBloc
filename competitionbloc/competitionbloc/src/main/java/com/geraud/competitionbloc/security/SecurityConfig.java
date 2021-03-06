package com.geraud.competitionbloc.security;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Bean;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@EnableWebFluxSecurity
public class SecurityConfig {


    /**
     * Bean créant la chain de filtres via spring sécurity avec les différents endpoints et le convertisseur de JWT car approche spring reactive
     */
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/competition/*").hasAuthority("gestion")
                .pathMatchers("/categorie/all").permitAll()
                .pathMatchers("/categorie/inscription").hasAuthority("gestion")
                .pathMatchers("/juges/*").hasAnyAuthority("gestion","juge")
                .anyExchange().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(grantedAuthoritiesExtractor());

        return http.build();
    }

    /**
     * convertisseur de jwt
     * @return
     */
    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        GrantedAuthoritiesExtractor extractor = new GrantedAuthoritiesExtractor();
        return new ReactiveJwtAuthenticationConverterAdapter(extractor);
    }

    /**
     * Extractions des différents paramètres d'authorisation du jwt
     */
    static class GrantedAuthoritiesExtractor extends JwtAuthenticationConverter {
        @Override
        protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
            Map<String, Object> claims = jwt.getClaims();
            JSONObject realmAccess = (JSONObject) claims.get("realm_access");
            JSONArray roles = (JSONArray) realmAccess.get("roles");
            return roles.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }
}
