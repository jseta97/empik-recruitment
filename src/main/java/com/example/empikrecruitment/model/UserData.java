package com.example.empikrecruitment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Schema(title = "User data")
public class UserData {
    @Schema(title = "User id")
    private int id;
    @Schema(title = "User login")
    private String login;
    @Schema(title = "User name")
    private String name;
    @Schema(title = "User type")
    private String type;
    @Schema(title = "Link to user avatar")
    private String avatarUrl;
    @Schema(title = "Account creation date")
    private LocalDateTime createdAt;
    @Schema(title = "Complex calculation based on repos and followers")
    private float calculations;
}
