package com.example.empikrecruitment.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
public class GithubUserData {
    private int id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("public_repos")
    private int publicRepos;
    private int followers;
}
