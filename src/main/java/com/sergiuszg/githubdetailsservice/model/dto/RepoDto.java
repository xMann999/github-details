package com.sergiuszg.githubdetailsservice.model.dto;

import lombok.Data;

@Data
public class RepoDto {

    private final String name;
    private final String fullName;
    private final String description;
}
