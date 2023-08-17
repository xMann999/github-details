package com.sergiuszg.githubdetailsservice.factory;

import com.sergiuszg.githubdetailsservice.mapper.GithubMapper;
import com.sergiuszg.githubdetailsservice.model.dto.ActivityDto;
import com.sergiuszg.githubdetailsservice.model.dto.BranchDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserDto;
import com.sergiuszg.githubdetailsservice.model.imported.Activity;
import com.sergiuszg.githubdetailsservice.model.imported.Branch;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import com.sergiuszg.githubdetailsservice.model.dto.RepositoryDetailsDto;

import java.util.List;

public final class RepositoryDetailsFactory {

    private RepositoryDetailsFactory() {
    }

    public static RepositoryDetailsDto create(List<BranchDto> branches, List<ActivityDto> changes, List<UserDto> collaborators) {
        return RepositoryDetailsDto.builder()
                .collaborators(collaborators)
                .branches(branches)
                .changes(changes)
                .build();
    }
}
