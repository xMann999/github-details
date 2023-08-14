package com.sergiuszg.githubdetailsservice.service;

import com.sergiuszg.githubdetailsservice.client.GithubDetailsClient;
import com.sergiuszg.githubdetailsservice.factory.RepositoryDetailsFactory;
import com.sergiuszg.githubdetailsservice.factory.UserDetailsFactory;
import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;
import com.sergiuszg.githubdetailsservice.model.imported.*;
import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubDetailsServiceTest {

    @InjectMocks
    GithubDetailsService githubDetailsService;
    @Mock
    GithubDetailsClient githubDetailsClient;

    @Test
    void showRepositoryDetails_RepositoryFound_DetailsReturned() throws Exception {
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
        when(githubDetailsClient.showAllBranches(eq(owner), eq(repo))).thenReturn(List.of(branch));
        when(githubDetailsClient.showAllCollaborators(eq(owner), eq(repo))).thenReturn(List.of(collaborator));
        when(githubDetailsClient.showAllChanges(eq(owner), eq(repo))).thenReturn(List.of(activity));

        var result = githubDetailsService.showRepositoryDetails(owner, repo);

        Assertions.assertEquals(branch, result.getBranches().get(0));
        Assertions.assertEquals(activity, result.getChanges().get(0));
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
        when(githubDetailsClient.showUserDetails(eq(username))).thenReturn(details);
        when(githubDetailsClient.showUserRepositories(eq(username))).thenReturn(repos);

        var result = githubDetailsService.showUserRepositories(username);

        Assertions.assertEquals(repos, result.getRepos());
        Assertions.assertEquals("xMann999", result.getDetails().getLogin());
    }
}
