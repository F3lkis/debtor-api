package com.finance.debtor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.finance.debtor.DataBankService.DataBankService;
import com.finance.debtor.dto.DataBankDTO;
import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.repo.DataBankRepo;
import com.finance.debtor.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DataBankServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private DataBankRepo dataBankRepo;

    @InjectMocks
    private DataBankService dataBankService;

    @Test
    void testGetAllBank() {
        dataBankService.getAllBank();
        verify(dataBankRepo, times(1)).findAll();
    }

    @Test
    void testGetBankByUser() {
        dataBankService.getBankByUser(1L);
        verify(dataBankRepo, times(1)).findByUserId(1L);
    }

    @Test
    void testGetBankByCategory() {
        Data_Bank bank = new Data_Bank();
        bank.setCategory("Lazer");
        when(dataBankRepo.findAll()).thenReturn(List.of(bank));

        List<Data_Bank> result = dataBankService.getBankByCategory("Lazer");
        assertEquals(1, result.size());
    }

    @Test
    void testSaveBankData_Success() {
        User mockUser = new User();
        mockUser.setId(1L); // Usando o Setter nativo do User
        when(userRepo.findById(1L)).thenReturn(Optional.of(mockUser));

        DataBankDTO dto = new DataBankDTO();
        dto.setItemBought("Monitor");
        dto.setPrice(800.0f);
        dto.setCategory("Eletrônicos");

        String result = dataBankService.saveBankData(1L, dto);
        assertEquals("Bank Saved", result);
        verify(dataBankRepo, times(1)).save(any(Data_Bank.class));
    }

    @Test
    void testUpdateBankData() {
        Data_Bank existingBank = new Data_Bank();
        existingBank.setProductId(1L); // Usando o Setter nativo do Data_Bank
        when(dataBankRepo.findById(1L)).thenReturn(Optional.of(existingBank));

        Data_Bank newData = new Data_Bank();
        newData.setItemBought("Teclado");

        String result = dataBankService.updateBankData(1L, newData);
        assertEquals("Bank Updated...", result);
        verify(dataBankRepo, times(1)).save(any(Data_Bank.class));
    }

    @Test
    void testDeleteBankData() {
        Data_Bank bank = new Data_Bank();
        bank.setProductId(1L); // Usando o Setter nativo do Data_Bank
        when(dataBankRepo.findById(1L)).thenReturn(Optional.of(bank));

        String result = dataBankService.deleteBankData(1L);
        assertEquals("Bank(id): 1 Deleted...", result);
        verify(dataBankRepo, times(1)).delete(any(Data_Bank.class));
    }

    @Test
    void testGetBankByUser_Empty() {
        when(dataBankRepo.findByUserId(99L)).thenReturn(List.of());
        List<Data_Bank> result = dataBankService.getBankByUser(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBankByCategory_NotFound() {
        when(dataBankRepo.findAll()).thenReturn(List.of()); // Lista vazia
        List<Data_Bank> result = dataBankService.getBankByCategory("Inexistente");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBankByCategory_NoResults() {
        // Simula busca em categoria vazia
        when(dataBankRepo.findAll()).thenReturn(List.of());

        List<Data_Bank> result = dataBankService.getBankByCategory("CategoriaInexistente");

        assertTrue(result.isEmpty());
        verify(dataBankRepo, times(1)).findAll();
    }
}