package oncall;

import java.util.Arrays;
import java.util.List;

import static oncall.InputHandler.printMessageAndReadSingleLine;
import static oncall.SplitUtil.splitByDelimiter;
import static oncall.Validator.validateMonthAndDayOfWeek;
import static oncall.Validator.validateWorker;

public class OncallController {

    private final WorkerSelector workerSelector;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public OncallController(WorkerSelector workerSelector) {
        this.workerSelector = workerSelector;
    }

    public void run() {
        DateInfo dateInfo = promptDateInfo();

        WorkerPair workers = promptWorkerPair();

        workerSelector.setWorker(workers.weekdayWorkers(), workers.weekendWorkers());

        System.out.println(generateOncallSchedule(dateInfo)); // 아웃풋 핸들러에게 넘겨도 괜찮을 듯
    }

    private DateInfo promptDateInfo() {
        return repeatUntilValid(() -> {
            String[] monthAndDayOfWeek = splitByDelimiter(
                    printMessageAndReadSingleLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> "));
            validateMonthAndDayOfWeek(monthAndDayOfWeek);

            Month currentMonth = Month.from(Integer.parseInt(monthAndDayOfWeek[0]));
            DayOfWeek startDayOfWeek = DayOfWeek.from(monthAndDayOfWeek[1]);
            return new DateInfo(currentMonth, startDayOfWeek);
        });
    }

    private WorkerPair promptWorkerPair() {
        return repeatUntilValid(() -> {
            List<Worker> weekdayWorkers = Arrays.stream(
                            splitByDelimiter(printMessageAndReadSingleLine("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")))
                    .map(Worker::new)
                    .toList();

            List<Worker> weekendWorkers = Arrays.stream(
                            splitByDelimiter(printMessageAndReadSingleLine("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")))
                    .map(Worker::new)
                    .toList();

            validateWorker(weekdayWorkers, weekendWorkers);
            return new WorkerPair(weekdayWorkers, weekendWorkers);
        });
    }

    private String generateOncallSchedule(DateInfo dateInfo) {
        StringBuilder oncallResult = new StringBuilder();

        for (int dayCount = 1; dayCount <= dateInfo.getMonth().getEndDay(); dayCount++) {
            Worker worker = workerSelector.selectWorker(dateInfo);

            oncallResult.append(String.format("%d월 ", dateInfo.getMonth().getSeq()));
            oncallResult.append(String.format("%d일 ", dayCount));
            oncallResult.append(String.format("%s", dateInfo.getDayOfWeek().getKorean()));
            oncallResult.append(String.format("%s", dateInfo.isPublicHoliday() ? "(휴일)" : ""));
            oncallResult.append(String.format(" %s", worker.getName()));
            oncallResult.append(LINE_SEPARATOR);

            if (dateInfo.getMonth().getEndDay() > dayCount) {
                dateInfo = dateInfo.nextDay();
            }
        }

        return oncallResult.toString();
    }

    private <T> T repeatUntilValid(InputProvider<T> provider) {
        while (true) {
            try {
                return provider.get(); // 동작 실행
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 에러 메시지 출력
            }
        }
    }

    @FunctionalInterface
    interface InputProvider<T> {
        T get() throws IllegalArgumentException; // 예외를 던질 수 있는 추상 메서드
    }

}