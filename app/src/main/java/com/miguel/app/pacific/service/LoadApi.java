package com.miguel.app.pacific.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.miguel.app.pacific.view.Square;
import com.miguel.app.pacific.view.adapter.MyAdapter;
import com.miguel.app.pacific.view.Ocean;
import com.miguel.app.pacific.view.PacificType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadApi extends AsyncTask<String, String, List<String>> {

    Ocean ocean;
    Square[][] matrice;
    MyAdapter adapter;
    GridView gridView;
    Context context;
    ProgressDialog dialog;


    public LoadApi(Ocean ocean, MyAdapter adapter, GridView gridView, Context context) {
        this.ocean = ocean;
        this.matrice = ocean.getMap();
        this.adapter = adapter;
        this.gridView = gridView;
        this.context = context;
        this.dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Caricamento...");
        dialog.show();
    }


    @Override
    protected  ArrayList<String> doInBackground(String... urls) {
        ArrayList<String> dati = new ArrayList<>();
        String tmp = "";

        int k = 0;
        int tot = urls.length;

        try {
            for (String url : urls) {

                tmp = HTTPManager.get(url);
                publishProgress(url, String.valueOf(Math.round(100 * (++k) / tot)) + "%");
                dati.add(tmp);
            }

        } catch (Exception e) {
            for(StackTraceElement ste : e.getStackTrace()) {
                Log.e("MITO_DEBUG", "Errore SincronizzaDati: " + ste.toString());
            }
        }

        return dati;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        //super.onProgressUpdate(values);
        Log.i("MITO_DEBUG","Stato sync: " + values[0]);
        dialog.setMessage("Caricamento " + values[1]);
    }

    @Override
    protected void onPostExecute(List<String> dati) {

        for( String dato : dati ) {
            Log.i("MITO_DEBUG", "JSON da API / ENDPOINT: " + dato);

            try {
                JSONArray json = new JSONArray(dato);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);

                    if(Integer.parseInt(obj.getString("tipo")) == 0) {
                        Log.i("MITO_DEBUG", "ORCA");
                    } else {
                        Log.i("MITO_DEBUG", "Leone");
                    }

//                    Log.i("MITO_DEBUG", "id " + obj.getString("id") + " | tipo: " + obj.getString("tipo"));
                    Log.i("MITO_DEBUG", "riga " + obj.getString("riga") + " | colonna: " + obj.getString("colonna"));
//                    Log.i("MITO_DEBUG", "Delta riga " + obj.getString("delta_riga") + " | Delta colonna: " + obj.getString("delta_colonna"));

                    if(obj.getInt("tipo") == 0) {
                        matrice[obj.getInt("riga")][obj.getInt("colonna")] = new Square(PacificType.ORCA, obj.getInt("delta_riga"), obj.getInt("delta_colonna"));
                    } else {
                        matrice[obj.getInt("riga")][obj.getInt("colonna")] = new Square(PacificType.LEONE_MARINO, obj.getInt("delta_riga"), obj.getInt("delta_colonna"));
                    }
                }

                gridView.setAdapter(adapter);

//                Log.i("MITO_DEBUG", "Turno: " + savana.getTurno() + " Orche: " + adapter.getOrca() + " | Leoni: " + adapter.getLeone());


            } catch (Exception e) {
                Log.e("MITO_DEBUG", "JSON errore: " + e.getMessage());
            }
        }

        if(dialog.isShowing()) {
            dialog.dismiss();
        }

    }
}
