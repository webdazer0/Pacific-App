package com.miguel.app.pacific.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miguel.app.pacific.model.Square;
import com.miguel.app.pacific.model.PacificType;
import com.miguel.app.pacific.R;

public class MyAdapter extends BaseAdapter {

    Context context;
    Square[][] savana;
    LayoutInflater inflater;
    int n = 20;
    int leone = 0;
    int orca = 0;

    public MyAdapter(Context context, Square[][] savana) {
        this.context = context;
        this.savana = savana;
        this.inflater = LayoutInflater.from(context);
    }

    public void reload(Square[][] savana) {
        this.savana = savana;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return n * n;
    }

    @Override
    public Object getItem(int position) {
        int i = (int) (position / n);
        int j = position - i * n;
        return savana[i][j];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int widthParent = parent.getMeasuredWidth();
        int width = widthParent / n;

        convertView = inflater.inflate(R.layout.grid_element, null);

        TextView gridItem = convertView.findViewById(R.id.txtGridItem);
        gridItem.setWidth(width);
        gridItem.setHeight(width);

        Square square = (Square) getItem(position);
        int i = (int) (position / n);
        int j = position - i * n;


//
        if (square.getType() == PacificType.VUOTO) gridItem.setText("." );
        if (square.getType() == PacificType.ROCK) gridItem.setBackgroundColor(Color.BLACK);

        if (square.getType() == PacificType.ORCA) {
            gridItem.setText("O");
            gridItem.setBackgroundColor(Color.GREEN);
            orca++;
        }
        if (square.getType() == PacificType.LEONE_MARINO) {
            gridItem.setText("L");
            leone++;
        }


        return convertView;

//        gridItem.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

//        Log.i("MITO DEBUG", "pos " + position + " : " + square.isOpen());

//        if(!square.isOpen()) {
//            gridItem.setText("-");
//        }
//
//        if(square.isPlayer()) {
//            gridItem.setText("X");
//        }
//
//        if(square.isPc()) {
//            gridItem.setText("O");
////            convertView.setBackgroundResource(R.drawable.pc_2);
////            convertView.setBackgroundResource(R.drawable.michi_sad);
////            gridItem.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.michi_sad, 0, 0, 0);
//        }


//        if(!Tris.inProgress()) {
//
//            if(Tris.isWinner()) {
//                for (int number: Tris.getLine()) {
//
//                    if(number == position) {
//                        gridItem.setPaintFlags(gridItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                        convertView.setBackgroundResource(R.drawable.custom_border);
//                        gridItem.setTextSize(62);
//                        gridItem.setTextColor(Color.RED);
//                    };
//                }
//            }
//
//        }


    }

    public int getLeone() {
        return leone;
    }

    public int getOrca() {
        return orca;
    }

    public void animalsClear() {
        this.leone = 0;
        this.orca = 0;
    }
}
