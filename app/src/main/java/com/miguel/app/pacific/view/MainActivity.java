package com.miguel.app.pacific.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel.app.pacific.R;
import com.miguel.app.pacific.service.LoadApi;
import com.miguel.app.pacific.model.PacificType;
import com.miguel.app.pacific.view.adapter.MyAdapter;
import com.miguel.app.pacific.model.Ocean;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    Context context;

    Ocean ocean;

    TextView result;
    TextView resultTurn;
    Button btnPlay;
    Button btnPlay10;
    Button btnPlay100;
    Button btnSee;
    int n = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }

    private void startGame() {

        context = this;
        ocean = new Ocean();

        result = findViewById(R.id.txt_result);
        resultTurn = findViewById(R.id.txt_result_turn);
        btnPlay = findViewById(R.id.btn_play);
        btnPlay10 = findViewById(R.id.btn_play10);
        btnPlay100 = findViewById(R.id.btn_play100);


        gridView = findViewById(R.id.grid_view);
        adapter = new MyAdapter(this, ocean.getMap());
//        gridView.setAdapter(adapter);

        LoadApi sd = new LoadApi(ocean, adapter, gridView, context);

        final Handler initHandler = new Handler();
        Runnable runnable = () -> {
            sd.execute("https://torregatti.com/auth.php?animali=1");
            initHandler.post(() -> updateInfo());
        };
        runnable.run();

        btnPlay.setOnClickListener(v -> play(1)); // Esegue il gioco 1 volta
        btnPlay10.setOnClickListener(v -> play(10)); // Esegue il gioco 10 volte
        btnPlay100.setOnClickListener(v -> play(100)); // Esegue il gioco 100 volte

        gridView.setOnItemClickListener(addRockEvent);
    }


    private AdapterView.OnItemClickListener addRockEvent = (parent, view, position, id) -> {
        int i = (int) (position / n);
        int j = position - i * n;

        
        if (ocean.getMap()[i][j].getType() != PacificType.VUOTO && ocean.getMap()[i][j].getType() != PacificType.ROCK) {
//            Toast.makeText(context, "Casella occupata", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, " i: " + i + " j: " + j, Toast.LENGTH_SHORT).show();
        } else {
            if (ocean.getMap()[i][j].getType() == PacificType.VUOTO)  ocean.getMap()[i][j].setType(PacificType.ROCK);
            else ocean.getMap()[i][j].setType(PacificType.VUOTO);

            adapter.reload(ocean.getMap());
            gridView.invalidateViews();
            gridView.refreshDrawableState();
        }
    };



    private void play(int total) {
        adapter.animalsClear();
        ocean.run(total);
        adapter.reload(ocean.getMap());
        gridView.invalidateViews();
        gridView.refreshDrawableState();

        Handler mhandler = new Handler();
        mhandler.post(() -> updateInfo());

    }

    private void updateInfo() {
        result.setText("Orche: " + adapter.getOrca() + " || Leoni Marini: " + adapter.getLeone() + "  || bestemmie: 99");
        resultTurn.setText("Turno: " + ocean.getTurn());
    }


}