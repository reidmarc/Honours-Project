package reidmarc.student.napier.honoursproject;

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
    private static final float START_TOLERANCE = 15;
    private static final float TOLERANCE = 2;
    private Context context;

    // Draw the dividing line
    private Paint dividingLinePaint;
    private final int lineStartX = 0;
    private final int lineStartY = 215;
    private final int lineStopX = 1200;
    private final int lineStopY = 215;

    // Draw the starting circle
    private Paint startCirclePaint;
    private final int startingPointX = 0;
    private final int startingPointY = 100;
    private final int startingCircleRadius = 10;

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
    private int[][] patternOne;
    private int[][] patternTwo;
    private int[][] patternThree;
    private int targetCounter = 0;
    private int patternCounter = 0;


    // Related to timing

    //private ArrayList<Double> timingList = new ArrayList<>();
    private Timing patternTiming;


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
        dividingLinePaint.setStrokeWidth(30f);

        startCirclePaint = new Paint();
        startCirclePaint.setColor(Color.BLUE);

        targetCirclePaint = new Paint();
        targetCirclePaint.setColor(Color.RED);

        patternTiming = new Timing();


    }


    private void getPatterns()
    {
        Pattern firstPattern = new Pattern(1, 50, 55, 250, 190, 400, 10, 550, 190, 700, 10, 850, 190, 1000, 10, 1200, 100);
        Pattern secondPattern = new Pattern(2,50, 155, 90, 20, 160, 100, 190, 190, 250, 170, 350, 25, 500, 175, 1200, 5 );
        Pattern thirdPattern = new Pattern(3,100, 100, 250, 100, 400, 100, 550, 100, 700, 100, 850, 100, 1000, 100, 1200, 100 );


        patternOne = firstPattern.getArrayOfCoords();
        patternTwo = secondPattern.getArrayOfCoords();
        patternThree = thirdPattern.getArrayOfCoords();

        patternList.add(patternOne);
        patternList.add(patternTwo);
        patternList.add(patternThree);

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

        canvas.drawCircle(startingPointX, (startingPointY + 230), startingCircleRadius, startCirclePaint);

        drawTargetCircles(canvas);


    }

    private void drawTargetCircles(Canvas canvas)
    {
        canvas.drawCircle(patternList.get(patternCounter)[targetCounter][0], patternList.get(patternCounter)[targetCounter][1], targetCircleRadius, targetCirclePaint);
    }





    private void targetCheck(float x, float y)
    {
        float tx = Math.abs(x - patternList.get(patternCounter)[targetCounter][0]);
        float ty = Math.abs(y - patternList.get(patternCounter)[targetCounter][1]);

        if (tx < TARGET_TOLERANCE && ty < TARGET_TOLERANCE)
        {
            if (targetCounter < patternList.get(patternCounter).length - 1)
            {
                targetCounter = targetCounter + 1;
            }
            else
            {
                patternTiming.addTimeToList(patternTiming.timeDurationSeconds());


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

                //startingTimer = SystemClock.elapsedRealtimeNanos();
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

    public void clearCanvas()
    {
        writeToConsole();

        if (patternCounter >= patternList.size())
        {
            patternCounter = 0;
        }

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
        float y = event.getY() - 230;

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




    //////////////////TESTING/////////////////////

    private void writeToConsole()
    {
        for (int i = 0; i < xList.size(); i++ )
        {
            System.out.println("X co-ordinate: " + xList.get(i) + " for pattern " + patternCounter);
            System.out.println("Y co-ordinate: " + yList.get(i) + " for pattern " + patternCounter);
        }



        for (int i = 0; i < patternTiming.getTimingList().size(); i++ )
        {
            System.out.println("Total time elapsed: " + patternTiming.getTimingList().get(i) + " for pattern " + patternCounter);
        }
         patternTiming.clearTimingList();


        xList.clear();
        yList.clear();

    }


    private void storeCoordinates(float x, float y)
    {
        xList.add(x);
        yList.add(y);
    }
    /////////////////////////////////////////////
}

