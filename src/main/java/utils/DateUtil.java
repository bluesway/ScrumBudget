package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Integer> getEffectiveDays(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Integer> daysOfMonth = new HashMap<>();
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonthValue());
            if (yearMonth.equals(YearMonth.from(start)) || yearMonth.equals(YearMonth.from(end))) {
                // calculate the days of first month and last month
                int currentDays = daysOfMonth.getOrDefault(yearMonth, 0);
                daysOfMonth.put(yearMonth, currentDays + 1);
            } else {
                // sum up all days for the other months
                if (daysOfMonth.containsKey(yearMonth)) {
                    continue;
                }

                daysOfMonth.put(yearMonth, date.lengthOfMonth());
            }
        }

        return daysOfMonth;
    }
}
