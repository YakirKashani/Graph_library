package com.example.graphlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends View {

    private Paint paint;
    private String xAxisName;
    private String yAxisName;
    private List<Float> xValues;
    private List<Float> yValues;
    private boolean isPositiveProgressUpwards = true;
    private int xStepSize = 1;
    private int yStepSize = 1;
    private boolean addGrid = false;

    //Limit Lines
    private List<Float> horizontalLimitLines = new ArrayList<>();
    private List<Integer> horizontalLimitColors = new ArrayList<>();
    private List<Float> verticalLimitLines = new ArrayList<>();
    private List<Integer> verticalLimitColors = new ArrayList<>();

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setTextSize(30);
    }

    public void setXAxisName(String xAxisName) {
        this.xAxisName = xAxisName;
        invalidate();
    }

    public void setYAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
        invalidate();
    }

    public void setXValues(List<Float> xValues) {
        this.xValues = xValues;
        invalidate();
    }

    public void setHorizontalLimitLines(List<Float> horizontalLimitLines) {
        this.horizontalLimitLines = horizontalLimitLines;
        invalidate();
    }

    public void setHorizontalLimitColors(List<Integer> horizontalLimitColors) {
        this.horizontalLimitColors = horizontalLimitColors;
        invalidate();
    }

    public void setVerticalLimitLines(List<Float> verticalLimitLines) {
        this.verticalLimitLines = verticalLimitLines;
        invalidate();
    }

    public void setVerticalLimitColors(List<Integer> verticalLimitColors) {
        this.verticalLimitColors = verticalLimitColors;
        invalidate();
    }

    public void setYValues(List<Float> yValues) {
        this.yValues = yValues;
        invalidate();
    }

    public void setPositiveProgressDirection(boolean isPositiveUp) {
        this.isPositiveProgressUpwards = isPositiveUp;
        invalidate();
    }

    public void setStepSizeX(int xStepSize) {
        if(xStepSize <= 0)
            this.xStepSize = 1;
        else
            this.xStepSize = xStepSize;
        invalidate();
    }

    public void setStepSizeY(int yStepSize) {
        if(yStepSize <= 0)
            this.yStepSize = 1;
        else
            this.yStepSize = yStepSize;
        invalidate();
    }

    public void setAddGrid(boolean addGrid){
        this.addGrid = addGrid;
        invalidate();
    }


    private float getMaxValue(List<Float> values) {
        float max = Float.MIN_VALUE;
        for (float value : values) {
            if (value > max)
                max = value;
        }
        return max;
    }

    private List<String> generateAxisValues(float maxValue, int stepSize) {
        List<String> axisValues = new ArrayList<>();
        for (int i = 0; i <= maxValue; i += stepSize)
            axisValues.add(String.valueOf(i));
        return axisValues;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (xValues == null || yValues == null  || xValues.isEmpty() || yValues.isEmpty() || xValues.size() != yValues.size())
            return;
        int width = getWidth();
        int height = getHeight();
        int padding = 120;

        float maxX = xValues.get(xValues.size() - 1);
        float maxY = getMaxValue(yValues);

        float axisTextSize = Math.min(width, height) / 30;
        float valuesTextSize = Math.min(width, height) / 30;

        // Axis names
        if(xAxisName == null)
            xAxisName = "X";
        if(yAxisName == null)
            yAxisName = "Y";
        paint.setTextSize(axisTextSize);
        paint.setColor(Color.BLACK);
        canvas.drawText(xAxisName, width / 2, height - 10, paint);
        canvas.save();
        canvas.rotate(-90, 10, height / 2);
        canvas.drawText(yAxisName, 10, (height + 70) / 2, paint);
        canvas.restore();

        // Axis Lines
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawLine(padding, height - padding, width - padding, height - padding, paint); // X axis
        canvas.drawLine(padding, padding, padding, height - padding, paint); // Y axis

        // Generate Axis Values
        List<String> xAxisValues = generateAxisValues(maxX, xStepSize);
        List<String> yAxisValues = generateAxisValues(maxY, yStepSize);

        // Axis Values & Grids
        paint.setTextSize(valuesTextSize);
        paint.setStrokeWidth(3);
        float xInterval = (width - 2 * padding) / (xAxisValues.size() - 1);
        float yInterval = (height - 2 * padding) / (yAxisValues.size() - 1);

        for (int i = 0; i < xAxisValues.size(); i++) {
            float x = padding + i * xInterval;
            if(addGrid) {
                paint.setColor(Color.LTGRAY);
                canvas.drawLine(x, padding, x, height - padding, paint);
            }
            paint.setColor(Color.BLACK);
            canvas.drawText(xAxisValues.get(i), x, height - padding + 40, paint);
        }

        for (int i = 0; i < yAxisValues.size(); i++) {
            float y = height - padding - i * yInterval;
            if(addGrid) {
                paint.setColor(Color.LTGRAY);
                canvas.drawLine(padding, y, width - padding, y, paint);
            }
            paint.setColor(Color.BLACK);
            canvas.drawText(yAxisValues.get(i), padding - 30, y, paint);
        }

        // Limit Lines
        paint.setStrokeWidth(10);
        for (int i = 0; i < horizontalLimitLines.size(); i++) {
            float y = height - padding - (horizontalLimitLines.get(i) / getMaxValue(yValues)) * (height - 2 * padding);
            if(horizontalLimitColors.size() > i)
                paint.setColor(horizontalLimitColors.get(i));
            else
                paint.setColor(Color.DKGRAY);
            canvas.drawLine(padding, y, width - padding, y, paint);
        }
        for (int i = 0; i < verticalLimitLines.size(); i++) {
            float x = padding + (verticalLimitLines.get(i) / xValues.get(xValues.size() - 1)) * (width - 2 * padding);
            if(verticalLimitColors.size() > i)
                paint.setColor(verticalLimitColors.get(i));
            else
                paint.setColor(Color.DKGRAY);
            canvas.drawLine(x, padding, x, height - padding, paint);
        }

        // Graph Lines
        paint.setStrokeWidth(10);
        float prevX = padding;
        float prevY = height - padding - (yValues.get(0) / maxY) * (height - 2 * padding);
        for (int i = 1; i < xValues.size(); i++) {
            float currentX = padding + (xValues.get(i) / maxX) * (width - 2 * padding);
            float currentY = height - padding - (yValues.get(i) / maxY) * (height - 2 * padding);
            if (yValues.get(i) > yValues.get(i - 1))
                paint.setColor(isPositiveProgressUpwards ? Color.GREEN : Color.RED);
            else if (yValues.get(i) < yValues.get(i - 1))
                paint.setColor(isPositiveProgressUpwards ? Color.RED : Color.GREEN);
            else
                paint.setColor(Color.BLUE);
            canvas.drawLine(prevX, prevY, currentX, currentY, paint);
            prevX = currentX;
            prevY = currentY;
        }
    }
}