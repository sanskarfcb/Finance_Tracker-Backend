package com.financeapp.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MonthlyExpenseDTO {
    private String month;
    private Double totalExpense;
}
