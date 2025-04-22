package com.connor.budgetplanner.controller;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.service.BudgetItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BudgetItemController.class)
class BudgetItemControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @MockitoBean
    BudgetItemService service;

    @Test
    void shouldCreateBudgetItem() throws Exception {
        BudgetItem stub = new BudgetItem("Groceries", 50, "Food", Instant.now());
        when(service.create(any(BudgetItem.class))).thenReturn(stub);

        mockMvc.perform(post("/budget-items")
                        .with(csrf())
                        .with(user("tester").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(stub)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFetchAllBudgetItems() throws Exception {
        when(service.findAll()).thenReturn(
                List.of(new BudgetItem("Rent", 900, "Housing", Instant.now())));

        mockMvc.perform(get("/budget-items")
                        .with(user("tester").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
