package com.example.nik.assignmentone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PaintView extends View implements View.OnTouchListener {
    int i  = 0;
    int color = 1;
    float radius;
    ArrayList<Circle> circles = new ArrayList<>();
    ArrayList<Circle> dummyCircles = new ArrayList<>();
    float [] x = new float[20];
    float [] y = new float[20];
    float [] radii = new float[20];
    Paint [] painti = new Paint[20];;


    boolean isselect = false;
    int selectedIndex = 0;
            /*ArrayList <PointF> points = new ArrayList<PointF>();
    ArrayList <PointF> mPoints = new ArrayList<PointF>();
    ArrayList<Integer> mColor = new ArrayList<>();
    ArrayList<Float> mRadius = new ArrayList<>();
    ArrayList<Float> x = new ArrayList<>();
    ArrayList<Float> y = new ArrayList<>();
    */

    int controller;
    //float [] x = new float[1000];
    //float [] y = new float[1000];
    int [] c = new int[1000];
    float [] r = new float[1000];

    int v = 0;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius * 2;
        if(isselect && circles.size() > 0){
            change(circles.get(selectedIndex).getPaint(),radius);
        }
    }

    private void change(Paint p, float radius){
        circles.get(selectedIndex).setPaint(p);
        circles.get(selectedIndex).setRadius(radius);
    }


    public int getColor(){ return color; }

    public void setColor(int color){
        this.color = color;
        if(isselect && circles.size() > 0){
            Paint p = new Paint();
            p.setColor(color);
            change(p,circles.get(selectedIndex).getRadius());
        }
    }

    public int undoFun(){
        int y = 0;
        int size = circles.size();
        if(circles.size()>0) {
            dummyCircles.add(circles.remove(size - 1));
            invalidate();
        }
        else{
          y = 1;
        }
        /*if(i == 0){
            x[i] = circles.get(size-1).getPoint().x;
            y[i] = circles.get(size-1).getPoint().y;
            radii[i] = circles.get(size-1).getRadius();
            painti[i] = circles.get(size-1).getPaint();
            i++;
            circles.remove(size-1);

            Log.i("message","first if");
        }
        else{
            x[i] = circles.get(size).getPoint().x;
            y[i] = circles.get(size).getPoint().y;
            radii[i] = circles.get(size).getRadius();
            painti[i] = circles.get(size).getPaint();
            i++;
            circles.remove(size);
            invalidate();
            Log.i("message","second if");
        }*/
        return y;

    }
    public int redoFun(){
        int y = 0 ;
        int size = dummyCircles.size();
       if(dummyCircles.size() > 0){
           circles.add(dummyCircles.remove(size-1));
           invalidate();

       }else{
        y = 1;
       }
       return y;
    }



    public boolean isSelect(){
        isselect = !isselect;
        return isselect;
    }


    public PaintView(Context context) {
        super(context);

        setOnTouchListener(this);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);


    }
   public void init(){
        this.radius = radius;
        this.color = color;
   }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);

    }
    @Override
    public void onDraw(Canvas canvas){
        /*Paint paint = new Paint();
        paint.setColor(color);
        mColor.add(getColor());
        mRadius.add(getRadius());
        Log.i("one draw COlor code",String.valueOf(getColor()));
        Log.i("one draw radius",String.valueOf(getRadius()));
        for(int i = controller ; i < points.size();i++){
            canvas.drawCircle(points.get(i).x,points.get(i).y,mRadius.get(i),paint);
            //controller = i;
        }
        */
        for(int i = 0 ; i < circles.size();i++){
            canvas.drawCircle(circles.get(i).getPoint().x, circles.get(i).getPoint().y, circles.get(i).getRadius(),circles.get(i).getPaint());
        }
        invalidate();
    }

  /*
  *
 *  PointF pt = new PointF();
        pt.set(motionEvent.getX(),motionEvent.getY());
        points.add(pt);
        mColor.add(getColor());
        mradius.add(getRadius());
        motionEvent.getX();
        motionEvent.getY();
        invalidate();
        draw();
        return true;
 *
 *
 *
 * */

  /*
   Paint paint = new Paint();
        paint.setColor(color);
        for(PointF pt : points) {
            canvas.drawCircle(pt.x, pt.y, getRadius(), paint);
        }
   */
  private int mActivePointerId;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionEvent = motionEvent.getAction() & MotionEvent.ACTION_MASK;
       switch(actionEvent){
           case MotionEvent.ACTION_DOWN:
               if(!isselect){
                   //Log.i("working",String.valueOf(motionEvent.getPointerCount()));
                   /*if(motionEvent.getPointerCount() > 1){



                       PointF pt = new PointF();
                       pt.set(motionEvent.getX(index),motionEvent.getY(index));
                       Paint paint = new Paint();
                       paint.setColor(getColor());
                       circles.add(new Circle(paint, pt, getRadius()));
                   }*/
                   PointF pt = new PointF();
                   pt.set(motionEvent.getX(),motionEvent.getY());
                   Paint paint = new Paint();
                   paint.setColor(getColor());
                   circles.add(new Circle(paint, pt, getRadius()));
                   //Log.e("Cooredinates",motionEvent.getX() +","+motionEvent.getY());


               } else{
                   for(int i = 0; i < circles.size(); i++){
                       if(circles.get(i).isInterest(motionEvent.getX(),motionEvent.getY())){
                           selectedIndex = i;
                           //Log.e("selected Index", i+ " ");
                           break;
                       }
                   }
               }
               return true;
           case MotionEvent.ACTION_POINTER_DOWN:
               mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
               int pointerIndex = motionEvent.findPointerIndex(mActivePointerId);
               /*int actionPointerId= motionEvent.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK;
               int index = motionEvent.findPointerIndex(actionPointerId);*/
              // Log.i("action pointer downn","inside action point down");
               if(!isselect){
                   PointF pt = new PointF();
                   pt.set(motionEvent.getX(pointerIndex),motionEvent.getY(pointerIndex));
                   Log.i("multi points","Collected");

                   Paint paint = new Paint();
                   paint.setColor(getColor());
                   circles.add(new Circle(paint, pt, getRadius()));
                   //Log.e("Cooredinates",motionEvent.getX(pointerIndex) +","+motionEvent.getY(pointerIndex));


               }

               return true;
           case MotionEvent.ACTION_UP:
               return true;
       }

        /* PointF pt = new PointF();
        points.clear();
        pt.set(motionEvent.getX(),motionEvent.getY());
        points.add(pt);


        Log.i("COlor code",String.valueOf(getColor()));
        Log.i("radius",String.valueOf(getRadius()));
        x.add(pt.x);
        y.add(pt.y);*/
        invalidate();
        return true;
    }


}
