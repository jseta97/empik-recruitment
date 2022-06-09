package com.example.empikrecruitment.service;

import com.example.empikrecruitment.exception.CalculationException;
import com.example.empikrecruitment.exception.UserException;
import com.example.empikrecruitment.model.GithubUserData;
import com.example.empikrecruitment.model.LoginCounter;
import com.example.empikrecruitment.model.UserData;
import com.example.empikrecruitment.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserData getUser(String login){
        GithubUserData githubUserData = getGithubUser(login);
        UserData userData = parseToUserData(githubUserData);
        userData.setCalculations(calculate(githubUserData.getFollowers(), githubUserData.getPublicRepos()));
        updateCounter(login);
        return userData;
    }

    private GithubUserData getGithubUser(String login){
        try {
            return restTemplate.getForObject("/users/" + login, GithubUserData.class);
        }catch (RestClientException rce) {
            log.error(rce.getMessage(), rce);
            throw new UserException("User " + login + " not found", rce);
        }
    }

    private UserData parseToUserData(GithubUserData githubUserData){
        return UserData.builder()
                .id(githubUserData.getId())
                .login(githubUserData.getLogin())
                .name(githubUserData.getName())
                .type(githubUserData.getType())
                .avatarUrl(githubUserData.getAvatarUrl())
                .createdAt(githubUserData.getCreatedAt())
                .build();
    }

    private float calculate(int followers, int repos){
        if(followers == 0){
            CalculationException ce = new CalculationException("The number of followers is equal to 0, calculations cannot be divided by 0");
            log.error(ce.getMessage(), ce);
            throw ce;
        }
        return (float) 6 / followers * (2 + repos);
    }

    private void updateCounter(String login){
        if (!userRepository.existsById(login)) {
            validAndSave(new LoginCounter(login, 1));
        }else {
            userRepository.findById(login).ifPresent(counter -> {
                counter.setRequestCount(counter.getRequestCount() + 1);
                validAndSave(counter);
            });
        }
    }

    private void validAndSave(@Valid LoginCounter loginCounter){
        userRepository.save(loginCounter);
    }
}
