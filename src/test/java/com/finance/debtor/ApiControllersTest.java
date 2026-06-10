package com.finance.debtor; // Ajuste se o seu pacote principal for diferente

// Imports estáticos essenciais
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Imports das ferramentas de Teste do Spring
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean; // <-- Usando MockBean corretamente
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

// Imports das SUAS classes (conectando as camadas)
import com.finance.debtor.controllers.ApiControllers;
import com.finance.debtor.models.User;
import com.finance.debtor.repositories.UserRepo;
import com.finance.debtor.repositories.Data_BankRepo;

@WebMvcTest(ApiControllers.class)
class ApiControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private Data_BankRepo dataBankRepo;

    @Test
    void testSaveBank_Success() throws Exception {
        // Simulando o comportamento do banco de dados (Mockito)
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepo.findById(1L)).thenReturn(Optional.of(mockUser));

        String jsonPayload = "{\"itemBought\":\"Notebook\",\"price\":3000.0,\"category\":\"Trabalho\",\"paymentMethod\":\"Cartão\",\"date\":\"2026-06-10\"}";

        mockMvc.perform(post("/saveBank/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveBank_UserNotFound() throws Exception {
        // Simulando um ID que não existe
        when(userRepo.findById(99L)).thenReturn(Optional.empty());

        String jsonPayload = "{\"itemBought\":\"Cadeira\",\"price\":500.0,\"category\":\"Casa\",\"paymentMethod\":\"Pix\",\"date\":\"2026-06-10\"}";

        mockMvc.perform(post("/saveBank/user/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isNotFound());
    }
}