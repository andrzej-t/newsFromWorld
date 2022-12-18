package com.nfw.application.client;

import com.nfw.application.config.ConnectionConfig;
import com.nfw.application.domain.LoginRequest;
import com.nfw.application.domain.PublicationDto;
import com.nfw.application.domain.RegistrationRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Getter
public class BackendClient {

    private final RestTemplate restTemplate;
    private final ConnectionConfig connectionConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);

    public void registerAccount(RegistrationRequest registrationRequest) throws HttpClientErrorException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("name", registrationRequest.getName());
        headers.set("surname", registrationRequest.getSurname());
        headers.set("email", registrationRequest.getEmail());
        headers.set("password", registrationRequest.getPassword());
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        restTemplate.exchange(connectionConfig.getBackApiEndpoint() + "/registration/register", HttpMethod.POST, httpEntity, String.class);
    }

    public void login(LoginRequest loginRequest) throws HttpClientErrorException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("email", loginRequest.getEmail());
        headers.set("password", loginRequest.getPassword());
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        restTemplate.exchange(connectionConfig.getBackApiEndpoint() + "/registration/login", HttpMethod.POST, httpEntity, String.class);
    }

    public void logout(LoginRequest loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("email", loginRequest.getEmail());
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);

        restTemplate.exchange(connectionConfig.getBackApiEndpoint() + "/registration/logout", HttpMethod.POST, httpEntity, String.class);
    }

    public boolean isUserLocked(LoginRequest loginRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("email", loginRequest.getEmail());
            headers.set("password", loginRequest.getPassword());
            HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(connectionConfig.getBackApiEndpoint() + "/user/isLocked", HttpMethod.GET, httpEntity, Boolean.class);
            Boolean body = responseEntity.getBody();
            return Optional.ofNullable(body).orElse(true);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return true;
        }
    }

    public List<PublicationDto> fetchResults(String searchedText, String language, String category, Integer page, String published_before, LoginRequest loginRequest) throws HttpClientErrorException {

        URI url = UriComponentsBuilder.fromHttpUrl(connectionConfig.getBackApiEndpoint() + "/news/results")
                .queryParam("searchedText", searchedText)
                .queryParam("language", language)
                .queryParam("category", category)
                .queryParam("page", page)
                .queryParam("published_before", published_before)
                .build()
                .encode()
                .toUri();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("email", loginRequest.getEmail());
            headers.set("password", loginRequest.getPassword());
            HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<PublicationDto[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, PublicationDto[].class);

            PublicationDto[] body = responseEntity.getBody();

            return Optional.ofNullable(body)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}