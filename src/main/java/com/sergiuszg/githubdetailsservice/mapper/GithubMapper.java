package com.sergiuszg.githubdetailsservice.mapper;

import com.sergiuszg.githubdetailsservice.model.dto.*;
import com.sergiuszg.githubdetailsservice.model.dto.enums.PushTypeDto;
import com.sergiuszg.githubdetailsservice.model.imported.*;
import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GithubMapper {

    @Mapping(source = "actor", target = "actor")
    @Mapping(source = "activityType", target = "activityType", qualifiedByName = "pushTypeToPushTypeDto")
    ActivityDto activityToActivityDto(Activity activity);

    BranchDto branchToBranchDto(Branch branch);

    RepoDto repoToRepoDto(Repo repo);

    UserDto userToUserDto(User user);

    UserLoginDto userLoginToUserLoginDto(UserLogin userLogin);

    @Named("pushTypeToPushTypeDto")
    default PushTypeDto mapToFacilityId (PushType pushType) {
        return switch (pushType) {
            case branch_creation -> PushTypeDto.branch_creation;
            case branch_deletion -> PushTypeDto.branch_deletion;
            case push -> PushTypeDto.push;
            case force_push -> PushTypeDto.force_push;
            case pr_merge -> PushTypeDto.pr_merge;
            case merge_queue_merge -> PushTypeDto.merge_queue_merge;
        };
    }
}
