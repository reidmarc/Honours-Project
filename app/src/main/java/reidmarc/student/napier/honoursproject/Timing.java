package reidmarc.student.napier.honoursproject;

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

}
