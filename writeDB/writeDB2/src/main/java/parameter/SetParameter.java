package parameter;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.Month;
import java.time.format.DateTimeFormatter; // 移除多余的分号
import java.time.temporal.ChronoUnit;
import java.util.Calendar;



public class SetParameter {


    private static int year_now;
    private static int Month_now;
    private static int Date_now;
    private static int Hour_now;

    public SetParameter(int i, int i1, int i2, int i3) {
        year_now=i;
        Month_now=i1;
        Date_now=i2;
        Hour_now=i3;
    }

    public static void  SetTime(int year,int month,int date,int hour)
    {
        year_now = year;    // 获取完整的年份(4位,1970-????)
        Month_now = month;       // 获取当前月份(0-11,0代表1月)
        Date_now = date;
        Hour_now = hour;
    }

    public  int getDate_now() {
        return Date_now;
    }
    public  int getMonth_now() {
        return Month_now;
    }

    public  int getYear_now() {
        return year_now;
    }

    public  int getHour_now() {
        return Hour_now;
    }

    public  long getHour(int year, int month, int date, int hour){
        SetTime(year,month,date,hour);
        LocalDate date1 = LocalDate.of(1900, 1, 1);
        LocalDate date2 = LocalDate.of(year_now,Month_now, Date_now);

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);// 获取当前日(1-31)
        long hourBetween = daysBetween*24+Hour_now;
        return hourBetween;
    }

    public  long getNowHour(){
//        Calendar calendar = Calendar.getInstance();

//        year_now = calendar.get(Calendar.YEAR);    // 获取完整的年份(4位,1970-????)
//        Month_now = calendar.get(Calendar.MONTH);       // 获取当前月份(0-11,0代表1月)
//        Date_now = calendar.get(Calendar.DATE);
//        Hour_now = calendar.get(Calendar.HOUR);

        year_now = 2023;    // 获取完整的年份(4位,1970-????)
        Month_now = 3;       // 获取当前月份(0-11,0代表1月)
        Date_now = 1;
        Hour_now = 0;

        LocalDate date1 = LocalDate.of(1900, 1, 1);
        LocalDate date2 = LocalDate.of(year_now,Month_now, Date_now);

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);// 获取当前日(1-31)
        long hourBetween = daysBetween*24+Hour_now;
        return hourBetween;
    }


    public SetParameter calculatePreviousDate(SetParameter currentDate, int days) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(currentDate.getYear_now(), currentDate.getMonth_now(), currentDate.getDate_now());
        calendar.add(Calendar.DATE, -days);

        int year_prev = calendar.get(Calendar.YEAR);
        int month_prev = calendar.get(Calendar.MONTH);
        int date_prev = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = currentDate.getHour_now();

        return new SetParameter(year_prev, month_prev, date_prev, hour);
    }

    public SetParameter calculateNextDate(SetParameter currentDate, int days) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(currentDate.getYear_now(), currentDate.getMonth_now(), currentDate.getDate_now());
        calendar.add(Calendar.DATE, days);

        int year_next = calendar.get(Calendar.YEAR);
        int month_next = calendar.get(Calendar.MONTH);
        int date_next = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = currentDate.getHour_now();

        return new SetParameter(year_next, month_next, date_next, hour);
    }

    public int calculateDaysDifference(SetParameter date1, SetParameter date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.set(date1.getYear_now(), date1.getMonth_now(), date1.getDate_now());
        calendar2.set(date2.getYear_now(), date2.getMonth_now(), date2.getDate_now());

        long diffInMillis = Math.abs(calendar1.getTimeInMillis() - calendar2.getTimeInMillis());
        long diffDays = diffInMillis / (1000 * 60 * 60 * 24);

        return (int) diffDays;}

    public void main(String[] args) {
        SetParameter today = new SetParameter(2023,3,15,0);
        SetParameter frontday = null;
        frontday = calculatePreviousDate(today,7);

        SetParameter laterday = null;
        laterday = calculateNextDate(today,7);

        int days = calculateDaysDifference(frontday,laterday);
        System.out.println(days);

    }
}

