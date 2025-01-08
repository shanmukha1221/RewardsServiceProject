package com.example.rewardpoints.service;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.CustomerDTO;
import com.example.rewardpoints.model.Transcation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
public class RewardsServiceTests {

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    List<Customer> customers;

    @BeforeEach
    public void setUp() {
        Transcation transcation1 = new Transcation();
        transcation1.setDate(LocalDate.of(2024, 9, 2));
        transcation1.setPurchaseAmount(120);

        Transcation transcation2 = new Transcation();
        transcation2.setDate(LocalDate.of(2024,10, 13));
        transcation2.setPurchaseAmount(150);

        Transcation transcation3 = new Transcation();
        transcation3.setDate(LocalDate.of(2024,10, 13));
        transcation3.setPurchaseAmount(160);

        List<Transcation> transcations1 = new ArrayList<>();
        transcations1.add(transcation1);
        transcations1.add(transcation2);
        transcations1.add(transcation3);

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
    public void testGetCustomerRewardPointsWithCustomers() {

        List<CustomerDTO> customerDTOS = rewardsService.getCustomerRewardPoints(customers);
        Assertions.assertTrue(customerDTOS.size() > 0);
        Assertions.assertEquals(customerDTOS.get(0).getTotalRewardPoints(), 410);
    }

    @Test
    public void testGetCustomerRewardPointsWithoutCustomers() {

        customers = new ArrayList<>();
        List<CustomerDTO> customerDTOS = rewardsService.getCustomerRewardPoints(customers);
        Assertions.assertTrue(customerDTOS.size() == 0);
    }


}
