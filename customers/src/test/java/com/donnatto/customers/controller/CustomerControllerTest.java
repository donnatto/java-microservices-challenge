package com.donnatto.customers.controller;

import com.donnatto.customers.CustomersApplication;
import com.donnatto.customers.data.TestData;
import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerResponseDTO;
import com.donnatto.customers.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomersApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
    
    private MockMvc mvc;
    private CustomerService service;
    
    @Autowired
    public CustomerControllerTest(MockMvc mvc, CustomerService service) {
        this.mvc = mvc;
        this.service = service;
    }
    
    @Test
    void shouldCreateReadUpdateAndDeleteCustomer() throws Exception {
        CustomerRequestDTO requestDTO = TestData.createRequestDto();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    
        MvcResult mvcResult = mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dni", Matchers.is("12345678")))
                .andExpect(jsonPath("$.name", Matchers.is("Pedro Perez")))
                .andExpect(jsonPath("$.gender", Matchers.is("MALE")))
                .andExpect(jsonPath("$.age", Matchers.is(20)))
                .andExpect(jsonPath("$.address", Matchers.is("Acapulco 111")))
                .andExpect(jsonPath("$.phone", Matchers.is("977664511")))
                .andExpect(jsonPath("$.status", Matchers.is("ACTIVE")))
                .andReturn();
    
        String jsonString = mvcResult.getResponse().getContentAsString();
        CustomerResponseDTO createdResponseDto = mapper.readValue(jsonString, CustomerResponseDTO.class);
    
        mvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].customerId", Matchers.is(createdResponseDto.getCustomerId())))
                .andExpect(jsonPath("$[0].dni", Matchers.is("12345678")))
                .andExpect(jsonPath("$[0].name", Matchers.is("Pedro Perez")))
                .andExpect(jsonPath("$[0].gender", Matchers.is("MALE")))
                .andExpect(jsonPath("$[0].age", Matchers.is(20)))
                .andExpect(jsonPath("$[0].address", Matchers.is("Acapulco 111")))
                .andExpect(jsonPath("$[0].phone", Matchers.is("977664511")))
                .andExpect(jsonPath("$[0].status", Matchers.is("ACTIVE")));
    
        mvc.perform(get("/customers/{customerId}", createdResponseDto.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", Matchers.is(createdResponseDto.getCustomerId())))
                .andExpect(jsonPath("$.dni", Matchers.is("12345678")))
                .andExpect(jsonPath("$.name", Matchers.is("Pedro Perez")))
                .andExpect(jsonPath("$.gender", Matchers.is("MALE")))
                .andExpect(jsonPath("$.age", Matchers.is(20)))
                .andExpect(jsonPath("$.address", Matchers.is("Acapulco 111")))
                .andExpect(jsonPath("$.phone", Matchers.is("977664511")))
                .andExpect(jsonPath("$.status", Matchers.is("ACTIVE")));
        
        CustomerRequestDTO updateRequestDto = TestData.createUpdateRequestDto();
        
        mvc.perform(put("/customers/{customerId}", createdResponseDto.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dni", Matchers.is("87654321")))
                .andExpect(jsonPath("$.name", Matchers.is("Maria Ramos")))
                .andExpect(jsonPath("$.gender", Matchers.is("FEMALE")))
                .andExpect(jsonPath("$.age", Matchers.is(31)))
                .andExpect(jsonPath("$.address", Matchers.is("Flora Tristan 222")))
                .andExpect(jsonPath("$.phone", Matchers.is("942594919")))
                .andExpect(jsonPath("$.status", Matchers.is("ACTIVE")));
    
        mvc.perform(patch("/customers/{customerId}", createdResponseDto.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestData.patchJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", Matchers.is(createdResponseDto.getCustomerId())))
                .andExpect(jsonPath("$.address", Matchers.is("Jr Paz 394")));
    
        mvc.perform(delete("/customers/{customerId}", createdResponseDto.getCustomerId()))
                .andExpect(status().isOk());
        
        mvc.perform(get("/customers/{customerId}", createdResponseDto.getCustomerId()))
                .andExpect(status().isNotFound());
    }
}