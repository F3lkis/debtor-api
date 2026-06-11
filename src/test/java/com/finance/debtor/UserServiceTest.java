package com.finance.debtor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.finance.debtor.models.User;
import com.finance.debtor.repo.UserRepo;
import com.finance.debtor.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUsers() {
        userService.getUsers();
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testSaveUser() {
        User user = new User();
        String result = userService.saveUser(user);
        assertEquals("Saved ...", result);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L); // Usando o Setter nativo do User
        when(userRepo.findById(1L)).thenReturn(Optional.of(existingUser));

        User newData = new User();
        newData.setFirstName("Felipe");

        String result = userService.updateUser(1L, newData);
        assertEquals("Updated...", result);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L); // Usando o Setter nativo do User
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        String result = userService.deleteUser(1L);
        assertEquals("User(id): 1 Deleted...", result);
        verify(userRepo, times(1)).delete(any(User.class));
    }
}