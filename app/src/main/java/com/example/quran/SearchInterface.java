package com.example.quran;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quran.QuranData.QDH;

import java.util.stream.IntStream;

public class SearchInterface extends AppCompatActivity {

    Spinner surahName;
    Spinner startingVerse;
    Spinner endingVerse;
    Button search;
    QDH qdh = new QDH();
    Integer[] range = {};
    String surah;
    Integer start;
    Integer end;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_interface);
        surahName = findViewById(R.id.surahName);
        startingVerse = findViewById(R.id.startingVerse);
        endingVerse = findViewById(R.id.endingVerse);
        search = findViewById(R.id.btnSearch);
        ArrayAdapter<String> surahNameAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, qdh.englishSurahNames);
        surahNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        surahName.setAdapter(surahNameAdapter);
        surahName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                surah = adapterView.getItemAtPosition(i).toString();
                ArrayAdapter<Integer> startingVerseAdapter;
                ArrayAdapter<Integer> endingVerseAdapter;
                int totalVerses = qdh.getSurahVerses(i);
                range = IntStream.of( IntStream.rangeClosed(1, totalVerses).toArray() ).boxed().toArray( Integer[]::new );
                startingVerseAdapter = new ArrayAdapter<Integer>(SearchInterface.this,  android.R.layout.simple_spinner_item, range);
                startingVerseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                startingVerse.setAdapter(startingVerseAdapter);
//                startingVerse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        start = Integer.valueOf(adapterView.getItemAtPosition(i).toString());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });

                endingVerseAdapter = new ArrayAdapter<Integer>(SearchInterface.this,  android.R.layout.simple_spinner_item, range);
                endingVerseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                endingVerse.setAdapter(endingVerseAdapter);
//                endingVerse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        end = Integer.valueOf(adapterView.getItemAtPosition(i).toString());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        search.setOnClickListener(view -> {
            Intent intent = new Intent(SearchInterface.this, Result.class);
            intent.putExtra("surahName", surahName.getSelectedItem().toString());
            intent.putExtra("start", Integer.valueOf(startingVerse.getSelectedItem().toString()));
            intent.putExtra("end", Integer.valueOf(endingVerse.getSelectedItem().toString()));
            intent.putExtra("startingIndex", qdh.getSurahStart(surahName.getSelectedItemPosition()));
            startActivity(intent);
        });

    }

}