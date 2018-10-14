package reidmarc.student.napier.honours_project.Classes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Today
{
    private Calendar now;
    private Date sqlDay;

    private int year;
    private int month;
    private int day;
    private String monthAbbr;
    private int hour;
    private int minute;
    private int second;


    public Today()
    {
        String [] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        now = new GregorianCalendar();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH) + 1;
        day = now.get(Calendar.DATE);
        monthAbbr = months[month - 1];
        sqlDay = new Date(System.currentTimeMillis());
        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        second = now.get(Calendar.SECOND);

    }


    public String getAbbrTodayAndTime()
    {
        return  "[" + day + ", " + month + ", " + year + " - " + hour + ":" + minute + ":" + second + "]";
    }

    public String getAbbrToday()
    {
        return  "[" + day + ", " + monthAbbr + ", " + year + "]";
    }
}
