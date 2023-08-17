package com.sergiuszg.githubdetailsservice.client;
import com.sergiuszg.githubdetailsservice.configuration.FeignConfig;
import com.sergiuszg.githubdetailsservice.model.imported.Activity;
import com.sergiuszg.githubdetailsservice.model.imported.Branch;
import com.sergiuszg.githubdetailsservice.model.imported.Repo;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repository-details", url = "${api.github.url}", configuration = FeignConfig.class)
public interface GithubDetailsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<Branch> showAllBranches(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/collaborators")
    List<User> showAllCollaborators(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/activity")
    List<Activity> showAllChanges(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}")
    User showUserDetails(@PathVariable String username);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<Repo> showUserRepositories(@PathVariable String username);
}
