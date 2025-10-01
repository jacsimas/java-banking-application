package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SmokeTests {

    @Test
    void contextLoads() {}
}
