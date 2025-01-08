package com.example.rewardpoints.service;

import com.example.rewardpoints.model.Customer;
import com.example.rewardpoints.model.CustomerDTO;
import com.example.rewardpoints.model.Transcation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RewardsServiceImpl implements RewardsService {

    public List<CustomerDTO> getCustomerRewardPoints(List<Customer> customers) {

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer: customers) {
            CustomerDTO dto = new CustomerDTO();
            dto.setName(customer.getName());
            dto.setMonthlyRewards(getCustomerRewardPointsMonthly(customer));
            dto.setTotalRewardPoints(getTotalRewards(getCustomerRewardPointsMonthly(customer)));
            customerDTOS.add(dto);
        }
        return customerDTOS;
    }

    private Map<Integer, Long> getCustomerRewardPointsMonthly(Customer customer) {

        Map<Integer, Long> rewards = new HashMap<>();
        for (Transcation transcation : customer.getTranscationList()) {
            int month = getMonth(transcation.getDate());
            //long monthno = rewards.get(month);
            if (rewards.size() > 0 && rewards.get(month) != null) {
                rewards.put(getMonth(transcation.getDate()),
                            rewards.get(month)+calculatePoints(transcation.getPurchaseAmount()));
            } else {
                rewards.put(getMonth(transcation.getDate()),
                            calculatePoints(transcation.getPurchaseAmount()));
            }
        }
        return rewards;
    }

    private long calculatePoints(double amountSpent) {
        long points = 0;

        if (amountSpent > 100) {
            points = points + Math.round((amountSpent - 100) * 2);
            amountSpent = 100;
        }

        if (amountSpent > 50) {
            points = points + Math.round((amountSpent - 50));
        }

        return points;
    }

    private int getMonth(LocalDate date) {
        return date.getMonth().getValue();
    }

    private Long getTotalRewards(Map<Integer, Long> rewards) {
        return  rewards.values().stream().mapToLong(Long::longValue).sum();
    }
}
