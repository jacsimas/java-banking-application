package org.example.Controller;

import org.example.Model.Customers;
import org.example.Services.CustomerEntityService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.json.JsonAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
     void shouldGetCustomerByIdTest() throws Exception {

        this.mockMvc.perform(get("/customers/other-id?id=5")).andDo(print()).andExpect(status().isOk());
    }

    //TODO: THIS TEST DOWN BELOW FAILED, I WILL HAVE TO UPDATE IT
    @Test
    void  shouldRegisterCustomerTest() throws Exception {
        this.mockMvc.perform(post("/customers/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nickname\":\"dig\",\"password\":\"pass\",\"email\":\"dig@game.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAll()  throws Exception {

        this.mockMvc.perform(get("/customers")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldSend ()  throws Exception {

        this.mockMvc.perform(patch("/customers/add-money/1/100")).andDo(print()).andExpect(status().isOk());
    }

}
//   .andExpect(content().string(containsString("Hello, World")));
