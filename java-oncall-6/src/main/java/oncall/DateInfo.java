package oncall;

public final class DateInfo {
    private final Month month;
    private final int day;
    private final DayOfWeek dayOfWeek;

    public DateInfo(
            Month month,
            int day,
            DayOfWeek startDayOfWeek
    ) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = startDayOfWeek;
    }

    public DateInfo(
            Month month,
            DayOfWeek startDayOfWeek
    ) {
        this.month = month;
        this.day = 1;
        this.dayOfWeek = startDayOfWeek;
    }

    public boolean isHoliday() {
        return isPublicHoliday() || isWeekend();
    }

    public boolean isPublicHoliday() {
        return PublicHoliday.isHoliday(this.month, this.day);
    }

    public DateInfo nextDay() {
        if (this.day >= month.getEndDay()) {
            throw new IllegalStateException();
        }
        DayOfWeek nextDayOfWeek = dayOfWeek.getNextDayOfWeek();
        int nextDay = day + 1;
        return new DateInfo(month, nextDay, nextDayOfWeek);
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    private boolean isWeekend() {
        return this.dayOfWeek == DayOfWeek.SAT || this.dayOfWeek == DayOfWeek.SUN;
    }

}
