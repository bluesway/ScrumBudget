package model;

public class MonthlyBudget {

    public final static long FULL_MONTH = Long.MAX_VALUE;

    int days;
    double amount;
    long effectiveDays;

    public MonthlyBudget(int days, double amount) {
        this.days = days;
        this.amount = amount;
        this.effectiveDays = 0L;
    }

    public void updateEffectiveDays(long effectiveDays) {
        this.effectiveDays = effectiveDays;
    }

    public boolean isEffective() {
        return effectiveDays > 0L;
    }

    public String toString() {
        return String.format("days: %d%namounts: %f%neffective days: %d%n",
                days, amount, effectiveDays);
    }

    public double getEffectiveBudget() {
        if (effectiveDays == FULL_MONTH) {
            return amount;
        }

        return amount / days * effectiveDays;
    }
}
