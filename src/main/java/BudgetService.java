import model.LocalDateInclusivePeriod;

import java.time.LocalDate;

/**
 * Simple Design Pattern
 *
 * 1. Pass tests -> fulfill the requirement
 * 2. Present the intent from the code itself
 * 3. Prevent the redundant codes
 * 4. Pass the parameters as less as possible
 * 5. Return the values as simple as possible
 *
 * Primitive Obsession
 */
public class BudgetService {

    private final IBudgetRepo budgetRepo;
    private final BudgetManager budgetManager;

    public BudgetService(IBudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
        this.budgetManager = new BudgetManager();
    }

    public double query(LocalDate dateFrom, LocalDate dateTo) {
        // move this outside of the method if we need to reuse in the future
        budgetManager.updateBudget(budgetRepo.getAll());

        LocalDateInclusivePeriod period = LocalDateInclusivePeriod.between(dateFrom, dateTo);
        if (period.isValidPeriod()) {
            return budgetManager.updateQueryPeriod(period)
                    .mapToDouble(entry -> entry.getValue().getEffectiveBudget())
                    .sum();
        }

        return 0.0;
    }
}
