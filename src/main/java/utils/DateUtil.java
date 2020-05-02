package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Long> getDateList(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Long> map = new HashMap<>();
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            YearMonth key = YearMonth.of(date.getYear(), date.getMonthValue());
            if (!map.containsKey(key)) {
                map.put(key, 0L);
            }

            long current = map.get(key);
            map.put(key, current + 1);
        }

        return map;
    }
}
