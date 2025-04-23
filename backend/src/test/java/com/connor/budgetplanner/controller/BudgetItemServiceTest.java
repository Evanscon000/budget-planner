package com.connor.budgetplanner.controller;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.service.BudgetItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BudgetItemControllerTest {

    private MockMvc mockMvc;
    private BudgetItemService service;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        service = mock(BudgetItemService.class);
        BudgetItemController controller = new BudgetItemController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createsBudgetItem() throws Exception {
        BudgetItem item = new BudgetItem("Groceries", 55.0, "Food", Instant.now());
        when(service.create(any(BudgetItem.class))).thenReturn(item);

        mockMvc.perform(post("/budget-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item)))
                .andExpect(status().isCreated());
    }

    @Test
    void getsAllBudgetItems() throws Exception {
        List<BudgetItem> stubItems = List.of(
                new BudgetItem("Rent", 1000.0, "Housing", Instant.now()),
                new BudgetItem("Gas", 50.0, "Transport", Instant.now())
        );

        when(service.findAll()).thenReturn(stubItems);

        mockMvc.perform(get("/budget-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getsBudgetItemById() throws Exception {
        BudgetItem item = new BudgetItem("Gym", 30.0, "Health", Instant.now());
        when(service.findById(1L)).thenReturn(item);

        mockMvc.perform(get("/budget-items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Gym"));
    }


}
