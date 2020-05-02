import utils.DateUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BudgetService {

    private IBudgetRepo budgetRepo;

    public BudgetService(IBudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    private Map<YearMonth,Integer> getDailyBudgetByMonthTable(){
        Map<YearMonth,Integer> result = new HashMap<>();
        List<Budget> budgets = budgetRepo.getAll();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMM");
        for (Budget budget: budgets){

            YearMonth month = YearMonth.parse(budget.YearMonth,f);
            result.put(month,budget.amount/month.lengthOfMonth());
        }
        return result;
    }

    public int query(LocalDate dateFrom, LocalDate dateTo) {


        Map<YearMonth, Integer> dailyBudgetByMonthTable = getDailyBudgetByMonthTable();
        Map<YearMonth, Long> daysByMonthTable = DateUtil.getDateList(dateFrom,dateTo);

        long totalBudget = 0L;

        for (Map.Entry daysByMonth : daysByMonthTable.entrySet() ){
            totalBudget += dailyBudgetByMonthTable.get(daysByMonth.getKey()) * (long)daysByMonth.getValue();
        }
        return (int)totalBudget;
    }

}
