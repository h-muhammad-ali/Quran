package com.example.quran;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rc_view);
        Intent intent = getIntent();
        String surah = intent.getExtras().getString("surahName");
        Integer start = intent.getExtras().getInt("start");
        Integer end = intent.getExtras().getInt("end");
        Integer startingIndex = intent.getExtras().getInt("startingIndex");
        List<String> verses = Arrays.asList(quranArabicText.GetData(startingIndex + (start - 1), startingIndex + end));
        Adapter adapter = new Adapter(this, verses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                try {
                    Uri webpage = Uri.parse("https://github.com/mali-ai/Quran");
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}