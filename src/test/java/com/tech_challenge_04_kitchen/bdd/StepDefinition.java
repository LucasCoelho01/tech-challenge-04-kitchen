package com.tech_challenge_04_kitchen.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefinition {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private CreateOrderDto createOrderDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("a Kitchen payload with id {string}, customer {string} and totalPrice {double}")
    public void a_kitchen_payload_with_id_customer_and_total_price(String string, String string2, Double double1) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createOrderDto = new CreateOrderDto("123456", "Lucas", new ArrayList<>(), "", "", new BigDecimal(15.0));
    }
    @When("the client requests to create a Kitchen")
    public void the_client_requests_to_create_a_kitchen() throws Exception{
        mvcResult = mockMvc.perform(post("/api/kitchen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Then("the response should contain the Kitchen's ID and details")
    public void the_response_should_contain_the_kitchen_s_id_and_details() throws Exception{
        String content = mvcResult.getResponse().getContentAsString();
        Order createdOrder = objectMapper.readValue(content, Order.class);
        assertThat(createdOrder.getId()).isNotNull();
    }

}
