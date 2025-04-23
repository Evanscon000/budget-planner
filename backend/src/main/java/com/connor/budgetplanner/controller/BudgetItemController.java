package com.connor.budgetplanner.controller;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.service.BudgetItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(201).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<BudgetItem>> findAll() {
        List<BudgetItem> items = service.findAll();
        return ResponseEntity.ok(items);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BudgetItem> findById(@PathVariable Long id) {
        BudgetItem item = service.findById(id);
        return ResponseEntity.ok(item);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BudgetItem> update(@PathVariable Long id, @RequestBody BudgetItem item) {
        BudgetItem updated = service.update(id, item);
        return ResponseEntity.ok(updated);
    }


}
