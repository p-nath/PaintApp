package com.example.artemis.paintapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemis on 03/06/16.
 */

public class PaintView extends View {
    private Paint paint = new Paint();
    private List<Path> PathList = new ArrayList<>();
    private int color_change = 0;
    private List <Integer> PaintVal = new ArrayList<>();

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        PathList.add(new Path());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GREEN);
        PaintVal.add(paint.getColor());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i <= color_change; i++){
            paint.setColor(PaintVal.get(i));
            canvas.drawPath(PathList.get(i), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (event.getActionMasked() ==  MotionEvent.ACTION_DOWN) {
            PathList.get(color_change).moveTo(x, y);
        }
        else {
            PathList.get(color_change).lineTo(x, y);
        }

        invalidate();

        return true;
    }

    public void Erase() {
        PathList.add(new Path());
        paint.setColor(Color.WHITE);
        PaintVal.add(paint.getColor());
        color_change++;
    }

    public void ChangeColorRed() {
        PathList.add(new Path());
        paint.setColor(Color.RED);
        PaintVal.add(paint.getColor());
        color_change++;
    }
    public void ChangeColorBlue() {
        PathList.add(new Path());
        paint.setColor(Color.BLUE);
        PaintVal.add(paint.getColor());
        color_change++;
    }
    public void ChangeColorBlack() {
        PathList.add(new Path());
        paint.setColor(Color.BLACK);
        PaintVal.add(paint.getColor());
        color_change++;
    }
    public void ChangeColorGreen() {
        PathList.add(new Path());
        paint.setColor(Color.GREEN);
        PaintVal.add(paint.getColor());
        color_change++;
    }

}
