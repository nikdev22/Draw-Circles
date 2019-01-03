package com.example.nik.assignmentone;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public float rad;
    PaintView pv;
    Button selectionButton;
    Button undoButton;
    Button redoButton;

    public void undoFunction(View view){
        int result = pv.undoFun();
        if(result == 1){
            Toast toast = Toast.makeText(this, "Arraylist is empty", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();

        }
    }
    public void redoFunction(View view){
        int result = pv.redoFun();
        if(result == 1){
            Toast toast = Toast.makeText(this, "Please draw more circles", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //undoButton = findViewById(R.id.undo);
        //redoButton = findViewById(R.id.redo);
       // undoButton.setOnClickListener(this);
        //redoButton.setOnClickListener(this);
        selectionButton = findViewById(R.id.selectionButton);
        selectionButton.setOnClickListener(this);
        pv = findViewById(R.id.view2);
        pv.setRadius(10);
        pv.setColor(Color.RED);
        ColorSeekBar colorSeekBar = findViewById(R.id.color_seekbar);
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i, int i1, int i2) {
                //String num = String.valueOf(i2);
                pv.setColor(i2);

            }
        });
        SeekBar seekBar = findViewById(R.id.seekBar);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        rad = i;


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        pv.setRadius(rad);
                        pv.invalidate();

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.selectionButton){
            if(pv.isSelect()){
                selectionButton.setText("SELECTED");
            }else{
                selectionButton.setText("SELECT");
            }
        }

    }
}
