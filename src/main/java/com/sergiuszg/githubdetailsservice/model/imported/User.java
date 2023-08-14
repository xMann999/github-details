package com.sergiuszg.githubdetailsservice.model.imported;

import lombok.Data;

@Data
public class User {

    private final String login;
    private final String name;
    private final String email;
    private final String bio;
}
