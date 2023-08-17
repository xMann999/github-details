package com.sergiuszg.githubdetailsservice.service;

import com.sergiuszg.githubdetailsservice.client.GithubDetailsClient;
import com.sergiuszg.githubdetailsservice.factory.RepositoryDetailsFactory;
import com.sergiuszg.githubdetailsservice.factory.UserDetailsFactory;
import com.sergiuszg.githubdetailsservice.mapper.GithubMapper;
import com.sergiuszg.githubdetailsservice.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubDetailsService {

    private final GithubDetailsClient githubDetailsClient;
    private final GithubMapper githubMapper;

    public RepositoryDetailsDto showRepositoryDetails(String owner, String repository) {
        List<BranchDto> branches = githubDetailsClient.showAllBranches(owner, repository).stream().map(branch -> githubMapper.branchToBranchDto(branch)).toList();
        List<ActivityDto> changes = githubDetailsClient.showAllChanges(owner, repository).stream().map(activity -> githubMapper.activityToActivityDto(activity)).toList();
        List<UserDto> collaborators = githubDetailsClient.showAllCollaborators(owner, repository).stream().map(user -> githubMapper.userToUserDto(user)).toList();
        return RepositoryDetailsFactory.create(branches, changes, collaborators);
    }

    public UserRepositoriesDto showUserRepositories(String user) {
        UserDto details = githubMapper.userToUserDto(githubDetailsClient.showUserDetails(user));
        List<RepoDto> repos = githubDetailsClient.showUserRepositories(user).stream().map(repo -> githubMapper.repoToRepoDto(repo)).toList();
        return UserDetailsFactory.create(details, repos);
    }
}
