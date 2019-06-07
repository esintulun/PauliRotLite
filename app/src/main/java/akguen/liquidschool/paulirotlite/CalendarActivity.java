package akguen.liquidschool.paulirotlite;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class CalendarActivity extends AppCompatActivity {

    // Define the variable of CalendarView type
    // and TextView type;
    CalendarView calenderView;
    TextView date_view;

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // to get today's date
        Date today = Calendar.getInstance().getTime();
        LocalDate localDateToday = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // By ID we can use each component
        // which id is assign in xml file
        // use findViewById() to get the
        // CalendarView and TextView
        calenderView = (CalendarView) findViewById(R.id.calender);
        date_view = (TextView) findViewById(R.id.date_view);

        // Add Listener in calendar
        calenderView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override

                    // In this Listener have one method
                    // and in this method we will
                    // get the value of DAYS, MONTH, YEARS
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                        // Store the value of date with
                        // format in String type Variable
                        // Add 1 in month because month
                        // index is start with 0
                        String datum = dayOfMonth + "-" + (month + 1) + "-" + year;

                        // set this date in TextView for Display
                        date_view.setText(datum);
                        String datevoncalender = date_view.getText().toString();
                        Log.i("--", " " + datevoncalender);
                        String[] datevoncalenderSplit = datevoncalender.split("-");

                        int yearvonCalender = Integer.parseInt(datevoncalenderSplit[2]);
                        int monatvonCalender= Integer.parseInt(datevoncalenderSplit[1]);
                        int dayvonCalender= Integer.parseInt(datevoncalenderSplit[0]);
                        LocalDate localDateSchulungTag = LocalDate.of(yearvonCalender, monatvonCalender, dayvonCalender);

                        // rechnen in welcher Klasse gehört Schüler

                        calculateKlasse(localDateSchulungTag, localDateToday);

                    }
                });

                //findDaysDiff();
                getCountOfDays("1982-05-25", "1980-09-02");


    }

    private static int findDaysDiff(long unixStartTime, long unixEndTime)
    {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(unixStartTime);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(unixEndTime);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);

        return (int) ((calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/(24 * 60 * 60 * 1000));

    }

    public int getCountOfDays(String createdDate, String expiredDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date createdConvertedDate = null;
        Date todayCovertedDate = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDate);
            todayCovertedDate = dateFormat.parse(expiredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar start = new GregorianCalendar();
        start.setTime(createdConvertedDate);
        Calendar today_ = new GregorianCalendar();
        today_.setTime(todayCovertedDate);

        long diff = today_.getTimeInMillis() - start.getTimeInMillis();

        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(diff);


        System.out.println(calender.get(Calendar.YEAR));
        System.out.println(calender.get(Calendar.MONTH));
        System.out.println(calender.get(Calendar.DAY_OF_MONTH));
        // get Date
        System.out.println(calender.getTime());

        Log.i("count:", " -----    -----------: " );
        Log.i("count:", " --- : "   + calender.get(Calendar.YEAR) + " - "
                                               + calender.get(Calendar.MONTH) + "-"
                                               + calender.get(Calendar.DAY_OF_MONTH) );



        int numOfYear = (int) ((diff / (1000 * 60 * 60 * 24))/365);
        int numOfMonth = numOfYear *  12;
        float dayCount = (float) diff / (1000 * 24 * 60 * 60 );
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));

        int day = (int) (dayCount);
        Log.i("count:", "Jahre: " + numOfYear);
        Log.i("count:", "Monate: " + numOfMonth);
        //Log.i("count:", "Day: " +  numOfDays);
       // Log.i("count:", "Hour: " + hours);

        // Mit TimeUnit

        long days_tu = TimeUnit.MILLISECONDS.toDays(diff);
        long hours_tu = TimeUnit.MILLISECONDS.toHours(diff);
        long min_tu = TimeUnit.MILLISECONDS.toMinutes(diff);
        long sec_tu = TimeUnit.MILLISECONDS.toSeconds(diff);

        Log.i("count:", "Tage: " +  days_tu);
        Log.i("count:", "Stunde: " + hours_tu);
        Log.i("count:", "Minute: " + min_tu);

        // Zwischen 2 Datum mit year, mounth, day

        LocalDate firstDate = LocalDate.of(1980, Month.SEPTEMBER, 02);
        LocalDate secondDate = LocalDate.of(1982, Month.MAY, 25);


        long years = firstDate.until(secondDate, ChronoUnit.YEARS);
        long month = firstDate.until(secondDate, ChronoUnit.MONTHS);
        long days = firstDate.until(secondDate, ChronoUnit.DAYS);


        Period period = firstDate.until(secondDate);


        //Log.i("count:", "YEAR-MOUNTH-DAYS : " + years +  "- " + month + "- " + days);
        Log.i("count :", "YEAR-MOUNTH-DAYS : " + period.getYears() + " -- " + period.getMonths()+ " -- "  + period.getDays());

        // Zwischen 2 Datum mit year, mounth, day, hour, minute, second
        LocalDateTime fromDateTime = LocalDateTime.of(1980, Month.SEPTEMBER, 02, 04, 55, 10 );
        LocalDateTime toDateTime = LocalDateTime.of(1982, Month.MAY, 25, 13, 22, 10);

        Period period_ = getPeriod(fromDateTime, toDateTime);
        long time[] = getTimeFromTo(fromDateTime, toDateTime); //???

        Log.i("count :", "  ****    YEAR-MOUNTH-DAYS-hour-minute-seconds : " +
                period.getYears() + " years " +
                period_.getMonths() + " months " +
                period_.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.");

        return day;

    }


    private static Period getPeriod(LocalDateTime von, LocalDateTime bis ) {
        return Period.between(von.toLocalDate(), bis.toLocalDate());
    }

    private static long[] getTimeFromTo(LocalDateTime dob, LocalDateTime now) {

        LocalDateTime today = LocalDateTime.of(now.getYear(),
                                               now.getMonthValue(),
                                               now.getDayOfMonth(),
                                                dob.getHour(),
                                                dob.getMinute(),
                                                dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();
        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }

    public  int calculateKlasse(LocalDate einschulungsDatum, LocalDate currentDate) {

        int year = 0;
        int month= 0;
        int day = 0;

        if ((einschulungsDatum != null) && (currentDate != null)) {

            //SchulBeginnDate = LocalDate.of(2018, 8, 7);

            year = Period.between(einschulungsDatum, currentDate).getYears() + 5; // Beginn ist von 5 Klasse

            month = Period.between(einschulungsDatum, currentDate).getMonths();
            day = Period.between(einschulungsDatum, currentDate).getDays();
           // Log.i("Schulgang:", "month - dayss " +  einschulungsmonth + " - " +  "- " +  einschulungsday );


           // LocalDate refDate = LocalDate.of(2018, 8, 7);

           // einschulungsDatum.isBefore(refDate);

            int monthvalue = einschulungsDatum.getMonth().getValue();
            int dayvalue = einschulungsDatum.getDayOfMonth();
            Log.i("Schulgang:", "monat  - tag  " +  monthvalue + " - " +  "- " +  dayvalue );


            LocalDate schulungStart = LocalDate.parse("2018-08-07");
            LocalDate date1 = LocalDate.parse("2017-03-03");
            System.out.println(date1.isBefore(schulungStart));

            if(einschulungsDatum.isBefore(schulungStart)) {

                if (monthvalue < 8 || dayvalue < 7) {

                    year = year + 1;
                    Log.i("Schulgang:", "Klasse >:   " + year);
                }

            }

            Log.i("Schulgang:", "- SinschulungsDatum:  " +  einschulungsDatum + " Klasse-> " + year);

            return year;


        } else {
            return 0;
        }
    }




}
