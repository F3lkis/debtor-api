package com.finance.debtor.controller;

import com.finance.debtor.services.DataBankService;
import com.finance.debtor.dto.DataBankDTO;
import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Health check", description = "Verifica se a API está online")
    public String getPage() {
        return "Welcome to Debtor API";
    }

//  Rotas de Usuário
    @Tag(name = "Usuários", description = "Operações relacionadas a usuários")
    @GetMapping(value = "/users")
    @Operation(summary = "Listar usuários", description = "Retorna uma lista de todos os usuários")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/save")
    @Operation(summary = "Salvar usuário", description = "Cria um novo usuário")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza as informações de um usuário existente")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário existente")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

//  Rotas de Gastos
    @Tag(name = "Dados Bancários", description = "Operações relacionadas a dados bancários")
    @GetMapping(value = "/bank")
    @Operation(summary = "Listar dados bancários", description = "Retorna uma lista de todos os dados bancários")
    public List<Data_Bank> getBank(){
        return dataBankService.getAllBank();
    }

    @GetMapping(value = "/bank/user/{userId}")
    @Operation(summary = "Listar dados bancários por usuário", description = "Retorna os dados bancários de um usuário específico")
    public List<Data_Bank> getBankByUser(@PathVariable long userId){
        return dataBankService.getBankByUser(userId);
    }

    // Rota de Filtro Específico
    @GetMapping(value = "/bank/category/{category}")
    @Operation(summary = "Listar dados bancários por categoria", description = "Retorna os dados bancários de uma categoria específica")
    public List<Data_Bank> getBankByCategory(@PathVariable String category){
        return dataBankService.getBankByCategory(category);
    }

    @PostMapping(value = "/saveBank/user/{userId}")
    @Operation(summary = "Salvar dados bancários", description = "Cria novos dados bancários para um usuário")
    public String saveBank(@PathVariable long userId, @Valid @RequestBody DataBankDTO dto) {
        return dataBankService.saveBankData(userId, dto);
    }

    @PutMapping(value = "/updateBank/{ProductId}")
    @Operation(summary = "Atualizar dados bancários", description = "Atualiza os dados bancários de um produto específico")
    public String updateBank(@PathVariable long ProductId, @RequestBody Data_Bank dataBank){
        return dataBankService.updateBankData(ProductId, dataBank);
    }

    @DeleteMapping(value = "/deleteBank/{ProductId}")
    @Operation(summary = "Excluir dados bancários", description = "Remove os dados bancários de um produto específico")
    public String deleteBank(@PathVariable long ProductId){
        return dataBankService.deleteBankData(ProductId);
    }
}