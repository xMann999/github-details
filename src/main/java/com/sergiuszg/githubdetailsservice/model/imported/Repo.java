package com.sergiuszg.githubdetailsservice.model.imported;

import lombok.Data;

@Data
public class Repo {

    private final String name;
    private final String full_name;
    private final String description;
}
