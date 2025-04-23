package com.connor.budgetplanner.service;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.repository.BudgetItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetItemService {

    private final BudgetItemRepository repo;

    public BudgetItemService(BudgetItemRepository repo) {
        this.repo = repo;
    }

    public BudgetItem create(BudgetItem item) {
        return repo.save(item);
    }

    public List<BudgetItem> findAll() {
        return repo.findAll();
    }
    public BudgetItem findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }
    public BudgetItem update(Long id, BudgetItem newItem) {
        BudgetItem existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existing.setDescription(newItem.getDescription());
        existing.setAmount(newItem.getAmount());
        existing.setCategory(newItem.getCategory());
        existing.setDate(newItem.getDate());

        return repo.save(existing);
    }


}
