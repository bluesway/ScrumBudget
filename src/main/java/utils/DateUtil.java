package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DateUtil {
    public static HashMap<YearMonth, Long> getDateList(LocalDate start, LocalDate end) {
        HashMap<YearMonth, Long> map = new HashMap<>();
        if (start.getYear() == end.getYear()) {
            if (start.getMonthValue() == end.getMonthValue()) {
                map.put(YearMonth.of(start.getYear(), start.getMonthValue()),
                        Duration.between(start, end).toDays());
            } else {
                for (int m = start.getMonthValue(); m <= end.getMonthValue(); m++) {
                    if (m == end.getMonthValue()) {
                        map.put(YearMonth.of(start.getYear(), m), Duration.between(start, end).toDays());
                    } else {
                        map.put(YearMonth.of(start.getYear(), m), (long) (start.lengthOfMonth() - start.getDayOfMonth() + 1));
                    }
                }
            }
        } else {
            for (int y = start.getYear(); y <= end.getYear(); y++) {
                if (start.getMonthValue() == end.getMonthValue()) {
                    map.put(YearMonth.of(start.getYear(), start.getMonthValue()),
                            Duration.between(start, end).toDays());
                } else {
                    for (int m = start.getMonthValue(); m <= end.getMonthValue(); m++) {
                        if (m == end.getMonthValue()) {
                            map.put(YearMonth.of(start.getYear(), m), Duration.between(start, end).toDays());
                        } else {
                            map.put(YearMonth.of(start.getYear(), m), (long) (start.lengthOfMonth() - start.getDayOfMonth() + 1));
                        }
                    }
                }
            }
        }

        return map;
    }
}