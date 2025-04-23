package com.connor.budgetplanner.service;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.repository.BudgetItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class BudgetItemServiceTest {

    @Mock
    BudgetItemRepository repo;

    @InjectMocks
    BudgetItemService service;

    @Test
    void savesBudgetItem() {
        BudgetItem item = new BudgetItem("Groceries", 55.0, "Food", Instant.now());
        when(repo.save(item)).thenReturn(item);

        BudgetItem result = service.create(item);

        assertThat(result).isSameAs(item);
        verify(repo).save(item);
    }

    @Test
    void returnsAllBudgetItems() {
        List<BudgetItem> stubItems = List.of(
                new BudgetItem("Rent", 1000.0, "Housing", Instant.now()),
                new BudgetItem("Gas", 50.0, "Transport", Instant.now())
        );

        when(repo.findAll()).thenReturn(stubItems);

        List<BudgetItem> result = service.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDescription()).isEqualTo("Rent");
        verify(repo).findAll();

    }
}
