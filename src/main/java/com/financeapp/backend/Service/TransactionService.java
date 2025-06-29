package com.financeapp.backend.Service;

import com.financeapp.backend.DTOs.Summary;
import com.financeapp.backend.Model.Transaction;
import com.financeapp.backend.Model.User;
import com.financeapp.backend.Repo.TransactionRepo;
import com.financeapp.backend.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public Transaction addTransaction(Transaction transaction, String email) {
        System.out.println("📧 Email received in addTransaction(): " + email);

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        transaction.setUser(user);
        if (transaction.getDate() == null) transaction.setDate(LocalDate.now());
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getallTransaction(String email){
        User user = userRepo.findByEmail(email).orElseThrow();
        return transactionRepo.findByUserId(user.getId());
    }
    public void deleteTransaction(Long id , String email){
        Transaction trans = transactionRepo.findById(id).orElseThrow();
        if(!trans.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized access");
        }
        transactionRepo.deleteById(id);
    }
    public List<Transaction> filterTransaction(String email , String type , String category){
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(type !=null && category !=null){
            return transactionRepo.findByUserIdAndTypeAndCategory(user.getId(), type,category);
        } else if (type !=null) {
            return transactionRepo.findByUserIdAndType(user.getId(), type);
        } else if (category != null) {
            return transactionRepo.findByUserIdAndCategory(user.getId(), category);
        } else{
            return transactionRepo.findByUserId(user.getId());
        }
    }
    public Summary getSummary(String email){
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Transaction> transactions = transactionRepo.findByUserId(user.getId());

        double income = transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase("INCOME"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase("EXPENSE"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return new Summary(income, expense, income - expense);
    }
    public Page<Transaction> getTransactionsPaginated(String email , int page , int size , String sortBy , String direction){
        User user = userRepo.findByEmail(email).orElseThrow();

        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);
        return transactionRepo.findByUserId(user.getId(),pageable);
    }
}
