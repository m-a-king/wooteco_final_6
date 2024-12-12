package oncall;

import java.util.ArrayList;
import java.util.List;

public class WorkerSelector {
    public static final String EMPTY = "";
    private final List<Worker> weekdayWorkers = new ArrayList<>();
    private final List<Worker> weekendWorkers = new ArrayList<>();
    private int weekdayWorkerIdx = 0;
    private int weekendWorkerIdx = 0;
    private Worker previousWorker = new Worker(EMPTY);
    private Worker deferredWorker = new Worker(EMPTY);

    public void setWorker(List<Worker> weekdayWorkers, List<Worker> weekendWorkers) {
        this.weekdayWorkers.clear();
        this.weekendWorkers.clear();
        this.weekdayWorkers.addAll(weekdayWorkers);
        this.weekendWorkers.addAll(weekendWorkers);
    }

    public Worker selectWorker(DateInfo dateInfo) {
        Worker currentWorker = selectOrDeferredWorker(dateInfo);

        if (isWorkerSameAsPrevious(currentWorker)) {
            deferredWorker = previousWorker;
            currentWorker = selectWorkerByDayType(dateInfo);
        }

        previousWorker = currentWorker;
        return currentWorker;
    }

    private Worker selectOrDeferredWorker(DateInfo dateInfo) {
        if (!deferredWorker.getName().equals(EMPTY)) {
            return retrieveDeferredWorker();
        }

        return selectWorkerByDayType(dateInfo);
    }

    private Worker selectWorkerByDayType(DateInfo dateInfo) {
        if (dateInfo.isHoliday()) {
            return getNextWeekendWorker();
        }

        return getNextWeekdayWorker();
    }

    private Worker retrieveDeferredWorker() {
        Worker worker = deferredWorker;
        deferredWorker = new Worker(EMPTY);
        return worker;
    }

    private Worker getNextWeekdayWorker() {
        weekdayWorkerIdx = weekdayWorkerIdx % weekdayWorkers.size();
        return weekdayWorkers.get(weekdayWorkerIdx++);
    }

    private Worker getNextWeekendWorker() {
        weekendWorkerIdx = weekendWorkerIdx % weekendWorkers.size();
        return weekendWorkers.get(weekendWorkerIdx++);
    }

    private boolean isWorkerSameAsPrevious(Worker currentWorker) {
        return currentWorker.getName().equals(previousWorker.getName());
    }
}