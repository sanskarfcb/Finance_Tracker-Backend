package com.financeapp.backend.Controller;

import com.financeapp.backend.DTOs.MonthlyExpenseDTO;
import com.financeapp.backend.DTOs.Summary;
import com.financeapp.backend.Model.Transaction;
import com.financeapp.backend.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction , Principal principal){
        return ResponseEntity.ok(transactionService.addTransaction(transaction, principal.getName()));

    }
    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(Principal principal){
        return ResponseEntity.ok(transactionService.getallTransaction(principal.getName()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id , Principal principal){
        transactionService.deleteTransaction(id,principal.getName());
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> filterTransaction(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            Principal principal
    ){
        return ResponseEntity.ok(transactionService.filterTransaction(principal.getName(),type,category));
    }

    @GetMapping("/summary")
    public ResponseEntity<Summary> getSummary(Principal principal){
        return ResponseEntity.ok(transactionService.getSummary(principal.getName()));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Transaction>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            Principal principal
    ){
        return ResponseEntity.ok(
                transactionService.getTransactionsPaginated(principal.getName(),page,size,sortBy,direction));
    }
    @GetMapping("/monthly-expense")
    public ResponseEntity<List<MonthlyExpenseDTO>> getMonthlyExpense(Principal principal) {
        return ResponseEntity.ok(transactionService.getMonthlyExpenseSummary(principal.getName()));
    }
}
