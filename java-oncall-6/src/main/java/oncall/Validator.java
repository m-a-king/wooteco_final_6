package oncall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static oncall.Exception.INVALID_INPUT;

public class Validator {

    private Validator() {
    }

    public static void validateMonthAndDayOfWeek(String[] monthAndDayOfWeek) {
        if (monthAndDayOfWeek.length != 2) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    public static void validateWorker(List<Worker> weekdayWorkers, List<Worker> weekendWorkers) {
        Set<Worker> distinctWeekdayWorkers = new HashSet<>(weekdayWorkers);
        Set<Worker> distinctWeekendWorkers = new HashSet<>(weekendWorkers);

        checkDuplicate(weekdayWorkers, weekendWorkers, distinctWeekdayWorkers, distinctWeekendWorkers);
        compareWeekdayAndWeekend(distinctWeekdayWorkers, distinctWeekendWorkers);
        checkWorkerCount(distinctWeekdayWorkers.size() > 35);
    }

    private static void checkDuplicate(List<Worker> weekdayWorkers,
                                       List<Worker> weekendWorkers,
                                       Set<Worker> distinctWeekdayWorkers,
                                       Set<Worker> distinctWeekendWorkers

    ) {
        if (weekdayWorkers.size() != distinctWeekdayWorkers.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }

        if (weekendWorkers.size() != distinctWeekendWorkers.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void compareWeekdayAndWeekend(Set<Worker> distinctWeekdayWorkers,
                                                 Set<Worker> distinctWeekendWorkers) {
        if (distinctWeekdayWorkers.equals(distinctWeekendWorkers)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }

    private static void checkWorkerCount(boolean distinctWeekdayWorkers) {
        if (distinctWeekdayWorkers) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }
}
