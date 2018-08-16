package reidmarc.student.napier.honoursproject;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class CanvasView extends View
{
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 1;
    private static final float START_TOLERANCE = 10;
    private Context context;

    // Draw the dividing line
    private Paint linePaint;
    private int lineStartX = 0;
    private int lineStartY = 215;
    private int lineStopX = 1200;
    private int lineStopY = 215;

    // Draw the circles
    private Paint circlePaint;
    private int startingPointX = 0;
    private int startingPointY = 100;
    private int circleRadius = 10;


    // Arraylist to store co ords
    private ArrayList<Float> xList = new ArrayList<>();
    private ArrayList<Float> yList = new ArrayList<>();

    private boolean readyToStart = false;






    public CanvasView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);




        linePaint = new Paint();
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(30f);

        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);



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

        canvas.drawLine(lineStartX,lineStartY,lineStopX,lineStopY,linePaint);

        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(startingPointX, (startingPointY + 230), circleRadius, circlePaint);

    }

    private void startTouch(float x, float y)
    {
        // float sx = Math.abs(x - startingPointX);
        // float sy = Math.abs(y - startingPointY);



        //if (!readyToStart)
        // {
        //  if (sx < START_TOLERANCE && sy < START_TOLERANCE)
        //  {
                mPath.moveTo(x, y);
                mX = x;
                mY = y;
                readyToStart = true;
        //     }
        //   else
        //   {
        //        Toast.makeText(this.context, "You must start closer to the red dot.", Toast.LENGTH_SHORT).show();
        //    }
        //   }
        //   else
        {
            //mPath.moveTo(x, y);
            //mX = x;
            // mY = y;
        }

    }

    private void moveTouch(float x, float y)
    {
        //if (readyToStart)
        // {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOLERANCE || dy >= TOLERANCE)
        {
            storeCoordinates(x, y);
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        // }
    }

    private void storeCoordinates(float x, float y)
    {
        xList.add(x);
        yList.add(y);
    }

    //////////////////TESTING/////////////////////

    private void writeToConsole()
    {
        for (int i = 0; i < xList.size(); i++ )
        {

            System.out.println("X co-ordinate: " + xList.get(i));
            System.out.println("Y co-ordinate: " + yList.get(i));
        }

    }

    /////////////////////////////////////////////

    public void clearCanvas()
    {
        readyToStart = false;
        writeToConsole();
        mPath.reset();
        invalidate();
    }

    private void upTouch()
    {
        //if (readyToStart)
        // {
        mPath.lineTo(mX, mY);
        // }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY() - 230;

        switch(event.getAction())
        {
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

