package org.example.Controller.OldControllersT;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
     void shouldGetCustomerByIdTest() throws Exception {

        this.mockMvc.perform(get("/customers/other-id?id=5")).andExpect(status().isOk());
    }


    //TODO: THIS TEST BELOW HAS SUCCEEDED
    @Test
    void  shouldRegisterCustomerTest() throws Exception {
        this.mockMvc.
                perform(post("/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nickname\":\"boba\",\"money\":100,\"password\":\"pass\",\"email\":\"boba@game.com\"}"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    //these methods used to work before 25.09.2025, and works well since.
    @Test
    void shouldGetAll()  throws Exception {

        this.mockMvc.perform(get("/customers")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldSend ()  throws Exception {

        this.mockMvc.perform(patch("/customers/add-money/1/100")).andDo(print()).andExpect(status().isOk());
    }

}
