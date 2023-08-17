package com.sergiuszg.githubdetailsservice.model.dto;

import com.sergiuszg.githubdetailsservice.model.dto.enums.PushTypeDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDto {

    private final UserLoginDto actor;
    private final PushTypeDto activityType;
    private final LocalDateTime timestamp;
}
