package com.example.rewardpoints.controller;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.CustomerDTO;
import com.example.rewardpoints.service.RewardsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @PostMapping("/calculaterewardpoints")
    public ResponseEntity<List<CustomerDTO>> calculateRewardPointsPerMonth(@RequestBody List<Customer> customers) throws JsonProcessingException {

        return ResponseEntity.ok(rewardsService.getCustomerRewardPoints(customers));
    }

}
