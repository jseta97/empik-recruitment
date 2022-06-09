package com.example.empikrecruitment.controller;

import com.example.empikrecruitment.exception.CalculationException;
import com.example.empikrecruitment.exception.UserException;
import com.example.empikrecruitment.model.Response;
import com.example.empikrecruitment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Operation(summary = "Get user", description = "Method used to return user data after complex calculations based on repos and followers.")
    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable String login){
        try {
            return ResponseEntity.ok(userService.getUser(login));
        }catch (UserException ue){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(ue.getMessage()));
        }catch (CalculationException ce){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(ce.getMessage()));
        }catch (Throwable t){
            return ResponseEntity.internalServerError().body(new Response("Something went wrong\n" + t.getMessage()));
        }
    }
}
