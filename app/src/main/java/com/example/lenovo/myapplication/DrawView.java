package com.example.lenovo.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Lenovo on 2017/11/21.
 */

public class DrawView extends View {
    private Path mPath;

    private Paint mPaint;

    private boolean mRun;

    private Handler handler;

    public DrawView(Context context, Handler handler) {
        super(context);
        this.handler = handler;

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        mRun = true;

        new Thread(controlRunnable).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
        }

        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRun = false;
    }

    private Runnable controlRunnable = new Runnable() {
        @Override
        public void run() {
            while (mRun) {
                handler.post(mUpdateUIRunnable);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Runnable mUpdateUIRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
}
