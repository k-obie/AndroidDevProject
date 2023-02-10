package com.androiddev.project;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class GridView extends View {

    private Grid grid;

    public GridView(Context context, AttributeSet attributes){
        super(context, attributes);
        int gridSize = MainActivity.GameSettings.GRID_SIZE;
        int color = MainActivity.GameSettings.GRID_COLOR;
        grid = new Grid(gridSize, color);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        grid.Draw(canvas);
    }
}
