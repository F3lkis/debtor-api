package com.finance.debtor;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.finance.debtor.controller.ApiControllers;
import com.finance.debtor.models.User;
import com.finance.debtor.repo.DataBankRepo;
import com.finance.debtor.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(ApiControllers.class)
class ApiControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepo userRepo;

    @MockitoBean
    private DataBankRepo dataBankRepo;

    @Test
    void testSaveBank_Success() throws Exception {
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
        when(userRepo.findById(99L)).thenReturn(Optional.empty());

        String jsonPayload = "{\"itemBought\":\"Cadeira\",\"price\":500.0,\"category\":\"Casa\",\"paymentMethod\":\"Pix\",\"date\":\"2026-06-10\"}";

        mockMvc.perform(post("/saveBank/user/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isNotFound());
    }
}