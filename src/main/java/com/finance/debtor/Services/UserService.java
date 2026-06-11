package com.finance.debtor.services;

import com.finance.debtor.models.User;
import com.finance.debtor.repo.UserRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public String saveUser(User user) {
        userRepo.save(user);
        return "Saved ...";
    }

    public String updateUser(long id, User user) {
        User updatedUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + id));

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userRepo.save(updatedUser);
        return "Updated...";
    }

    public String deleteUser(long id) {
        User deleteUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + id));
        userRepo.delete(deleteUser);
        return "User(id): " + id + " Deleted...";
    }
}