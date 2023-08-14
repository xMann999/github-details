package com.sergiuszg.githubdetailsservice.factory;

import com.sergiuszg.githubdetailsservice.model.imported.Repo;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import com.sergiuszg.githubdetailsservice.model.dto.UserRepositoriesDto;

import java.util.List;

public final class UserDetailsFactory {

    public static UserRepositoriesDto create(User details, List<Repo> repos) {
        return UserRepositoriesDto.builder()
                .details(details)
                .repos(repos)
                .build();
    }
}
