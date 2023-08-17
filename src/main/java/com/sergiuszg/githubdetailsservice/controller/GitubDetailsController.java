package com.sergiuszg.githubdetailsservice.controller;

import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;
import com.sergiuszg.githubdetailsservice.service.GithubDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/github-details")
public class GitubDetailsController {

    private final GithubDetailsService githubDetailsService;

    @Operation(summary = "Show repository details", tags = "Repository")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RepositoryDetailsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @GetMapping("/repos/{owner}/{repo}")
    RepositoryDetailsDto showRepositoryDetails(@PathVariable String owner, @PathVariable String repo) {
        return githubDetailsService.showRepositoryDetails(owner, repo);
    }

    @Operation(summary = "Show user details", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRepositoriesDto.class))}),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content)
    })
    @GetMapping("/users/{user}")
    UserRepositoriesDto showUserRepositories(@PathVariable String user) {
        return githubDetailsService.showUserRepositories(user);
    }
}
