package com.financeapp.backend.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Summary {
    private double totalIncome;
    private double totalExpense;
    private double balance;
    private String name;
}
