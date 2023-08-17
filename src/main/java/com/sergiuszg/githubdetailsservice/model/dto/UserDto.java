package com.sergiuszg.githubdetailsservice.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private final String login;
    private final String name;
    private final String email;
    private final String bio;
}
