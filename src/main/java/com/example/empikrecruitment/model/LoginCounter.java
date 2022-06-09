package com.example.empikrecruitment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LOGIN_COUNTER")
public class LoginCounter{
    @Id
    @NotNull
    @Column(name = "LOGIN")
    String login;
    @NotNull
    @Column(name = "REQUEST_COUNT")
    long requestCount;
}
