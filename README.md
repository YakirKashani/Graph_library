# Graph Library

The Graph library provides an easy-to-use custom view for Android developers to plot data in the form of graphs. This library supports various features like axis labeling, step sizes for axes, limit lines and cusomizable graph lines.

## Features

- **Customizable Axes**: Set custom labels for both X and Y axes.
- **Dynamic Axis Values**: Automatically generates axis values based on the provided data and step size.
- **Graph Lines**: Cusomizable graph lines indicating progress direction. with color indicating progress trends.
- **Limit Lines**: Add vertical and horizontal limit lines with customizable colors.
- **Grid Lines**: Oprion to add a background grid for better data visualization.
- **Responsive Design**: Adjusts text and line sizes based on the view size.

## Installation

Add the following dependency to your `build.gradle` file:

```groovy
dependencies {
  implementation 'com.github.YakirKashani:Graph_library:1.0.0'
}
```

## Usage

To use the Graph in your layout, include it as follows:

```xml
<com.example.graphlibrary.GraphView
  android:id="@+id/graphView"
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```

In your Activity or Fragment, you can set up the graph like this:

```Java
GraphView graphView = findViewsById(R.id.graphView);

// Setting the axis names
graphView.setXAxisName("Time");
graphView.setYAxisName("Value");

// Setting data points
graphView.setXValues(Arrays.asList(0f,1f,2f,3f,3f,4f,5f,6f,7f));
graphView.setYValues(Arrays.asList(1f,2f,3f,4f,1f,1f,3f,3f,4f));

// Setting progress direction

graphView.setPositiveProgressDirection(true);

//Adding limit lines
graphView.setHorizontalLimitLines(Arrays.asList(2f));
graphView.setHorizontalLimitColors(Arrays.asList(Color.YELLOW));
graphView.setVerticalLimitLines(Arrays.asList(5f));
graphView.setVerticalLimitColors(Arrays.asList(Color.YELLOW));

//Enabling grid
graphView.setAddGrid(true);

// Setting axis step sizes
graphView.setStepSizeX(0);
graphView.setStepSizeY(0);
```

## Customization

- *Axis Names*: Use 'setXAxisName(String name)' and 'setYAxisName(String name)' to set cusom names.
- *Data points*: Use 'setXValues(List<Float> xValues)' and 'setYValues(List<Float> yValues)' to input your data points.
- *Axis Step Sizes*: Use 'setStepSizeX(int stepSize)' and 'setStepSizeY(int stepSize)' to define the spcing between axis values.
- *Limit Lines*: Use 'setHorizontalLimitLines(List<Float> lines)', 'setHorizontalLimitColors(List<Float> lines)', 'setVerticalLimitLines(List<Float> lines)','setVerticalLimitColors(List<Float> lines)' to add and customize limit lines.
- *Grid Lines*: Use setAddGrid(boolean addGrid)' to toggle grid visibility.
- *Progress Direction*: Use 'setPositiveProgressDirection(boolean isPositiveUp)' to set whether increasing values are drawn upwards.

![Graph_Example](https://github.com/user-attachments/assets/f482084f-6200-42af-a164-eabd82c1e249)
