package com.finance.debtor.controller;

import com.finance.debtor.DataBankService.DataBankService;
import com.finance.debtor.dto.DataBankDTO;
import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ApiControllers {

    private final DataBankService dataBankService;
    private final UserService userService;

    // Injeção de dependência via construtor
    public ApiControllers(DataBankService dataBankService, UserService userService){
        this.dataBankService = dataBankService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome to Debtor API";
    }

//  Rotas de Usuário
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

//  Rotas de Gastos
    @GetMapping(value = "/bank")
    public List<Data_Bank> getBank(){
        return dataBankService.getAllBank();
    }

    @GetMapping(value = "/bank/user/{userId}")
    public List<Data_Bank> getBankByUser(@PathVariable long userId){
        return dataBankService.getBankByUser(userId);
    }

    // Rota de Filtro Específico
    @GetMapping(value = "/bank/category/{category}")
    public List<Data_Bank> getBankByCategory(@PathVariable String category){
        return dataBankService.getBankByCategory(category);
    }

    @PostMapping(value = "/saveBank/user/{userId}")
    public String saveBank(@PathVariable long userId, @Valid @RequestBody DataBankDTO dto) {
        return dataBankService.saveBankData(userId, dto);
    }

    @PutMapping(value = "/updateBank/{ProductId}")
    public String updateBank(@PathVariable long ProductId, @RequestBody Data_Bank dataBank){
        return dataBankService.updateBankData(ProductId, dataBank);
    }

    @DeleteMapping(value = "/deleteBank/{ProductId}")
    public String deleteBank(@PathVariable long ProductId){
        return dataBankService.deleteBankData(ProductId);
    }
}