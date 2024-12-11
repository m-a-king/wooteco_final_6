package oncall;

public final class DateInfo {
    private final Month month;
    private int day;
    private DayOfWeek dayOfWeek;

    public DateInfo(
            Month month,
            DayOfWeek startDayOfWeek
    ) {
        this.month = month;
        this.day = 1;
        this.dayOfWeek = startDayOfWeek;
    }

    public boolean isHoliday() {
        return Holiday.isHoliday(this.month, this.day);
    }

    public

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

}
