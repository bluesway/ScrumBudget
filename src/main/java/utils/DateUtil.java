package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Long> getDateList(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Long> map = new HashMap<>();
        for (int y = start.getYear(); y <= end.getYear(); y++) {
            for (int m = start.getMonthValue(); m <= end.getMonthValue(); m++) {
                map.put(YearMonth.of(y, m), Duration.between(start, end).toDays());
            }
        }

        return map;
    }
}