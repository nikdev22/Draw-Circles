package com.example.nik.assignmentone;

import android.graphics.Paint;
import android.graphics.PointF;

public class Circle {
    Paint paint;
    PointF point;
    float radius;
    public Circle(Paint paint,PointF point,float radius){
        this.paint = paint;
        this.point = point;
        this.radius = radius;
    }

    public Paint getPaint(){
        return paint;
    }
    public void setPaint(Paint paint){
        this.paint = paint;
    }
    public PointF getPoint(){
        return point;
    }
    public void setPoint(PointF point){
        this.point = point;
    }
    public float getRadius(){
        return radius;
    }
    public void setRadius(float radius){
        this.radius = radius;
    }
    public boolean isInterest(float x, float y){
        if((x - point.x)*(x-point.x) + (y-point.y)*(y - point.y) <+radius*radius)
            return true;
        else
            return false;
    }






}
