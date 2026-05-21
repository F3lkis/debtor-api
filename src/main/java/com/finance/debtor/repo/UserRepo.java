package com.finance.debtor.repo;

import com.finance.debtor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
