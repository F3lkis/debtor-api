package com.finance.debtor.repo;

import com.finance.debtor.models.Data_Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DataBankRepo extends JpaRepository<Data_Bank, Long> {
    List<Data_Bank> findByUserId(long userId);
}