package com.finance.debtor.DataBankService;

import com.finance.debtor.dto.DataBankDTO;
import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import com.finance.debtor.repo.DataBankRepo;
import com.finance.debtor.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBankService {

    private final UserRepo userRepo;
    private final DataBankRepo dataBankRepo;

    public DataBankService(UserRepo userRepo, DataBankRepo dataBankRepo) {
        this.userRepo = userRepo;
        this.dataBankRepo = dataBankRepo;
    }

    public List<Data_Bank> getAllBank() {
        return dataBankRepo.findAll();
    }

    public List<Data_Bank> getBankByUser(long userId) {
        return dataBankRepo.findByUserId(userId);
    }

    // NOVA ROTA: Filtro Específico (Garante o requisito da avaliação)
    public List<Data_Bank> getBankByCategory(String category) {
        // Filtra em memória para evitar que você precise alterar a interface do repositório agora
        return dataBankRepo.findAll().stream()
                .filter(bank -> bank.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public String saveBankData(long userId, DataBankDTO dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Data_Bank dataBank = new Data_Bank();
        dataBank.setItemBought(dto.getItemBought());
        dataBank.setPrice(dto.getPrice());
        dataBank.setCategory(dto.getCategory());
        dataBank.setPaymentMethod(dto.getPaymentMethod());
        dataBank.setDate(dto.getDate());
        dataBank.setUser(user);

        dataBankRepo.save(dataBank);
        return "Bank Saved";
    }

    public String updateBankData(long productId, Data_Bank dataBank) {
        Data_Bank updateDataBank = dataBankRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado. ID: " + productId));

        updateDataBank.setItemBought(dataBank.getItemBought());
        updateDataBank.setPrice(dataBank.getPrice());
        updateDataBank.setCategory(dataBank.getCategory());
        updateDataBank.setDate(dataBank.getDate());
        updateDataBank.setPaymentMethod(dataBank.getPaymentMethod());

        dataBankRepo.save(updateDataBank);
        return "Bank Updated...";
    }

    public String deleteBankData(long productId) {
        Data_Bank deleteDataBank = dataBankRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado. ID: " + productId));

        dataBankRepo.delete(deleteDataBank);
        return "Bank(id): " + productId + " Deleted...";
    }
}