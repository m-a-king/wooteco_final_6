package oncall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static oncall.Exception.INVALID_INPUT;

public class Validator {

    private Validator() {
    }

    public static boolean validateWorker(List<String> weekdayWorkers, List<String> weekendWorkers) {
        try {
            Set<String> distinctWeekdayWorkers = new HashSet<>(weekdayWorkers);
            Set<String> distinctWeekendWorkers = new HashSet<>(weekendWorkers);

            checkDuplicate(weekdayWorkers, weekendWorkers, distinctWeekdayWorkers, distinctWeekendWorkers);
            compareWeekdayAndWeekend(distinctWeekdayWorkers, distinctWeekendWorkers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private static void checkDuplicate(List<String> weekdayWorkers,
                                       List<String> weekendWorkers,
                                       Set<String> distinctWeekdayWorkers,
                                       Set<String> distinctWeekendWorkers

    ) {
        if (weekdayWorkers.size() != distinctWeekdayWorkers.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }

        if (weekendWorkers.size() != distinctWeekendWorkers.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void compareWeekdayAndWeekend(Set<String> distinctWeekdayWorkers,
                                                 Set<String> distinctWeekendWorkers) {
        if (distinctWeekdayWorkers.equals(distinctWeekendWorkers)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }
}
