package com.connor.budgetplanner.service;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.repository.BudgetItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.Instant;

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
}
