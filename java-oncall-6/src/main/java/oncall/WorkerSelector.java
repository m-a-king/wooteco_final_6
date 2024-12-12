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
        Worker currentWorker = getCurrentUser(dateInfo);

        if (isSameAsPreviousWorker(currentWorker)) {
            deferredWorker = previousWorker;
            currentWorker = selectWeekendWorker();
        }

        previousWorker = currentWorker;
        return currentWorker;
    }

    private Worker getCurrentUser(DateInfo dateInfo) {
        if (!deferredWorker.getName().equals(EMPTY)) {
            return assignDeferredWorker();
        }

        if (dateInfo.isHoliday()) {
            return selectWeekendWorker();
        }

        return selectWeekdayWorker();
    }

    private Worker assignDeferredWorker() {
        Worker worker = deferredWorker;
        deferredWorker = new Worker(EMPTY);
        return worker;
    }

    private Worker selectWeekdayWorker() {
        weekdayWorkerIdx = weekdayWorkerIdx % weekdayWorkers.size();
        return weekdayWorkers.get(weekdayWorkerIdx++);
    }

    private Worker selectWeekendWorker() {
        weekendWorkerIdx = weekendWorkerIdx % weekendWorkers.size();
        return weekendWorkers.get(weekendWorkerIdx++);
    }

    private boolean isSameAsPreviousWorker(Worker currentWorker) {
        return currentWorker.getName().equals(previousWorker.getName());
    }

}