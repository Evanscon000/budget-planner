package com.connor.budgetplanner.controller;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.service.BudgetItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget-items")
public class BudgetItemController {

    private final BudgetItemService service;

    public BudgetItemController(BudgetItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BudgetItem> create(@RequestBody BudgetItem item) {
        BudgetItem saved = service.create(item);
        return ResponseEntity.status(201).body(saved);
    }
}
