package reidmarc.student.napier.honours_project.Classes;

import android.os.SystemClock;

import java.util.ArrayList;

public class Timing
{
    private long startTime;

    private ArrayList<Double> timingList = new ArrayList<>();

    public ArrayList<Double> getTimingList()
    {
        return timingList;
    }



    public void clearTimingList()
    {
        timingList.clear();
    }

    public void addTimeToList(double time)
    {
        timingList.add(time);
    }

    public void startTiming()
    {
        startTime = SystemClock.elapsedRealtimeNanos();

    }

    public double timeDurationSeconds()
    {
        long timeDuration = SystemClock.elapsedRealtimeNanos() - startTime;
        return ((double)timeDuration / 1000000000);

    }

    public double timeDurationMilliSeconds()
    {
        long timeDuration = SystemClock.elapsedRealtimeNanos() - startTime;
        return ((double)timeDuration / 1000000);

    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TESTING//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void printTimingList(int pattern)
    {
        for (int i = 0; i < timingList.size(); i++)
        {
            System.out.println("Pattern: " + ( pattern + 1 ) + " - Target: " + ( i + 1 ) + " - Time: " + timingList.get( i ));
        }
        clearTimingList();
    }

    ///////////////////////////////////////////////////////////////////////////

}
