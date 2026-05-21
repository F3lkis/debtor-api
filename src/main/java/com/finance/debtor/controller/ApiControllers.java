package com.finance.debtor.controller;

import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.repo.DataBankRepo;
import com.finance.debtor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ApiControllers {

//    Login Controllers
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
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

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userRepo.save(updatedUser);

        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "User(id): " + id + " Deleted...";
    }


//    Application Controllers

    @Autowired
    private DataBankRepo dataBankRepo;

    @GetMapping(value = "/bank")
    public List<Data_Bank> getBank(){
        return dataBankRepo.findAll();
    }

    @PostMapping(value = "/saveBank")
    public String saveBank(@RequestBody Data_Bank dataBank){
        dataBankRepo.save(dataBank);
        return "Bank Saved";
    }

    @PutMapping(value = "/updateBank/{ProductId}")
    public String updateBank(@PathVariable long ProductId, @RequestBody Data_Bank dataBank){
        Data_Bank updateDataBank = dataBankRepo.findById(ProductId).get();

        updateDataBank.setItemBought(dataBank.getItemBought());
        updateDataBank.setPrice(dataBank.getPrice());
        updateDataBank.setCategory(dataBank.getCategory());
        updateDataBank.setDate(dataBank.getDate());
        updateDataBank.setPaymentMethod(dataBank.getPaymentMethod());

        dataBankRepo.save(updateDataBank);

        return "Bank Updated...";
    }

    @DeleteMapping(value = "/deleteBank/{id}")
    public String deleteBank(@PathVariable long ProductId){
        Data_Bank deleteDataBank = dataBankRepo.findById(ProductId).get();
        dataBankRepo.delete(deleteDataBank);
        return "User(id): " + ProductId + " Deleted...";
    }

}

