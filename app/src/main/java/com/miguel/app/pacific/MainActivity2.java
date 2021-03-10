package com.miguel.app.pacific;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView pacific;

    CharSequence titleApp;
    int index;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pacific = findViewById(R.id.txtPacific);


        Button invio = findViewById(R.id.inviox);

//        animateText("Pacific");

        invio.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            titleApp = pacific.getText();
            pacific.setText(titleApp.subSequence(0, index++));
            if (index <= titleApp.length()) {
                handler.postDelayed(runnable, 200);
            }
        }
    };


    public void animateText(CharSequence cs) {

        titleApp = cs;
        index = 0;
        pacific.setText("");

        handler.removeCallbacks(runnable);

        handler.postDelayed(runnable, 2000);
    }
}