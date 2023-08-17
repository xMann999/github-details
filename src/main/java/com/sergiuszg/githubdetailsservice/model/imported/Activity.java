package com.sergiuszg.githubdetailsservice.model.imported;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Activity {

    private final UserLogin actor;
    @JsonProperty("activity_type")
    private final PushType activityType;
    private final LocalDateTime timestamp;
}
