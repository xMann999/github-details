package com.sergiuszg.githubdetailsservice.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;
import com.sergiuszg.githubdetailsservice.model.imported.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.core.ParameterizedTypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ("server.port=8282"))
@AutoConfigureWireMock(port = 8888)
public class GithubClientTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WireMockServer mockServer;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void showRepositoryDetails_RepositoryFound_RepositoryDetailsReturned() throws JsonProcessingException {
        String owner = "xMann999";
        String repo = "medical-service";

        Branch branch = new Branch("main");
        User collaborator = new User("xMann999", "Sergiusz", "sg@gmail.com", "sds");
        Activity activity = new Activity(new UserLogin("xMann999"), PushType.branch_creation, LocalDateTime.of(2023, 10, 3, 10, 15));
        RepositoryDetailsDto repositoryDetailsDto = RepositoryDetailsDto.builder()
                .branches(List.of(branch))
                .collaborators(List.of(collaborator))
                .changes(List.of(activity))
                .build();


        mockServer.stubFor(get(urlEqualTo("/repos/" + owner + "/" + repo + "/branches")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(Collections.singletonList(branch)))));

        mockServer.stubFor(get(urlEqualTo("/repos/" + owner + "/" + repo + "/collaborators")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(Collections.singletonList(collaborator)))));

        mockServer.stubFor(get(urlEqualTo("/repos/" + owner + "/" + repo + "/activity")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(Collections.singletonList(activity)))));

        ResponseEntity<RepositoryDetailsDto> response = restTemplate.exchange("http://localhost:8282/github-details/" + owner + "/" + repo, HttpMethod.GET, null , new ParameterizedTypeReference<>(){});

        Assertions.assertEquals(repositoryDetailsDto, response.getBody());
    }

    @Test
    void showUserRepositories_UserFound_DetailsReturned() throws Exception {
        String username = "xMann999";

        User details = new User("xMann999", "Sergiusz", "sg@gmail.com", "dds");
        List<Repo> repos = List.of(new Repo("medical-service", "medical-service", "rest api"));
        UserRepositoriesDto userRepositoriesDto = UserRepositoriesDto.builder()
                .details(details)
                .repos(repos)
                .build();

        mockServer.stubFor(get(urlEqualTo("/users/" + username)).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(details))));

        mockServer.stubFor(get(urlEqualTo("/users/" + username + "/repos")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(repos))));

        ResponseEntity<UserRepositoriesDto> response = restTemplate.exchange("http://localhost:8282/github-details/" + username, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        Assertions.assertEquals(userRepositoriesDto, response.getBody());
    }
}
