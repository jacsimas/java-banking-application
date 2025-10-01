package org.example.Controller.OldControllersT;

import org.example.Controller.OldControllers.CustomerController;
import org.example.ModelDTO.OldDTO.RequestCustomerDataDTO;
import org.example.Services.OldService.CustomerEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)

//@WebMvcTest(CustomerController.class)
public class WebMvcCustomerControllerTest {

    @Mock
    CustomerEntityService customerEntityService;

    @InjectMocks
    CustomerController customerController;

    @Autowired
    MockMvc mockMvc;

//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build(); // no Spring Boot, no DB
//    }

    //TODO: something doesn't work, test dont pass, come back to fix this issue.
    @Test
    void shouldRegisterCustomer() throws Exception {
        // return a real DTO/entity, not String.valueOf(...)
        RequestCustomerDataDTO saved = new RequestCustomerDataDTO();
        saved.setNickname("dig");
        saved.setEmail("dig@game.com");
        saved.setPassword("password499");
        saved.setMoney(100);

//        when(customerEntityService.saveNewCustomer(RequestCustomerDataDTO saved));
//any())).thenReturn(String.valueOf(saved)
        String body =
                "{"
                        + "  \"nickname\": \"dig\","
                        + "  \"password\": \"password499\","
                        + "  \"email\": \"dig@game.com\""
                        + "}";

        mockMvc.perform(
                        post("/customers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.money").value(100))
                .andExpect((ResultMatcher) jsonPath("$.nickname").value("dig"));
    }


}
