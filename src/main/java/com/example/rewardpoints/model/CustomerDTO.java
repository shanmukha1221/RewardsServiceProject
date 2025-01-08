package com.example.rewardpoints.model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CustomerDTO {
    private String name;
    private Map<Integer, Long> monthlyRewards;
    private Long totalRewardPoints;
}
