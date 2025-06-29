package com.financeapp.backend.Repo;

import com.financeapp.backend.Model.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByUserIdAndCategory(Long userId,String category);
    List<Transaction> findByUserIdAndType(Long userId, String type);
    List<Transaction> findByUserIdAndTypeAndCategory(Long userId, String type, String category);
    Page<Transaction> findByUserId(Long userId,Pageable pageable);
}
