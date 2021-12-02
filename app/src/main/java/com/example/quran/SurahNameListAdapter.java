package com.example.quran;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SurahNameListAdapter extends ArrayAdapter<String>{
    public SurahNameListAdapter(Context context, String[] surahNames) {
        super(context, 0, surahNames);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.surah_name_list, parent, false
            );
        }
        TextView surahName = convertView.findViewById(R.id.customSurahName);
        surahName.setText(getItem(position));
        return convertView;
    }

    //    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(R.layout.surah_name_list, null, true);
//
//        TextView surahName = (TextView) rowView.findViewById(R.id.customSurahName);
//        surahName.setText(surahNames[position]);
//        return rowView;
//    };
}

