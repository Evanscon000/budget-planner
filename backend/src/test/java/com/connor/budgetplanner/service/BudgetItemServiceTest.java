package com.connor.budgetplanner.service;

import com.connor.budgetplanner.entity.BudgetItem;
import com.connor.budgetplanner.repository.BudgetItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetItemServiceTest {

    @Mock  BudgetItemRepository repo;
    @InjectMocks BudgetItemService service;

    @Test
    void savesEntity() {
        BudgetItem item = new BudgetItem("Gas", 40, "Transport", Instant.now());
        when(repo.save(item)).thenReturn(item);

        BudgetItem saved = service.create(item);

        Assertions.assertThat(saved).isSameAs(item);
        verify(repo).save(item);
    }
}
