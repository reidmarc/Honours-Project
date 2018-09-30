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
    private long time;


    public Today()
    {
        String [] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        now = new GregorianCalendar();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH) + 1;
        day = now.get(Calendar.DATE);
        monthAbbr = months[month - 1];
        sqlDay = new Date(System.currentTimeMillis());
        time = System.currentTimeMillis();

    }


    public String getAbbrTodayAndTime()
    {
        return  "[" + day + ", " + month + ", " + year + " - " + time + "]";
    }

    public String getToday()
    {
        return "[" + year + ", " + month + ", " + day + ", " + monthAbbr + ", " + sqlDay + "]";
    }


    public String getAbbrToday()
    {
        return  "[" + day + ", " + monthAbbr + ", " + year + "]";
    }
}
