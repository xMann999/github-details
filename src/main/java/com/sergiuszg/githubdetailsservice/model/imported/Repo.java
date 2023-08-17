package com.sergiuszg.githubdetailsservice.model.imported;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Repo {

    private final String name;
    @JsonProperty("full_name")
    private final String fullName;
    private final String description;
}
