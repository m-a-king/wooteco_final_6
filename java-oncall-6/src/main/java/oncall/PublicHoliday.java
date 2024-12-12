package oncall;

public enum PublicHoliday {
    SIN_JUNG(Month.JAN, 1),
    SAM_IL(Month.MAR, 1),
    CHILD(Month.MAY, 5),
    HYUN_CHUNG(Month.JUN, 6),
    GWANG_BOK(Month.AUG, 15),
    GAE_CHUN(Month.OCT, 3),
    HAN_GUL(Month.OCT, 9),
    CHRISTMAS(Month.DEC, 25),
    ;

    private final Month month;
    private final int day;

    PublicHoliday(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static boolean isHoliday(Month month, int day) {
        for (PublicHoliday publicHoliday : PublicHoliday.values()) {
            if (publicHoliday.month == month && publicHoliday.day == day) {
                return true;
            }
        }
        return false;
    }
}