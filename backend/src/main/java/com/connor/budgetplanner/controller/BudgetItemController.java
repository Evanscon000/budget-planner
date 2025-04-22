package com.connor.budgetplanner.controller;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.service.BudgetItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        return ResponseEntity
                .created(URI.create("/budget-items/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public List<BudgetItem> all() {
        return service.findAll();
    }
}
