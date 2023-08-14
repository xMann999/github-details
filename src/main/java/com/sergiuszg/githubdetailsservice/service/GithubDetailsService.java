package com.sergiuszg.githubdetailsservice.service;

import com.sergiuszg.githubdetailsservice.client.GithubDetailsClient;
import com.sergiuszg.githubdetailsservice.factory.RepositoryDetailsFactory;
import com.sergiuszg.githubdetailsservice.factory.UserDetailsFactory;
import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubDetailsService {

    private final GithubDetailsClient githubDetailsClient;

    public RepositoryDetailsDto showRepositoryDetails(String owner, String repository) {
        var branches = githubDetailsClient.showAllBranches(owner, repository);
        var changes = githubDetailsClient.showAllChanges(owner, repository);
        var collaborators = githubDetailsClient.showAllCollaborators(owner, repository);
        return RepositoryDetailsFactory.create(branches, changes, collaborators);
    }

    public UserRepositoriesDto showUserRepositories(String user) {
        var details = githubDetailsClient.showUserDetails(user);
        var repos = githubDetailsClient.showUserRepositories(user);
        return UserDetailsFactory.create(details, repos);
    }
}
