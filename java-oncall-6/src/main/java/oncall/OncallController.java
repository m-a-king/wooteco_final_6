package oncall;

import java.util.Arrays;
import java.util.List;

import static oncall.InputHandler.printMessageAndReadSingleLine;
import static oncall.SplitUtil.splitByDelimiter;
import static oncall.Validator.validateWorker;

public class OncallController {

    private final WorkerSelector workerSelector;

    public OncallController(WorkerSelector workerSelector) {
        this.workerSelector = workerSelector;
    }

    public void run() {
        String[] monthAndDayOfWeek = splitByDelimiter(
                printMessageAndReadSingleLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> "));

        Month currentMonth = Month.from(Integer.parseInt(monthAndDayOfWeek[0]));
        DayOfWeek startDayOfWeek = DayOfWeek.from(monthAndDayOfWeek[1]);
        DateInfo dateInfo = new DateInfo(currentMonth, startDayOfWeek);

        while (true) {
            List<String> weekdayWorkers = Arrays.asList(splitByDelimiter(
                    printMessageAndReadSingleLine("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")));
            List<String> weekendWorkers = Arrays.asList(splitByDelimiter(
                    printMessageAndReadSingleLine("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")));

            if (validateWorker(weekdayWorkers, weekendWorkers)) {
                workerSelector.setWorker(weekdayWorkers, weekendWorkers);
                break;
            }
        }

        buildOncall(currentMonth, startDayOfWeek, weekdayWorkers, weekendWorkers);
    }

    private void buildOncall(Month currentMonth, DayOfWeek startDayOfWeek, List<String> weekdayWorkers, List<String> weekendWorkers) {

        StringBuilder oncallResult = new StringBuilder();

        for (int dayCount = 1; dayCount <= currentMonth.getEndDay(); dayCount++) {
            DayOfWeek dayOfWeek = calcDayOfWeek(startDayOfWeek, dayCount);

            String worker = workerSelector.selectWorker(dayOfWeek, holidays);

            oncallResult.append(String.format("%d월 ", currentMonth.getSeq()));
            oncallResult.append(String.format("%d일 ", dayCount));
            oncallResult.append(String.format("%s ", dayOfWeek.getKorean()));
            oncallResult.append(String.format("%s\n", dayCount));
        }
    }

    private static DayOfWeek calcDayOfWeek(DayOfWeek startDayOfWeek, int day) {
        return startDayOfWeek.getDayAfter(day);
    }

    private static String calcWorker(int dayCount, DayOfWeek dayOfWeek, String previousWorker, List<Integer> holidays) {

    }
}
