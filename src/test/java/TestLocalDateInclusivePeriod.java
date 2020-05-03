import model.LocalDateInclusivePeriod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class TestLocalDateInclusivePeriod {

    @Test
    public void sameDay() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 1));
        daysShouldBe(1, period.days());
    }

    @Test
    public void startDaysInOneMonth() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 15));
        daysShouldBe(15, period.daysInStartYearMonth());
    }

    @Test
    public void endDaysInOneMonth() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 4, 30));
        daysShouldBe(30, period.daysInEndYearMonth());
    }

    @Test
    public void crossTwoMonths() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 5, 20));
        daysShouldBe(50, period.days());
    }

    @Test
    public void crossThreeMonth() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 6, 1));
        daysShouldBe(62, period.days());
    }

    @Test
    public void startDayAfterEndDay() {
        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(
                LocalDate.of(2020, 4, 1),
                LocalDate.of(2020, 2, 15));
        periodValidOrNot(false, period.isValidPeriod());
    }

    private void daysShouldBe(double expected, double actual) {
        Assert.assertEquals(expected, actual, 0);
    }

    private void periodValidOrNot(boolean expected, boolean actual) {
        Assert.assertEquals(expected, actual);
    }
}
