import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BudgetService {

    private IBudgetRepo budgetRepo;

    public BudgetService(IBudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    private Map<YearMonth,Integer> getBudgetTable(){
        Map<YearMonth,Integer> result = new HashMap<>();
        List<Budget> budgets = budgetRepo.getAll();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuuM");
        for (Budget budget: budgets){
            result.put(YearMonth.parse(budget.YearMonth,f),budget.amount);
        }
        return result;
    }

    public void query(LocalDate dateFrom, LocalDate dateTo) {

//        int monthBudgetFrom = getMontheBudget(dateFrom);
//        int monthBudgetTo = getMontheBudget(dateTo);


        Map<YearMonth, Integer> dayListTale = getBudgetTable();
        Map<YearMonth, Integer> dayList = getDayList();

        Double totalBudget = 0.0;

        if (dateFrom.lengthOfMonth() == dayList.get("")){
            totalBudget = totalBudget + monthBudgetFrom;
        }else{
            totalBudget = totalBudget + (monthBudgetFrom/dayList.get())
        }
    }

}
