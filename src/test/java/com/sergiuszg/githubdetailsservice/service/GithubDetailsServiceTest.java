package com.sergiuszg.githubdetailsservice.service;

import com.sergiuszg.githubdetailsservice.client.GithubDetailsClient;
import com.sergiuszg.githubdetailsservice.mapper.GithubMapper;
import com.sergiuszg.githubdetailsservice.model.dto.*;
import com.sergiuszg.githubdetailsservice.model.dto.enums.PushTypeDto;
import com.sergiuszg.githubdetailsservice.model.imported.*;
import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubDetailsServiceTest {

    @InjectMocks
    GithubDetailsService githubDetailsService;
    @Mock
    GithubDetailsClient githubDetailsClient;
    @Mock
    GithubMapper githubMapper;

    @Test
    void showRepositoryDetails_RepositoryFound_DetailsReturned() throws Exception {
        String owner = "xMann999";
        String repo = "medical-service";
        BranchDto branchDto = new BranchDto("main");
        UserDto collaboratorDto = new UserDto("xMann999", "Sergiusz", "sg@gmail.com", "sds");
        ActivityDto activityDto = new ActivityDto(new UserLoginDto("xMann999"), PushTypeDto.branch_creation, LocalDateTime.now().minusDays(20).truncatedTo(ChronoUnit.MINUTES));
        RepositoryDetailsDto repositoryDetailsDto = RepositoryDetailsDto.builder()
                .branches(List.of(branchDto))
                .collaborators(List.of(collaboratorDto))
                .changes(List.of(activityDto))
                .build();
        Branch branch = new Branch("main");
        User collaborator = new User("xMann999", "Sergiusz", "sg@gmail.com", "sds");
        Activity activity = new Activity(new UserLogin("xMann999"), PushType.branch_creation, LocalDateTime.now().minusDays(20).truncatedTo(ChronoUnit.MINUTES));

        when(githubDetailsClient.showAllBranches(eq(owner), eq(repo))).thenReturn(List.of(branch));
        when(githubDetailsClient.showAllCollaborators(eq(owner), eq(repo))).thenReturn(List.of(collaborator));
        when(githubDetailsClient.showAllChanges(eq(owner), eq(repo))).thenReturn(List.of(activity));
        when(githubMapper.branchToBranchDto(eq(branch))).thenReturn(branchDto);
        when(githubMapper.activityToActivityDto(eq(activity))).thenReturn(activityDto);
        when(githubMapper.userToUserDto(eq(collaborator))).thenReturn(collaboratorDto);

        var result = githubDetailsService.showRepositoryDetails(owner, repo);

        Assertions.assertEquals(branchDto, result.getBranches().get(0));
        Assertions.assertEquals(activityDto, result.getChanges().get(0));
    }


    @Test
    void showUserRepositories_UserFound_DetailsReturned() throws Exception {
        String username = "xMann999";
        UserDto detailsDto = new UserDto("xMann999", "Sergiusz", "sg@gmail.com", "dds");
        List<RepoDto> reposDto = List.of(new RepoDto("medical-service", "medical-service", "rest api"));
        UserRepositoriesDto userRepositoriesDto = UserRepositoriesDto.builder()
                .details(detailsDto)
                .repos(reposDto)
                .build();
        User details = new User("xMann999", "Sergiusz", "sg@gmail.com", "dds");
        List<Repo> repos = List.of(new Repo("medical-service", "medical-service", "rest api"));

        when(githubDetailsClient.showUserDetails(eq(username))).thenReturn(details);
        when(githubDetailsClient.showUserRepositories(eq(username))).thenReturn(repos);
        when(githubMapper.userToUserDto(eq(details))).thenReturn(detailsDto);
        when(githubMapper.repoToRepoDto(eq(repos.get(0)))).thenReturn(reposDto.get(0));

        var result = githubDetailsService.showUserRepositories(username);

        Assertions.assertEquals(reposDto, result.getRepos());
        Assertions.assertEquals("xMann999", result.getDetails().getLogin());
    }
}
