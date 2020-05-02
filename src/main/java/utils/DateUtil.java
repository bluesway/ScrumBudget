package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Integer> getDateList(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Integer> map = new HashMap<>();
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            YearMonth key = YearMonth.of(date.getYear(), date.getMonthValue());
            if (key.equals(YearMonth.from(start)) || key.equals(YearMonth.from(end))) {
                int current = map.getOrDefault(key, 0);
                map.put(key, current + 1);
            } else {
                if (!map.containsKey(key)) {
                    map.put(key, date.lengthOfMonth());
                }
            }
        }

        return map;
    }
}
