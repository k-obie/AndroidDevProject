package com.androiddev.project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Grid
{
    private final int gridSize;
    private final Paint paint;

    public Grid(int gridSize, int color){
        this.gridSize = gridSize;
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(1);
    }
    public void Draw(Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        for(int i = 0; i < width; i+=gridSize){
            canvas.drawLine(i, 0, i, height, paint);
        }
        for(int i = 0; i < width; i += gridSize){
            canvas.drawLine(0, i, width, i, paint);
        }
    }

    public void Move(MotionEvent event) {
    }
}
