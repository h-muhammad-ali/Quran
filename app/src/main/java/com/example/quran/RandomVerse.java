package com.example.quran;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quran.QuranData.QDH;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class RandomVerse extends AppCompatActivity {
    TextView randomVerse;
    TextView verseAddress;
    QDH qdh = new QDH();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_verse);
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar_random_verse);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        HashMap<String, String > verse = qdh.getRandomVerse();
        randomVerse = findViewById(R.id.randomVerse);
        verseAddress = findViewById(R.id.verseAddress);
        randomVerse.setText(verse.get("verse"));
        verseAddress.setText(String.format("%s :%s", verse.get("surah"), verse.get("verseNo")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_random_verse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
//                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                shareImage(store(getScreenShot(findViewById(R.id.random_verse_body)), "result"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public Bitmap getScreenShot(View view) {
        View screenView = view;
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public String store(Bitmap bm, String fileName){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File file = new File(directory,"result.jpg");

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Store Path: ", file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    private void shareImage(String path){
        Log.i("Share", "Share");
        //Uri uri = Uri.fromFile(file);
        Uri uri = FileProvider.getUriForFile(RandomVerse.this, RandomVerse.this.getApplicationContext().getPackageName() + ".fileprovider", new File(path));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(RandomVerse.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

}