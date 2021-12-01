package com.example.quran;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SurahNameListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] surahNames;

    public SurahNameListAdapter(Activity context, String[] surahNames) {
        super(context, R.layout.surah_name_list, surahNames);
        this.context = context;
        this.surahNames = surahNames;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.surah_name_list, null, true);

        TextView surahName = (TextView) rowView.findViewById(R.id.customSurahName);
        surahName.setText(surahNames[position]);
        return rowView;
    };
}

