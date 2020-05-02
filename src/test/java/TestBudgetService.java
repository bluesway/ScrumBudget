import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestBudgetService {


    @Mock private IBudgetRepo budgetRepo;
    private List<Budget> dbList;
    BudgetService budgetService;
    @Before
    public void setup(){
        budgetService = new BudgetService(budgetRepo);
        dbList = new LinkedList<>();
    }

    private void normalMock(){
        Budget b1 = new Budget();
        b1.YearMonth = "202004";
        b1.amount = 300;
        Budget b2 = new Budget();
        b2.YearMonth = "202005";
        b2.amount = 310;
        Budget b3 = new Budget();
        b3.YearMonth = "202006";
        b3.amount = 300;
        dbList.add(b1);
        dbList.add(b2);
        dbList.add(b3);
        when(budgetRepo.getAll()).thenReturn(dbList);
    }

    @Test
    public void sameDay(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                            LocalDate.of(2020, 4, 1));
        Assert.assertEquals(10, result);
    }

    @Test
    public void sameMonth(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                            LocalDate.of(2020, 4, 15));
        Assert.assertEquals(150, result);
    }

    @Test
    public void oneMonth(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 30));
        Assert.assertEquals(300, result);
    }

    @Test
    public void crossTwoMonth(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                            LocalDate.of(2020, 5, 15));
        Assert.assertEquals(450, result);
    }

    @Test
    public void crossThreeMonth(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                            LocalDate.of(2020, 6, 15));
        Assert.assertEquals(760, result);
    }

    @Test
    public void startDayGrtEndDay(){
        normalMock();
        int result = budgetService.query(LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 2, 15));
        Assert.assertEquals(0, result);
    }
}
