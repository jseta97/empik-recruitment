package com.example.empikrecruitment.service;

import com.example.empikrecruitment.exception.CalculationException;
import com.example.empikrecruitment.exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads(){
        assertNotNull(userService);
    }

    @Test
    public void shouldCalculateCorrectValue(){
        assertEquals(userService.getUser("octocat").getCalculations(), 0.009902623482048512);
    }

    @Test
    public void shouldThrowException(){
        UserException exception = Assertions.assertThrows(UserException.class, () -> userService.getUser("randomNameThatNotExist"));
        Assertions.assertEquals("User randomNameThatNotExist not found", exception.getMessage());
    }

    @Test
    public void testCalculateMethodUsingReflection() {
        CalculationException exception = Assertions.assertThrows(CalculationException.class, () -> ReflectionTestUtils.invokeMethod(userService, "calculate", 0, 10));
        Assertions.assertEquals("The number of followers is equal to 0, calculations cannot be divided by 0", exception.getMessage());
    }

}