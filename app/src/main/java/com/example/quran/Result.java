package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.quran.QuranData.QuranArabicText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result extends AppCompatActivity {

    RecyclerView recyclerView;
    QuranArabicText quranArabicText = new QuranArabicText();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.rc_view);
        Intent intent = getIntent();
        String surah = intent.getExtras().getString("surahName");
        Integer start = intent.getExtras().getInt("start");
        Integer end = intent.getExtras().getInt("end");
        Integer startingIndex = intent.getExtras().getInt("startingIndex");
        List<String> verses = Arrays.asList(quranArabicText.GetData(startingIndex + start - 1, startingIndex + end - 1));
        Adapter adapter = new Adapter(this, verses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}