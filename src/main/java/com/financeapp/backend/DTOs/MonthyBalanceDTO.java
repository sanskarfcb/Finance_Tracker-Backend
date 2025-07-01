package com.financeapp.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthyBalanceDTO {
    private String month;
    private Double balance;
}
