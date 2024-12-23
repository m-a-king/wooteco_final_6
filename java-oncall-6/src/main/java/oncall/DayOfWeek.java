package oncall;

import static oncall.Exception.INVALID_INPUT;

public enum DayOfWeek {
    MON(1, "월"),
    TUE(2, "화"),
    WED(3, "수"),
    THU(4, "목"),
    FRI(5, "금"),
    SAT(6, "토"),
    SUN(7, "일");

    public static final int DAYS_OF_WEEK = 7;
    private final int seq;
    private final String korean;

    DayOfWeek(int seq, String korean) {
        this.seq = seq;
        this.korean = korean;
    }

    public int getSeq() {
        return seq;
    }

    public String getKorean() {
        return korean;
    }

    public static DayOfWeek from(String korean) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.korean.equals(korean)) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }

    public DayOfWeek getNextDayOfWeek() {
        int targetIdx = (this.seq) % DAYS_OF_WEEK;
        return DayOfWeek.values()[targetIdx];
    }
}