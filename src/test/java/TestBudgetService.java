import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner::class)
public class TestBudgetService {


    @Mock private IBudgetRepo budgetRepo;

    @Test
    public void sameDay(){
        BudgetService budgetService = new BudgetService();

        when(budgetRepo.getAll()).thenReturn(Arrays.asList(new Budget()));

        budgetService.query(LocalDate.of(2020, 04, 01),
                            LocalDate.of(2020, 04, 01));
    }

    @Test
    public void sameMonth(){
        BudgetService budgetService = new BudgetService();
        budgetService.query(LocalDate.of(2020, 04, 01),
                            LocalDate.of(2020, 04, 30));
    }

    @Test
    public void crossTwoMonth(){
        BudgetService budgetService = new BudgetService();
        budgetService.query(LocalDate.of(2020, 04, 01),
                            LocalDate.of(2020, 05, 15));
    }

    @Test
    public void crossThreeMonth(){
        BudgetService budgetService = new BudgetService();
        budgetService.query(LocalDate.of(2020, 04, 01),
                            LocalDate.of(2020, 06, 15));
    }

    @Test
    public void startDayGrtEndDay(){
        BudgetService budgetService = new BudgetService();
        budgetService.query(LocalDate.of(2020, 04, 01),
                LocalDate.of(2020, 02, 15));
    }


}
