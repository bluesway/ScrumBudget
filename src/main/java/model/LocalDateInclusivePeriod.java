package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoUnit.DAYS;

public class LocalDateInclusivePeriod {
    LocalDate startDate;
    LocalDate endDate;
    Period embeddedPeriod;

    public LocalDateInclusivePeriod() { }

    public static LocalDateInclusivePeriod between(LocalDate startDate, LocalDate endDate) {
        LocalDateInclusivePeriod period = new LocalDateInclusivePeriod();
        period.startDate = startDate;
        period.endDate = endDate;
        period.embeddedPeriod = Period.between(startDate, endDate.plusDays(1));

        return period;
    }

    public YearMonth getStartYearMonth() {
        return YearMonth.from(startDate);
    }

    public YearMonth getEndYearMonth() {
        return YearMonth.from(endDate);
    }

    public long days() {
        if (getStartYearMonth() == getEndYearMonth()) {
            return embeddedPeriod.getDays();
        }

        return DAYS.between(startDate, endDate.plusDays(1));
    }

    public long daysInStartYearMonth() {
        if (getStartYearMonth().equals(getEndYearMonth())) {
            return embeddedPeriod.getDays();
        }

        return DAYS.between(
                startDate, startDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1));
    }

    public long daysInEndYearMonth() {
        if (getStartYearMonth() == getEndYearMonth()) {
            return embeddedPeriod.getDays();
        }

        return DAYS.between(
                endDate.with(TemporalAdjusters.firstDayOfMonth()), endDate.plusDays(1));
    }

    public boolean isValidPeriod() {
        return !embeddedPeriod.isNegative() && !embeddedPeriod.isZero() &&
                startDate.compareTo(endDate) <= 0;
    }
}
