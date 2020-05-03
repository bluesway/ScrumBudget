import model.Budget;
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
    @Mock
    private IBudgetRepo budgetRepo;
    private List<Budget> dbList;
    BudgetService budgetService;

    @Before
    public void setup() {
        budgetService = new BudgetService(budgetRepo);
        dbList = new LinkedList<>();
    }

    private void normalMock() {
        Budget b1 = new Budget();
        b1.yearMonth = "202004";
        b1.amount = 30;
        Budget b2 = new Budget();
        b2.yearMonth = "202005";
        b2.amount = 310;
        Budget b3 = new Budget();
        b3.yearMonth = "202006";
        b3.amount = 3000;
        dbList.add(b1);
        dbList.add(b2);
        dbList.add(b3);
        when(budgetRepo.getAll()).thenReturn(dbList);
    }

    @Test
    public void noBudget() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 2, 1),
                LocalDate.of(2020, 3, 1));
        budgetShouldBe(0, result);
    }

    @Test
    public void sameDay() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 1));
        budgetShouldBe(1, result);
    }

    @Test
    public void noBudgetCrossToBudget() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 3, 31),
                LocalDate.of(2020, 4, 1));
        budgetShouldBe(1, result);
    }

    @Test
    public void noBudgetIncludeBudget() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 3, 31),
                LocalDate.of(2020, 7, 1));
        budgetShouldBe(3340, result);
    }

    @Test
    public void sameMonth() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 15));
        budgetShouldBe(15, result);
    }

    @Test
    public void oneMonth() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 30));
        budgetShouldBe(30, result);
    }

    @Test
    public void crossTwoMonth() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 5, 10));
        budgetShouldBe(130, result);
    }

    @Test
    public void crossThreeMonth() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 6, 20));
        budgetShouldBe(2340, result);
    }

    @Test
    public void startDayAfterEndDay() {
        normalMock();
        double result = budgetService.query(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 2, 15));
        budgetShouldBe(0, result);
    }

    private void budgetShouldBe(double expected, double actual) {
        Assert.assertEquals(expected, actual, 0);
    }
}
