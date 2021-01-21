package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector; // padetectina touch ir swipe
    private ScaleGestureDetector mScaleDetector; // kadangi tu su 2 pirstais pinchini, tai pinch to zoom. Kai tas apskritimas piesiamas
    private float mScaleFactor = 1.f; // dabartinis scale, kuri kelia scalegesturedetector
    public float x; // ten kur paclickina
    public float y; // ten kur paclickina
    private float radius = 100f; // pirminis apskritimo radius, kai piesi kart scale factor buna
    private ShapeDrawable drawable; // cia issisaugo figura, kuria nupiesiu
    ArrayList<Point> points = new ArrayList<>(); // figuros pointai ^
    Context context;
    boolean isPlaying = false;

    MediaPlayer mp;
    Uri alarmSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        context = this;
        setContentView(new CustomDrawableView(context)); // padaro kad butu galima piesti
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){ // kai ivyksta touch event, kokie listeneriai aktyvuojami
        this.mDetector.onTouchEvent(event);
        this.mScaleDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) { // kai vartotojas viena karta paspaudzia
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            if(isPlaying) { // sustopina music
                mp.stop();
                isPlaying = false;
            }
            x = event.getX(); // nustato x, y koordinates paspaudimo
            y = event.getY();
            setContentView(new CustomDrawableEmpty(context)); // padaro, kad tuscias ekranas butu
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) { // swipe, pakeicia figura
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            setContentView(new CustomDrawableView(context));
            return true;
        }
    }

    public class CustomDrawableEmpty extends View {
        private ShapeDrawable drawable;

        public CustomDrawableEmpty(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){}

    }

    public class CustomDrawableView extends View{

        public CustomDrawableView(Context context) {
            super(context);
            Random rand = new Random();
            Path path = new Path();
            points.clear();

            // padaromas path is 4 random point'u
            Point point = new Point(rand.nextInt(500), rand.nextInt(500));
            points.add(point);
            path.moveTo(points.get(0).x, points.get(0).y);

            point = new Point(rand.nextInt(500), rand.nextInt(500));
            points.add(point);
            path.lineTo(points.get(1).x, points.get(1).y);

            point = new Point(rand.nextInt(500), rand.nextInt(500));
            points.add(point);
            path.lineTo(points.get(2).x, points.get(2).y);

            point = new Point(rand.nextInt(500), rand.nextInt(500));
            points.add(point);
            path.lineTo(points.get(3).x, points.get(3).y);

            // is path padaromas drawable shape
            drawable = new ShapeDrawable(new PathShape(path,500f, 500f));
            // If the color isn't set, the shape uses black as the default.
            drawable.getPaint().setColor(0xff74AC23);
            // If the bounds aren't set, the shape can't be drawn.
            drawable.setBounds(0, 0, 1000, 1000);
        }


        protected void onDraw(Canvas canvas) { // nupiesia figura ant canvas
            drawable.draw(canvas);
        }
    }

    public class CustomDrawableCircle extends View {
        public CustomDrawableCircle(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.save(); // issaugo canvas
            canvas.scale(mScaleFactor, mScaleFactor); // scale'ina visa canvas
            Paint paint = new Paint(0xff74AC23); // spalva
            canvas.drawCircle(x / mScaleFactor, y / mScaleFactor, radius, paint); // piesia apskritima
            canvas.restore(); // parestorina canvas i default

            boolean fits = true;

            for(int i = 0; i < points.size(); i++){
                // ziurima ar kuris nors is tanku nepatenka i radius apskritimo. Jei nepatenka, fits = false;
                if(x + radius * mScaleFactor > points.get(i).x
                        && x - radius * mScaleFactor < points.get(i).x
                        && y + radius * mScaleFactor > points.get(i).y
                        && y - radius * mScaleFactor < points.get(i).y){
                    fits = false;
                }
            }

            if(isPlaying) {
                mp.stop();
                isPlaying = false;
            }

            if(fits){ // jei tilpo, viena playina sound
                isPlaying = true;
                alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_NOTIFICATION );
                mp = MediaPlayer.create(context.getApplicationContext(), alarmSound);
                mp.start();
            } else { // jei netilpo kita
                isPlaying = true;
                alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_ALARM );
                mp = MediaPlayer.create(context.getApplicationContext(), alarmSound);
                mp.start();
            }

        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d(MyGestureListener.DEBUG_TAG, "onScale: " + detector.toString());
            mScaleFactor *= detector.getScaleFactor(); // keiciamas scale pagal 2 swipe

            setContentView(new CustomDrawableCircle(context)); // piesia apskritima
//            invalidate();
            return true;
        }
    }

}