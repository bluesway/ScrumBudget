package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Long> getDateList(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Long> map = new HashMap<>();
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            YearMonth key = YearMonth.of(date.getYear(), date.getMonthValue());
            long current = map.getOrDefault(key, 0L);
            map.put(key, current + 1);
        }

        return map;
    }
}
