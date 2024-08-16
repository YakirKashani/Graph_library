package com.example.mygraph;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.graphlibrary.GraphView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        GraphView graphView = findViewById(R.id.GraphView);
        graphView.setXAxisName("Time");
        graphView.setYAxisName("Values");
        graphView.setXValues(Arrays.asList(0f,1f,2f,3f,3f,4f,5f,6f,7f));
        graphView.setYValues(Arrays.asList(1f,2f,3f,4f,1f,1f,3f,3f,4f));
        graphView.setPositiveProgressDirection(true);
        graphView.setHorizontalLimitLines(Arrays.asList(2f));
        graphView.setHorizontalLimitColors(Arrays.asList(Color.YELLOW));
        graphView.setVerticalLimitLines(Arrays.asList(5f));
        graphView.setVerticalLimitColors(Arrays.asList(Color.YELLOW));
        graphView.setAddGrid(true);
        graphView.setStepSizeX(0);
        graphView.setStepSizeY(0);
    }
}