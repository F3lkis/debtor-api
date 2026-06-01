package com.finance.debtor.controller;

import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.repo.DataBankRepo;
import com.finance.debtor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ApiControllers {

//  Login
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome to Debtor API";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved ...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + id));

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userRepo.save(updatedUser);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + id));
        userRepo.delete(deleteUser);
        return "User(id): " + id + " Deleted...";
    }

//  Application Controllers
    @Autowired
    private DataBankRepo dataBankRepo;

    // Busca todos os gastos no sistema (Visão de Admin)
    @GetMapping(value = "/bank")
    public List<Data_Bank> getBank(){
        return dataBankRepo.findAll();
    }

    // Busca apenas os gastos de um usuário específico
    @GetMapping(value = "/bank/user/{userId}")
    public List<Data_Bank> getBankByUser(@PathVariable long userId){
        return dataBankRepo.findByUserId(userId);
    }

    // Salva um gasto amarrando-o ao ID do usuário
    @PostMapping(value = "/saveBank/user/{userId}")
    public String saveBank(@PathVariable long userId, @RequestBody Data_Bank dataBank){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + userId));

        // Relaciona o dono deste gasto antes de salvar no banco
        dataBank.setUser(user);
        dataBankRepo.save(dataBank);

        return "Bank Saved";
    }

    @PutMapping(value = "/updateBank/{ProductId}")
    public String updateBank(@PathVariable long ProductId, @RequestBody Data_Bank dataBank){
        Data_Bank updateDataBank = dataBankRepo.findById(ProductId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado. ID: " + ProductId));

        updateDataBank.setItemBought(dataBank.getItemBought());
        updateDataBank.setPrice(dataBank.getPrice());
        updateDataBank.setCategory(dataBank.getCategory());
        updateDataBank.setDate(dataBank.getDate());
        updateDataBank.setPaymentMethod(dataBank.getPaymentMethod());

        dataBankRepo.save(updateDataBank);
        return "Bank Updated...";
    }

    @DeleteMapping(value = "/deleteBank/{ProductId}")
    public String deleteBank(@PathVariable long ProductId){
        Data_Bank deleteDataBank = dataBankRepo.findById(ProductId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado. ID: " + ProductId));

        dataBankRepo.delete(deleteDataBank);
        return "Bank(id): " + ProductId + " Deleted...";
    }
}