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

    private final List<BranchDto> branches;
    private final List<UserDto> collaborators;
    private final List<ActivityDto> changes;
}
