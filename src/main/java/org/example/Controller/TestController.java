package org.example.Controller;


import org.example.Model.Test;
import org.example.Services.TestService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/testnew")
public class TestController {

    TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/uuid")
    public Optional<Optional<Test>> getId(@RequestParam(value = "id", defaultValue = "1") UUID id) throws SQLException {
        return Optional.ofNullable(testService.returnById(id));
    } // http://localhost:8080/testnew/uuid?id=21168101-c0ce-481d-9ca3-b6b44239ed93
}
