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


    public double printTimingList(int pattern)
    {
        double totalTargetTime = 0;

        for (int i = 0; i < timingList.size(); i++)
        {
            if (timingList.size() > 1)
            {
                System.out.println("Pattern: " + pattern + " - Target: " + (i + 1) + " - Time: " + timingList.get(i));

                totalTargetTime += timingList.get(i);
            }
            else
            {
                System.out.println("Pattern: " + pattern + " - Total Pattern Time: " + timingList.get(i));
            }
        }

        if (totalTargetTime > 0)
        {
            clearTimingList();

            System.out.println("Pattern: " + pattern + " - Total Target Time: " + totalTargetTime);
            return totalTargetTime;
        }
        else
        {
            double totalPatternTime = timingList.get(0);

            clearTimingList();

            return totalPatternTime;
        }
    }


    ///////////////////////////////////////////////////////////////////////////

}
