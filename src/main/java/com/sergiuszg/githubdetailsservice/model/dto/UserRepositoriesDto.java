package com.sergiuszg.githubdetailsservice.model.dto;

import com.sergiuszg.githubdetailsservice.model.imported.Repo;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRepositoriesDto {

    private final User details;
    private final List<Repo> repos;
}
