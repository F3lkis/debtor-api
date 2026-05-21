package com.finance.debtor.repo;

import com.finance.debtor.models.Data_Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataBankRepo extends JpaRepository<Data_Bank, Long> {
}