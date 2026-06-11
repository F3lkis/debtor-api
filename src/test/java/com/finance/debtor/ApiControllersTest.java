package com.finance.debtor;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.finance.debtor.controller.ApiControllers;
import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.services.DataBankService;
import com.finance.debtor.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(ApiControllers.class)
class ApiControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DataBankService dataBankService;

    @MockitoBean
    private UserService userService;

    @Test
    void testGetPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    // --- TESTES DE USER ---
    @Test
    void testGetUsers() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    void testSaveUser() throws Exception {
        String json = "{\"firstName\":\"Felipe\",\"email\":\"teste@teste.com\"}";
        mockMvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        String json = "{\"firstName\":\"Felipe Editado\"}";
        mockMvc.perform(put("/update/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/delete/1")).andExpect(status().isOk());
    }

    // --- TESTES DE BANK ---
    @Test
    void testGetBank() throws Exception {
        mockMvc.perform(get("/bank")).andExpect(status().isOk());
    }

    @Test
    void testGetBankByUser() throws Exception {
        mockMvc.perform(get("/bank/user/1")).andExpect(status().isOk());
    }

    @Test
    void testGetBankByCategory() throws Exception {
        mockMvc.perform(get("/bank/category/Trabalho")).andExpect(status().isOk());
    }

    @Test
    void testSaveBank_Success() throws Exception {
        String jsonPayload = "{\"itemBought\":\"Notebook\",\"price\":3000.0,\"category\":\"Trabalho\",\"paymentMethod\":\"Cartão\",\"date\":\"2026-06-10\"}";
        when(dataBankService.saveBankData(eq(1L), any())).thenReturn("Bank Saved");

        mockMvc.perform(post("/saveBank/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBank() throws Exception {
        String json = "{\"itemBought\":\"Cadeira\",\"price\":500.0}";
        mockMvc.perform(put("/updateBank/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBank() throws Exception {
        mockMvc.perform(delete("/deleteBank/1")).andExpect(status().isOk());
    }

    @Test
    void testSaveBank_UserNotFound_Coverage() throws Exception {
        // Força o erro para o GlobalExceptionHandler capturar
        when(dataBankService.saveBankData(anyLong(), any())).thenThrow(new RuntimeException("Usuário não encontrado."));

        mockMvc.perform(post("/saveBank/user/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBank_UserNotFound_ExceptionHandler() throws Exception {
        // Simula o erro que dispara o seu ExceptionHandler
        when(dataBankService.saveBankData(eq(999L), any())).thenThrow(new RuntimeException("Usuário não encontrado."));

        mockMvc.perform(post("/saveBank/user/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"itemBought\":\"Teste\",\"price\":10.0}"))
                .andExpect(status().isBadRequest()); // Ou isInternalServerError(), conforme seu Handler
    }
}