package org.example.Controller;

import org.example.Model.Customers;
import org.example.Services.CustomerEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@ExtendWith(MockitoExtension.class)
public class UnitTestControllerTest {

    @Mock
    CustomerEntityService customerEntityService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;


//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build(); // no Spring Boot, no DB
//    }

//    @Test
//    void shouldRegisterCustomer() {
//        when(customerEntityService.saveNewCustomer(any()))
//                .thenReturn(String.valueOf(new Customers()));
//
//        mockMvc.perform(post("/customers/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                     {"nickname":"dig","password":"pass","email":"dig@game.com"}
//                     """))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(10))
//                .andExpect(jsonPath("$.nickname").value("dig"));
//
//    }

}
