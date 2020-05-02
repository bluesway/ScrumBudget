import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner::class)
public class TestBudgetService {



    @Test
    public void sameMonth(){
        BudgetService budgetService = new BudgetService();
        budgetService.query(LocalDatete.of(2020, 04, 01));
    }
}
