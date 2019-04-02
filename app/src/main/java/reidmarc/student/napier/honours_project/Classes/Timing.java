package reidmarc.student.napier.honours_project.Classes;

import android.os.SystemClock;

import java.util.ArrayList;

public class Timing
{
    private long startTime;

    private ArrayList<Double> timingList = new ArrayList<>();

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
}
