package oncall;

import static oncall.Exception.INVALID_INPUT;

public enum Month {
    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31),
    ;

    private final int seq;
    private final int endDay;

    Month(int seq, int endDay) {
        this.seq = seq;
        this.endDay = endDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getSeq() {
        return seq;
    }

    public static Month from(int month) {
        for (Month calender : Month.values()) {
            if (calender.getSeq() == month) {
                return calender;
            }
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }

}
