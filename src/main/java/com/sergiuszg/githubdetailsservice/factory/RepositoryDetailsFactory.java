package com.sergiuszg.githubdetailsservice.factory;

import com.sergiuszg.githubdetailsservice.model.imported.Activity;
import com.sergiuszg.githubdetailsservice.model.imported.Branch;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;

import java.util.List;

public final class RepositoryDetailsFactory {

    public static RepositoryDetailsDto create(List<Branch> branches, List<Activity> changes, List<User> collaborators) {
        return RepositoryDetailsDto.builder()
                .collaborators(collaborators)
                .branches(branches)
                .changes(changes)
                .build();
    }
}
