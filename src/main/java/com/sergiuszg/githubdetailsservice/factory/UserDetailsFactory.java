package com.sergiuszg.githubdetailsservice.factory;

import com.sergiuszg.githubdetailsservice.model.dto.RepoDto;
import com.sergiuszg.githubdetailsservice.model.dto.UserDto;
import com.sergiuszg.githubdetailsservice.model.imported.Repo;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;

import java.util.List;

public final class UserDetailsFactory {

    private UserDetailsFactory() {
    }

    public static UserRepositoriesDto create(UserDto details, List<RepoDto> repos) {
        return UserRepositoriesDto.builder()
                .details(details)
                .repos(repos)
                .build();
    }
}
