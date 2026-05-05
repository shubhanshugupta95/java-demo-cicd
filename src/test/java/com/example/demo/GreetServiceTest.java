package com.example.demo;

import com.example.demo.service.GreetService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetServiceTest {

    @Test
    void testGreet() {
        GreetService service = new GreetService();
        String result = service.greet("CI/CD");

        assertEquals("Hello, CI/CD!", result);
    }
}