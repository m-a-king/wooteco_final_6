package oncall;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WorkerSelector {
    private final Set<String> weekdayWorkers = new LinkedHashSet<>();
    private final Set<String> weekendWorkers = new LinkedHashSet<>();
    private int previousWeekdayWorkerIdx = -1;
    private int previousWeekendWorkerIdx = -1;

    public void setWorker(List<String> weekdayWorkers, List<String> weekendWorkers) {
        this.weekdayWorkers.addAll(weekdayWorkers);
        this.weekdayWorkers.addAll(weekendWorkers);
    }

    public String selectWorker(int day, DayOfWeek dayOfWeek, List<Integer> holidays) {
        day ==

        return
    }


}
