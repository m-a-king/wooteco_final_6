package oncall;

import java.util.List;

public record WorkerPair(List<Worker> weekdayWorkers,
                         List<Worker> weekendWorkers) {
}
