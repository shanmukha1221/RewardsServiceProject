package com.example.rewardpoints.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Transcation {
    private LocalDate date;
    private double purchaseAmount;
}
