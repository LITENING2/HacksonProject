package com.Hackason.BankAccountProject;

import com.Hackason.BankAccountProject.entity.AccountBalance;
import com.Hackason.BankAccountProject.entity.TransactionStatus;
import com.Hackason.BankAccountProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AccountRepository extends JpaRepository<User, String> {

    @Query("SELECT ab FROM AccountBalance ab WHERE ab.accountId = :accountId")
    List<AccountBalance> findBalancesByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT ts FROM TransactionStatus ts WHERE ts.fromAccount = :accountId OR ts.toAccount = :accountId")
    List<TransactionStatus> findTransactionsByAccountId(@Param("accountId") String accountId);

    User findByCardId(String cardId);
}