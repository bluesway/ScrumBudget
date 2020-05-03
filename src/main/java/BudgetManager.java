import model.Budget;
import model.LocalDateInclusivePeriod;
import model.MonthlyBudget;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BudgetManager {
    private final Map<YearMonth, MonthlyBudget> mBudgetTable = new HashMap<>();

    void updateBudget(List<Budget> budgets) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMM");

        for (Budget budget: budgets) {
            YearMonth yearMonth = YearMonth.parse(budget.yearMonth, f);
            MonthlyBudget monthlyBudget = new MonthlyBudget(yearMonth.lengthOfMonth(), budget.amount);
            mBudgetTable.put(yearMonth, monthlyBudget);
        }
    }

    /**
     * update the effective days of the MonthlyBudget
     * @param period the period between the starting date and ending date of the query
     * @return the stream of effective MonthlyBudget table
     */
    Stream<Map.Entry<YearMonth, MonthlyBudget>> updateQueryPeriod(LocalDateInclusivePeriod period) {
        YearMonth fromMonth = period.getStartYearMonth();
        YearMonth toMonth = period.getEndYearMonth();

        if (fromMonth.getMonth().equals(toMonth.getMonth())) {
            if (mBudgetTable.containsKey(fromMonth)) {
                mBudgetTable.get(fromMonth).updateEffectiveDays(period.days());
            }
        } else {
            if (mBudgetTable.containsKey(fromMonth)) {
                mBudgetTable.get(fromMonth).updateEffectiveDays(period.daysInStartYearMonth());
            }

            if (mBudgetTable.containsKey(toMonth)) {
                mBudgetTable.get(toMonth).updateEffectiveDays(period.daysInEndYearMonth());
            }

            for (YearMonth month = fromMonth.plusMonths(1L);
                 month.isBefore(toMonth);
                 month = month.plusMonths(1L)) {

                if (mBudgetTable.containsKey(month)) {
                    mBudgetTable.get(month).updateEffectiveDays(MonthlyBudget.FULL_MONTH);
                }
            }
        }

        return mBudgetTable.entrySet()
                .stream()
                .filter(entry -> entry.getKey().compareTo(fromMonth) >= 0 &&
                        entry.getKey().compareTo(toMonth) <= 0 &&
                        entry.getValue().isEffective());
    }
}
