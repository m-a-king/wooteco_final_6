package oncall;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

class ApplicationTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            run("0,일",
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 기능_테스트() {
        assertSimpleTest(() -> {
            run(
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR,
                    "4월 6일 목 라온" + LINE_SEPARATOR,
                    "4월 7일 금 헤나" + LINE_SEPARATOR,
                    "4월 8일 토 애쉬" + LINE_SEPARATOR,
                    "4월 9일 일 푸만능" + LINE_SEPARATOR,
                    "4월 10일 월 우코" + LINE_SEPARATOR,
                    "4월 11일 화 에단" + LINE_SEPARATOR,
                    "4월 12일 수 수달" + LINE_SEPARATOR,
                    "4월 13일 목 파워" + LINE_SEPARATOR,
                    "4월 14일 금 히이로" + LINE_SEPARATOR,
                    "4월 15일 토 우가" + LINE_SEPARATOR,
                    "4월 16일 일 아이크" + LINE_SEPARATOR,
                    "4월 17일 월 마코" + LINE_SEPARATOR,
                    "4월 18일 화 슬링키" + LINE_SEPARATOR,
                    "4월 19일 수 모디" + LINE_SEPARATOR,
                    "4월 20일 목 연어" + LINE_SEPARATOR,
                    "4월 21일 금 깃짱" + LINE_SEPARATOR,
                    "4월 22일 토 첵스" + LINE_SEPARATOR,
                    "4월 23일 일 로지" + LINE_SEPARATOR,
                    "4월 24일 월 리오" + LINE_SEPARATOR,
                    "4월 25일 화 고니" + LINE_SEPARATOR,
                    "4월 26일 수 박스터" + LINE_SEPARATOR,
                    "4월 27일 목 달리" + LINE_SEPARATOR,
                    "4월 28일 금 조이" + LINE_SEPARATOR,
                    "4월 29일 토 해시" + LINE_SEPARATOR,
                    "4월 30일 일 폴로"
            );
        });
    }


    @Test
    void 닉네임_중복_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍,도밥,고니,수아,수아", // 중복 닉네임
                    "도밥,고니,루루,글로,솔로스타"
            );
            assertThat(output()).contains("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 닉네임_길이_초과_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍12345,도밥,고니,수아,루루", // 5글자 초과 닉네임
                    "도밥,고니,루루,글로,솔로스타"
            );
            assertThat(output()).contains("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 근무자_수_초과_테스트() {
        assertSimpleTest(() -> {
            String weekdayWorkers = String.join(",", List.of(
                    "준팍", "도밥", "고니", "수아", "루루", "글로", "솔로스타", "우코", "슬링키",
                    "참새", "도리", "준팍2", "도밥2", "고니2", "수아2", "루루2", "글로2",
                    "솔로스타2", "우코2", "슬링키2", "참새2", "도리2", "준팍3", "도밥3",
                    "고니3", "수아3", "루루3", "글로3", "솔로스타3", "우코3", "슬링키3",
                    "참새3", "도리3", "추가"
            )); // 36명

            String weekendWorkers = String.join(",", List.of(
                    "준팍2", "도밥", "고니", "수아", "루루", "글로", "솔로스타", "우코", "슬링키",
                    "참새", "도리", "준팍1", "도밥2", "고니2", "수아2", "루루2", "글로2",
                    "솔로스타2", "우코2", "슬링키2", "참새2", "도리2", "준팍3", "도밥3",
                    "고니3", "수아3", "루루3", "글로3", "솔로스타3", "우코3", "슬링키3",
                    "참새3", "도리3", "추가"
            )); // 36명

            run(
                    "5,월",
                    weekdayWorkers,
                    weekendWorkers
            );
            assertThat(output()).contains("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 근무자_수_부족_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍,도밥,고니", // 5명 미만
                    "도밥,고니,루루,글로,솔로스타"
            );
            assertThat(output()).contains("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 연속_근무_배정_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,토",
                    "도밥,준팍,고니,수아,루루", // 평일 순번
                    "준팍,도밥,고니,수아,루루"  // 휴일 순번
            );
            assertThat(output()).doesNotContain("[ERROR]").contains(
                    "5월 1일 토 준팍",
                    "5월 2일 일 도밥",
                    "5월 3일 월 준팍", // 당겨진
                    "5월 4일 화 도밥", // 미뤄진
                    "5월 5일 수(휴일) 고니",
                    "5월 6일 목 수아", // 당겨진
                    "5월 7일 금 고니", // 미뤄진
                    "5월 8일 토 수아", // 정상 근무
                    "5월 9일 일 루루"
            );
        });
    }

    @Test
    void 법정공휴일_연속_근무_테스트() {
        assertSimpleTest(() -> {
            run(
                    "10,월", // 개천절 시작
                    "준팍,도밥,글로,수아,루루",
                    "수아,루루,글로,도밥,준팍"
            );
            assertThat(output()).doesNotContain("[ERROR]").contains(
                    "10월 1일 월 준팍",
                    "10월 2일 화 도밥",
                    "10월 3일 수(휴일) 수아",
                    "10월 4일 목 글로",
                    "10월 5일 금 수아",
                    "10월 6일 토 루루",
                    "10월 7일 일 글로",
                    "10월 8일 월 루루",
                    "10월 9일 화(휴일) 도밥"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
