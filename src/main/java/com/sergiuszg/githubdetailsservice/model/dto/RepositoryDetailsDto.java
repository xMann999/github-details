package com.sergiuszg.githubdetailsservice.model.dto;

import com.sergiuszg.githubdetailsservice.model.imported.Activity;
import com.sergiuszg.githubdetailsservice.model.imported.Branch;
import com.sergiuszg.githubdetailsservice.model.imported.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepositoryDetailsDto {

    private final List<Branch> branches;
    private final List<User> collaborators;
    private final List<Activity> changes;
}
