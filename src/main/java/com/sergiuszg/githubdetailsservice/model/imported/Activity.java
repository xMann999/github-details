package com.sergiuszg.githubdetailsservice.model.imported;

import com.sergiuszg.githubdetailsservice.model.imported.enums.PushType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Activity {

    private final UserLogin actor;
    private final PushType activity_type;
    private final LocalDateTime timestamp;
}
