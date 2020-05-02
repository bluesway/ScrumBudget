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
            YearMonth month = YearMonth.parse(budget.YearMonth, f);
            result.put(month, budget.amount/month.lengthOfMonth());
        }
        return result;
    }

    public int query(LocalDate dateFrom, LocalDate dateTo) {
        Map<YearMonth, Integer> dailyBudgetByMonthTable = getDailyBudgetByMonthTable();
        Map<YearMonth, Integer> effectiveDaysOfMonth = DateUtil.getDateList(dateFrom,dateTo);

        int totalBudget = 0;

        for (Map.Entry<YearMonth, Integer> effectiveDays : effectiveDaysOfMonth.entrySet() ){
            totalBudget += dailyBudgetByMonthTable.get(effectiveDays.getKey()) * effectiveDays.getValue();
        }

        return totalBudget;
    }
}
