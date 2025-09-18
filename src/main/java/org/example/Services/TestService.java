package org.example.Services;

import org.example.Model.Test;
import org.example.Repositories.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TestService {

    TestRepository testRepository;
    final Logger log = LoggerFactory.getLogger(TestService.class);

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Optional<Test> returnById(UUID id){
        return testRepository.findById(id);
    }

}
