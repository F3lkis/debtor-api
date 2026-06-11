package com.finance.debtor;

import com.finance.debtor.models.Data_Bank;
import com.finance.debtor.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelCoverageTest {
    @Test
    void testUserGettersSetters() {
        User u = new User();
        u.setFirstName("Felipe");
        assertEquals("Felipe", u.getFirstName());
    }

    @Test
    void testBankGettersSetters() {
        Data_Bank b = new Data_Bank();
        b.setCategory("Trabalho");
        b.setPrice(100.0f);
        assertEquals("Trabalho", b.getCategory());
        assertEquals(100.0f, b.getPrice());
    }

    @Test
    void testBankFullCoverage() {
        Data_Bank b = new Data_Bank();
        b.setRealeses(1);
        b.setActiveCategories(1);
        b.setDate(java.time.LocalDate.now());
        b.setPaymentMethod("Pix");

        assertEquals(1, b.getRealeases());
        assertEquals(1, b.getActiveCategories());
        assertEquals("Pix", b.getPaymentMethod());
    }

    @Test
    void testUserFullCoverage() {
        User u = new User();
        u.setLastName("Santos");
        u.setEmail("felipe@email.com");
        u.setPassword("12345");

        assertEquals("Santos", u.getLastName());
        assertEquals("felipe@email.com", u.getEmail());
        assertEquals("12345", u.getPassword());
    }

    @Test
    void testDataBankFullCoverage() {
        Data_Bank b = new Data_Bank();
        b.setProductId(1L);
        b.setItemBought("Livro");
        b.setPrice(50.0f);
        b.setCategory("Estudo");
        b.setDate(java.time.LocalDate.now());
        b.setPaymentMethod("Dinheiro");
        b.setActiveCategories(1);
        b.setRealeses(1);

        assertNotNull(b);
        assertEquals(1L, b.getProductId());
        assertEquals("Livro", b.getItemBought());
        // Continue a asserção para todos os campos...
    }
}