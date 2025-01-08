package com.example.rewardpoints.service;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.CustomerDTO;
import java.util.List;

public interface RewardsService {

    List<CustomerDTO> getCustomerRewardPoints(List<Customer> customers);
}
