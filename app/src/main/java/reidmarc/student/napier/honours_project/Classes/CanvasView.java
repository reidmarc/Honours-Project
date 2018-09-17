package reidmarc.student.napier.honours_project.Classes;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class CanvasView extends View
{
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint userLine;
    private float mX, mY;
    private static final float TARGET_TOLERANCE = 10;
    private static final float START_TOLERANCE = 20;
    private static final float TOLERANCE = 1;
    private Context context;

    // Draw the dividing line
    private Paint dividingLinePaint;
    private final int lineStartX = 0;
    private final int lineStartY = 275;
    private final int lineStopX = 1200;
    private final int lineStopY = 275;

    // Draw the starting circle
    private Paint startCirclePaint;
    private final int startingPointX = 11;
    private final int startingPointY = 100;
    private final int startingCircleRadius = 10;

    // Draw the ending circle
    private Paint endCirclePaint;
    private final int endPointX = 1189;
    private final int endPointY = 100;
    private final int endCircleRadius = 10;
    private boolean isTheLastTarget = false;

    // Draw the target circles
    private Paint targetCirclePaint;
    private int targetCircleRadius = 5;

    // Arraylist to store co-ords
    private ArrayList<Float> xList = new ArrayList<>();
    private ArrayList<Float> yList = new ArrayList<>();

    private boolean hasStarted = false;
    private boolean hasFinished = false;


    // Related to storing and retrieving patterns
    private ArrayList<int[][]> patternList = new ArrayList<>();




    private int targetCounter = 0;
    private int patternCounter = 0;


    // Related to timing

    //private ArrayList<Double> timingList = new ArrayList<>();
    private Timing patternTiming;
    private Timing targetTiming;
    private Timing pauseTiming;

    private boolean targetTimingStarted;


    public CanvasView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;

        getPatterns();

        mPath = new Path();


        userLine = new Paint();
        userLine.setAntiAlias(true);
        userLine.setColor(Color.BLACK);
        userLine.setStyle(Paint.Style.STROKE);
        userLine.setStrokeJoin(Paint.Join.ROUND);
        userLine.setStrokeWidth(4f);

        dividingLinePaint = new Paint();
        dividingLinePaint.setColor(Color.DKGRAY);
        dividingLinePaint.setStrokeWidth(150f);

        startCirclePaint = new Paint();
        startCirclePaint.setColor(Color.BLUE);

        endCirclePaint = new Paint();
        endCirclePaint.setColor(Color.BLUE);

        targetCirclePaint = new Paint();
        targetCirclePaint.setColor(Color.RED);

        patternTiming = new Timing();
        targetTiming = new Timing();
        pauseTiming = new Timing();


    }


    private void getPatterns()
    {
        // --------------------- PRACTICE PATTERNS ---------------------
        int[][] patternOne;
        int[][] patternTwo;

        Pattern pattern1 = new Pattern(1, 75, 10, 225, 190, 375, 50, 525, 150, 675, 10, 825, 190, 975, 75, 1125, 125);
        Pattern pattern2 = new Pattern(2,75, 190, 225, 10, 375, 150, 525, 50, 675, 190, 825, 10, 975, 125, 1125, 75 );

        patternOne = pattern1.getArrayOfCoords();
        patternTwo = pattern2.getArrayOfCoords();

        patternList.add(patternOne);
        patternList.add(patternTwo);



        // --------------------- 9 DOT PATTERNS ---------------------
        int[][] patternThree;
        int[][] patternFour;

        Pattern pattern3 = new Pattern(3,175, 10, 225, 125, 375, 190, 425, 130, 675, 100, 925, 170, 975, 10, 1125, 140 );
        Pattern pattern4 = new Pattern(4,175, 190, 225, 75, 375, 10, 425, 70, 675, 100, 925, 30, 975, 190, 1125, 60 );

        patternThree = pattern3.getArrayOfCoords();
        patternFour = pattern4.getArrayOfCoords();

        patternList.add(patternThree);
        patternList.add(patternFour);



        // --------------------- 15 DOT PATTERNS ---------------------
        int[][] patternFive;
        int[][] patternSix;

        Pattern pattern5 = new Pattern(5,125, 190, 160, 160, 240, 130, 320, 100, 400, 190, 480, 10, 560, 100, 640, 75 ,750, 125, 800, 125, 830, 190, 960, 100, 1090, 190, 1165, 80 );
        Pattern pattern6 = new Pattern(6,125, 10, 160, 40, 240, 70, 320, 100, 400, 10, 480, 190, 560, 100, 640, 125, 750, 75, 800, 75, 830, 10, 960, 100, 1090, 10, 1165, 120 );

        patternFive = pattern5.getArrayOfCoords();
        patternSix = pattern6.getArrayOfCoords();

        patternList.add(patternFive);
        patternList.add(patternSix);



        // --------------------- 21 DOT PATTERNS ---------------------
        int[][] patternSeven;
        int[][] patternEight;

        Pattern pattern7 = new Pattern(7,130, 100, 150, 50, 180, 10, 230, 50, 370, 10, 410, 100, 450, 190, 470, 170 ,500, 190, 540, 100, 580, 120, 630, 80, 680, 180, 730, 10, 780, 50, 830, 100, 880, 150, 1000, 115, 1060, 140, 1130, 10 );
        Pattern pattern8 = new Pattern(8,130, 100, 150, 150, 180, 190, 230, 150, 370, 180, 410, 100, 450, 10, 470, 30, 500, 10, 540, 100, 580, 80, 630, 120, 680, 20, 730, 190, 780, 150, 830, 100, 880, 50, 1000, 85, 1060, 60, 1130, 180 );

        patternSeven = pattern7.getArrayOfCoords();
        patternEight = pattern8.getArrayOfCoords();

        patternList.add(patternSeven);
        patternList.add(patternEight);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawLine(lineStartX,lineStartY,lineStopX,lineStopY, dividingLinePaint);

        canvas.drawPath(mPath, userLine);

        canvas.drawCircle(startingPointX, (startingPointY + 350), startingCircleRadius, startCirclePaint);

        drawTargetCircles(canvas);
    }

    private void drawTargetCircles(Canvas canvas)
    {
        if (isTheLastTarget)
        {
            canvas.drawCircle(endPointX, endPointY, endCircleRadius, endCirclePaint);
        }
        else
        {
            canvas.drawCircle(patternList.get(patternCounter)[targetCounter][0], patternList.get(patternCounter)[targetCounter][1], targetCircleRadius, targetCirclePaint);
        }

    }





    private void targetCheck(float x, float y)
    {
        float tx = Math.abs(x - patternList.get(patternCounter)[targetCounter][0]);
        float ty = Math.abs(y - patternList.get(patternCounter)[targetCounter][1]);

        if (tx < TARGET_TOLERANCE && ty < TARGET_TOLERANCE)
        {
            targetTiming.addTimeToList(targetTiming.timeDurationSeconds());

            if (targetCounter < patternList.get(patternCounter).length - 1)
            {
                targetTiming.startTiming();

                targetCounter = targetCounter + 1;

                if (targetCounter == patternList.get(patternCounter).length - 1)
                {
                    isTheLastTarget = true;
                }

            }
            else
            {
                patternTiming.addTimeToList(patternTiming.timeDurationSeconds());

                // TESTING
                // targetTiming.printTimingList(patternCounter);
                //


                patternCounter = patternCounter + 1;

                String toastText = "You have completed pattern # " + patternCounter + " of " + patternList.size() + " in " + new DecimalFormat("#.##").format(patternTiming.timeDurationSeconds()) + " seconds.";
                Toast.makeText(this.context, toastText, Toast.LENGTH_LONG).show();

                clearCanvas();
            }

        }
    }

    private void startTouch(float x, float y)
    {
        float sx = Math.abs(x - startingPointX);
        float sy = Math.abs(y - startingPointY);

        if (!hasStarted)
        {
            if (sx < START_TOLERANCE && sy < START_TOLERANCE)
            {
                mPath.moveTo(x, y);
                mX = x;
                mY = y;
                hasStarted = true;

                patternTiming.startTiming();
                targetTiming.startTiming();


            }
            else
            {
                Toast.makeText(this.context, "You must start closer to the blue dot.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }
    }

    private void moveTouch(float x, float y)
    {
        targetCheck(x, y);

        if (hasStarted)
        {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);

            if (dx >= TOLERANCE || dy >= TOLERANCE)
            {
                storeCoordinates(x, y);
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }
    }

    private void clearCanvas()
    {
        writeToConsole();

        if (patternCounter >= patternList.size())
        {
            patternCounter = 0;
        }

        isTheLastTarget = false;
        hasStarted = false;
        targetCounter = 0;
        mX = 0;
        mY = 0;

        mPath.reset();
        invalidate();
    }

    private void upTouch()
    {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY() - 350;

        // Limits the drawable area
        if ( x > 1200 || y > 200 || x < 0 || y < 0)
        {
            return false;
        }
        else
        {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startTouch(x, y);
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    moveTouch(x, y);
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    upTouch();
                    invalidate();
                    break;
            }

            return true;
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TESTING//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    private void writeToConsole()
    {
        /*
        for (int i = 0; i < xList.size(); i++ )
        {
            System.out.println("Co-ordinate: " + ( i + 1 ) + "");
            System.out.println("X co-ordinate: " + xList.get(i) + " for pattern " + patternCounter);
            System.out.println("Y co-ordinate: " + yList.get(i) + " for pattern " + patternCounter);
        }
        */

        compareTimings(targetTiming.printTimingList(patternCounter), patternTiming.printTimingList(patternCounter));

        xList.clear();
        yList.clear();

    }

    private void compareTimings(double totalTargetTime, double totalPattenTime)
    {
        double difference = totalTargetTime - totalPattenTime;

        if (difference < 0.01 && difference > -0.01)
        {
            System.out.println("The timings have passed, with a difference of: " + difference + " seconds.");
        }
        else
        {
            System.out.println("The timings have failed, with a difference of: " + difference + " seconds.");
        }
    }



    private void storeCoordinates(float x, float y)
    {
        xList.add(x);
        yList.add(y);
    }
    ///////////////////////////////////////////////////////////////////////////
}

