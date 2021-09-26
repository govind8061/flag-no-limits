package com.example.fbstorageandgesture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetectorCompat;
    Toolbar toolbar;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetectorCompat=new GestureDetectorCompat(this,new GestureListener());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        int threshold=100;
        int velocity_threshold=100;

        @SuppressLint("ResourceAsColor")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float xdiff=e2.getX()-e1.getX();
            float ydiff=e2.getY()-e1.getY();

            if (Math.abs(xdiff)>Math.abs(ydiff)){
             if (Math.abs(xdiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                 if (xdiff>0){
                     toolbar.setTitle("Swipe Right");
                 }else{
                     toolbar.setTitle("Swipe Left");
                 }
             }
            }else{
                if (Math.abs(ydiff)>Math.abs(xdiff)){
                    if (Math.abs(ydiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                        toolbar.setAlpha(1);
                        if (ydiff>0){
                            toolbar.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                        }else {
                            toolbar.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                        }
                    }
                }

            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}