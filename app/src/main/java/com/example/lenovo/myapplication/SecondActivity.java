package com.example.lenovo.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Lenovo on 2017/11/28.
 */

public class SecondActivity extends AppCompatActivity {

    private ImageView img;
    private Bitmap mBitmap;
    private Canvas canvas;
    private Paint paint;
    // 重置按钮
    private Button reset_btn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        img = (ImageView) findViewById(R.id.img);

        reset_btn = (Button) findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                img.setImageBitmap(null);
                showImage();
            }
        });
        // 绘图
        showImage();

    }

    private void showImage() {
        // 创建一张空白图片
        mBitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        // 创建一张画布
        canvas = new Canvas(mBitmap);
        // 画布背景为白色
        canvas.drawColor(Color.WHITE);
        // 创建画笔
        paint = new Paint();
        // 画笔颜色为蓝色
        paint.setColor(Color.BLUE);
        // 宽度5个像素
        paint.setStrokeWidth(5);
        // 先将白色背景画上
        canvas.drawBitmap(mBitmap, new Matrix(), paint);
        img.setImageBitmap(mBitmap);

        img.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取手按下时的坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取手移动后的坐标
                        int endX = (int) event.getX();
                        int endY = (int) event.getY();
                        // 在开始和结束坐标间画一条线
                        canvas.drawLine(startX, startY, endX, endY, paint);
                        // 刷新开始坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        img.setImageBitmap(mBitmap);
                        break;
                }
                return true;
            }
        });

    }

}