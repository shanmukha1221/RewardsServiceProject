package com.example.rewardpoints.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.CustomerDTO;
import com.example.rewardpoints.model.Transcation;
import com.example.rewardpoints.service.RewardsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RewardsServiceImpl rewardsService;

    List<Customer> customers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Transcation transcation1 = new Transcation();
        transcation1.setDate(LocalDate.of(2024, 9, 2));
        transcation1.setPurchaseAmount(120);

        Transcation transcation2 = new Transcation();
        transcation2.setDate(LocalDate.of(2024,10, 13));
        transcation2.setPurchaseAmount(150);

        List<Transcation> transcations1 = new ArrayList<>();
        transcations1.add(transcation1);
        transcations1.add(transcation2);

        Customer customer1 = new Customer();
        customer1.setName("Person1");
        customer1.setTranscationList(transcations1);

        Customer customer2 = new Customer();
        customer2.setName("Person2");
        customer2.setTranscationList(transcations1);

        customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
    }

    @Test
    public void testCalculateRewardPointsPerMonth() throws Exception {

        Map<Integer, Long> monthlyRewards = new HashMap<>();
        monthlyRewards.put(9, 90L);
        monthlyRewards.put(10, 320L);
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Person1");
        dto.setMonthlyRewards(monthlyRewards);
        dto.setTotalRewardPoints(410L);

        Map<Integer, Long> monthlyRewards2 = new HashMap<>();
        monthlyRewards2.put(9, 90L);
        monthlyRewards2.put(10, 320L);
        CustomerDTO dto2 = new CustomerDTO();
        dto2.setName("Person2");
        dto2.setMonthlyRewards(monthlyRewards2);
        dto2.setTotalRewardPoints(410L);
        List<CustomerDTO> list = new ArrayList<>();
        list.add(dto);
        list.add(dto2);

        Mockito.when(rewardsService.getCustomerRewardPoints(Mockito.any())).thenReturn(list);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String customerJson = mapper.writeValueAsString(customers);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculaterewardpoints")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerJson))
                .andExpect(status().isOk());
    }

}
